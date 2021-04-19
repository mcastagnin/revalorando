package com.bit.revalorando;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class UsuarioViewHolder extends RecyclerView.ViewHolder {
    private final TextView usuarioItemView;
   /* private final TextView passwordItemView;
    private final TextView nombreItemView;
    private final TextView apellidoItemView;
    private final TextView emailItemView;
    private final TextView telefonoItemView;
    private final TextView direccionItemView;
    private final TextView numeroItemView;
    private final TextView localidadItemView;
    private final TextView departamentoItemView;*/

    private UsuarioViewHolder(View itemView){
        super(itemView);
        usuarioItemView = itemView.findViewById(R.id.textViewUsuario);
       /*passwordItemView = itemView.findViewById(R.id.textViewIngresarPassword);
        nombreItemView = itemView.findViewById(R.id.textViewIngresarNombre);
        apellidoItemView = itemView.findViewById(R.id.textViewIngresarApellido);
        emailItemView = itemView.findViewById(R.id.textViewIngresarEmail);
        telefonoItemView = itemView.findViewById(R.id.textViewIngresarTelefono);
        direccionItemView = itemView.findViewById(R.id.textViewIngresarDireccion);
        numeroItemView = itemView.findViewById(R.id.textViewIngresarNumero);
        localidadItemView = itemView.findViewById(R.id.textViewIngresarLocalidad);
        departamentoItemView = itemView.findViewById(R.id.textViewIngresarDepartamento);*/

    }

    public void bind(String usuario)/*, String password, String nombre, String apellido, String email, String telefono, String direccion, String numero, String localidad, String departamento)*/{
        usuarioItemView.setText(usuario);/*
        passwordItemView.setText(password);
        nombreItemView.setText(nombre);
        apellidoItemView.setText(apellido);
        emailItemView.setText(email);
        telefonoItemView.setText(telefono);
        direccionItemView.setText(direccion);
        numeroItemView.setText(numero);
        localidadItemView.setText(localidad);
        departamentoItemView.setText(departamento);*/

    }

    static UsuarioViewHolder create(ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.usuario_item, parent, false);
        return new UsuarioViewHolder(view);
    }
}

