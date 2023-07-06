package com.upn.collazos.blanco.finall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.upn.collazos.blanco.finall.model.Carta;

public class DetalleCarta extends AppCompatActivity {

    private TextView nombreCarta;
    private TextView puntosAtaque;
    private TextView puntoDefensa;

    private ImageView imagenCarta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_carta);

        Intent intent = getIntent();
        String nombre = intent.getStringExtra("nombre");

        nombreCarta = findViewById(R.id.txtNombreCartaDetalle);

        puntosAtaque = findViewById(R.id.txtPuntosAtaque);
        puntoDefensa = findViewById(R.id.txtPuntosDefensa);

        Carta carta = AppDatabase.getInstance(getApplicationContext()).cartaDao().loadAllByNombre(nombre);

        nombreCarta.setText(carta.nombre);
        puntosAtaque.setText(carta.puntosAtaque);
        puntoDefensa.setText(carta.puntosDefensa);


    }
}