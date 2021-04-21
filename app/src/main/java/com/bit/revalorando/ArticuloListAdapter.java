package com.bit.revalorando;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.bit.revalorando.entities.Articulo;

public class ArticuloListAdapter extends ListAdapter<Articulo,ArticuloViewHolder> {

    public ArticuloListAdapter(@NonNull DiffUtil.ItemCallback<Articulo> diffCallbak){
        super(diffCallbak);
    }

    @NonNull
    @Override
    public ArticuloViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return ArticuloViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticuloViewHolder holder, int position) {
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
