package com.upn.collazos.blanco.finall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.upn.collazos.blanco.finall.model.Carta;

public class RegistrarCarta extends AppCompatActivity {


    private TextView tvNombre;
    private TextView tvPuntosAtaque;
    private TextView tvPuntosDefensa;

    private ImageView ivImagen;
    private TextView tvUbicacionRegistro;


    private Button btnGuardarCarta;

    private Button btnCargarImagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_carta);

        Intent intent = getIntent();

        String nombreDuelista = intent.getStringExtra("nombre");

        btnGuardarCarta = findViewById(R.id.btnSaveCarta);
        btnCargarImagen = findViewById(R.id.btnCargarImagenCarta);

        tvNombre = findViewById(R.id.edtNombreNewCarta);
        tvPuntosAtaque = findViewById(R.id.edtPuntosAtaqueNewCarta);
        tvPuntosDefensa = findViewById(R.id.edtPuntosDefensaNewCarta);
        ivImagen = findViewById(R.id.imageViewNewCarta);
        tvUbicacionRegistro = findViewById(R.id.edtUbicacionRegistroNewCarta);


        btnGuardarCarta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nombre = tvNombre.getText().toString();
                int puntosAtaque = Integer.parseInt(tvPuntosAtaque.getText().toString());
                int puntosDefensa = Integer.parseInt(tvPuntosDefensa.getText().toString());
                String image = "";
                String ubicacion = tvUbicacionRegistro.getText().toString();

                Carta carta = new Carta(nombre, nombreDuelista, puntosAtaque, puntosDefensa, image, ubicacion);

                AppDatabase.getInstance(getApplicationContext()).cartaDao().insert(carta);

                Intent intent = new Intent(getApplicationContext(), CrearDuelista.class);
                intent.putExtra("nombre", nombreDuelista);
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