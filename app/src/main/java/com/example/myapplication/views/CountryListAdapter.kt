package com.example.myapplication.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.CountryListItemBinding
import com.example.myapplication.databinding.LoadingBinding
import com.example.myapplication.model.CountriesJSONResponseItem

class CountryListAdapter(
    private val list: MutableList<CountriesJSONResponseItem?> = mutableListOf(),
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    fun setList(newList: List<CountriesJSONResponseItem>) {
        if(list.isNotEmpty()) list.removeLast()
        list.addAll(newList)
        list.add(null)
        notifyDataSetChanged()
    }

    inner class CountryViewHolder(private val binding: CountryListItemBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: CountriesJSONResponseItem) {
            binding.apply {
                tvListName.text = "${item.name}, ${item.region}"
                tvListCapital.text = item.capital
                tvListCode.text = item.code
            }

        }
    }

    inner class LoadingViewHolder(private val binding: LoadingBinding)
        : RecyclerView.ViewHolder(binding.root)

    companion object {
        const val LOADING = 1
        const val List = 2
    }

    override fun getItemViewType(position: Int): Int {
        return if (list[position] == null) LOADING else List
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            List -> {
                CountryViewHolder(
                    CountryListItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            else -> {
                LoadingViewHolder(
                    LoadingBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is CountryViewHolder -> holder.onBind(list[position]!!)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}