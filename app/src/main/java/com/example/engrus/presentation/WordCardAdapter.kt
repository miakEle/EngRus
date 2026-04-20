package com.example.engrus.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.engrus.databinding.WordCardItemBinding
import com.example.engrus.domain.entities.WordCard

class WordCardAdapter : ListAdapter<WordCardUi, WordCardViewHolder>(WordCardDiffCallBack()) {

    var onShopItemClickListener: ((WordCardUi) -> Unit)? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WordCardViewHolder {
        val binding = WordCardItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return WordCardViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: WordCardViewHolder,
        position: Int
    ) {
        val item = getItem(position)
        val binding = holder.binding

        holder.bind(getItem(position))
        binding.root.setOnClickListener {
            onShopItemClickListener?.invoke(item)
        }
    }
}