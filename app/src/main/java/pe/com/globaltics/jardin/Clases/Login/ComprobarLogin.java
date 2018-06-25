package pe.com.globaltics.jardin.Clases.Login;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

import pe.com.globaltics.jardin.Clases.Conexion;
import pe.com.globaltics.jardin.R;

public class ComprobarLogin extends AsyncTask<Void,Void,String>{
    @SuppressLint("StaticFieldLeak")
    private Context c;
    private ProgressDialog pd;
    private String urla,usuario,password;
    public ComprobarLogin(Context c, String urla, String usuario, String password) {
        this.c = c;
        this.urla = urla;
        this.usuario = usuario;
        this.password = password;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd = new ProgressDialog(c,android.R.style.Theme_Translucent_NoTitleBar);
        pd.show();
        pd.setContentView(R.layout.login);
        TextView titulo = pd.findViewById(R.id.titulo);
        String i = "Iniciando Sesion";
        titulo.setText(i);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        pd.dismiss();
        if(s==null){
            Toast.makeText(c,"No tiene internet",Toast.LENGTH_SHORT).show();
        }else {
            AnalizadorLogin a= new AnalizadorLogin(c,s);
            a.execute();
        }

    }
    @Override
    protected String doInBackground(Void... voids) {
        return this.iniciarsesion();
    }

    private String iniciarsesion() {
        HttpURLConnection con = Conexion.httpURLConnection(urla);
        if(con==null){
            return null;
        }
        try {
            OutputStream os = con.getOutputStream();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
            bw.write(new EmpaqueLogin(usuario,password).packageData());

            bw.flush();
            bw.close();
            os.close();
            int responseCode = con.getResponseCode();
            if(responseCode== HttpURLConnection.HTTP_OK){
                InputStream is = con.getInputStream();

                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String line;
                StringBuilder response = new StringBuilder();
                while ((line=br.readLine())!=null){
                    response.append(line).append("n");
                }
                return response.toString();
            }else{
                return String.valueOf(responseCode);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
