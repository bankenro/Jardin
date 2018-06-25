package pe.com.globaltics.jardin.Clases.Views.MisPlantas;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
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

public class LlenarPlantas extends AsyncTask<Void,Void,String> {
    @SuppressLint("StaticFieldLeak")
    private Context c;
    private String urla,  accion;
    private Integer codigo,i;
    @SuppressLint("StaticFieldLeak")
    private RecyclerView rv;
    @SuppressLint("StaticFieldLeak")
    private SwipeRefreshLayout swipeRefreshLayout;

    public LlenarPlantas(Context c, String urla, String accion, Integer codigo, RecyclerView rv, SwipeRefreshLayout swipeRefreshLayout) {
        this.c = c;
        this.urla = urla;
        this.accion = accion;
        this.codigo = codigo;
        this.rv = rv;
        this.swipeRefreshLayout = swipeRefreshLayout;
    }

    @Override
    protected String doInBackground(Void... voids) {
        return this.Llenar();
    }

    private String Llenar() {
        HttpURLConnection con = Conexion.httpURLConnection(urla);
        if (con == null) {
            return null;
        }
        try {
            OutputStream os = con.getOutputStream();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
            bw.write(new EmpaquePlantas(accion,codigo).packageData());
            bw.flush();
            bw.close();
            os.close();
            int resp = con.getResponseCode();
            if (resp == HttpURLConnection.HTTP_OK) {
                InputStream is = con.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String linea;
                StringBuilder respuesta = new StringBuilder();
                while ((linea = br.readLine()) != null) {
                    respuesta.append(linea).append("n");
                }
                return respuesta.toString();
            } else {
                return String.valueOf(resp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        //pd.dismiss();
        swipeRefreshLayout.setRefreshing(false);
        if (s==null ){
            Toast.makeText(c,"No tiene internet",Toast.LENGTH_SHORT).show();
        }else {
            new AnalizadorPlantas(c,s,rv,i).execute();
        }
    }
}
