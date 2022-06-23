package com.example.appbootcampusemobile.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.appbootcampusemobile.R
import com.example.appbootcampusemobile.databinding.FragmentDetailsAnimalBinding
import com.example.appbootcampusemobile.presentation.viewmodel.DetailsAnimalViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class DetailsAnimalFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentDetailsAnimalBinding
    private lateinit var detailsAnimalViewModel: DetailsAnimalViewModel
    private val args: DetailsAnimalFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsAnimalBinding.inflate(layoutInflater, container, false)

        detailsAnimalViewModel = ViewModelProvider(this).get(DetailsAnimalViewModel::class.java)
        detailsAnimalViewModel.getDetailsAnimal()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        setupViewDetails()
    }

    private fun setListeners() {
        binding.toolbarDetails.setOnClickListener {
            val action = DetailsAnimalFragmentDirections.actionDetailsAnimalFragmentToHomeFragment()
            findNavController().navigate(action)
        }
    }

    private fun setupViewDetails() {
        binding.apply {
            textViewNameDetails.text = args.animal.name
            textViewDescriptionDetails.text = args.animal.description
            textViewAgeDetails.text = args.animal.age.toString()
            textViewSpeciesDetails.text = args.animal.species
            shapeableImageViewDetails.load(args.animal.image) {
                error(R.drawable.ic_launcher_background)
            }
        }
    }
}