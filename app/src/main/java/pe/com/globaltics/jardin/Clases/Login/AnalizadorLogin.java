package pe.com.globaltics.jardin.Clases.Login;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import pe.com.globaltics.jardin.Activitys.NavigationActivity;

class AnalizadorLogin extends AsyncTask<Void, Void, Integer> {
    @SuppressLint("StaticFieldLeak")
    private Context c;
    private String s;
    private Integer codigo;
    private String usuario;

    AnalizadorLogin(Context c, String s) {
        this.c = c;
        this.s = s;
    }

    @Override
    protected Integer doInBackground(Void... voids) {
        return this.analizar();
    }

    private Integer analizar() {
        try {
            Log.w("log",s);
            JSONArray ja = new JSONArray(s);
            JSONObject jo;
            for (int i = 0; i < ja.length(); i++) {
                jo = ja.getJSONObject(i);
                usuario = jo.getString("nombres");
                codigo = jo.getInt("codigo");
            }
            return 1;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
        if (integer == 1) {
            Intent intent = new Intent(c, NavigationActivity.class);
            SharedPreferences preferences = c.getSharedPreferences("jardin", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("usuario", usuario);
            editor.putInt("codigo", codigo);
            editor.apply();

            Toast.makeText(c, "Bienvenido " + usuario, Toast.LENGTH_SHORT).show();
            c.startActivity(intent);
            ((Activity) (c)).finish();
        } else {
            Toast.makeText(c, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
            SharedPreferences preferences = c.getSharedPreferences("datos", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            editor.apply();
                /*Intent intent = new Intent(c,LoginActvity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                c.startActivity(intent);*/
        }
    }
}
