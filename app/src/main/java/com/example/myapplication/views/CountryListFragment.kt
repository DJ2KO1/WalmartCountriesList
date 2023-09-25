package com.example.myapplication.views

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.FragmentCountryListBinding
import com.example.myapplication.model.CountriesJSONResponseItem
import com.example.myapplication.model.UIState
import com.example.myapplication.viewmodel.ViewModelFragment

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class CountryListFragment : ViewModelFragment() {
    private lateinit var binding: FragmentCountryListBinding

    private val countryListAdapter by lazy {
        CountryListAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCountryListBinding.inflate(inflater, container, false)
        configureObserver()

        return binding.root
    }

    private fun configureObserver() {


        viewModel.countryListData.observe(viewLifecycleOwner) { uiState ->
            when (uiState) {
                is UIState.Loading -> {

                    viewModel.getCountries()

                    binding.rvCountries.adapter = countryListAdapter
                    binding.rvCountries.addOnScrollListener(object: RecyclerView.OnScrollListener(){
                        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                            super.onScrolled(recyclerView, dx, dy)
                            if (!recyclerView.canScrollVertically(1)) {
                                viewModel.getCountries()
                            }
                        }
                    })
                }
                is UIState.Error -> {
                    binding.pbLoading.visibility = View.GONE
                    binding.tvLoadingText.text = uiState.error.message
                }
                is UIState.Success<*> -> {

                    binding.apply {
                        pbLoading.visibility = View.GONE
                        tvLoadingText.visibility = View.GONE
                        countryListAdapter.setList(uiState.response as List<CountriesJSONResponseItem>)
                    }
                }
                else ->{
                    binding.pbLoading.visibility = View.GONE
                }

            }
        }
    }
}