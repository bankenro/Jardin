package pe.com.globaltics.jardin.Clases.Register;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import static pe.com.globaltics.jardin.Activitys.LoginActivity.urla;

import pe.com.globaltics.jardin.Clases.Login.ComprobarLogin;

class AnalizadorRegistro extends AsyncTask<Void,Void,Integer> {
    @SuppressLint("StaticFieldLeak")
    private Context c;
    private String s, user1, encrypt;
    @SuppressLint("StaticFieldLeak")
    private String mensaje;
     AnalizadorRegistro(Context c, String s, String user1, String encrypt) {
        this.c = c;
        this.s = s;
        this.user1 = user1;
        this.encrypt = encrypt;
    }

    @Override
    protected Integer doInBackground(Void... voids) {
        return this.Analizar();
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
        if(integer==1){
            if (mensaje!=null){
                Toast.makeText(c,mensaje,Toast.LENGTH_SHORT).show();
                new ComprobarLogin(c,urla,user1,encrypt).execute();
            }
        }else {
            Toast.makeText(c,"No registrado",Toast.LENGTH_SHORT).show();
        }
    }

    private Integer Analizar() {
        try {
            JSONArray ja = new JSONArray(s);
            JSONObject jo;
            for (int i=0; i<ja.length();i++){
                jo=ja.getJSONObject(i);
                mensaje = jo.getString("mensaje");
            }

            return 1;
        }catch (JSONException e){
            e.printStackTrace();
        }
        return 0;
    }
}
