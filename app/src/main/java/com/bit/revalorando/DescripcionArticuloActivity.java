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
    public static final String EXTRA_MSG_TRUEQUE_ID = "com.bit.revalorando.MSG_GUARDAR_ID_TRUEQUE";
    private int  idTrueque = -1;
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



        // editTextImagen = findViewById(R.id.textViewIngresarImagen);

        final Button btnIntercambiar = findViewById(R.id.btnIntercambiar);
        btnIntercambiar.setOnClickListener(view -> {

            Intent intentD = getIntent();

            int idTrueque = intentD.getIntExtra(EXTRA_MSG_TRUEQUE_ID, -1);

            Intent intent = new Intent(DescripcionArticuloActivity.this,ListarSeleccionActivity.class);
            intent.putExtra(EXTRA_MSG_ID, idTrueque);


            //intent.putExtra(ListarSeleccionActivity.EXTRA_MSG_ARTICULO_ID, ListarSeleccionActivity.EXTRA_MSG_ID);

            startActivityForResult(intent, OFERTAR_TRUEQUE_REQ_CODE);
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

        idTrueque = intent.getIntExtra(EXTRA_MSG_ID,-1);

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

        if (resultCode == RESULT_OK){
            int idArticulo2 = data.getIntExtra(ListarSeleccionActivity.EXTRA_MSG_ARTICULO_ID, -1);
            Log.d("id art. 2 al regresar:", data.getIntExtra(ListarSeleccionActivity.EXTRA_MSG_ARTICULO_ID, -1) +"");
            Log.d("truquei id alreg:", idTrueque+"");

            if (idArticulo2 == -1 ){
                Toast.makeText(getApplicationContext(), "No se ha realizado la oferta.", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(DescripcionArticuloActivity.this, ListarTruequeActivity.class);
                startActivity(intent);
                finish();

            }



/*
            Trueque trueque = new Trueque();
            trueque.setIdUsuario1();
            trueque.setIdArticulo1(data.getIntExtra(AgregarArticuloActivity.EXTRA_MSG_ID,-1));
            trueque.setIdUsuario2(vLogin.idUsuarioGlobal);
            trueque.setIdArticulo2(idArticulo2);
            trueque.setEstado("p");*/
            //truequeViewModel.update(trueque);



            //Intent intent = getIntent();
            Articulo articulo = new Articulo();
            int idArt = data.getIntExtra(ListarSeleccionActivity.EXTRA_MSG_ARTICULO_ID,-1);
            articulo = articuloViewModel.getArticulo(idArt);
/*
            articulo.setId(data.getIntExtra(ListarSeleccionActivity.EXTRA_MSG_ID,-1));
            articulo.setNombre(intent.getStringExtra(EXTRA_MSG_NOMBRE));
            articulo.setDescripcion(intent.getStringExtra(EXTRA_MSG_DESCRIPCION));
            articulo.setFoto(intent.getStringExtra(EXTRA_MSG_FOTO));
            articulo.setIdUsuario(vLogin.idUsuarioGlobal);
            articulo.setCategoria(1);*/
            articulo.setEstado("n");
            articuloViewModel.update(articulo);
Log.d("idAsr2 - idusu2, idArt1", ""+ articulo.getId()+"  "+articulo.getIdUsuario()+"  "+data.getIntExtra(DescripcionArticuloActivity.EXTRA_MSG_ID,-1));
            truequeViewModel.updateTruequeByTruequeId(articulo.getId(),articulo.getIdUsuario(), idTrueque);
            Toast.makeText(getApplicationContext(), "Se ha realizado la oferta.", Toast.LENGTH_LONG).show();

            finish();
            Intent intent = new Intent(DescripcionArticuloActivity.this, ListarTruequeActivity.class);
            startActivity(intent);


            //Log.d("info articulo", articulo.getNombre());


        } else {
            Toast.makeText(getApplicationContext(), R.string.no_eliminado_articulo, Toast.LENGTH_LONG).show();
        }
    }
}