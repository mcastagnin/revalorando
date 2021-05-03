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
import com.bit.revalorando.models.ArticuloViewModel;
import com.bit.revalorando.models.TruequeViewModel;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;


public class ListarOfertasRecibidasActivity extends OptionsMenuActivity implements NavigationView.OnNavigationItemSelectedListener{

    //private ArticuloTruequeViewModel truequeArticuloViewModel;
    private TruequeViewModel truequeViewModel;
    private ArticuloViewModel articuloViewModel;


    public static final int NEW_ARTICULO_REQ_CODE = 1;
    public static final int UPDATE_ARTICULO_REQ_CODE = 2;
    VariablesLogin vLogin = VariablesLogin.getInstance();
    public int idUsuario = vLogin.idUsuarioGlobal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_ofertas_recibidas);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewMisOfertasRecibidas);
        final MisTruequesListAdapter adapterA = new MisTruequesListAdapter(new ArticuloListAdapter.ArticuloDiff());
        recyclerView.setAdapter(adapterA);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        articuloViewModel = new ViewModelProvider(this, new MisTruequesFactory(getApplication())).get(ArticuloViewModel.class);

        articuloViewModel.findTruequesOfertados().observe(this, articulos -> {

            adapterA.submitList(articulos);
        });


        final TruequeListAdapter adapterT = new TruequeListAdapter(new TruequeListAdapter.TruequeDiff());
        truequeViewModel = new ViewModelProvider(this, new TruequeFactory(getApplication())).get(TruequeViewModel.class);

        truequeViewModel.getTrueques().observe(this, trueques -> {

            adapterT.submitList(trueques);
        });



        FloatingActionButton fab = findViewById(R.id.btnAgregar);
        fab.setOnClickListener( view -> {
            Intent intent = new Intent(ListarOfertasRecibidasActivity.this, AgregarArticuloActivity.class);

            startActivityForResult(intent, NEW_ARTICULO_REQ_CODE);
        });

        adapterA.setOnItemClickListener(new MisTruequesListAdapter.OnItemClickListener() {
            @Override
            public void onItemDelete(Articulo articulo) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ListarOfertasRecibidasActivity.this);
                builder.setMessage(R.string.msg_borrar);
                builder.setTitle("Eliminar Trueque disponible");

                builder.setPositiveButton(R.string.si, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        articulo.setEstado("d");
                        Log.d("articuloID",articulo.getId()+"");
                        truequeViewModel.deleteTruequeByArticuloId(articulo.getId());
                        articuloViewModel.update(articulo);

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
                Intent intent = new Intent(ListarOfertasRecibidasActivity.this, OfertaActivity.class);
                intent.putExtra(OfertaActivity.EXTRA_MSG_ID, articulo.getId());
                Log.d("id?", articulo.getId()+"");
                /*
                intent.putExtra(AgregarArticuloActivity.EXTRA_MSG_NOMBRE, articulo.getNombre());
                intent.putExtra(AgregarArticuloActivity.EXTRA_MSG_DESCRIPCION, articulo.getDescripcion());
                intent.putExtra(AgregarArticuloActivity.EXTRA_MSG_FOTO, articulo.getFoto());
                intent.putExtra(AgregarArticuloActivity.EXTRA_MSG_ID, articulo.getId());
*/finish();
               startActivity(intent);


            }

            @Override
            public void onItemPublicar(Articulo articulo) {

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
            case R.id.nav_usuario:
                break;
            case R.id.nav_trueque:
                Intent intentT = new Intent(ListarOfertasRecibidasActivity.this, ListarOfertasRecibidasActivity.class);

                startActivity(intentT);                break;
            case R.id.nav_perfil:
                Intent intentP = new Intent(ListarOfertasRecibidasActivity.this, AgregarUsuarioActivity.class);

                startActivity(intentP);
                break;
            case R.id.nav_articulo:
                Intent intentLU = new Intent(ListarOfertasRecibidasActivity.this, ListarArticuloActivity.class);


                startActivity(intentLU);
                //Toast.makeText(getApplicationContext(), R.string.menu_articulo, Toast.LENGTH_LONG).show();
                break;
            default:
                throw new IllegalArgumentException("Opción no existente");
        }

        return true;
    }

}