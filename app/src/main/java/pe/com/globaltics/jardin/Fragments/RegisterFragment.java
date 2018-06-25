package pe.com.globaltics.jardin.Fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kosalgeek.android.md5simply.MD5;

import java.util.Objects;

import pe.com.globaltics.jardin.Clases.Register.EnviarRegistro;
import pe.com.globaltics.jardin.R;

import static pe.com.globaltics.jardin.Activitys.LoginActivity.urla;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment implements View.OnClickListener {


    public RegisterFragment() {
        // Required empty public constructor
    }

    private EditText user, pass, reppass;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        user = view.findViewById(R.id.user);
        pass = view.findViewById(R.id.pass);
        reppass = view.findViewById(R.id.reppass);
        Button registrar = view.findViewById(R.id.registrar);
        registrar.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.registrar:
                String user1 = user.getText().toString().trim();
                String pass1 = pass.getText().toString().trim();
                String reppass1 = reppass.getText().toString().trim();
                ComprobarDatos(user1, pass1, reppass1);
                break;
        }
    }

    private void ComprobarDatos(String user1, String pass1, String reppass1) {
        if (user1.length() <= 0 || pass1.length() <= 0 || reppass1.length() <= 0) {
            Toast.makeText(getActivity(), "Rellene todos los campos porfavor", Toast.LENGTH_SHORT).show();
        } else {
            if (CheckContra(pass1)) {
                if (Objects.equals(pass1,reppass1)){
                    new EnviarRegistro(getActivity(), urla,user1, MD5.encrypt(pass1)).execute();
                }else {
                    Toast.makeText(getActivity(), "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(getActivity(), "La contraseña solo puede contener mayusculas, minusculas y numeros", Toast.LENGTH_SHORT).show();
            }
        }

    }
    private boolean CheckContra(String contra) {
        boolean respuesta = false;
        if ((contra).matches("([0-9]|[a-z]|[A-Z])+")) {
            respuesta = true;
        }
        return respuesta;
    }
}