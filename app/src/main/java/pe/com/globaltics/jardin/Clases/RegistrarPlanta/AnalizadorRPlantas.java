package pe.com.globaltics.jardin.Clases.RegistrarPlanta;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class AnalizadorRPlantas extends AsyncTask<Void, Void, Integer> {
    @SuppressLint("StaticFieldLeak")
    private Context c;
    private String s;
    @SuppressLint("StaticFieldLeak")
    private String mensaje;
    AnalizadorRPlantas(Context c, String s) {
        this.c = c;
        this.s = s;
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
            }
        } else {
            Toast.makeText(c, "No registrado", Toast.LENGTH_SHORT).show();
        }
    }

    private Integer Analizar() {
        try {
            //Log.w("AQUI",s);
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
