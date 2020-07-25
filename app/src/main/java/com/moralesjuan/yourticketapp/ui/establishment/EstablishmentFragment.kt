package com.moralesjuan.yourticketapp.ui.establishment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.moralesjuan.yourticketapp.R

class EstablishmentFragment : Fragment() {

    private lateinit var establishmentViewModel: EstablishmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        establishmentViewModel =
            ViewModelProviders.of(this).get(EstablishmentViewModel::class.java)
        val root = inflater.inflate(R.layout.establishment_slideshow, container, false)
        val textView: TextView = root.findViewById(R.id.text_slideshow)
        establishmentViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}