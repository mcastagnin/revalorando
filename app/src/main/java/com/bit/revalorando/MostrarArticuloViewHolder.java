package com.bit.revalorando;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

public class MostrarArticuloViewHolder extends RecyclerView.ViewHolder {
        ImageView posterView;
        Context context;

        public MostrarArticuloViewHolder(View itemView) {
            super(itemView);

            posterView = (ImageView) itemView.findViewById(R.id.imagenArticulo);
            context = itemView.getContext();
        }

}
