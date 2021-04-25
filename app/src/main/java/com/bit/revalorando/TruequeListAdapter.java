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
import com.bit.revalorando.entities.Trueque;
import com.bit.revalorando.entities.Articulo;


public class TruequeListAdapter extends ListAdapter<Trueque,TruequeViewHolder> {

    private OnItemClickListener listener;
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

    public interface OnItemClickListener {
        void onItemDelete(Articulo articulo);
        void OnItemClick(Articulo articulo);
    }

    public void setOnItemClickListener(TruequeListAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }
}

