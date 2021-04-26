package com.bit.revalorando;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.bit.revalorando.entities.Articulo;
import com.bit.revalorando.entities.Trueque;
import com.bit.revalorando.models.ArticuloViewModel;
import com.bit.revalorando.models.TruequeViewModel;
import com.google.android.material.appbar.MaterialToolbar;
import com.squareup.picasso.Picasso;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.squareup.picasso.Picasso;


public class DescripcionArticuloActivity extends OptionsMenuActivity {
    public static final String EXTRA_MSG_ID = "com.bit.revalorando.MSG_GUARDAR_ID";
    public static final String EXTRA_MSG_NOMBRE = "com.bit.revalorando.MSG_GUARDAR_NOMBRE";
    public static final String EXTRA_MSG_DESCRIPCION = "com.bit.revalorando.MSG_GUARDAR_DESCRIPCION";
    public static final String EXTRA_MSG_FOTO = "com.bit.revalorando.MSG_GUARDAR_FOTO";
    public static final String EXTRA_MSG_ID_USUARIO = "com.bit.revalorando.MSG_GUARDAR_ID_USUARIO";

    private static final int OFERTAR_TRUEQUE_REQ_CODE = 1;

    private TextView editTextNombre;
    private TextView editTextDescripcion;
    private ImageView imagenView;
    VariablesLogin vLogin = VariablesLogin.getInstance();

    private ArticuloViewModel articuloViewModel;
    private TruequeViewModel truequeViewModel;


//    private EditText editTextImagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descripcion_articulo);

        editTextNombre = findViewById(R.id.textViewIngresarNombre);
        editTextDescripcion = findViewById(R.id.textViewIngresarDescripcion);
        imagenView =  findViewById(R.id.imagenArticulo);

        // editTextImagen = findViewById(R.id.textViewIngresarImagen);

        final Button btnIntercambiar = findViewById(R.id.btnIntercambiar);
        btnIntercambiar.setOnClickListener(view -> {
            Intent respuesta = new Intent();
            if(TextUtils.isEmpty(editTextNombre.getText())){
                setResult(RESULT_CANCELED, respuesta);
            } else {
                String articulo = editTextNombre.getText().toString();
                String descripcion = editTextDescripcion.getText().toString();
               // String url = editTextImagen.getText().toString();
                respuesta.putExtra(EXTRA_MSG_NOMBRE, articulo);
                respuesta.putExtra(EXTRA_MSG_DESCRIPCION, descripcion);
                //respuesta.putExtra(EXTRA_MSG_FOTO, url);

                int id = getIntent().getIntExtra(EXTRA_MSG_ID, -1);
                if(id != -1){
                    respuesta.putExtra(EXTRA_MSG_ID, id);
                }
                setResult(RESULT_OK, respuesta);
            }
            finish();
        });

        Intent intent = getIntent();

        if(intent.hasExtra(EXTRA_MSG_ID)){
            String url = intent.getStringExtra(EXTRA_MSG_FOTO);
            Picasso.with(getApplicationContext())
                    .load(url)
                    .centerCrop()
                    .fit()
                    .into(imagenView);
            editTextNombre.setText(intent.getStringExtra(EXTRA_MSG_NOMBRE));
            editTextDescripcion.setText(intent.getStringExtra(EXTRA_MSG_DESCRIPCION));
           // editTextImagen.setText(intent.getStringExtra(EXTRA_MSG_FOTO));
        } else {
            //editTextImagen.setText("https://cdn.icon-icons.com/icons2/2534/PNG/128/product_delivery_icon_152013.png");
        }

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setSubtitle(R.string.app_subtitle);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DescripcionArticuloActivity.super.onBackPressed();
            }
        });

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if ( requestCode == OFERTAR_TRUEQUE_REQ_CODE && resultCode == RESULT_OK){
            int idArticulo2 = data.getIntExtra(ListarArticuloActivity.EXTRA_MSG_ARTICULO_ID, -1);
            if (idArticulo2 == -1 ){
                Toast.makeText(getApplicationContext(), "No se ha realizado la oferta.", Toast.LENGTH_LONG).show();
            }

            final TruequeListAdapter adapterT = new TruequeListAdapter(new TruequeListAdapter.TruequeDiff());
            truequeViewModel = new ViewModelProvider(this, new TruequeFactory(getApplication())).get(TruequeViewModel.class);

            truequeViewModel.getTrueques().observe(this, trueques -> {

                adapterT.submitList(trueques);
            });

/*
            Trueque trueque = new Trueque();
            trueque.setIdUsuario1();
            trueque.setIdArticulo1(data.getIntExtra(AgregarArticuloActivity.EXTRA_MSG_ID,-1));
            trueque.setIdUsuario2(vLogin.idUsuarioGlobal);
            trueque.setIdArticulo2(idArticulo2);
            trueque.setEstado("p");*/
            truequeViewModel.updateTruequeByArticuloId(idArticulo2,vLogin.idUsuarioGlobal, data.getIntExtra(AgregarArticuloActivity.EXTRA_MSG_ID,-1));

            //truequeViewModel.update(trueque);


            Articulo articulo = new Articulo();
            articulo.setId(data.getIntExtra(AgregarArticuloActivity.EXTRA_MSG_ID,-1));
            articulo.setNombre(data.getStringExtra(AgregarArticuloActivity.EXTRA_MSG_NOMBRE));
            articulo.setDescripcion(data.getStringExtra(AgregarArticuloActivity.EXTRA_MSG_DESCRIPCION));
            articulo.setFoto(data.getStringExtra(AgregarArticuloActivity.EXTRA_MSG_FOTO));
            articulo.setIdUsuario(vLogin.idUsuarioGlobal);
            articulo.setCategoria(1);
            articulo.setEstado("n");
            articuloViewModel.update(articulo);


            //Log.d("info articulo", articulo.getNombre());


        } else {
            Toast.makeText(getApplicationContext(), R.string.no_eliminado_articulo, Toast.LENGTH_LONG).show();
        }
    }
}