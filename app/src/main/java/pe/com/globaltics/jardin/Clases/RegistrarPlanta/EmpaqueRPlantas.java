package pe.com.globaltics.jardin.Clases.RegistrarPlanta;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;

public class EmpaqueRPlantas {
    private String accion,foto1, nombre1,altura1,color1;
    private Integer codigo;
    EmpaqueRPlantas(String accion, String foto1, String nombre1,String altura1,String color1, Integer codigo) {
        this.accion = accion;
        this.foto1 = foto1;
        this.nombre1 = nombre1;
        this.altura1 = altura1;
        this.color1 = color1;
        this.codigo = codigo;
    }
    String packageData(){
        JSONObject jo = new JSONObject();
        StringBuilder sb = new StringBuilder();
        try {
            jo.put("accion",accion);
            jo.put("foto1",foto1);
            jo.put("nombre1",nombre1);
            jo.put("altura1",altura1);
            jo.put("color1",color1);
            jo.put("codigo",codigo);
            Boolean primervalor = true;
            Iterator it = jo.keys();
            do {
                String key=it.next().toString();
                String value=jo.get(key).toString();
                if(primervalor){
                    primervalor = false;
                }else{
                    sb.append("&");
                }
                sb.append(URLEncoder.encode(key,"UTF-8"));
                sb.append("=");
                sb.append(URLEncoder.encode(value,"UTF-8"));
            }while (it.hasNext());
            return sb.toString();
        } catch (JSONException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
