package com.bit.revalorando;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bit.revalorando.entities.Usuario;
import com.bit.revalorando.models.UsuarioViewModel;

public class MainActivity extends AppCompatActivity {

    private UsuarioViewModel usuarioViewModel;
    public static final int NEW_LISTAR_USUARIO_REQ_CODE = 1;
    public static final int NEW_USUARIO_REQ_CODE = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final UsuarioListAdapter adapter = new UsuarioListAdapter(new UsuarioListAdapter.UsuarioDiff());

        usuarioViewModel = new ViewModelProvider(this, new UsuarioFactory(getApplication())).get(UsuarioViewModel.class);

        usuarioViewModel.getUsuarios().observe(this, usuarios -> {

            adapter.submitList(usuarios);
        });

        final Button btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(view -> {


            final TextView tvUsuario = findViewById(R.id.textViewIngresarUsuarioLogin);
            final TextView tvPassword = findViewById(R.id.textViewIngresarPasswordLogin);

            Usuario usuarioLogin = null;
            usuarioLogin = usuarioViewModel.getUsuario(tvUsuario.getText().toString());
/*
            Log.d("usuario=", usuarioLogin.getUsuario());
            Log.d("contraseña:",usuarioLogin.getPassword());
            Log.d("contraseñaingresada: ",tvPassword.getText().toString());
*/
            if(usuarioLogin != null){
                if(usuarioLogin.getPassword().equals(tvPassword.getText().toString())){
                    VariablesLogin vLogin = VariablesLogin.getInstance();
                    vLogin.idUsuarioGlobal = usuarioLogin.getId();
                    vLogin.usuarioGlobal = usuarioLogin.getUsuario();
                    vLogin.passwordGlobal= usuarioLogin.getPassword();
/*
                    TextView itemUsuario = findViewById(R.id.nav_usuario);
                    itemUsuario.setText(idUsuarioGlobal);
*/
                    Intent intent = new Intent(MainActivity.this, ListarTruequeActivity.class);

                    startActivityForResult(intent, NEW_LISTAR_USUARIO_REQ_CODE);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Usuario o contraseña incorrecta", Toast.LENGTH_LONG).show();
                }
            }
            else{
                Toast.makeText(getApplicationContext(), "Usuario o contraseña incorrecta", Toast.LENGTH_LONG).show();
            }

        });

        final Button btnRegistro = findViewById(R.id.btnRegistro);
        btnRegistro.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AgregarUsuarioActivity.class);

            startActivityForResult(intent, NEW_USUARIO_REQ_CODE);
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==NEW_USUARIO_REQ_CODE && resultCode == RESULT_OK){
            Usuario usuario = new Usuario();
            usuario.setUsuario(data.getStringExtra(AgregarUsuarioActivity.EXTRA_MSG_USUARIO));
            usuario.setPassword(data.getStringExtra(AgregarUsuarioActivity.EXTRA_MSG_PASSWORD));
            usuario.setNombre(data.getStringExtra(AgregarUsuarioActivity.EXTRA_MSG_NOMBRE));
            usuario.setApellido(data.getStringExtra(AgregarUsuarioActivity.EXTRA_MSG_APELLIDO));
            usuario.setEmail(data.getStringExtra(AgregarUsuarioActivity.EXTRA_MSG_EMAIL));
            usuario.setTelefono(data.getStringExtra(AgregarUsuarioActivity.EXTRA_MSG_TELEFONO));
            usuario.setDireccion(data.getStringExtra(AgregarUsuarioActivity.EXTRA_MSG_DIRECCION));
            usuario.setNumero(data.getStringExtra(AgregarUsuarioActivity.EXTRA_MSG_NUMERO));
            usuario.setLocalidad(data.getStringExtra(AgregarUsuarioActivity.EXTRA_MSG_LOCALIDAD));
            usuario.setDepartamento(data.getStringExtra(AgregarUsuarioActivity.EXTRA_MSG_DEPARTAMENTO));


            usuarioViewModel.insert(usuario);
        } else {
            Toast.makeText(getApplicationContext(), R.string.no_registrado, Toast.LENGTH_LONG).show();
        }
    }

    @Override public void onBackPressed() { return; }


}