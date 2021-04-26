package com.bit.revalorando;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bit.revalorando.entities.Articulo;
import com.bit.revalorando.entities.Trueque;
import com.bit.revalorando.models.ArticuloTruequeViewModel;
import com.bit.revalorando.models.ArticuloViewModel;
import com.bit.revalorando.models.TruequeViewModel;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;


public class ListarMisTruequesActivity extends OptionsMenuActivity implements NavigationView.OnNavigationItemSelectedListener{

    private ArticuloTruequeViewModel truequeArticuloViewModel;
    private TruequeViewModel truequeViewModel;
    private ArticuloViewModel articuloViewModel;


    public static final int NEW_ARTICULO_REQ_CODE = 1;
    public static final int UPDATE_ARTICULO_REQ_CODE = 2;
    VariablesLogin vLogin = VariablesLogin.getInstance();
    public int idUsuario = vLogin.idUsuarioGlobal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_mis_trueques);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewMisTruequesDisponibles);
        final ArticuloTruequeListAdapter adapter = new ArticuloTruequeListAdapter(new ArticuloTruequeListAdapter.ArticuloDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        truequeArticuloViewModel = new ViewModelProvider(this, new ArticuloTruequeFactory(getApplication(),"")).get(ArticuloTruequeViewModel.class);

        truequeArticuloViewModel.findMisTruequesDisponibles().observe(this, articulos -> {

            adapter.submitList(articulos);
        });


        final TruequeListAdapter adapterT = new TruequeListAdapter(new TruequeListAdapter.TruequeDiff());
        truequeViewModel = new ViewModelProvider(this, new TruequeFactory(getApplication())).get(TruequeViewModel.class);

        truequeViewModel.getTrueques().observe(this, trueques -> {

            adapterT.submitList(trueques);
        });

        final ArticuloListAdapter adapterA = new ArticuloListAdapter(new ArticuloListAdapter.ArticuloDiff());
        articuloViewModel = new ViewModelProvider(this, new ArticuloFactory(getApplication())).get(ArticuloViewModel.class);

        articuloViewModel.getArticulos().observe(this, articulos -> {

            adapterA.submitList(articulos);
        });


        FloatingActionButton fab = findViewById(R.id.btnAgregar);
        fab.setOnClickListener( view -> {
            Intent intent = new Intent(ListarMisTruequesActivity.this, AgregarArticuloActivity.class);

            startActivityForResult(intent, NEW_ARTICULO_REQ_CODE);
        });

        adapter.setOnItemClickListener(new ArticuloTruequeListAdapter.OnItemClickListener() {
            @Override
            public void onItemDelete(Articulo articulo) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ListarMisTruequesActivity.this);
                builder.setMessage(R.string.msg_borrar);
                builder.setTitle("Eliminar Trueque disponible");

                builder.setPositiveButton(R.string.si, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        articulo.setEstado("n");
                        articuloViewModel.update(articulo);
                        truequeViewModel.deleteTruequeByArticuloId(articulo.getId());
                        //articuloViewModel.delete(articulo);
                        Toast.makeText(getApplicationContext(), "Se ha eliminado el trueque", Toast.LENGTH_LONG).show();
                    }
                });
                builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "No se ha eliminado el trueque", Toast.LENGTH_LONG).show();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

            }

            @Override
            public void OnItemClick(Articulo articulo) {
                Intent intent = new Intent(ListarMisTruequesActivity.this, AgregarArticuloActivity.class);
                intent.putExtra(AgregarArticuloActivity.EXTRA_MSG_NOMBRE, articulo.getNombre());
                intent.putExtra(AgregarArticuloActivity.EXTRA_MSG_DESCRIPCION, articulo.getDescripcion());
                intent.putExtra(AgregarArticuloActivity.EXTRA_MSG_FOTO, articulo.getFoto());
                intent.putExtra(AgregarArticuloActivity.EXTRA_MSG_ID, articulo.getId());

                startActivityForResult(intent, UPDATE_ARTICULO_REQ_CODE);
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

        navigationView.getMenu().findItem(R.id.nav_usuario).setTitle(vLogin.usuarioGlobal);

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==NEW_ARTICULO_REQ_CODE && resultCode == RESULT_OK){
            Articulo articulo = new Articulo();
            articulo.setNombre(data.getStringExtra(AgregarArticuloActivity.EXTRA_MSG_NOMBRE));
            articulo.setDescripcion(data.getStringExtra(AgregarArticuloActivity.EXTRA_MSG_DESCRIPCION));
            articulo.setFoto(data.getStringExtra(AgregarArticuloActivity.EXTRA_MSG_FOTO));
            articulo.setCategoria(1);
            articulo.setEstado("d");

            /*articulo.setCategoria(data.getIntExtra(AgregarArticuloActivity.EXTRA_MSG_CATEGORIA,0));
            articulo.setCondicion(data.getStringExtra(AgregarArticuloActivity.EXTRA_MSG_CONDICION));
            articulo.setEstado(data.getStringExtra(AgregarArticuloActivity.EXTRA_MSG_ESTADO));*/
            articulo.setIdUsuario(idUsuario);
            articuloViewModel.insert(articulo);

        } else if ( requestCode == UPDATE_ARTICULO_REQ_CODE && resultCode == RESULT_OK){
            int id = data.getIntExtra(AgregarArticuloActivity.EXTRA_MSG_ID, -1);
            if (id == -1 ){
                Toast.makeText(getApplicationContext(), R.string.no_eliminado_articulo, Toast.LENGTH_LONG).show();
            }


            Articulo articulo = new Articulo();
            articulo.setId(data.getIntExtra(AgregarArticuloActivity.EXTRA_MSG_ID,-1));
            articulo.setNombre(data.getStringExtra(AgregarArticuloActivity.EXTRA_MSG_NOMBRE));
            articulo.setDescripcion(data.getStringExtra(AgregarArticuloActivity.EXTRA_MSG_DESCRIPCION));
            articulo.setFoto(data.getStringExtra(AgregarArticuloActivity.EXTRA_MSG_FOTO));
            articulo.setIdUsuario(idUsuario);
            articulo.setCategoria(1);
            articulo.setEstado("d");

            Log.d("info articulo", articulo.getNombre());
            articuloViewModel.update(articulo);


        } else {
            Toast.makeText(getApplicationContext(), R.string.no_eliminado_articulo, Toast.LENGTH_LONG).show();
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