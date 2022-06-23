package com.example.appbootcampusemobile.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appbootcampusemobile.databinding.FragmentHomeBinding
import com.example.appbootcampusemobile.presentation.adapter.ListAnimalAdapter
import com.example.appbootcampusemobile.presentation.viewmodel.HomeViewModel
import com.example.appbootcampusemobile.presentation.viewmodel.HomeViewModelFactory

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var adapterRecyclerViewListAnimals: ListAnimalAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        homeViewModel = HomeViewModelFactory(requireContext()).create(HomeViewModel::class.java)
        homeViewModel.getAnimals()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onObserver()
        startAdapter()
    }

    private fun onObserver() {
        homeViewModel.apply {
            animalResponse.observe(viewLifecycleOwner) { response ->
                adapterRecyclerViewListAnimals.setList(response)
            }
            error.observe(viewLifecycleOwner) {
                Toast.makeText(
                    requireContext(),
                    "Falha na lista de animais",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun startAdapter() {
        binding.recyclerViewListAnimals.apply {
            adapterRecyclerViewListAnimals = ListAnimalAdapter()
            adapterRecyclerViewListAnimals.setOnClickFavorite {
                homeViewModel.updateFavorites(animal = it)
            }
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = adapterRecyclerViewListAnimals
        }
    }
}