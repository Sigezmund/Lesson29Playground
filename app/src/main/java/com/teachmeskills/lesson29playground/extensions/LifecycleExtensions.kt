package com.teachmeskills.lesson29playground.extensions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner


inline fun <reified VIEW_MODEL : ViewModel> ViewModelStoreOwner.getViewModel(crossinline factoryBlock: () -> VIEW_MODEL): VIEW_MODEL {
    return ViewModelProvider(this, object : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return factoryBlock() as T
        }

    }).get(VIEW_MODEL::class.java)
}