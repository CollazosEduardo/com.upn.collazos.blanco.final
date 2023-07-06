package com.upn.collazos.blanco.finall;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.upn.collazos.blanco.finall.adapters.DuelistaItemAdapter;
import com.upn.collazos.blanco.finall.model.Duelista;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btnNewDuelista;
    private Button btnSinc;

    private DuelistaItemAdapter itemAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rcvDuelistas);
        LinearLayoutManager layoutManager;// = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));




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

        List<Duelista> lista = AppDatabase.getInstance(getApplicationContext()).duelistaDao().getAll();
        itemAdapter = new DuelistaItemAdapter(lista, new DuelistaItemAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(Duelista duelista) {
                moveToDetalle(duelista);
            }
        });

        recyclerView.setAdapter(itemAdapter);
    }
    private void moveToDetalle(Duelista duelista){
        Intent description = new Intent(getApplicationContext(), DetalleDuelista.class);
        description.putExtra("nombre", duelista.nombre);
        startActivity(description);
    }
}