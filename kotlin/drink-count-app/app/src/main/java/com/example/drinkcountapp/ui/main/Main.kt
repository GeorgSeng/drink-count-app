package com.example.drinkcountapp.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.drinkcountapp.R
import kotlinx.coroutines.flow.combine

class main : Fragment() {


    companion object {
        fun newInstance() = main()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentMainBinder = DataBindingUtil.inflate(inflater, R.layout.fragment_main,
        container,false)

        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
        val binding: UserBinding = DataBindingUtil.setContentView(this, R.layout.user)

        binding. =
    }

}