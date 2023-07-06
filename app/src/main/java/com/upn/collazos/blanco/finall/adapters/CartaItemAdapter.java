package com.upn.collazos.blanco.finall.adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import com.upn.collazos.blanco.finall.R;
import com.upn.collazos.blanco.finall.model.Carta;

import java.util.List;

public class CartaItemAdapter extends RecyclerView.Adapter<CartaItemAdapter.CartaViewHolder>{

    final OnItemClickListener listener;
    List<Carta> cartas;

    public interface OnItemClickListener {
        void onItemClick(Carta carta);
    }
    public CartaItemAdapter(List<Carta> cartas, OnItemClickListener onItemClickListener) {
        this.listener = onItemClickListener;
        this.cartas = cartas;
    }
    @Override
    public CartaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.carta_item,parent,false);
        return new CartaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CartaItemAdapter.CartaViewHolder holder, int position) {

        View view = holder.itemView;
        Carta carta = cartas.get(position);

        TextView tvNombreCarta = view.findViewById(R.id.tvNombreCartaItem);
        ImageView portada = view.findViewById(R.id.tvImagenCartaItem);

        tvNombreCarta.setText(carta.nombre);

        carta.imagen = carta.imagen.replace("data:image/png;base64,","");

        byte[] bytes = Base64.decode(carta.imagen, Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);

        portada.setImageBitmap(bitmap);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(cartas.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartas.size();
    }


    public class CartaViewHolder extends RecyclerView.ViewHolder {
        public CartaViewHolder(View itemView) {
            super(itemView);
        }
    }
}
