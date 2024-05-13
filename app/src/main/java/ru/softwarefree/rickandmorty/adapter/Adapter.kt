package ru.softwarefree.rickandmorty.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import ru.softwarefree.rickandmorty.R
import ru.softwarefree.rickandmorty.databinding.ListItemBinding
import ru.softwarefree.rickandmorty.retrofit.Character

class Adapter : ListAdapter<Character, Adapter.Holder>(Comparator()) {
    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ListItemBinding.bind(view)
        fun bind(character: Character) = with(binding) {
            name.text = character.name
            status.text = character.status
            species.text = character.species
            type.text = character.type
            gender.text = character.gender
            Glide.with(imageView.context)
                .load(character.image)
                .apply(RequestOptions().override(400, 400))
                .into(imageView)
        }
    }
    class Comparator : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem == newItem
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return Holder(view)
    }
    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }
}