package com.bit.revalorando;
import android.app.AlertDialog;
import android.app.Application;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bit.revalorando.entities.Articulo;
import com.bit.revalorando.entities.Trueque;
import com.bit.revalorando.models.ArticuloViewModel;
import com.bit.revalorando.models.TruequeViewModel;
import com.bit.revalorando.repositories.ArticuloRepository;
import com.bit.revalorando.repositories.UsuarioRepository;
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
    private ArticuloViewModel articuloViewModel;
    private TruequeViewModel truequeViewModel;


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

        final ArticuloListAdapter adapter = new ArticuloListAdapter(new ArticuloListAdapter.ArticuloDiff());
        articuloViewModel = new ViewModelProvider(this, new ArticuloFactory(getApplication())).get(ArticuloViewModel.class);

        articuloViewModel.getArticulos().observe(this, articulos -> {

            adapter.submitList(articulos);
        });

        final TruequeListAdapter adapterT = new TruequeListAdapter(new TruequeListAdapter.TruequeDiff());
        truequeViewModel = new ViewModelProvider(this, new TruequeFactory(getApplication())).get(TruequeViewModel.class);

        truequeViewModel.getTrueques().observe(this, trueques -> {

            adapterT.submitList(trueques);

        });

        tvNombre1 = findViewById(R.id.textViewNombre1);
        tvDescripcion1 = findViewById(R.id.textViewDescripcion1);
        imagenView1 = findViewById(R.id.textViewImagen1);
        tvNombre2 = findViewById(R.id.textViewNombre2);
        tvDescripcion2 = findViewById(R.id.textViewDescripcion2);
        imagenView2 = findViewById(R.id.textViewImagen2);




        final Button btnAceptarOferta = findViewById(R.id.btnAceptarOferta);
        btnAceptarOferta.setOnClickListener(view -> {

            AlertDialog.Builder builder = new AlertDialog.Builder(OfertaActivity.this);
            builder.setMessage("Desea aceptar la oferta");
            builder.setTitle("Intercambio");

            builder.setPositiveButton(R.string.si, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    Trueque trueque = truequeViewModel.getTrueque(getIntent().getIntExtra(EXTRA_MSG_ID,-1));
                    trueque.setEstado("f");
                    truequeViewModel.update(trueque);

                    Intent intent = new Intent(OfertaActivity.this, ContactoActivity.class);
                    intent.putExtra(ContactoActivity.EXTRA_MSG_ID, trueque.getIdUsuario2());
                    startActivity(intent);
                    finish();                }
            });
            builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();


        });


        final Button btnDeclinarOferta = findViewById(R.id.btnRechazarOferta);
        btnDeclinarOferta.setOnClickListener(view -> {

            AlertDialog.Builder builder = new AlertDialog.Builder(OfertaActivity.this);
            builder.setMessage("Desea rechazar la oferta");
            builder.setTitle("Intercambio");

            builder.setPositiveButton(R.string.si, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    Intent intent = new Intent(OfertaActivity.this, ListarTruequeActivity.class);
                    startActivity(intent);
                    finish();           }
            });
            builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();

        });


        Trueque trueque = truequeViewModel.getTrueque(getIntent().getIntExtra(EXTRA_MSG_ID,-1));

        Articulo articulo1 = articuloViewModel.getArticulo(trueque.getIdArticulo1());
        //articulo1 = articuloViewModel.getArticulo(39);
        Articulo articulo2 = articuloViewModel.getArticulo(trueque.getIdArticulo2());
       // articulo2 = articuloViewModel.getArticulo(47);
        Log.d("nombre art1", articulo1.getNombre());

        Intent intent = getIntent();


            Picasso.with(getApplicationContext())
                    .load(articulo1.getFoto())
                    .centerCrop()
                    .fit()
                    .into(imagenView1);
            tvNombre1.setText(articulo1.getNombre());
            tvDescripcion1.setText(articulo1.getDescripcion());

            Picasso.with(getApplicationContext())
                    .load(articulo2.getFoto())
                    .centerCrop()
                    .fit()
                    .into(imagenView2);
            tvNombre2.setText(articulo2.getNombre());
            tvDescripcion2.setText(articulo2.getDescripcion());



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