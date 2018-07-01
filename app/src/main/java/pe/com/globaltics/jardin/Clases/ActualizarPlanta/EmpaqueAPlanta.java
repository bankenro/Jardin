package pe.com.globaltics.jardin.Clases.ActualizarPlanta;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;

public class EmpaqueAPlanta {
    private String accion;
    private Integer codigo;
    private String nombre,altura,color;

    EmpaqueAPlanta(String accion, Integer codigo, String nombre,String altura,String color) {
        this.accion = accion;
        this.codigo = codigo;
        this.nombre = nombre;
        this.altura = altura;
        this.color = color;
    }

    String packageData(){
        JSONObject jo = new JSONObject();
        StringBuilder sb = new StringBuilder();
        try {
            jo.put("accion",accion);
            jo.put("codigo",codigo);
            jo.put("nombre",nombre);
            jo.put("altura",altura);
            jo.put("color",color);
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
