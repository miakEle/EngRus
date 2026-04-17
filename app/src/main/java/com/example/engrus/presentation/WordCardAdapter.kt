package com.example.engrus.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.engrus.databinding.WordCardItemBinding

class WordCardAdapter : ListAdapter<WordCardUi, WordCardViewHolder>(WordCardDiffCallBack()) {

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
        Log.d("TEST", "UI item: id=${item.id}, word=${item.word}")
        holder.bind(getItem(position))
    }
}