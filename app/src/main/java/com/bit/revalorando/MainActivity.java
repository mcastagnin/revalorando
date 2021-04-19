package com.bit.revalorando;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.bit.revalorando.entities.Usuario;
import com.bit.revalorando.models.UsuarioViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private UsuarioViewModel usuarioViewModel;
    public static final int NEW_USUARIO_REQ_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewUsuarios);
        final UsuarioListAdapter adapter = new UsuarioListAdapter(new UsuarioListAdapter.UsuarioDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        usuarioViewModel = new ViewModelProvider(this, new UsuarioFactory(getApplication())).get(UsuarioViewModel.class);

        usuarioViewModel.getUsuarios().observe(this, usuarios -> {

            adapter.submitList(usuarios);
        });

        FloatingActionButton fab = findViewById(R.id.btnAgregar);
        fab.setOnClickListener( view -> {
            Intent intent = new Intent(MainActivity.this, AgregarUsuarioActivity.class);

            startActivityForResult(intent, NEW_USUARIO_REQ_CODE);
        });

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==NEW_USUARIO_REQ_CODE && resultCode == RESULT_OK){
            Usuario usuario = new Usuario();
            usuario.setUsuario(data.getStringExtra(AgregarUsuarioActivity.EXTRA_MSG_USUARIO));
            usuario.setPassword(data.getStringExtra(AgregarUsuarioActivity.EXTRA_MSG_PASSWORD));
            usuario.setNombre(data.getStringExtra(AgregarUsuarioActivity.EXTRA_MSG_NOMBRE));
            usuario.setApellido(data.getStringExtra(AgregarUsuarioActivity.EXTRA_MSG_APELLIDO));
            usuario.setEmail(data.getStringExtra(AgregarUsuarioActivity.EXTRA_MSG_EMAIL));
            usuario.setTelefono(data.getIntExtra(AgregarUsuarioActivity.EXTRA_MSG_TELEFONO,0));
            usuario.setDireccion(data.getStringExtra(AgregarUsuarioActivity.EXTRA_MSG_DIRECCION));
            usuario.setNumero(data.getStringExtra(AgregarUsuarioActivity.EXTRA_MSG_NUMERO));
            usuario.setLocalidad(data.getStringExtra(AgregarUsuarioActivity.EXTRA_MSG_LOCALIDAD));
            usuario.setDepartamento(data.getStringExtra(AgregarUsuarioActivity.EXTRA_MSG_DEPARTAMENTO));


            usuarioViewModel.insert(usuario);
        } else {
            Toast.makeText(getApplicationContext(), R.string.no_registrado, Toast.LENGTH_LONG).show();
        }
    }

}