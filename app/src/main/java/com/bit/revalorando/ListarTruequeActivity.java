package com.bit.revalorando;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bit.revalorando.entities.Articulo;
import com.bit.revalorando.models.ArticuloTruequeViewModel;
import com.google.android.material.appbar.MaterialToolbar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.SearchView;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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
import androidx.appcompat.widget.SearchView;

public class ListarTruequeActivity extends OptionsMenuActivity implements NavigationView.OnNavigationItemSelectedListener, SearchView.OnQueryTextListener {

    private ArticuloTruequeViewModel articuloTruequeViewModel;
    public static final int NEW_ARTICULO_REQ_CODE = 1;
    private SearchView svBarraBusqueda;
    VariablesLogin vLogin = VariablesLogin.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_trueque_articulo);


        RecyclerView recyclerView = findViewById(R.id.recyclerViewTrueques);
        final ArticuloTruequeListAdapter adapter = new ArticuloTruequeListAdapter(new ArticuloTruequeListAdapter.ArticuloDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        articuloTruequeViewModel = new ViewModelProvider(this, new ArticuloTruequeFactory(getApplication(),"")).get(ArticuloTruequeViewModel.class);

        articuloTruequeViewModel.findTruequesDisponibles().observe(this, articulos -> {

            adapter.submitList(articulos);
        });
        svBarraBusqueda = findViewById(R.id.barraBusquedaTrueques);
        if(vLogin.busquedaGlobal != ""){
            svBarraBusqueda.setQuery(vLogin.busquedaGlobal.toString(), false);

        }
        initListener();


        FloatingActionButton fab = findViewById(R.id.btnAgregar);
        fab.setOnClickListener( view -> {
            Intent intent = new Intent(ListarTruequeActivity.this, AgregarArticuloActivity.class);

            startActivityForResult(intent, NEW_ARTICULO_REQ_CODE);
        });


        adapter.setOnItemClickListener(new ArticuloTruequeListAdapter.OnItemClickListener() {
            @Override
            public void onItemDelete(Articulo articulo) {
                /*
                AlertDialog.Builder builder = new AlertDialog.Builder(ListarTruequeActivity.this);
                builder.setMessage(R.string.msg_borrar);
                builder.setTitle(R.string.titulo_borrar);

                builder.setPositiveButton(R.string.si, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       // articuloTruequeViewModel.delete(articulo);
                        Toast.makeText(getApplicationContext(), R.string.eliminado_articulo, Toast.LENGTH_LONG).show();
                    }
                });
                builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), R.string.no_eliminado_articulo, Toast.LENGTH_LONG).show();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
*/
            }
            @Override
            public void OnItemClick(Articulo articulo) {

                Intent intent = new Intent(ListarTruequeActivity.this, DescripcionArticuloActivity.class);
                intent.putExtra(AgregarArticuloActivity.EXTRA_MSG_NOMBRE, articulo.getNombre());
                intent.putExtra(AgregarArticuloActivity.EXTRA_MSG_DESCRIPCION, articulo.getDescripcion());
                intent.putExtra(AgregarArticuloActivity.EXTRA_MSG_FOTO, articulo.getFoto());
                intent.putExtra(AgregarArticuloActivity.EXTRA_MSG_ID, articulo.getId());

                startActivity(intent);
                /*Articulo art = arti;
                int id = art.getId();
                String idString = "" + id;
                Toast.makeText(getApplicationContext(),idString, Toast.LENGTH_LONG).show();*/

            }
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

        VariablesLogin variablesLogin = VariablesLogin.getInstance();
        navigationView.getMenu().findItem(R.id.nav_usuario).setTitle(variablesLogin.usuarioGlobal);

        //updateMenuTitles();

    }



/*
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.nav_usuario);
        VariablesLogin vLogin = VariablesLogin.getInstance();
        item.setTitle(vLogin.usuarioGlobal);


        return super.onPrepareOptionsMenu(menu);
    }*/

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==NEW_ARTICULO_REQ_CODE && resultCode == RESULT_OK){
            Articulo articulo = new Articulo();
            articulo.setNombre(data.getStringExtra(AgregarArticuloActivity.EXTRA_MSG_NOMBRE));
            articulo.setDescripcion(data.getStringExtra(AgregarArticuloActivity.EXTRA_MSG_DESCRIPCION));
            articulo.setFoto(data.getStringExtra(AgregarArticuloActivity.EXTRA_MSG_FOTO));
            /*articulo.setCategoria(data.getIntExtra(AgregarArticuloActivity.EXTRA_MSG_CATEGORIA,0));
            articulo.setCondicion(data.getStringExtra(AgregarArticuloActivity.EXTRA_MSG_CONDICION));
            articulo.setEstado(data.getStringExtra(AgregarArticuloActivity.EXTRA_MSG_ESTADO));
            articulo.setIdUsuario(data.getIntExtra(AgregarArticuloActivity.EXTRA_MSG_IDUSUARIO,0));
*/




            articuloTruequeViewModel.insert(articulo);
        } else {
            Toast.makeText(getApplicationContext(), R.string.no_guardado, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_usuario:
                break;
            case R.id.nav_trueque:
                Toast.makeText(getApplicationContext(), R.string.menu_trueque, Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_perfil:
                Intent intentP = new Intent(ListarTruequeActivity.this, AgregarUsuarioActivity.class);

                startActivity(intentP);
                break;
            case R.id.nav_articulo:
                Intent intentLU = new Intent(ListarTruequeActivity.this, ListarArticuloActivity.class);

                startActivity(intentLU);
                //Toast.makeText(getApplicationContext(), R.string.menu_articulo, Toast.LENGTH_LONG).show();
                break;
            default:
                throw new IllegalArgumentException("Opción no existente");
        }

        return true;
    }

    private void initListener(){
        svBarraBusqueda.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        //vLogin.busquedaGlobal = svBarraBusqueda.getQuery().toString();

        vLogin.busquedaGlobal = svBarraBusqueda.getQuery().toString();
        Log.d("veremos:",vLogin.busquedaGlobal);
        Intent intentLT = new Intent(ListarTruequeActivity.this, ListarTruequeActivity.class);

        finish();
        startActivity(getIntent());
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

/*
        setContentView(R.layout.activity_listar_trueque_articulo);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewTrueques);
        final ArticuloTruequeListAdapter adapter = new ArticuloTruequeListAdapter(new ArticuloTruequeListAdapter.ArticuloDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        articuloTruequeViewModel = new ViewModelProvider(this, new ArticuloTruequeFactory(getApplication(),"")).get(ArticuloTruequeViewModel.class);

        articuloTruequeViewModel.findTruequesDisponibles().observe(this, articulos -> {

            adapter.submitList(articulos);
        });*/

        return false;
    }
}