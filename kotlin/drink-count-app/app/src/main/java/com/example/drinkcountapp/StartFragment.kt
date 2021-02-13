package com.example.drinkcountapp

//import androidx.databinding.DataBindingUtil
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.drinkcountapp.data.AppDatabase
import com.example.drinkcountapp.data.StartRepository
import com.example.drinkcountapp.databinding.FragmentStartBinding
import com.example.drinkcountapp.viewmodels.StartViewModel
import com.example.drinkcountapp.viewmodels.StartViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class StartFragment : Fragment() {

    private lateinit var binding: FragmentStartBinding

    lateinit var viewModelFactory: StartViewModelFactory
    private lateinit var viewModel: StartViewModel

//    @Inject
//    lateinit var repo: StartRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStartBinding.inflate(inflater, container, false)

        Log.d("StartFragment", "Hello World!")

        val database = AppDatabase.getInstance(this.requireContext()).drinkDao()
        this.viewModelFactory = StartViewModelFactory(repository = StartRepository(database))
        this.viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(StartViewModel::class.java)

        binding.viewModel = viewModel
        //viewModel.onCancel()

        //binding.btnCancel.isEnabled = false

        viewModel.isNewEntry.observe(viewLifecycleOwner, {
            binding.btnNew.isEnabled = !it
            binding.btnCancel.isEnabled = it
            //binding.btnSubmit.isEnabled = it

            binding.btniCoffeeAdd.isEnabled = it
            binding.btniCoffeeRm.isEnabled = it
            binding.btniWaterAdd.isEnabled = it
            binding.btniWaterRm.isEnabled = it
        })

        // subscribe for changes of the count field im viewmodel
        viewModel.coffeeCount.observe(viewLifecycleOwner, {
            binding.tvCoffeeCount.text = it.toString()
            binding.btniCoffeeRm.isEnabled = (it > 0)
            binding.btnSubmit.isEnabled = changeSubmitEnabledStatus()
        })

        viewModel.waterCount.observe(viewLifecycleOwner, {
            binding.tvWaterCount.text = it.toString()
            binding.btniWaterRm.isEnabled = (it > 0)
            binding.btnSubmit.isEnabled = changeSubmitEnabledStatus()
        })

        return binding.root
    }

    fun changeSubmitEnabledStatus(): Boolean {
        if (viewModel.coffeeCount.value!! > 0 || viewModel.waterCount.value!! > 0){
            return true
        }
        return false
    }

}