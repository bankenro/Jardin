package pe.com.globaltics.jardin.Activitys;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import pe.com.globaltics.jardin.Fragments.LoginFragment;
import pe.com.globaltics.jardin.R;

public class LoginActivity extends AppCompatActivity {
    public static final String urla = "http://192.168.1.38/jardin/v1/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SharedPreferences preferences = getSharedPreferences("jardin", Context.MODE_PRIVATE);
        String nombre = preferences.getString("usuario", "");
        Integer codigo = preferences.getInt("codigo", 0);
        if (savedInstanceState == null) {
            if (nombre.length() > 0 || codigo != 0){
                Intent intent = new Intent(this, NavigationActivity.class);
                startActivity(intent);
                finish();
            }else {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Fragment fragment = new LoginFragment();
                fragmentTransaction.add(R.id.contenedor, fragment);
                fragmentTransaction.commit();
            }
        }
    }
    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
            getSupportFragmentManager().beginTransaction().commit();
        } else {
            super.onBackPressed();
        }
    }
}
