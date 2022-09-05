package com.example.hw09zooretrofirt

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.hw09zooretrofirt.databinding.ItemAnimalBinding
import retrofit2.Callback

class AnimalAdapter(
    context: Context
) : ListAdapter<Animal, AnimalViewHolder>(DIFF_UTIL) {

    private val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        return AnimalViewHolder(
            binding = ItemAnimalBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<Animal>() {
            override fun areItemsTheSame(oldItem: Animal, newItem: Animal): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Animal, newItem: Animal): Boolean {
                return oldItem == newItem
            }
        }
    }
}

class AnimalViewHolder(
    private val binding: ItemAnimalBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Animal) {
        with(binding) {
            imageAnimal.load(item.url)
            textName.text = item.name
        }
    }

}