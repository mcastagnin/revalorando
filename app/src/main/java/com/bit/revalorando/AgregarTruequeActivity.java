package com.bit.revalorando;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.appbar.MaterialToolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AgregarTruequeActivity extends OptionsMenuActivity {
    public static final String EXTRA_MSG_ID = "com.bit.revalorando.MSG_GUARDAR_ID";
    public static final String EXTRA_MSG_NOMBRE = "com.bit.revalorando.MSG_GUARDAR_NOMBRE";
    public static final String EXTRA_MSG_DESCRIPCION = "com.bit.revalorando.MSG_GUARDAR_DESCRIPCION";
    public static final String EXTRA_MSG_FOTO = "com.bit.revalorando.MSG_GUARDAR_FOTO";

    private EditText editTextNombre;
    private EditText editTextDescripcion;
    private EditText editTextImagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_articulo);

        editTextNombre = findViewById(R.id.textViewIngresarNombre);
        editTextDescripcion = findViewById(R.id.textViewIngresarDescripcion);
        editTextImagen = findViewById(R.id.textViewIngresarImagen);

        final Button btnAgregar = findViewById(R.id.btnGuardar);
        btnAgregar.setOnClickListener(view -> {
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
            }
            finish();
        });

        Intent intent = getIntent();

        if(intent.hasExtra(EXTRA_MSG_ID)){
            editTextNombre.setText(intent.getStringExtra(EXTRA_MSG_NOMBRE));
            editTextDescripcion.setText(intent.getStringExtra(EXTRA_MSG_DESCRIPCION));
            editTextImagen.setText(intent.getStringExtra(EXTRA_MSG_FOTO));
        } else {
            editTextImagen.setText("https://cdn.icon-icons.com/icons2/2534/PNG/128/product_delivery_icon_152013.png");
        }

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setSubtitle(R.string.app_subtitle);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AgregarTruequeActivity.super.onBackPressed();
            }
        });

    }
}