package com.upn.collazos.blanco.finall;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.upn.collazos.blanco.finall.model.Carta;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

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

        Toast.makeText(this, nombre, Toast.LENGTH_SHORT).show();

        nombreCarta = findViewById(R.id.txtNombreCartaDetalle);

        puntosAtaque = findViewById(R.id.txtPuntosAtaque);
        puntoDefensa = findViewById(R.id.txtPuntosDefensa);
        imagenCarta = findViewById(R.id.imagenCartaDetalle);

        Carta carta = AppDatabase.getInstance(getApplicationContext()).cartaDao().loadAllByNombre(nombre);

        nombreCarta.setText(carta.nombre);

        String puntosAtaqueString = String.valueOf(carta.puntosAtaque);
        String puntosDefensaString = String.valueOf(carta.puntosDefensa);

        puntosAtaque.setText(puntosAtaqueString);
        puntoDefensa.setText(puntosDefensaString);

        byte[] bytes = Base64.decode(carta.imagen, Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);


        imagenCarta.setImageBitmap(bitmap);

    }

}