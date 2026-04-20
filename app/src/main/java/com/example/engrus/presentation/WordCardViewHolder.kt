package com.example.engrus.presentation

import androidx.recyclerview.widget.RecyclerView
import com.example.engrus.databinding.WordCardItemBinding

class WordCardViewHolder (
    val binding: WordCardItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: WordCardUi) = with(binding) {
        tvword.text = item.word
        tvtranslate.text = item.translation
        tvprogress.text = "${item.successfulAttempts}/${item.attempts}"
    }
}