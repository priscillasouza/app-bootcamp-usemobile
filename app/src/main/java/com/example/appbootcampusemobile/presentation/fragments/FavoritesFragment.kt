package com.example.appbootcampusemobile.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appbootcampusemobile.databinding.FragmentFavoritesBinding
import com.example.appbootcampusemobile.presentation.adapter.ListAnimalAdapter
import com.example.appbootcampusemobile.presentation.viewmodel.FavoriteViewModel
import com.example.appbootcampusemobile.presentation.viewmodel.FavoriteViewModelFactory

class FavoritesFragment : Fragment() {

    private lateinit var binding: FragmentFavoritesBinding
    private lateinit var favoriteViewModel: FavoriteViewModel
    private lateinit var adpaterRecyclerViewListFavorites: ListAnimalAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritesBinding.inflate(layoutInflater,container, false)

        favoriteViewModel = FavoriteViewModelFactory(requireContext()).create(FavoriteViewModel::class.java)
        favoriteViewModel.getFavorites()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onObserver()
        startAdapter()
    }

    private fun onObserver() {
       favoriteViewModel.apply {
           animalFavorite.observe(viewLifecycleOwner) { animal ->
               adpaterRecyclerViewListFavorites.setList(animal)
           }

           error.observe(viewLifecycleOwner) {
               Toast.makeText(requireContext(), "Falha na lista de favoritos", Toast.LENGTH_SHORT).show()
           }
       }
    }

    private fun startAdapter() {
        binding.recyclerViewListFavoritesAnimals.apply {
            adpaterRecyclerViewListFavorites = ListAnimalAdapter()
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = adpaterRecyclerViewListFavorites
        }
    }
}