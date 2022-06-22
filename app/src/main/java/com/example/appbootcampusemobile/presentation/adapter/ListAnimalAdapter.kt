package com.example.appbootcampusemobile.presentation.adapter

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val binding =
            ItemLayoutListAnimalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AnimalViewHolder(binding)
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

    class AnimalViewHolder(val layout: ItemLayoutListAnimalBinding) :
        RecyclerView.ViewHolder(layout.root) {
        fun onBind(animal: Animal) {
            layout.textViewName.text = animal.name
            layout.textViewDescription.text = animal.description
            layout.imageViewAnimal.load(animal.image) {
                error(R.drawable.ic_launcher_background)
            }

            layout.itemRecyclerView.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeFragmentToDetailsAnimalFragment()
                layout.itemRecyclerView.findNavController().navigate(action)
            }
        }
    }
}