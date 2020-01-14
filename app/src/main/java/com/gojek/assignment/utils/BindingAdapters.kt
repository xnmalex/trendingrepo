package com.gojek.assignment.utils

import android.graphics.Color
import android.graphics.drawable.LayerDrawable
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import com.gojek.assignment.R
import com.squareup.picasso.Picasso


@BindingAdapter("adapter")
fun setAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.adapter = adapter
}

@BindingAdapter("imageUrl")
fun setImageUrl(view: ImageView, imagePath: String) {
    Picasso.get().load(imagePath).into(view)
}

@BindingAdapter("stopShimmer")
fun setStopShimmer(view: ShimmerFrameLayout, isLoading:Boolean) {
    view.visibility = if (isLoading)  View.VISIBLE else View.GONE
}

@BindingAdapter("isLanguageVisible")
fun setLanguageVisible(view: LinearLayout, language:String?) {
    view.visibility = if (TextUtils.isEmpty(language))  View.GONE else View.VISIBLE
}

@BindingAdapter("isDescVisible")
fun setDescVisible(view: TextView, description:String?) {
    view.visibility = if (TextUtils.isEmpty(description))  View.GONE else View.VISIBLE
}

@BindingAdapter("languageColor")
fun setLanguageColor(view: View, languageColor:String?) {
    if (!TextUtils.isEmpty(languageColor))   view.setBackgroundColor(Color.parseColor(languageColor))
}
