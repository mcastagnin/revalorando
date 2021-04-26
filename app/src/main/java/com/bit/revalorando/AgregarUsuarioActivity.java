package com.bit.revalorando;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;

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
    private Spinner  spinnerDepartamento;

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
        spinnerDepartamento = (Spinner) findViewById(R.id.textViewIngresarDepartamento);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.departamentos_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinnerDepartamento.setAdapter(adapter);
       // editTextDepartamento = findViewById(R.id.textViewIngresarDepartamento);




        final Button btnAgregar = findViewById(R.id.btnGuardar);
        btnAgregar.setBackgroundColor(getResources().getColor(R.color.colorGris));
        btnAgregar.setEnabled(false);

        CheckBox checkTerminos= ( CheckBox ) findViewById( R.id.checkBoxCondiciones);
        checkTerminos.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if ( isChecked )
                {
                    btnAgregar.setBackgroundColor(getResources().getColor(R.color.colorverde));
                    btnAgregar.setEnabled(true);


                }else{
                    btnAgregar.setBackgroundColor(getResources().getColor(R.color.colorGris));
                    btnAgregar.setEnabled(false);
                }

            }
        });
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
                String departamento = spinnerDepartamento.getSelectedItem().toString();

                //String departamento = editTextDepartamento.getText().toString();

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