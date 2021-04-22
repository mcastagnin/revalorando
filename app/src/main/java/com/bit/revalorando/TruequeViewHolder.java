package com.bit.revalorando;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class TruequeViewHolder extends RecyclerView.ViewHolder {
    private final TextView nombreItemView;
    private final TextView descripcionItemView;
    private final ImageView imagenView;

    private TruequeViewHolder(View itemView){
        super(itemView);
        nombreItemView = itemView.findViewById(R.id.textViewNombre);
        descripcionItemView = itemView.findViewById(R.id.textViewDescripcion);
        imagenView = itemView.findViewById(R.id.imagen);
    }

    public void bind(String nombre, String descripcion, String url){
        nombreItemView.setText(nombre);
        descripcionItemView.setText(descripcion);
        Picasso.with(itemView.getContext())
                .load(url)
                .centerCrop()
                .fit()
                .into(imagenView);
    }


    static TruequeViewHolder create(ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trueque_item, parent, false);
        return new TruequeViewHolder(view);
    }
}