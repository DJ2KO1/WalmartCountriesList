package com.example.myapplication.viewmodel

import androidx.fragment.app.Fragment
import com.example.myapplication.repo.Networking

open class ViewModelFragment: Fragment() {

    protected val viewModel by lazy {
        Networking.provideViewModel(requireActivity())
    }
}