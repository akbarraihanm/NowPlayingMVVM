package com.example.testevermos.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.request.RequestOptions
import com.example.testevermos.R

class BindingConverters {
    companion object {
        @JvmStatic
        @BindingAdapter("loadImage")
        fun loadImage(imageView: ImageView, url : String) {
            Glide.with(imageView.context)
                .load(url)
                .apply(RequestOptions()
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)
                    .format(DecodeFormat.PREFER_ARGB_8888))
                .into(imageView)
        }
    }
}