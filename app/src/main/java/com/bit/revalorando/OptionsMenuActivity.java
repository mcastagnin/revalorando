package com.bit.revalorando;

import android.content.Intent;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;


public class OptionsMenuActivity extends AppCompatActivity {
/*
    private GoogleSignInClient googleSignInClient;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.appbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }*/

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add:
                return true;
            case R.id.action_about:
                acercaDe();
                return true;
            case R.id.action_logout:
               // logout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
/*
    private void logout(){
        googleSignInClient.signOut().addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Intent intent = new Intent(OptionsMenuActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }*/

    private void acercaDe(){
        DialogFragment dialog = new AcercaDeDialogFragment();
        dialog.show(getSupportFragmentManager(),"AcercaDeDialogFragment");
    }
}
