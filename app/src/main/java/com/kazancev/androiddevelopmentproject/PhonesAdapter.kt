package com.kazancev.androiddevelopmentproject

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class PhonesAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>()
{
    private var mPhonesList: ArrayList<PhoneModel> = ArrayList()

    @SuppressLint("NotifyDataSetChanged")
    fun setupPhones(phonesList: Array<PhoneModel>, scoreFrom: Int, scoreTo: Int)
    {
        mPhonesList.clear()

        for (phone in phonesList)
        {
            if(phone.score.toInt() < scoreFrom || phone.score.toInt() > scoreTo)
            {
                continue
            }

            mPhonesList.add(phone)
        }

        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int)
    {
        if (holder is PhonesViewHolder)
        {
            holder.bind(mPhones = mPhonesList[position], position)
        }
    }

    override fun getItemCount(): Int
    {
        return mPhonesList.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
    {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.recylcerview_item, parent, false)

        return PhonesViewHolder(itemView)
    }
}
