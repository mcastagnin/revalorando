package com.bit.revalorando;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bit.revalorando.entities.Articulo;
import com.google.android.material.appbar.MaterialToolbar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bit.revalorando.entities.Articulo;
import com.bit.revalorando.models.ArticuloViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ListarArticuloActivity extends OptionsMenuActivity implements NavigationView.OnNavigationItemSelectedListener{

    private ArticuloViewModel articuloViewModel;
    public static final int NEW_ARTICULO_REQ_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_articulo);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewArticulos);
        final ArticuloListAdapter adapter = new ArticuloListAdapter(new ArticuloListAdapter.ArticuloDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        articuloViewModel = new ViewModelProvider(this, new ArticuloFactory(getApplication())).get(ArticuloViewModel.class);

        articuloViewModel.getArticulos().observe(this, articulos -> {

            adapter.submitList(articulos);
        });

        FloatingActionButton fab = findViewById(R.id.btnAgregar);
        fab.setOnClickListener( view -> {
            Intent intent = new Intent(ListarArticuloActivity.this, AgregarArticuloActivity.class);

            startActivityForResult(intent, NEW_ARTICULO_REQ_CODE);
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

        if(requestCode==NEW_ARTICULO_REQ_CODE && resultCode == RESULT_OK){
            Articulo articulo = new Articulo();
            articulo.setNombre(data.getStringExtra(AgregarArticuloActivity.EXTRA_MSG_NOMBRE));
            articulo.setDescripcion(data.getStringExtra(AgregarArticuloActivity.EXTRA_MSG_DESCRIPCION));
            /*articulo.setFoto(data.getStringExtra(AgregarArticuloActivity.EXTRA_MSG_FOTO));
            articulo.setCategoria(data.getIntExtra(AgregarArticuloActivity.EXTRA_MSG_CATEGORIA,0));
            articulo.setCondicion(data.getStringExtra(AgregarArticuloActivity.EXTRA_MSG_CONDICION));
            articulo.setEstado(data.getStringExtra(AgregarArticuloActivity.EXTRA_MSG_ESTADO));
            articulo.setIdUsuario(data.getIntExtra(AgregarArticuloActivity.EXTRA_MSG_IDUSUARIO,0));
*/




            articuloViewModel.insert(articulo);
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
                Toast.makeText(getApplicationContext(), R.string.menu_articulo, Toast.LENGTH_LONG).show();
                break;
            default:
                throw new IllegalArgumentException("Opcion no existente");
        }

        return true;
    }

}