package com.bit.revalorando;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;


public class OptionsMenuActivity extends AppCompatActivity {



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.appbar_menu, menu);
        return super.onCreateOptionsMenu(menu);

    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_about:
                acercaDe();
                return true;
            case R.id.action_logout:
                logout();
                Toast.makeText(getApplicationContext(), "Se ha cerrado la sesión", Toast.LENGTH_LONG).show();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void acercaDe(){
        DialogFragment dialog = new AcercaDeDialogFragment();
        dialog.show(getSupportFragmentManager(),"AcercaDeDialogFragment");

    }
    private void logout(){
        VariablesLogin vLogin = VariablesLogin.getInstance();
        vLogin.busquedaGlobal ="";
        vLogin.idUsuarioGlobal = -1;
        vLogin.usuarioGlobal = "";
        vLogin.passwordGlobal= "";
        finish();

        Intent intent = new Intent(OptionsMenuActivity.this, MainActivity.class);
        startActivity(intent);

    }
}
