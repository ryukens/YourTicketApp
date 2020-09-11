package com.moralesjuan.yourticketapp.InfoCupon

import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import com.moralesjuan.yourticketapp.EXTRA_EMAIL
import com.moralesjuan.yourticketapp.EXTRA_ID
import com.moralesjuan.yourticketapp.PrincipalActivity
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
            guardarCupon()
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

    fun guardarCupon(){
        val db = FirebaseFirestore.getInstance()

        val data = hashMapOf(
            "id_cupon" to id_cupon,
            "id_usuario" to PrincipalActivity.Companion.globalVarId
        )

        db.collection("cuponGuardado")
            .whereEqualTo("id_cupon", data["id_cupon"])
            .get()
            .addOnCompleteListener() {
                if(it.isSuccessful){
                    var documents = it.getResult()
                    if(documents != null){
                        if( documents.documents.size == 0) {
                            // Add a new document with a generated ID
                            db.collection("cuponGuardado")
                                .add(data)
                                .addOnSuccessListener { documentReference ->
                                    Log.d("Info", "DocumentSnapshot written with ID: ${documentReference.id}")
                                    Toast.makeText(context, "Coupon Saved", Toast.LENGTH_SHORT).show()
                                }
                                .addOnFailureListener { e ->
                                    Log.w("Error", "Error saving coupon", e)
                                    Toast.makeText(context, "Error reading from database", Toast.LENGTH_SHORT).show()
                                }
                        }else{
                            Toast.makeText(context, "This coupon is already saved", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
    }
}