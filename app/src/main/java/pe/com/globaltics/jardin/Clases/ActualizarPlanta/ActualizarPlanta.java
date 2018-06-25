package pe.com.globaltics.jardin.Clases.ActualizarPlanta;

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

public class ActualizarPlanta extends AsyncTask<Void,Void,String>{
    @SuppressLint("StaticFieldLeak")
    private Context c;
    private String urla, accion;
    private Integer codigo;
    private String nombre;
    private ProgressDialog pd;
    @SuppressLint("StaticFieldLeak")
    private TextView estado;
    public ActualizarPlanta(Context c, String urla, String accion, Integer codigo, String nombre, TextView estado) {
        this.c = c;
        this.urla = urla;
        this.accion = accion;
        this.codigo = codigo;
        this.nombre = nombre;
        this.estado = estado;
    }

    @Override
    protected String doInBackground(Void... voids) {
        return this.Actualizar();
    }

    private String Actualizar() {
        HttpURLConnection con = Conexion.httpURLConnection(urla);
        if(con==null){
            return null;
        }
        try{
            OutputStream os = con.getOutputStream();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
            bw.write(new EmpaqueAPlanta(accion,codigo,nombre).packageData());
            bw.flush();
            bw.close();
            os.close();
            int respuesta = con.getResponseCode();
            if(respuesta== HttpURLConnection.HTTP_OK){
                InputStream is =con.getInputStream();

                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String linea;
                StringBuilder respuestast = new StringBuilder();
                while ((linea=br.readLine())!=null){
                    respuestast.append(linea).append("n");
                }
                return respuestast.toString();
            }else{
                return String.valueOf(respuesta);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd = new ProgressDialog(c);
        pd.setTitle("Registrando");
        pd.setMessage("Por favor espere un momento");
        pd.show();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        pd.dismiss();
        if (s==null){
            Toast.makeText(c,"No tiene internet",Toast.LENGTH_SHORT).show();
        }else{
            new AnalizadorAPlanta(c,s,estado).execute();
        }
    }

}
