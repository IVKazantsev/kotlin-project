package com.kazancev.androiddevelopmentproject

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recylcerview_item.view.*

class PhonesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
{
    @SuppressLint("SetTextI18n")
    fun bind(mPhones: PhoneModel)
    {
        itemView.model.text = mPhones.name
        itemView.launchPrice.text = "${R.string.launchPricePrefix} ${mPhones.price}"
        itemView.launchDate.text = "${R.string.launchDatePrefix} ${mPhones.date}"
        itemView.cameraScope.text = "${R.string.cameraScorePrefix} ${mPhones.score}"
    }
}
