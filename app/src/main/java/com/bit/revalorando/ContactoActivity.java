package com.bit.revalorando;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;

import com.bit.revalorando.entities.Articulo;
import com.bit.revalorando.entities.Usuario;
import com.bit.revalorando.models.ArticuloViewModel;
import com.bit.revalorando.models.UsuarioViewModel;
import com.google.android.material.appbar.MaterialToolbar;
import com.squareup.picasso.Picasso;

public class ContactoActivity extends OptionsMenuActivity {
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
    private UsuarioViewModel usuarioViewModel;


    private TextView tvNombre;
    private TextView tvTelefono;
    private TextView tvDireccion;
    private String direccion;
    private String telefono;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        final UsuarioListAdapter adapter = new UsuarioListAdapter(new UsuarioListAdapter.UsuarioDiff());
        usuarioViewModel = new ViewModelProvider(this, new UsuarioFactory(getApplication())).get(UsuarioViewModel.class);

        usuarioViewModel.getUsuarios().observe(this, usuarios -> {

            adapter.submitList(usuarios);
        });

        tvNombre = findViewById(R.id.textViewNombre);
        tvTelefono = findViewById(R.id.textViewTelefono);
        tvDireccion = findViewById(R.id.textViewDireccion);


       Usuario usuario = new Usuario();
       usuario = usuarioViewModel.buscarUsuarioPorId(getIntent().getIntExtra(EXTRA_MSG_ID,-1));
       direccion = ""+ usuario.getDireccion() + " Nº " + usuario.getNumero() + " - " + usuario.getLocalidad() + " - " + usuario.getDepartamento() + " - Uruguay";
       telefono = usuario.getTelefono();


       tvNombre.setText("Nombre: " + usuario.getNombre() + " " + usuario.getApellido());
       tvTelefono.setText("Teléfono: " + telefono);
       tvDireccion.setText("Dirección: "+ direccion);

        final Button btnWhatsapp = findViewById(R.id.btnWhastapp);
        btnWhatsapp.setOnClickListener(view -> {
            String url = "https://api.whatsapp.com/send?phone=+598"+telefono;
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        });


        final Button btnMapa = findViewById(R.id.btnMapa);
        btnMapa.setOnClickListener(view -> {

            Intent searchAddress = new  Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q="+direccion));
            startActivity(searchAddress);
        });





        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setSubtitle(R.string.app_subtitle);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContactoActivity.super.onBackPressed();
            }
        });

    }
}