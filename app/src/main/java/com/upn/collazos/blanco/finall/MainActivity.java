package com.upn.collazos.blanco.finall;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnNewDuelista;
    private Button btnSinc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNewDuelista = findViewById(R.id.btnNewDuelista);
        btnSinc = findViewById(R.id.btnSincDuelistas);

    }
}