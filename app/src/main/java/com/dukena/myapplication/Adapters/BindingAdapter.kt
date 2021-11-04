package com.dukena.myapplication.Adapters

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("visible")
fun View.bindVisivle(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}