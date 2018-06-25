package pe.com.globaltics.jardin.Fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kosalgeek.android.md5simply.MD5;

import pe.com.globaltics.jardin.Clases.Login.ComprobarLogin;
import pe.com.globaltics.jardin.R;

import static pe.com.globaltics.jardin.Activitys.LoginActivity.urla;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements View.OnClickListener{
    private EditText pass;
    private EditText user;
    private String accion;
    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        pass = view.findViewById(R.id.pass);
        user = view.findViewById(R.id.user);
        Button reg = view.findViewById(R.id.ingresar);
        Button log = view.findViewById(R.id.registrar);

        reg.setOnClickListener(LoginFragment.this);
        log.setOnClickListener(LoginFragment.this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.registrar:
                FragmentManager fragmentManager = getFragmentManager();
                assert fragmentManager != null;
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Fragment fragment = new RegisterFragment();
                fragmentTransaction.replace(R.id.contenedor,fragment);
                fragmentTransaction.addToBackStack(null).commit();
                break;
            case R.id.ingresar:
                if(user.getText().toString().trim().length()<=0 || pass.getText().toString().trim().length()<=0 ){
                    Toast.makeText(getActivity(),"Rellene todos los campos ",Toast.LENGTH_SHORT).show();
                }else {
                    String usuario = user.getText().toString().trim();
                    String password = MD5.encrypt(pass.getText().toString().trim());
                    comprobarlogin(usuario,password);
                }
                break;
        }
    }

    private void comprobarlogin(String usuario, String password) {
        new ComprobarLogin(getActivity(), urla,usuario,password).execute();
    }
}
