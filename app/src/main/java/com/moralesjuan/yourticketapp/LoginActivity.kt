package com.moralesjuan.yourticketapp

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_login.*
import java.util.regex.Pattern

class LoginActivity : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences
    var credencialesCorrectas = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        InicializarArchivoDePreferencias()
        LeerDatosDeArchivoPreferencias()

        button_Login.setOnClickListener() {

            EscribirDatosEnArchivoPreferencias()

            if(!ValidarEmail(editTextEmail.text.toString())){
                editTextEmail.error = "Invalid email"
                return@setOnClickListener
            }else if(editTextTextPersonName2.length() <= MIN_PASSWORD_LENGTH){
            editTextTextPersonName2.error = "Password must be at least $MIN_PASSWORD_LENGTH characters long"
                return@setOnClickListener
            }else {
                val db = FirebaseFirestore.getInstance()
                db.collection("usuario")
                    .get()
                    .addOnSuccessListener { documents ->
                        if(documents != null){
                            for (documento in documents) {
                                if(editTextEmail.text.toString() == documento.data.getValue("correo_usuario") && editTextTextPersonName2.text.toString() == documento.data.getValue("contrasenia_usuario")){
                                    val i = Intent(this@LoginActivity, PrincipalActivity::class.java)
                                    i.putExtra(EXTRA_ID, documento.id)
                                    i.putExtra(EXTRA_EMAIL, editTextEmail.text.toString())
                                    startActivity(i)
                                    credencialesCorrectas = true
                                    finish()
                                }
                            }
                            if(!credencialesCorrectas){
                                editTextEmail.error = "Wrong credentials"
                                editTextTextPersonName2.error = "Wrong credentials"
                            }
                        }else{
                            Toast.makeText(this,"There are no records in the database",Toast.LENGTH_SHORT)
                        }
                    }
            }

        }

        button_Google.setOnClickListener() {
            startActivity(Intent(this, RegistroActivity::class.java))
        }

        button_registrarse.setOnClickListener() {
            startActivity(Intent(this, RegistroActivity::class.java))
        }
    }

    fun InicializarArchivoDePreferencias(){
        val masterKeyAlias: String = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

        sharedPreferences = EncryptedSharedPreferences.create(
            "secret_shared_prefs",//filename
            masterKeyAlias,
            this,//context
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    fun EscribirDatosEnArchivoPreferencias() {
        if (checkBoxRemember.isChecked) {
            val editor = sharedPreferences.edit()
            editor.putString(LOGIN_KEY, editTextEmail.text.toString())
            editor.putString(PASSWORD_KEY, editTextTextPersonName2.text.toString())
            editor.commit()
        } else {
            val editor = sharedPreferences.edit()
            editor.putString(LOGIN_KEY, "")
            editor.putString(PASSWORD_KEY, "")
            editor.commit()
        }
    }

    fun LeerDatosDeArchivoPreferencias() {
        editTextEmail.setText(sharedPreferences.getString(LOGIN_KEY, ""))
        editTextTextPersonName2.setText(sharedPreferences.getString(PASSWORD_KEY, ""))
    }

    private fun ValidarEmail(email: String): Boolean {
        val pattern: Pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }
}