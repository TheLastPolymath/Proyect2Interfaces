package com.example.interfaces

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton

class ConfirmarActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_NOMBRE = "extra_nombre"
        const val EXTRA_FECHA = "extra_fecha"
        const val EXTRA_TELEFONO = "extra_telefono"
        const val EXTRA_EMAIL = "extra_email"
        const val EXTRA_DESCRIPCION = "extra_descripcion"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_confirmar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(bars.left, bars.top, bars.right, bars.bottom)
            insets
        }

        findViewById<TextView>(R.id.tvNombre).text = intent.getStringExtra(EXTRA_NOMBRE)
        findViewById<TextView>(R.id.tvFecha).text =
            "Fecha de nacimiento: ${intent.getStringExtra(EXTRA_FECHA)}"
        findViewById<TextView>(R.id.tvTelefono).text =
            "Tel. ${intent.getStringExtra(EXTRA_TELEFONO)}"
        findViewById<TextView>(R.id.tvEmail).text =
            "Email: ${intent.getStringExtra(EXTRA_EMAIL)}"
        findViewById<TextView>(R.id.tvDescripcion).text =
            "Descripción: ${intent.getStringExtra(EXTRA_DESCRIPCION)}"

        // Volver al formulario: sigue en la pila con los datos escritos
        findViewById<MaterialButton>(R.id.btnEditar).setOnClickListener { finish() }
    }
}