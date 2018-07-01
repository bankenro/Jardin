package pe.com.globaltics.jardin.Activitys;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import pe.com.globaltics.jardin.Fragments.AbonarFragment;
import pe.com.globaltics.jardin.Fragments.FragmentPrincipal;
import pe.com.globaltics.jardin.Fragments.PodarFragment;
import pe.com.globaltics.jardin.Fragments.RegarFragment;
import pe.com.globaltics.jardin.Fragments.TrasplanarFragment;
import pe.com.globaltics.jardin.R;

public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle bundle = new Bundle();
        bundle.putString("accion","all");
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = new FragmentPrincipal();
        fragment.setArguments(bundle);
        fragmentTransaction.add(R.id.contenedor,fragment);
        fragmentTransaction.commit();

        preferences = getSharedPreferences("jardin", Context.MODE_PRIVATE);

        String usuario = preferences.getString("usuario", "");

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View view = navigationView.getHeaderView(0);
        TextView usuario1 = view.findViewById(R.id.usuario);

        usuario1.setText(usuario);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (drawer.isDrawerOpen(GravityCompat.END)) {  /*Closes the Appropriate Drawer*/
            drawer.closeDrawer(GravityCompat.END);
        } else {
            if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                getSupportFragmentManager().popBackStack();
                getSupportFragmentManager().beginTransaction().commit();
            }
            else {
                super.onBackPressed();
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        boolean fragmentTransaction = false;
        Fragment fragment = null;
        Bundle bundle = new Bundle();

        switch (id){
            case R.id.home:
                fragment = new FragmentPrincipal();
                fragmentTransaction = true;
                bundle.putString("accion","all");
                fragment.setArguments(bundle);
                break;
            case R.id.regar:
                fragment = new RegarFragment();
                fragmentTransaction = true;
                bundle.putString("accion","regar");
                fragment.setArguments(bundle);
                break;
            case R.id.abonar:
                fragment = new AbonarFragment();
                fragmentTransaction = true;
                bundle.putString("accion","abonar");
                fragment.setArguments(bundle);
                break;
            case R.id.podar:
                fragment = new PodarFragment();
                fragmentTransaction = true;
                bundle.putString("accion","podar");
                fragment.setArguments(bundle);
                break;
            case R.id.trasplanar:
                fragment = new TrasplanarFragment();
                fragmentTransaction = true;
                bundle.putString("accion","trasplanar");
                fragment.setArguments(bundle);
                break;
            case R.id.contacto:
                String url = "https://www.facebook.com/AlexJC2O18";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                break;
            case R.id.cerrar:
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear().apply();
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                break;
        }
        if(fragmentTransaction){
            getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,fragment).commit();
            item.setChecked(true);
            setTitle(item.getTitle());
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
