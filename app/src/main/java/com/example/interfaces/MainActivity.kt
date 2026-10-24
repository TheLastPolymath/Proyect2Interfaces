package com.example.interfaces

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.DatePicker
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var tilNombre: TextInputLayout
    private lateinit var tilTelefono: TextInputLayout
    private lateinit var tilEmail: TextInputLayout
    private lateinit var etNombre: TextInputEditText
    private lateinit var etTelefono: TextInputEditText
    private lateinit var etEmail: TextInputEditText
    private lateinit var etDescripcion: TextInputEditText
    private lateinit var dpFecha: DatePicker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(bars.left, bars.top, bars.right, bars.bottom)
            insets
        }

        tilNombre = findViewById(R.id.tilNombre)
        tilTelefono = findViewById(R.id.tilTelefono)
        tilEmail = findViewById(R.id.tilEmail)
        etNombre = findViewById(R.id.etNombre)
        etTelefono = findViewById(R.id.etTelefono)
        etEmail = findViewById(R.id.etEmail)
        etDescripcion = findViewById(R.id.etDescripcion)
        dpFecha = findViewById(R.id.dpFecha)

        // No permitir fechas de nacimiento en el futuro
        dpFecha.maxDate = System.currentTimeMillis()

        findViewById<MaterialButton>(R.id.btnSiguiente).setOnClickListener {
            if (validar()) irAConfirmar()
        }
    }

    private fun validar(): Boolean {
        var ok = true

        if (etNombre.text.isNullOrBlank()) {
            tilNombre.error = "Ingresa tu nombre"; ok = false
        } else tilNombre.error = null

        if (etTelefono.text.isNullOrBlank()) {
            tilTelefono.error = "Ingresa tu teléfono"; ok = false
        } else tilTelefono.error = null

        val email = etEmail.text.toString().trim()
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            tilEmail.error = "Email no válido"; ok = false
        } else tilEmail.error = null

        return ok
    }

    private fun irAConfirmar() {
        // El mes del DatePicker empieza en 0, por eso el +1
        val fecha = String.format(
            Locale.getDefault(), "%02d/%02d/%04d",
            dpFecha.dayOfMonth, dpFecha.month + 1, dpFecha.year
        )

        val intent = Intent(this, ConfirmarActivity::class.java).apply {
            putExtra(ConfirmarActivity.EXTRA_NOMBRE, etNombre.text.toString().trim())
            putExtra(ConfirmarActivity.EXTRA_FECHA, fecha)
            putExtra(ConfirmarActivity.EXTRA_TELEFONO, etTelefono.text.toString().trim())
            putExtra(ConfirmarActivity.EXTRA_EMAIL, etEmail.text.toString().trim())
            putExtra(ConfirmarActivity.EXTRA_DESCRIPCION, etDescripcion.text.toString().trim())
        }
        startActivity(intent)
    }
}