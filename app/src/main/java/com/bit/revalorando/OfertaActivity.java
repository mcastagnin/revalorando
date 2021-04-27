package com.bit.revalorando;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bit.revalorando.entities.Articulo;
import com.google.android.material.appbar.MaterialToolbar;
import com.squareup.picasso.Picasso;

public class OfertaActivity extends OptionsMenuActivity {
    public static final String EXTRA_MSG_ID = "com.bit.revalorando.MSG_GUARDAR_ID";
    public static final String EXTRA_MSG_NOMBRE = "com.bit.revalorando.MSG_GUARDAR_NOMBRE";
    public static final String EXTRA_MSG_DESCRIPCION = "com.bit.revalorando.MSG_GUARDAR_DESCRIPCION";
    public static final String EXTRA_MSG_FOTO = "com.bit.revalorando.MSG_GUARDAR_FOTO";

    public static final String EXTRA_MSG_ID2 = "com.bit.revalorando.MSG_GUARDAR_ID2";
    public static final String EXTRA_MSG_NOMBRE2 = "com.bit.revalorando.MSG_GUARDAR_NOMBRE2";
    public static final String EXTRA_MSG_DESCRIPCION2 = "com.bit.revalorando.MSG_GUARDAR_DESCRIPCION2";
    public static final String EXTRA_MSG_FOTO2 = "com.bit.revalorando.MSG_GUARDAR_FOTO2";

    VariablesLogin vLogin = VariablesLogin.getInstance();
    public int idUsuario = vLogin.idUsuarioGlobal;

    private TextView tvNombre1;
    private TextView tvDescripcion1;
    private ImageView imagenView1;
    private TextView tvNombre2;
    private TextView tvDescripcion2;
    private ImageView imagenView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oferta);

        tvNombre1 = findViewById(R.id.textViewNombre1);
        tvDescripcion1 = findViewById(R.id.textViewDescripcion1);
        imagenView1 = findViewById(R.id.textViewImagen1);
        tvNombre2 = findViewById(R.id.textViewNombre2);
        tvDescripcion2 = findViewById(R.id.textViewDescripcion2);
        imagenView2 = findViewById(R.id.textViewImagen2);


        final Button btnAceptarOferta = findViewById(R.id.btnAceptarOferta);
        btnAceptarOferta.setOnClickListener(view -> {
            /*Intent respuesta = new Intent();
            if(TextUtils.isEmpty(editTextNombre.getText())){
                setResult(RESULT_CANCELED, respuesta);
            } else {
                String articulo = editTextNombre.getText().toString();
                String descripcion = editTextDescripcion.getText().toString();
                String url = editTextImagen.getText().toString();
                respuesta.putExtra(EXTRA_MSG_NOMBRE, articulo);
                respuesta.putExtra(EXTRA_MSG_DESCRIPCION, descripcion);
                respuesta.putExtra(EXTRA_MSG_FOTO, url);

                int id = getIntent().getIntExtra(EXTRA_MSG_ID, -1);
                if(id != -1){
                    respuesta.putExtra(EXTRA_MSG_ID, id);
                }
                setResult(RESULT_OK, respuesta);
            }*/
            finish();
        });


        final Button btnDeclinarOferta = findViewById(R.id.btnDeclinarOferta);
        btnDeclinarOferta.setOnClickListener(view -> {
           /*
            Intent respuesta = new Intent();
            if(TextUtils.isEmpty(editTextNombre.getText())){
                setResult(RESULT_CANCELED, respuesta);
            } else {
                String articulo = editTextNombre.getText().toString();
                String descripcion = editTextDescripcion.getText().toString();
                String url = editTextImagen.getText().toString();
                respuesta.putExtra(EXTRA_MSG_NOMBRE, articulo);
                respuesta.putExtra(EXTRA_MSG_DESCRIPCION, descripcion);
                respuesta.putExtra(EXTRA_MSG_FOTO, url);

                int id = getIntent().getIntExtra(EXTRA_MSG_ID, -1);
                if(id != -1){
                    respuesta.putExtra(EXTRA_MSG_ID, id);
                }
                setResult(RESULT_OK, respuesta);
            }*/
            finish();
        });


        Articulo articulo1 = new Articulo();
        Articulo articulo2 = new Articulo();

        Intent intent = getIntent();

        if(intent.hasExtra(EXTRA_MSG_ID)) {
            Picasso.with(getApplicationContext())
                    .load(articulo1.getFoto())
                    .centerCrop()
                    .fit()
                    .into(imagenView1);
            tvNombre1.setText(articulo1.getNombre());
            tvDescripcion1.setText(articulo1.getDescripcion());

            Picasso.with(getApplicationContext())
                    .load(articulo1.getFoto())
                    .centerCrop()
                    .fit()
                    .into(imagenView2);
            tvNombre2.setText(articulo1.getNombre());
            tvDescripcion2.setText(articulo1.getDescripcion());

        }

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setSubtitle(R.string.app_subtitle);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OfertaActivity.super.onBackPressed();
            }
        });

    }
}