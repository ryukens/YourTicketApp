package com.moralesjuan.yourticketapp

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.firestore.FirebaseFirestore


class MapsFragment(private var nombre_establecimiento: String="") : Fragment() {

    private val callback = OnMapReadyCallback { googleMap ->
        googleMap.clear()
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(-0.225219,-78.5248),11.0F));
        if(nombre_establecimiento != ""){
            val db = FirebaseFirestore.getInstance()
            db.collection("establecimiento")
                .whereEqualTo("nombre_est", nombre_establecimiento)
                .get()
                .addOnSuccessListener { documents ->
                    for (documento in documents) {
                        for (ubicacion in documento["ubicacion"] as ArrayList<String>){
                            googleMap.addMarker(
                                MarkerOptions()
                                    .position(LatLng(ubicacion.substringBefore(";").toDouble(),ubicacion.substringAfter(";").toDouble()))
                                    .icon(getIcono(documento["categoria_est"].toString()))
                                    .anchor(0.05f,0.05f)
                            )
                        }
                    }
                }
        }
        googleMap.uiSettings.isZoomControlsEnabled = true
        googleMap.uiSettings.isCompassEnabled = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var root = inflater.inflate(R.layout.fragment_maps, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)

    }

    fun getIcono(categoria: String): BitmapDescriptor? {
        if (categoria == "Food"){
            return BitmapDescriptorFactory.fromResource(R.drawable.food)
        }else if(categoria == "Car"){
            return BitmapDescriptorFactory.fromResource(R.drawable.car)
        }else if(categoria == "Clothes"){
            return BitmapDescriptorFactory.fromResource(R.drawable.clothes)
        }else if(categoria == "Shoes"){
            return BitmapDescriptorFactory.fromResource(R.drawable.shoe)
        }else if(categoria == "Jeans"){
            return BitmapDescriptorFactory.fromResource(R.drawable.jean)
        }else if(categoria == "Desserts") {
            return BitmapDescriptorFactory.fromResource(R.drawable.dessert)
        }else
            return BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)
        return null
    }

}