package com.example.engrus.presentation

import androidx.recyclerview.widget.DiffUtil

class WordCardDiffCallBack: DiffUtil.ItemCallback<WordCardUi>() {
    override fun areItemsTheSame(
        oldItem: WordCardUi,
        newItem: WordCardUi
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: WordCardUi,
        newItem: WordCardUi
    ): Boolean {
        return oldItem == newItem
    }
}