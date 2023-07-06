package com.upn.collazos.blanco.finall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.upn.collazos.blanco.finall.model.Duelista;

import java.util.ArrayList;
import java.util.List;

public class CrearDuelista extends AppCompatActivity {

    private Button btnNewCardDuelista;

    private Button btnSaveDuelista;

    private EditText txtNombreDuelista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_duelista);

        btnNewCardDuelista = findViewById(R.id.btnNewCardNewDuelista);
        btnSaveDuelista = findViewById(R.id.btnSaveNewDuelista);
        txtNombreDuelista = findViewById(R.id.edtNombreNewDuelista);

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
                String nombre = txtNombreDuelista.getText().toString();

                ArrayList<String> cartas = new ArrayList<String>();

                Duelista duelista = new Duelista(nombre,cartas);
                saveNewDuelistaData(view, duelista);
                back(view);
            }
        });
    }

    private void saveNewDuelistaData(View view, Duelista duelista){
        AppDatabase.getInstance(getApplicationContext()).duelistaDao().insert(duelista);
    }
    public void back(View view){
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }
}