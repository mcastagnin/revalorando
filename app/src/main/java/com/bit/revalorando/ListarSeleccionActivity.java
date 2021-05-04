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

    public static final String EXTRA_MSG_ID = "com.bit.revalorando.MSG_GUARDAR_ID";;
    public static final String EXTRA_MSG_NOMBRE = "com.bit.revalorando.MSG_GUARDAR_NOMBRE";
    public static final String EXTRA_MSG_DESCRIPCION = "com.bit.revalorando.MSG_GUARDAR_DESCRIPCION";
    public static final String EXTRA_MSG_FOTO = "com.bit.revalorando.MSG_GUARDAR_FOTO";
    public static final String EXTRA_MSG_TRUEQUE_ID = "com.bit.revalorando.MSG_GUARDAR_TRUEQUE_ID";;
    private int idTrueque = -1;


    public static final int NEW_ARTICULO_REQ_CODE = 1;
    public static final int UPDATE_ARTICULO_REQ_CODE = 2;
    public static final String EXTRA_MSG_ARTICULO_ID = "com.bit.revalorando.MSG_GUARDAR_ARTICULO_ID";

    //public static final int EXTRA_MSG_ARTICULO_ID = -1;

    VariablesLogin vLogin = VariablesLogin.getInstance();
    public int idUsuario = vLogin.idUsuarioGlobal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_seleccion_articulos);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewSeleccionArticulos);
        final MisTruequesListAdapter adapterA = new MisTruequesListAdapter(new ArticuloListAdapter.ArticuloDiff());
        recyclerView.setAdapter(adapterA);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        articuloViewModel = new ViewModelProvider(this, new MisTruequesFactory(getApplication())).get(ArticuloViewModel.class);

        articuloViewModel.getArticulos().observe(this, articulos -> {

            adapterA.submitList(articulos);
        });


        idTrueque = getIntent().getIntExtra(EXTRA_MSG_TRUEQUE_ID, -1);

        //EXTRA_MSG_ID = getIntent().getIntExtra(EXTRA_MSG_ID, -1);
/*
        Button btnPublicar= findViewById(R.id.buttonPublicar);
        btnPublicar.setVisibility(View.GONE);

        ImageView iBtnDelete = findViewById(R.id.imageButtonDelete);
        iBtnDelete.setVisibility(View.GONE);
*/

        adapterA.setOnItemClickListener(new MisTruequesListAdapter.OnItemClickListener() {

            @Override
            public void OnItemClick(Articulo articulo) {

                AlertDialog.Builder builder = new AlertDialog.Builder(ListarSeleccionActivity.this);
                builder.setMessage("Desea ofertar con el artículo seleccionado?");
                builder.setTitle("Oferta");

                builder.setPositiveButton(R.string.si, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        Intent respuesta = new Intent();

                /*
                if(id != -1){
                    respuesta.putExtra(EXTRA_MSG_ID, id);
                }*/
                        setResult(RESULT_OK, respuesta);
                        respuesta.putExtra(EXTRA_MSG_ARTICULO_ID, articulo.getId());
                        respuesta.putExtra(EXTRA_MSG_TRUEQUE_ID, idTrueque);

                        //respuesta.putExtra(EXTRA_MSG_ID, );
                        finish();
                        //Log.d("id nart oferta:", articulo.getId()+"");

                }
                });
                builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();



            }

            @Override
            public void onItemDelete(Articulo articulo) {


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

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListarSeleccionActivity.super.onBackPressed();
            }
        });




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