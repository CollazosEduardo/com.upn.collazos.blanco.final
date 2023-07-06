package com.upn.collazos.blanco.finall.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.upn.collazos.blanco.finall.model.Duelista;
import com.upn.collazos.blanco.finall.R;

import java.util.List;

public class DuelistaItemAdapter extends RecyclerView.Adapter<DuelistaItemAdapter.DuelistaViewHolder> {
    final OnItemClickListener listener;
    List<Duelista> duelistas;

    public interface OnItemClickListener {
        void onItemClick(Duelista duelista);
    }
    public DuelistaItemAdapter(List<Duelista> duelista, OnItemClickListener onItemClickListener) {
        this.listener = onItemClickListener;
        this.duelistas = duelista;
    }
    @Override
    public DuelistaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.duelista_item,parent,false);
        return new DuelistaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DuelistaItemAdapter.DuelistaViewHolder holder, int position) {

        View view = holder.itemView;
        Duelista duelista = duelistas.get(position);

        TextView tvTitle = view.findViewById(R.id.tvNombreDuelistaItem);


        tvTitle.setText(duelista.nombre);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(duelistas.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return duelistas.size();
    }


    public class DuelistaViewHolder extends RecyclerView.ViewHolder {
        public DuelistaViewHolder(View itemView) {
            super(itemView);
        }
    }
}
