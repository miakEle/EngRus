package com.example.engrus.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.engrus.databinding.WordCardItemBinding

class WordCardAdapter : ListAdapter<WordCardUi, WordCardViewHolder>(WordCardDiffCallBack()) {

    private val listOfWordCards = mutableListOf<WordCardUi>()

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
        holder.bind(listOfWordCards[position])
    }

    override fun getItemCount(): Int {
        return listOfWordCards.size
    }
}