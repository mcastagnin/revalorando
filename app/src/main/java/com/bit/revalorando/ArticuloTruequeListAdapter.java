package com.bit.revalorando;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.bit.revalorando.entities.Articulo;


public class ArticuloTruequeListAdapter extends ListAdapter<Articulo,ArticuloTruequeViewHolder> {

    private OnItemClickListener listener;
    public ArticuloTruequeListAdapter(@NonNull DiffUtil.ItemCallback<Articulo> diffCallbak){
        super(diffCallbak);
    }

    @NonNull
    @Override
    public ArticuloTruequeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return ArticuloTruequeViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticuloTruequeViewHolder holder, int position) {
        Articulo articuloActual = getItem(position);
        holder.bind(articuloActual.getNombre(), articuloActual.getDescripcion(), articuloActual.getFoto());/*, articuloActual.getPassword(), articuloActual.getNombre(), articuloActual.getApellido(), articuloActual.getEmail(), articuloActual.getTelefono(), articuloActual.getDireccion(), articuloActual.getNumero(), articuloActual.getLocalidad(), articuloActual.getDepartamento());*/
        //holder.bind(articuloActual.getNombre());
        //holder.bind(articuloActual.getArticulo());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null){
                    listener.OnItemClick(articuloActual);
                }
            }
        });

    }

    static class ArticuloDiff extends DiffUtil.ItemCallback<Articulo>{
        @Override
        public boolean areItemsTheSame(@NonNull Articulo oldItem, @NonNull Articulo newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Articulo oldItem, @NonNull Articulo newItem) {
            return oldItem.getNombre().equals(newItem.getNombre());
        }
    }

    public interface OnItemClickListener {
        void onItemDelete(Articulo articulo);
        void OnItemClick(Articulo articulo);
    }

    public void setOnItemClickListener(ArticuloTruequeListAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }
}
