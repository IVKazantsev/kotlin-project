package com.kazancev.androiddevelopmentproject

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recylcerview_item.view.*

class PhonesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
{
    @SuppressLint("SetTextI18n")
    fun bind(mPhones: PhoneModel, position: Int)
    {
        val place = position + 1;

        itemView.model.text = "${place}. ${mPhones.name}"
        itemView.launchPrice.text = "Launch price: ${mPhones.price}"
        itemView.launchDate.text = "Launch date: ${mPhones.date}"
        itemView.cameraScope.text = "Camera score: ${mPhones.score}"
    }
}
