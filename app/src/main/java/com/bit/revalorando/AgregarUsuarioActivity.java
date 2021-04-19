package com.bit.revalorando;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

public class AgregarUsuarioActivity extends AppCompatActivity {
    public static final String EXTRA_MSG_USUARIO = "com.bit.revalorando.MSG_GUARDAR_USUARIO";
    public static final String EXTRA_MSG_PASSWORD = "com.bit.revalorando.MSG_GUARDAR_PASSWORD ";
    public static final String EXTRA_MSG_NOMBRE = "com.bit.revalorando.MSG_GUARDAR_NOMBRE";
    public static final String EXTRA_MSG_APELLIDO = "com.bit.revalorando.MSG_GUARDAR_APELLIDO";
    public static final String EXTRA_MSG_EMAIL = "com.bit.revalorando.MSG_GUARDAR_EMAIL";
    public static final String EXTRA_MSG_TELEFONO = "com.bit.revalorando.MSG_GUARDAR_TELEFONO";
    public static final String EXTRA_MSG_DIRECCION = "com.bit.revalorando.MSG_GUARDAR_DIRECCION";
    public static final String EXTRA_MSG_NUMERO = "com.bit.revalorando.MSG_GUARDAR_NUMERO";
    public static final String EXTRA_MSG_LOCALIDAD = "com.bit.revalorando.MSG_GUARDAR_LOCALIDAD";
    public static final String EXTRA_MSG_DEPARTAMENTO = "com.bit.revalorando.MSG_GUARDAR_DEPARTAMENTO";



    private EditText editTextNick;
    private EditText editTextPassword;
    private EditText editTextPassword2;
    private EditText editTextNombre;
    private EditText editTextApellido;
    private EditText editTextTelefono;
    private EditText editTextEmail;
    private EditText editTextDireccion;
    private EditText editTextNumero;
    private EditText editTextLocalidad;
    private EditText editTextDepartamento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_usuario);

        editTextNick = findViewById(R.id.textViewIngresarUsuario);
        editTextPassword = findViewById(R.id.textViewIngresarPassword);
        editTextPassword2 = findViewById(R.id.textViewIngresarPassword2);
        editTextNombre = findViewById(R.id.textViewIngresarNombre);
        editTextApellido = findViewById(R.id.textViewIngresarApellido);
        editTextTelefono = findViewById(R.id.textViewIngresarTelefono);
        editTextEmail = findViewById(R.id.textViewIngresarEmail);
        editTextDireccion = findViewById(R.id.textViewIngresarDireccion);
        editTextNumero = findViewById(R.id.textViewIngresarNumero);
        editTextLocalidad = findViewById(R.id.textViewIngresarLocalidad);
        editTextDepartamento = findViewById(R.id.textViewIngresarDepartamento);


        final Button btnAgregar = findViewById(R.id.btnGuardar);
        btnAgregar.setOnClickListener(view -> {
            Intent respuesta = new Intent();
            if(TextUtils.isEmpty(editTextNick.getText())){
                setResult(RESULT_CANCELED, respuesta);
            }

            else {
                String usuario = editTextNick.getText().toString();
                String password = editTextPassword.getText().toString();
                String nombre = editTextNombre.getText().toString();
                String apellido = editTextApellido.getText().toString();
                String telefono = editTextTelefono.getText().toString();
                String email = editTextEmail.getText().toString();
                String direccion = editTextDireccion.getText().toString();
                String numero = editTextNumero.getText().toString();
                String localidad = editTextLocalidad.getText().toString();
                String departamento = editTextDepartamento.getText().toString();

                respuesta.putExtra(EXTRA_MSG_USUARIO, usuario);
                respuesta.putExtra(EXTRA_MSG_PASSWORD, password);
                respuesta.putExtra(EXTRA_MSG_NOMBRE, nombre);
                respuesta.putExtra(EXTRA_MSG_APELLIDO, apellido);
                respuesta.putExtra(EXTRA_MSG_EMAIL, email);
                respuesta.putExtra(EXTRA_MSG_TELEFONO, telefono);
                respuesta.putExtra(EXTRA_MSG_DIRECCION, direccion);
                respuesta.putExtra(EXTRA_MSG_NUMERO, numero);
                respuesta.putExtra(EXTRA_MSG_LOCALIDAD, localidad);
                respuesta.putExtra(EXTRA_MSG_DEPARTAMENTO, departamento);

                setResult(RESULT_OK, respuesta);
            }
            finish();
        });
    }
}