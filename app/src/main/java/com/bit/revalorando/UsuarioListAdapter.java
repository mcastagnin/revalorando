package com.bit.revalorando;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.bit.revalorando.entities.Usuario;

public class UsuarioListAdapter extends ListAdapter<Usuario,UsuarioViewHolder> {

    public UsuarioListAdapter(@NonNull DiffUtil.ItemCallback<Usuario> diffCallbak){
        super(diffCallbak);
    }

    @NonNull
    @Override
    public UsuarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return UsuarioViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull UsuarioViewHolder holder, int position) {
        Usuario usuarioActual = getItem(position);
        holder.bind(usuarioActual.getUsuario());/*, usuarioActual.getPassword(), usuarioActual.getNombre(), usuarioActual.getApellido(), usuarioActual.getEmail(), usuarioActual.getTelefono(), usuarioActual.getDireccion(), usuarioActual.getNumero(), usuarioActual.getLocalidad(), usuarioActual.getDepartamento());*/
        //holder.bind(usuarioActual.getNombre());
        //holder.bind(usuarioActual.getUsuario());


    }

    static class UsuarioDiff extends DiffUtil.ItemCallback<Usuario>{
        @Override
        public boolean areItemsTheSame(@NonNull Usuario oldItem, @NonNull Usuario newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Usuario oldItem, @NonNull Usuario newItem) {
            return oldItem.getUsuario().equals(newItem.getUsuario());
        }
    }
}
