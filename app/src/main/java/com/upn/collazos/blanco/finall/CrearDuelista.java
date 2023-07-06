package com.upn.collazos.blanco.finall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class CrearDuelista extends AppCompatActivity {

    private Button btnNewCardDuelista;

    private Button btnSaveDuelista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_duelista);

        btnNewCardDuelista = findViewById(R.id.btnNewCardNewDuelista);
        btnSaveDuelista = findViewById(R.id.btnSaveNewDuelista);

        btnNewCardDuelista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),RegistrarCarta.class);
                startActivity(intent);
            }
        });

        btnSaveDuelista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }
}