package pe.com.globaltics.jardin.Fragments;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.Objects;

import pe.com.globaltics.jardin.Clases.RegistrarPlanta.RegistrarPlantas;
import pe.com.globaltics.jardin.R;
import static pe.com.globaltics.jardin.Activitys.LoginActivity.urla;

/**
 * A simple {@link Fragment} subclass.
 */
public class AgregarPlantas extends Fragment implements View.OnClickListener{


    public AgregarPlantas() {
        // Required empty public constructor
    }
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private EditText nombre,altura,color;
    private ImageView foto;
    private String accion;
    private Integer codigo;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_agregar_plantas, container, false);

        if (getArguments()!=null){
            accion = getArguments().getString("accion","");
        }
        SharedPreferences preferences = Objects.requireNonNull(getActivity()).getSharedPreferences("jardin", Context.MODE_PRIVATE);
        codigo = preferences.getInt("codigo",0);
        nombre = view.findViewById(R.id.nombre);
        altura = view.findViewById(R.id.altura);
        color = view.findViewById(R.id.color);
        foto = view.findViewById(R.id.foto);
        Button registrar = view.findViewById(R.id.registrar);

        registrar.setOnClickListener(this);
        foto.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.registrar:
                Registrar();
                break;
            case R.id.foto:
                sacarfoto();
                break;
        }
    }

    private void Registrar() {
        foto.buildDrawingCache();
        Bitmap bitmap = foto.getDrawingCache();
        String foto1 = convertirfoto(bitmap);
        String nombre1 = nombre.getText().toString().trim();
        String altura1 = altura.getText().toString().trim();
        String color1 = color.getText().toString().trim();
        if (nombre1.length()>0 && altura1.length()>0 && color1.length()>0){
            new RegistrarPlantas(getActivity(),urla,accion,foto1,nombre1,altura1,color1,codigo).execute();
        }else {
            Toast.makeText(getActivity(),"Ingrese un nombre",Toast.LENGTH_SHORT).show();
        }
    }

    private void sacarfoto() {
        Intent tomarfoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //noinspection ConstantConditions
        if (tomarfoto.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(tomarfoto, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            Bundle bundle = data.getExtras();
            assert bundle != null;
            Bitmap imagen = (Bitmap) bundle.get("data");
            foto.setImageBitmap(imagen);
        }
    }

    private String convertirfoto(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 70, stream);
        byte[] byteFormat = stream.toByteArray();
        return Base64.encodeToString(byteFormat, Base64.NO_WRAP);
    }
}
