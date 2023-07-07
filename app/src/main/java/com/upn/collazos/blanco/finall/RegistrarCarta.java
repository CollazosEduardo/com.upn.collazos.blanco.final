package com.upn.collazos.blanco.finall;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.widget.Button;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.upn.collazos.blanco.finall.Utils.RetrofitBuilder;
import com.upn.collazos.blanco.finall.model.Carta;
import com.upn.collazos.blanco.finall.model.Duelista;
import com.upn.collazos.blanco.finall.services.CartasService;
import com.upn.collazos.blanco.finall.services.DuelistaService;
import com.upn.collazos.blanco.finall.services.UserService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegistrarCarta extends AppCompatActivity {


    private TextView tvNombre;
    private TextView tvPuntosAtaque;
    private TextView tvPuntosDefensa;

    private ImageView ivImagen;
    private TextView tvUbicacionRegistro;


    private Button btnGuardarCarta;

    private Button btnCargarImagen;

    private String s64Image;
    private String base64img;
    String urlImage = "";

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
                String image = s64Image;

                String ubicacion = tvUbicacionRegistro.getText().toString();

                Carta carta = new Carta(nombre, nombreDuelista, puntosAtaque, puntosDefensa, image, ubicacion);

                saveNewCartaData(carta);
                onBack(nombreDuelista);
            }
        });

        btnCargarImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ContextCompat.checkSelfPermission(RegistrarCarta.this,
                        android.Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED){
                    //when permssion is not granted
                    //Request permission
                    ActivityCompat.requestPermissions(RegistrarCarta.this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},100);
                }else{
                    //when permission is granted
                    //Create Method
                    selectImage();
                    Log.i("MAIN_APP", "Despues de SI:" + s64Image);
                }
            }
        });
    }

    private void saveNewCartaData(Carta carta){
        if(!isNetworkConnected()) {
            System.out.println("No hay Internet");
            AppDatabase.getInstance(getApplicationContext()).cartaDao().insert(carta);
        }
        else {
            System.out.println("Hay Internet");
            AppDatabase.getInstance(getApplicationContext()).cartaDao().insert(carta);
            saveCartaApi(carta);
        }
    }

    private void saveCartaApi(Carta carta){

        Retrofit retrofit = RetrofitBuilder.build();

        /*
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://64a6b8de096b3f0fcc806f8a.mockapi.io/final/") // revisar
                .addConverterFactory(GsonConverterFactory.create())
                .build();
         */
        CartasService service = retrofit.create(CartasService.class);
        Call<Carta> callCarta = service.create(carta);

        callCarta.enqueue(new Callback<Carta>() {
            @Override
            public void onResponse(Call<Carta> callCarta, Response<Carta> response) {
                Log.i("MAIN_APP",  "Save API");
                System.out.println("Save Api");
                Log.i("MAIN_APP:",  String.valueOf(response.code()));
            }

            @Override
            public void onFailure(Call<Carta> callCarta, Throwable t) {
                Log.i("MAIN_APP",  "error en cartas ::CCCCCCCCC");
            }
        });
    }

    private void onBack(String nombreDuelista){
        Intent intent = new Intent(getApplicationContext(), CrearDuelista.class);
        intent.putExtra("nombre", nombreDuelista);
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //Chech condition
        if(requestCode == 100 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            //When permission is Granted
            //Call method
            selectImage();
        }else {
            Toast.makeText(getApplicationContext(), "Permission denied", Toast.LENGTH_LONG).show();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Chech condition
        if(requestCode==100 && resultCode == RESULT_OK && data != null){
            //When result is ok
            //Initialize uri
            Uri uri = data.getData();
            try {
                //Initialize bitmap
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                //Initialize byte stream
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                //Compress bitmap
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);

                //Initialize byte array
                byte[] bytes = stream.toByteArray();
                //Get base64 encoded string
                s64Image = Base64.encodeToString(bytes,Base64.DEFAULT);
                //Set encoded text on text

                byte[] bytes1 = Base64.decode(s64Image, Base64.DEFAULT);
                Bitmap bitmap1 = BitmapFactory.decodeByteArray(bytes,0,bytes1.length);
                //set Image
                ivImagen.setImageBitmap(bitmap1);


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void selectImage(){
        //clear Previus data
        //imageViewBitmap.setImageBitmap(null);

        //Initialize intent
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        //set type
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent,"Select Image"),100);
    }
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
}



/*
                Retrofit retrofit123 = new Retrofit.Builder()
                        .baseUrl("https://demo-upn.bit2bittest.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                UserService service=retrofit123.create(UserService.class);



                Call<UserService.ImageResponse> call = service.saveImage(new UserService.ImageToSave(s64Image));

                call.enqueue(new Callback<UserService.ImageResponse>() {
                    @Override
                    public void onResponse(Call<UserService.ImageResponse> call, Response<UserService.ImageResponse> response) {
                        Log.i("Respuesta activa", response.toString());
                        if (response.isSuccessful()) {

                            UserService.ImageResponse imageResponse = response.body();
                            Log.i("Respues", response.toString());
                            urlImage = imageResponse.getUrl();
                            Log.i("Imagen url:", urlImage);

                        } else {

                            Log.e("Error cargar imagen",response.toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<UserService.ImageResponse> call, Throwable t) {
                        // Error de red o de la API
                        Log.i("Respuesta inactiva", "");
                    }
                });
*/