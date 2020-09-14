package com.moralesjuan.yourticketapp

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.gms.ads.AdRequest
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_perfil.view.*
import java.util.regex.Pattern

class PerfilFragment : Fragment() {

    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var root = inflater.inflate(R.layout.fragment_perfil, container, false)

        root = cargarDatosPerfil(root)

        root.buttonUpdate.setOnClickListener {

            if(root.editTextProfileEmail.text.toString() != PrincipalActivity.Companion.globalVarEmail){
                if(!ValidarEmail(root.editTextProfileEmail.text.toString())){
                    root.editTextProfileEmail.error = "Invalid email"
                    return@setOnClickListener
                }
            }

            val db = FirebaseFirestore.getInstance()
            var documento = db.collection("usuario").document(PrincipalActivity.Companion.globalVarId)
            documento
                .update("nombre_usuario", root.editTextProfileName.text.toString(),
                    "apellido_usuario", root.editTextProfileLastname.text.toString(),
                    "correo_usuario", root.editTextProfileEmail.text.toString(),
                    "fecha_nacimiento", root.editTextProfileDate.text.toString())
                .addOnSuccessListener {
                    Toast.makeText(this.context, "Data successfully updated", Toast.LENGTH_SHORT).show()
                    PrincipalActivity.Companion.globalVarEmail = root.editTextProfileEmail.text.toString()
                    Log.d("Info", "DocumentSnapshot successfully updated!")
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this.context, "Error updating data", Toast.LENGTH_SHORT).show()
                    Log.w("Error", "Error updating document", e)
                }

        }

        val adRequest = AdRequest.Builder().build()
        root.adViewPerfil.loadAd(adRequest)

        return root
    }

    fun cargarDatosPerfil(root:View): View{

        val db = FirebaseFirestore.getInstance()
        db.collection("usuario")
            .whereEqualTo("correo_usuario",PrincipalActivity.Companion.globalVarEmail)
            .get()
            .addOnCompleteListener() {
                if(it.isSuccessful){
                    var documents = it.getResult()
                    if(documents != null){
                        if( documents.documents.size > 0) {
                            for (documento in documents) {
                                root.editTextProfileName.setText(documento.data.getValue("nombre_usuario").toString())
                                root.editTextProfileLastname.setText(documento.data.getValue("apellido_usuario").toString())
                                root.editTextProfileEmail.setText(documento.data.getValue("correo_usuario").toString())
                                root.editTextProfileDate.setText(documento.data.getValue("fecha_nacimiento").toString())
                            }
                        }
                    }
                }
            }

        return root
    }

    private fun ValidarEmail(email: String): Boolean {
        val pattern: Pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }
}