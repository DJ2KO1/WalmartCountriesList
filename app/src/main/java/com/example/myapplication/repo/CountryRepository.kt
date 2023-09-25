
import com.example.myapplication.repo.API

import com.example.myapplication.model.UIState
import java.lang.Exception
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow


interface CountryRepository {
    suspend fun getCountry(): Flow<UIState>
}

class CountryRepositoryImpl(private val service: API): CountryRepository {
    override suspend fun getCountry(): Flow<UIState> =
        flow {
            emit(UIState.Loading)

            try {

                val response = service.getCountries()

                if (response.isSuccessful) {
                    emit(response.body()?.let { CountryResponse ->
                        UIState.Success(CountryResponse)
                    } ?: throw Exception("Empty response"))
                } else throw Exception("Failed network call")

            } catch (e: Exception) {

                emit(UIState.Error(e))
            }
        }
}