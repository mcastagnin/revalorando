package com.bit.revalorando;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bit.revalorando.entities.Articulo;
import com.bit.revalorando.entities.Trueque;
import com.bit.revalorando.models.ArticuloViewModel;
import com.bit.revalorando.models.TruequeViewModel;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;


public class ListarSeleccionActivity extends OptionsMenuActivity implements NavigationView.OnNavigationItemSelectedListener{

    private ArticuloViewModel articuloViewModel;
    private TruequeViewModel truequeViewModel;

//    public static final String EXTRA_MSG_ID = "com.bit.revalorando.MSG_GUARDAR_ID";
    public static final String EXTRA_MSG_NOMBRE = "com.bit.revalorando.MSG_GUARDAR_NOMBRE";
    public static final String EXTRA_MSG_DESCRIPCION = "com.bit.revalorando.MSG_GUARDAR_DESCRIPCION";
    public static final String EXTRA_MSG_FOTO = "com.bit.revalorando.MSG_GUARDAR_FOTO";

    public static final int NEW_ARTICULO_REQ_CODE = 1;
    public static final int UPDATE_ARTICULO_REQ_CODE = 2;
    public static final String EXTRA_MSG_ARTICULO_ID = "com.bit.revalorando.MSG_GUARDAR_ID";

    //public static final int EXTRA_MSG_ARTICULO_ID = -1;

    VariablesLogin vLogin = VariablesLogin.getInstance();
    public int idUsuario = vLogin.idUsuarioGlobal;

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


        final TruequeListAdapter adapterT = new TruequeListAdapter(new TruequeListAdapter.TruequeDiff());
        truequeViewModel = new ViewModelProvider(this, new TruequeFactory(getApplication())).get(TruequeViewModel.class);

        truequeViewModel.getTrueques().observe(this, trueques -> {

            adapterT.submitList(trueques);
        });




        adapter.setOnItemClickListener(new ArticuloListAdapter.OnItemClickListener() {

            @Override
            public void OnItemClick(Articulo articulo) {

                Intent respuesta = new Intent();
                /*
                int id = getIntent().getIntExtra(EXTRA_MSG_ARTICULO_ID, -1);
                if(id != -1){
                    respuesta.putExtra(EXTRA_MSG_ARTICULO_ID, id);
                }*/
                setResult(RESULT_OK, respuesta);
                respuesta.putExtra(EXTRA_MSG_ARTICULO_ID, articulo.getId());
                finish();
                Log.d("id nart oferta:", articulo.getId()+"");

            }

            @Override
            public void onItemDelete(Articulo articulo) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ListarSeleccionActivity.this);
                builder.setMessage(R.string.msg_borrar);
                builder.setTitle(R.string.titulo_borrar);

                builder.setPositiveButton(R.string.si, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        articuloViewModel.delete(articulo);
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

            }
/*
            @Override
            public void OnItemClick(Articulo articulo) {


                    Intent respuesta = new Intent();
                    if(TextUtils.isEmpty("")){
                        setResult(RESULT_CANCELED, respuesta);
                    } else {
                        /*
                        String articulo = editTextNombre.getText().toString();
                        String descripcion = editTextDescripcion.getText().toString();
                        String url = editTextImagen.getText().toString();
                        respuesta.putExtra(EXTRA_MSG_NOMBRE, articulo);
                        respuesta.putExtra(EXTRA_MSG_DESCRIPCION, descripcion);
                        respuesta.putExtra(EXTRA_MSG_FOTO, url);

                        int id = getIntent().getIntExtra(EXTRA_MSG_ARTICULO_ID, -1);
                        if(id != -1){
                            respuesta.putExtra(EXTRA_MSG_ARTICULO_ID, id);
                        }
                        setResult(RESULT_OK, respuesta);
                    }
                    Log.d("id art inter", EXTRA_MSG_ARTICULO_ID);
                    finish();


                Intent intent = new Intent(ListarSeleccionActivity.this, AgregarArticuloActivity.class);
                intent.putExtra(AgregarArticuloActivity.EXTRA_MSG_NOMBRE, articulo.getNombre());
                intent.putExtra(AgregarArticuloActivity.EXTRA_MSG_DESCRIPCION, articulo.getDescripcion());
                intent.putExtra(AgregarArticuloActivity.EXTRA_MSG_FOTO, articulo.getFoto());
                intent.putExtra(AgregarArticuloActivity.EXTRA_MSG_ID, articulo.getId());

                startActivityForResult(intent, UPDATE_ARTICULO_REQ_CODE);
            }*/

            @Override
            public void onItemPublicar(Articulo articulo) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ListarSeleccionActivity.this);
                builder.setMessage("Desea publicar el artículo para ser intercambiado?");
                builder.setTitle("Publicar el Artículo");

                builder.setPositiveButton(R.string.si, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Trueque trueque = new Trueque();
                        trueque.setEstado("d");
                        trueque.setIdArticulo1(articulo.getId());
                        trueque.setIdUsuario1(idUsuario);
                        trueque.setIdArticulo2(-1);
                        trueque.setIdUsuario2(-1);
                        truequeViewModel.insert(trueque);
                        articulo.setEstado("n");
                        articuloViewModel.update(articulo);


                        //articuloViewModel.delete(articulo);
                        Toast.makeText(getApplicationContext(), "Artículo publicado", Toast.LENGTH_LONG).show();
                       // Toast.makeText(getApplicationContext(), "" + articulo.getId(), Toast.LENGTH_LONG).show();

                    }
                });
                builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Artículo no publicado", Toast.LENGTH_LONG).show();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
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

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListarSeleccionActivity.super.onBackPressed();
            }
        });




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
                Intent intentT = new Intent(ListarSeleccionActivity.this, ListarMisTruequesActivity.class);
                finish();
                startActivity(intentT);                break;
            case R.id.nav_perfil:
                Intent intentP = new Intent(ListarSeleccionActivity.this, AgregarUsuarioActivity.class);
                finish();

                startActivity(intentP);
                break;
            case R.id.nav_articulo:
                Intent intentLU = new Intent(ListarSeleccionActivity.this, ListarSeleccionActivity.class);
                finish();

                startActivity(intentLU);
                //Toast.makeText(getApplicationContext(), R.string.menu_articulo, Toast.LENGTH_LONG).show();
                break;
            default:
                throw new IllegalArgumentException("Opción no existente");
        }

        return true;
    }

}