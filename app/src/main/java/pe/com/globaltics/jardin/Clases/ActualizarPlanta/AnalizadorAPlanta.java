package pe.com.globaltics.jardin.Clases.ActualizarPlanta;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

import pe.com.globaltics.jardin.R;

public class AnalizadorAPlanta extends AsyncTask<Void,Void,Integer> {

    @SuppressLint("StaticFieldLeak")
    private Context c;
    private String s;
    @SuppressLint("StaticFieldLeak")
    private String mensaje;
    @SuppressLint("StaticFieldLeak")
    private TextView estado;
    AnalizadorAPlanta(Context c, String s, TextView estado) {
        this.c = c;
        this.s = s;
        this.estado = estado;
    }

    @Override
    protected Integer doInBackground(Void... voids) {
        return this.Analizar();
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
        if (integer == 1) {
            if (mensaje != null) {
                Toast.makeText(c, mensaje, Toast.LENGTH_SHORT).show();
                if (!Objects.equals(mensaje,"Se elimino correctamente")){
                    String hecho = "Realizado";
                    estado.setText(hecho);
                    estado.setTextColor(ContextCompat.getColor(c, R.color.activo));
                }
            }
        } else {
            Toast.makeText(c, "No registrado", Toast.LENGTH_SHORT).show();
        }
    }

    private Integer Analizar() {
        try {
            JSONArray ja = new JSONArray(s);
            JSONObject jo;
            for (int i = 0; i < ja.length(); i++) {
                jo = ja.getJSONObject(i);
                mensaje = jo.getString("mensaje");
            }
            return 1;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
