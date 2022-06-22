package com.example.appbootcampusemobile.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.appbootcampusemobile.databinding.FragmentRegisterAnimalBinding
import com.example.appbootcampusemobile.presentation.adapter.ListAnimalAdapter
import com.example.appbootcampusemobile.presentation.viewmodel.AnimalViewModel

class RegisterAnimalFragment : Fragment() {

    private lateinit var binding: FragmentRegisterAnimalBinding

    private lateinit var registerAnimalViewModel: AnimalViewModel

    private lateinit var adpterListAnimal: ListAnimalAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterAnimalBinding.inflate(layoutInflater, container, false)

        registerAnimalViewModel = ViewModelProvider(this).get(AnimalViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onObserver()
        setListener()
    }

    private fun onObserver() {
        registerAnimalViewModel.apply {
            registerAnimalSuccess.observe(viewLifecycleOwner) { response ->
                Toast.makeText(
                    requireContext(),
                    "Animal cadastrado com sucesso",
                    Toast.LENGTH_SHORT
                ).show()
                binding.inputEditTextName.text.toString()
                binding.inputEditTextDescription.text.toString()
                binding.inputEditTextImageLink.text.toString()
            }
            registerAnimalError.observe(viewLifecycleOwner) { response ->
                Toast.makeText(requireContext(), "Houve uma falha no cadastro", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun setListener() {
        binding.apply {
            buttonRegister.setOnClickListener {
                registerAnimal()
            }
        }
    }

    private fun registerAnimal() {
        registerAnimalViewModel.registerAnimal(
            name = binding.inputEditTextName.text.toString(),
            description = binding.inputEditTextDescription.text.toString(),
            species = binding.inputEditTextSpecies.text.toString(),
            age = binding.inputEditTextAge.text.toString().toInt(),
            image = binding.inputEditTextImageLink.text.toString()
        )
    }
}