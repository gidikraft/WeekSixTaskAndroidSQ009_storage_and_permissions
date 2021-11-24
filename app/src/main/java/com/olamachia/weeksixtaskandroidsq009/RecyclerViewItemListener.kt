package com.olamachia.weeksixtaskandroidsq009

interface RecyclerViewItemListener {
    fun onItemClicked(model: MyModel)

    fun onCallButtonClicked(model: MyModel)

    fun onEditButtonClicked(model: MyModel)

    fun onDeleteButtonClicked(model: MyModel)

    fun onShareButtonClicked(model: MyModel)
}