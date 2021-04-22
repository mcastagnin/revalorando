package com.bit.revalorando;

import androidx.annotation.NonNull;

import com.google.android.material.appbar.MaterialToolbar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import android.view.MenuItem;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bit.revalorando.entities.Usuario;
import com.bit.revalorando.models.UsuarioViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ListarUsuarioActivity extends OptionsMenuActivity implements NavigationView.OnNavigationItemSelectedListener{

    private UsuarioViewModel usuarioViewModel;
    public static final int NEW_USUARIO_REQ_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_usuario2);

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
            Intent intent = new Intent(ListarUsuarioActivity.this, AgregarUsuarioActivity.class);

            startActivityForResult(intent, NEW_USUARIO_REQ_CODE);
        });

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setSubtitle(R.string.app_subtitle);

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

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
            usuario.setTelefono(data.getStringExtra(AgregarUsuarioActivity.EXTRA_MSG_TELEFONO));
            usuario.setDireccion(data.getStringExtra(AgregarUsuarioActivity.EXTRA_MSG_DIRECCION));
            usuario.setNumero(data.getStringExtra(AgregarUsuarioActivity.EXTRA_MSG_NUMERO));
            usuario.setLocalidad(data.getStringExtra(AgregarUsuarioActivity.EXTRA_MSG_LOCALIDAD));
            usuario.setDepartamento(data.getStringExtra(AgregarUsuarioActivity.EXTRA_MSG_DEPARTAMENTO));


            usuarioViewModel.insert(usuario);
        } else {
            Toast.makeText(getApplicationContext(), R.string.no_registrado, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_trueque:
                Toast.makeText(getApplicationContext(), R.string.menu_trueque, Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_perfil:
                Toast.makeText(getApplicationContext(), R.string.menu_perfil, Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_articulo:
                Intent intent = new Intent(ListarUsuarioActivity.this, ListarArticuloActivity.class);

                startActivity(intent);
                //Toast.makeText(getApplicationContext(), R.string.menu_articulo, Toast.LENGTH_LONG).show();
                break;
            default:
                throw new IllegalArgumentException("Opcion no existente");
        }

        return true;
    }

}