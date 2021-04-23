package com.bit.revalorando;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.bit.revalorando.entities.Articulo;

public class ArticuloTruequeListAdapter extends ListAdapter<Articulo,ArticuloTruequeViewHolder> {

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
}
