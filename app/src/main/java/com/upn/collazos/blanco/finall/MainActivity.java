package com.upn.collazos.blanco.finall;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.upn.collazos.blanco.finall.adapters.DuelistaItemAdapter;
import com.upn.collazos.blanco.finall.model.Duelista;
import com.upn.collazos.blanco.finall.services.DuelistaService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://647742fc9233e82dd53b49b7.mockapi.io/") // revisar
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                DuelistaService service = retrofit.create(DuelistaService.class);
                Call<List<Duelista>> call = service.getAll();


                call.enqueue(new Callback<List<Duelista>>() {
                    @Override
                    public void onResponse(Call<List<Duelista>> call, Response<List<Duelista>> response) {
                        Log.i("MAIN_APP", String.valueOf(response.code()));
                        if(response.isSuccessful()) {
                            DuelistaItemAdapter adapter = new DuelistaItemAdapter(response.body(), new DuelistaItemAdapter.OnItemClickListener(){
                                @Override
                                public void onItemClick(Duelista duelista) {
                                    moveToDetalle(duelista);
                                }
                            });
                            recyclerView.setAdapter(adapter);
                        }

                    }

                    @Override
                    public void onFailure(Call<List<Duelista>> call, Throwable t) {
                        Log.i("MAIN_APP", t.toString());
                    }
                });


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