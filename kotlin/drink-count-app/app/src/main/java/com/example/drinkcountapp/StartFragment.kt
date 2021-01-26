package com.example.drinkcountapp

//import androidx.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.drinkcountapp.databinding.FragmentStartBinding
import com.example.drinkcountapp.viewmodels.StartViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class StartFragment : Fragment() {

    private lateinit var binding: FragmentStartBinding

    @Inject
    lateinit var startViewModelFactory: StartViewModel.AssistedFactory

    private val viewModel: StartViewModel by viewModels{
        StartViewModel.provideFactory(startViewModelFactory)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStartBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel

        viewModel.isNewEntry.observe(viewLifecycleOwner, {
            binding.btnNew.isClickable = !it
            binding.btnCancel.isClickable = it
            binding.btnSubmit.isClickable = it

            binding.btniCoffeeAdd.isClickable = it
            binding.btniCoffeeRm.isClickable = it
            binding.btniWaterAdd.isClickable = it
            binding.btniWaterRm.isClickable = it
        })

        // subscribe for changes of the count field im viewmodel
        viewModel.coffeeCount.observe(viewLifecycleOwner, {
            binding.tvCoffeeCount.text = it.toString()
            binding.btniCoffeeRm.isClickable = (it > 0)
        })

        viewModel.waterCount.observe(viewLifecycleOwner, {
            binding.tvWaterCount.text = it.toString()
            binding.btniWaterRm.isClickable = (it > 0)
        })

        return binding.root
    }

}