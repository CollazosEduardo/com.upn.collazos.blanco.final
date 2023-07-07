package com.upn.collazos.blanco.finall;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.upn.collazos.blanco.finall.adapters.CartaItemAdapter;
import com.upn.collazos.blanco.finall.model.Carta;
import com.upn.collazos.blanco.finall.model.Duelista;

import java.util.ArrayList;
import java.util.List;

public class DetalleDuelista extends AppCompatActivity {

    private TextView tvNombreDuelista;

    private CartaItemAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_duelista);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rcvCartasDuelistaDetalle);
        LinearLayoutManager layoutManager;// = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Intent intent = getIntent();
        String nombre = intent.getStringExtra("nombre");

        Duelista duelista = AppDatabase.getInstance(getApplicationContext()).duelistaDao().loadAllByNombre(nombre);

        tvNombreDuelista = findViewById(R.id.txtNombreDuelistaDetalle);

        tvNombreDuelista.setText("Nombre: " + duelista.nombre);



        List<Carta> lista = AppDatabase.getInstance(getApplicationContext()).cartaDao().getAll();

        List<Carta> listaCartasDuelsita = new ArrayList<Carta>();

        for(int i = 0; i < lista.size(); i++){
            if(lista.get(i).nombreDuelista.equals(duelista.nombre)){
                listaCartasDuelsita.add(lista.get(i));
            }
        }

        itemAdapter = new CartaItemAdapter(listaCartasDuelsita, new CartaItemAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(Carta carta) {
                moveToDetalle(carta.nombre);
            }
        });

        recyclerView.setAdapter(itemAdapter);
    }

    private void moveToDetalle(String nombre){
        Intent description = new Intent(getApplicationContext(), DetalleCarta.class);
        description.putExtra("nombre", nombre);
        startActivity(description);
    }
}