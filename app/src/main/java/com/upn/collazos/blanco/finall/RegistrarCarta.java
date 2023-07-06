package com.upn.collazos.blanco.finall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.Toast;

public class RegistrarCarta extends AppCompatActivity {

    private Button btnGuardarCarta;

    private Button btnCargarImagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_carta);

        btnGuardarCarta = findViewById(R.id.btnSaveCarta);
        btnCargarImagen = findViewById(R.id.btnCargarImagenCarta);
        btnGuardarCarta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

        btnCargarImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Cargando imagen", Toast.LENGTH_SHORT).show();
            }
        });
    }
}