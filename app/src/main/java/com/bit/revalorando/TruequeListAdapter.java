package com.bit.revalorando;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.bit.revalorando.entities.Trueque;

public class TruequeListAdapter extends ListAdapter<Trueque,TruequeViewHolder> {

    public TruequeListAdapter(@NonNull DiffUtil.ItemCallback<Trueque> diffCallbak){
        super(diffCallbak);
    }

    @NonNull
    @Override
    public TruequeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return TruequeViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull TruequeViewHolder holder, int position) {
        Trueque truequeActual = getItem(position);
        holder.bind(truequeActual.getEstado(), truequeActual.getEstado(), truequeActual.getEstado());/*, truequeActual.getPassword(), truequeActual.getNombre(), truequeActual.getApellido(), truequeActual.getEmail(), truequeActual.getTelefono(), truequeActual.getDireccion(), truequeActual.getNumero(), truequeActual.getLocalidad(), truequeActual.getDepartamento());*/
        //holder.bind(truequeActual.getNombre());
        //holder.bind(truequeActual.getTrueque());


    }

    static class TruequeDiff extends DiffUtil.ItemCallback<Trueque>{
        @Override
        public boolean areItemsTheSame(@NonNull Trueque oldItem, @NonNull Trueque newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Trueque oldItem, @NonNull Trueque newItem) {
            return oldItem.getEstado().equals(newItem.getEstado());
        }
    }
}
