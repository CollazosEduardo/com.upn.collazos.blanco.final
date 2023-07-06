package com.upn.collazos.blanco.finall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnNewDuelista;
    private Button btnSinc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNewDuelista = findViewById(R.id.btnNewDuelista);
        btnSinc = findViewById(R.id.btnSincDuelistas);

        btnNewDuelista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),CrearDuelista.class);
                startActivity(intent);
            }
        });

        btnSinc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Sincronizando", Toast.LENGTH_SHORT).show();
            }
        });


    }
}