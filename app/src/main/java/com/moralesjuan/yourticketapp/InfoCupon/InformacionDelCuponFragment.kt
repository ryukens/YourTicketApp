package com.moralesjuan.yourticketapp.InfoCupon

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore
import com.moralesjuan.yourticketapp.Establecimiento.Establecimiento
import com.moralesjuan.yourticketapp.R

class InformacionDelCuponFragment(private var id_cupon: String) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_informacion_del_cupon, container, false)

        cargarCupon(root, id_cupon)

        val buttonSaveCupon: Button = root.findViewById(R.id.buttonSaveCupon)
        buttonSaveCupon.setOnClickListener() {
            Toast.makeText(buttonSaveCupon.context, "Coupon Saved", Toast.LENGTH_LONG).show()
        }
        return root
    }

    fun cargarCupon(root: View, id_cupon: String) {
        val imageView_Cupon1: ImageView = root.findViewById(R.id.imageViewCupon1)
        val imageView_QRCode: ImageView = root.findViewById(R.id.imageViewQRcode)
        val textViewEstablishment: TextView = root.findViewById(R.id.textViewEstablishmentValue)
        val textViewDiscount: TextView = root.findViewById(R.id.textViewDiscountValue)
        val textViewDate: TextView = root.findViewById(R.id.textViewDueDateValue)
        val db = FirebaseFirestore.getInstance()
        db.collection("cupon")
            .document(id_cupon)
            .get()
            .addOnSuccessListener {
                Glide.with(this).load(it["path_cupon"]).into(imageView_Cupon1)
                Glide.with(this).load(it["codigo_qr"]).into(imageView_QRCode)
                textViewEstablishment.text = it["establecimiento"].toString()
                textViewDiscount.text = it["promocion"].toString()
                textViewDate.text = it["expiracion"].toString()
            }


    }
}