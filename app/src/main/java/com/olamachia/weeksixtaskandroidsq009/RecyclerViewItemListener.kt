package com.olamachia.weeksixtaskandroidsq009

interface RecyclerViewItemListener {
    fun onCallButtonClicked(model: MyModel)

    fun onEditButtonClicked(model: MyModel)

    fun onDeleteButtonClicked(model: MyModel)

    fun onShareButtonClicked(model: MyModel)
}