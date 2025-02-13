package com.kazancev.androiddevelopmentproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_first.view.*

class FirstFragment : Fragment()
{
    private val myAdapter = PhonesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        val root = inflater.inflate(R.layout.fragment_first, container, false)

        loadData()
        root.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        root.recyclerView.adapter = myAdapter

        return root
    }

    private fun loadData()
    {
        myAdapter.setupPhones(PhonesData.phonesArr, 0, 150)
    }
}
