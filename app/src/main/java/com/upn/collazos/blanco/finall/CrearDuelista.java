package com.upn.collazos.blanco.finall;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.upn.collazos.blanco.finall.adapters.CartaItemAdapter;
import com.upn.collazos.blanco.finall.adapters.DuelistaItemAdapter;
import com.upn.collazos.blanco.finall.model.Carta;
import com.upn.collazos.blanco.finall.model.Duelista;
import com.upn.collazos.blanco.finall.services.DuelistaService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CrearDuelista extends AppCompatActivity {

    private Button btnNewCardDuelista;

    private Button btnSaveDuelista;

    private EditText txtNombreDuelista;


    private CartaItemAdapter itemAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_duelista);

        Intent intent = getIntent();
        String duelistaReg = "";
        duelistaReg = intent.getStringExtra("nombre");

        btnNewCardDuelista = findViewById(R.id.btnNewCardNewDuelista);
        btnSaveDuelista = findViewById(R.id.btnSaveNewDuelista);
        txtNombreDuelista = findViewById(R.id.edtNombreNewDuelista);


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rcvCartasNewDuelista);
        LinearLayoutManager layoutManager;// = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        if(duelistaReg!=""){
            txtNombreDuelista.setText(duelistaReg);
        }

        btnNewCardDuelista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),RegistrarCarta.class);
                String nombreDuelista = txtNombreDuelista.getText().toString();
                intent.putExtra("nombre", nombreDuelista);
                startActivity(intent);
            }
        });

        btnSaveDuelista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = txtNombreDuelista.getText().toString();

                String cartas = "";

                Duelista duelista = new Duelista(nombre,cartas);
                saveNewDuelistaData(view, duelista);
                back(view);
            }
        });
        String nombreDuelista = txtNombreDuelista.getText().toString();

        List<Carta> lista = AppDatabase.getInstance(getApplicationContext()).cartaDao().loadAllCartsDuelista(nombreDuelista);
        itemAdapter = new CartaItemAdapter(lista, new CartaItemAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(Carta carta) {
                moveToDetalle(carta);
            }
        });

        recyclerView.setAdapter(itemAdapter);
    }
    private void moveToDetalle(Carta carta){
        Intent description = new Intent(getApplicationContext(), DetalleDuelista.class);
        description.putExtra("nombre", carta.nombre);
        startActivity(description);
    }

    private void saveNewDuelistaData(View view, Duelista duelista){
        AppDatabase.getInstance(getApplicationContext()).duelistaDao().insert(duelista);
        saveDuelistaApi(duelista);
    }
    public void back(View view){
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }
    private void saveDuelistaApi(Duelista duelista){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://64a6b8de096b3f0fcc806f8a.mockapi.io/final/") // revisar
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        DuelistaService service = retrofit.create(DuelistaService.class);


        Call<Duelista> call = service.create(duelista);

        call.enqueue(new Callback<Duelista>() {
            @Override
            public void onResponse(Call<Duelista> call, Response<Duelista> response) {
                Log.i("MAIN_APP",  String.valueOf(response.code()));
            }

            @Override
            public void onFailure(Call<Duelista> call, Throwable t) {

            }
        });
    }
}