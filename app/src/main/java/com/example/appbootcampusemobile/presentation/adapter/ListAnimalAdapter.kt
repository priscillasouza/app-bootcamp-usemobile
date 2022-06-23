package com.example.appbootcampusemobile.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.appbootcampusemobile.R
import com.example.appbootcampusemobile.databinding.ItemLayoutListAnimalBinding
import com.example.appbootcampusemobile.domain.model.Animal
import com.example.appbootcampusemobile.presentation.fragments.HomeFragmentDirections

class ListAnimalAdapter : RecyclerView.Adapter<ListAnimalAdapter.AnimalViewHolder>() {

    private var animalList = arrayListOf<Animal>()
    private var onClickFavorite: ((Animal) -> Unit?)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val binding =
            ItemLayoutListAnimalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AnimalViewHolder(binding, onClickFavorite)
    }

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        holder.onBind(animalList[position])
    }

    override fun getItemCount(): Int {
        return animalList.size
    }

    fun setList(list: List<Animal>) {
        if (list.isNotEmpty()) {
            animalList.clear()
            animalList.addAll(list)
            notifyDataSetChanged()
        }
    }

    fun setOnClickFavorite(run: ((Animal) -> Unit?)?) {
        onClickFavorite = run
    }

    class AnimalViewHolder(val layout: ItemLayoutListAnimalBinding, val onClickFavorite: ((Animal) -> Unit?)?) :
        RecyclerView.ViewHolder(layout.root) {
        fun onBind(animal: Animal) {
            layout.apply {
                textViewName.text = animal.name
                textViewDescription.text = animal.description
                imageViewAnimal.load(animal.image) {
                    error(R.drawable.ic_launcher_background)
                }

                checkBoxFavorite.isChecked = animal.favorite

                checkBoxFavorite.setOnClickListener {
                    onClickFavorite?.invoke(animal.copy(favorite = animal.favorite.not()))
                }

                itemRecyclerView.setOnClickListener {
                    val action = HomeFragmentDirections.actionHomeFragmentToDetailsAnimalFragment(animal)
                    itemRecyclerView.findNavController().navigate(action)
                }
            }
        }
    }
}