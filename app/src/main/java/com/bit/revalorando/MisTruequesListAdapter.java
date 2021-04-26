package com.bit.revalorando;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.bit.revalorando.entities.Articulo;

public class MisTruequesListAdapter extends ListAdapter<Articulo,MisTruequesViewHolder> {

    private OnItemClickListener listener;
    public MisTruequesListAdapter(@NonNull DiffUtil.ItemCallback<Articulo> diffCallbak){
        super(diffCallbak);
    }

    @NonNull
    @Override
    public MisTruequesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return MisTruequesViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull MisTruequesViewHolder holder, int position) {
        Articulo articuloActual = getItem(position);
        holder.bind(articuloActual.getNombre(), articuloActual.getDescripcion(), articuloActual.getFoto());/*, articuloActual.getPassword(), articuloActual.getNombre(), articuloActual.getApellido(), articuloActual.getEmail(), articuloActual.getTelefono(), articuloActual.getDireccion(), articuloActual.getNumero(), articuloActual.getLocalidad(), articuloActual.getDepartamento());*/
        //holder.bind(articuloActual.getNombre());
        //holder.bind(articuloActual.getArticulo());

        ImageButton deleteButton = holder.itemView.findViewById(R.id.imageButtonDeleteMisTrueques);

        deleteButton.setOnClickListener(view -> {
            if(listener!=null){
                listener.onItemDelete(articuloActual);
            }
        });


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
        void onItemPublicar(Articulo articulo);

    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
