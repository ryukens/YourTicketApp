package com.moralesjuan.yourticketapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_registro.*
import java.util.regex.Pattern

class RegistroActivity : AppCompatActivity() {

    lateinit var nombre: String
    lateinit var apellido: String
    lateinit var email: String
    lateinit var password: String
    lateinit var confirmPassword: String
    lateinit var fechaNacimiento: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        buttonSingIn.setOnClickListener() {

            if (validarCamposVacios(editTextName) && validarCamposVacios(editTextTextLastname) && validarCamposVacios(
                    editTextTextEmail
                ) && validarCamposVacios(editTextTextPassword) && validarCamposVacios(
                    editTextTextPasswordConfirm
                ) && validarCamposVacios(editTextDateBirth)
            ) {

                nombre = editTextName.text.toString()
                apellido = editTextTextLastname.text.toString()
                email = editTextTextEmail.text.toString()
                password = editTextTextPassword.text.toString()
                confirmPassword = editTextTextPasswordConfirm.text.toString()
                fechaNacimiento = editTextDateBirth.text.toString()

                if (validarEmail(email)) {

                    if (editTextTextPassword.length() >= MIN_PASSWORD_LENGTH) {
                        if (password == confirmPassword) {

                            ProcesarUsuario(nombre, apellido, email, password, fechaNacimiento)

                        } else {
                            editTextTextPassword.error = "Passwords don't match"
                            editTextTextPasswordConfirm.error = "Passwords don't match"
                            Toast.makeText(this, "Passwords don't match", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        editTextTextPassword.error =
                            "Password must be at least $MIN_PASSWORD_LENGTH characters long"
                        Toast.makeText(this, "Password must be at least $MIN_PASSWORD_LENGTH characters long", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    editTextTextEmail.error = "Invalid email"
                    Toast.makeText(this, "Invalid email format", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please, fill the empty fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validarCamposVacios(campo: EditText): Boolean {
        if (campo.text.toString().isEmpty()) {
            campo.error = "Error, empty field"
            return false
        }
        return true
    }

    private fun validarEmail(email: String): Boolean {
        val pattern: Pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }

    fun ProcesarUsuario(nombre:String, apellido:String, email:String, password:String, fechaNacimiento:String) {
        val db = FirebaseFirestore.getInstance()
        lateinit var idDocumentReference: String

        val data = hashMapOf(
            "nombre_usuario" to nombre,
            "apellido_usuario" to apellido,
            "correo_usuario" to email,
            "contrasenia_usuario" to password,
            "fecha_nacimiento" to fechaNacimiento
        )

        db.collection("usuario")
            .whereEqualTo("correo_usuario",email)
            .get()
            .addOnCompleteListener() {
                if(it.isSuccessful){
                    var documents = it.getResult()
                    if(documents != null){
                        if( documents.documents.size == 0) {
                            // Add a new document with a generated ID
                            db.collection("usuario")
                                .add(data)
                                .addOnSuccessListener { documentReference ->
                                    Log.d("Info", "DocumentSnapshot written with ID: ${documentReference.id}")
                                    idDocumentReference = documentReference.id
                                    val i = Intent(this@RegistroActivity, PrincipalActivity::class.java)
                                    i.putExtra(EXTRA_ID, idDocumentReference)
                                    i.putExtra(EXTRA_EMAIL, email)
                                    startActivity(i)
                                }
                                .addOnFailureListener { e ->
                                    Log.w("Error", "Error adding document", e)
                                    Toast.makeText(this, "Error reading from database", Toast.LENGTH_SHORT).show()
                                }
                        }else{
                            Toast.makeText(this, "This user already exists", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
    }
}