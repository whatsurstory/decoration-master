package com.beva.itemdecorationsample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.beva.itemdecorationsample.databinding.ItemGridBinding
import com.beva.itemdecorationsample.databinding.ItemListBinding

class ListCardAdapter: ListAdapter<Card, ListCardAdapter.ListViewHolder>(DiffCallback) {

   object DiffCallback : DiffUtil.ItemCallback<Card>() {
        override fun areItemsTheSame(oldItem: Card, newItem: Card): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Card, newItem: Card): Boolean {
            return oldItem == newItem
        }
    }

    class ListViewHolder(private val binding: ItemListBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Card) {
            with(binding) {
                listCardImage.setImageResource(item.image ?: 0)
                listCardTitle.text = item.title
                listCardDescription.text = item.description
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class GridCardAdapter: ListAdapter<Card, GridCardAdapter.GridViewHolder>(DiffCallback) {

   object DiffCallback : DiffUtil.ItemCallback<Card>() {
        override fun areItemsTheSame(oldItem: Card, newItem: Card): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Card, newItem: Card): Boolean {
            return oldItem == newItem
        }
    }

    class GridViewHolder(private val binding: ItemGridBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Card) {
            with(binding) {
                gridCardImage.setImageResource(item.image ?: 0)
                gridCardDescription.text = item.description
                gridCardTitle.text = item.title
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridViewHolder {
        return GridViewHolder(ItemGridBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}