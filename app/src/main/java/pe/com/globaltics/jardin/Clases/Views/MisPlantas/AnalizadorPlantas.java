package pe.com.globaltics.jardin.Clases.Views.MisPlantas;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

class AnalizadorPlantas extends AsyncTask<Void,Void,Integer> {
    @SuppressLint("StaticFieldLeak")
    private Context c;
    private String s;
    @SuppressLint("StaticFieldLeak")
    private RecyclerView rv;
    private Integer i;
    private ArrayList<Plantas> plantas = new ArrayList<>();
    AnalizadorPlantas(Context c, String s, RecyclerView rv, Integer i) {
        this.c = c;
        this.s = s;
        this.rv = rv;
        this.i = i;
    }

    @Override
    protected Integer doInBackground(Void... voids) {
        return this.analizador();
    }
    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
        if (integer == 0) {
            Toast.makeText(c, "No se cargaron las plantas", Toast.LENGTH_SHORT).show();
        } else {
            AdaptadorPlantas a = new AdaptadorPlantas(c, plantas,i);
            rv.setAdapter(a);
        }
    }
    private Integer analizador() {
        try {
            JSONObject jo;
            JSONArray ja = new JSONArray(s);
            Plantas plantas1;
            for (int i = 0; i < ja.length(); i++) {
                jo = ja.getJSONObject(i);
                String nombre = jo.getString("nombre");
                Integer codigo = jo.getInt("codigo");
                String foto = jo.getString("foto");
                Integer estado = jo.getInt("estado");

                plantas1 = new Plantas();
                plantas1.setNombre(nombre);
                plantas1.setCodigo(codigo);
                plantas1.setFoto(foto);
                plantas1.setEstado(estado);
                plantas.add(plantas1);
            }
            return 1;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
