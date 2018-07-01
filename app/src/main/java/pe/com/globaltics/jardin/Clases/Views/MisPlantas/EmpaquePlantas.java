package pe.com.globaltics.jardin.Clases.Views.MisPlantas;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;

class EmpaquePlantas {
    private String accion;
    private Integer codigo;
    private String s;
    EmpaquePlantas(String accion, Integer codigo, String s) {
        this.accion = accion;
        this.codigo = codigo;
        this.s = s;
    }
    String packageData() {
        JSONObject jo = new JSONObject();
        StringBuilder sb = new StringBuilder();
        try {
            jo.put("accion", accion);
            jo.put("codigo",codigo);
            jo.put("s",s);
            Iterator i = jo.keys();
            Boolean primer = true;
            do {
                String key = i.next().toString();
                String value = jo.get(key).toString();
                if (primer) {
                    primer = false;
                } else {
                    sb.append("&");
                }
                sb.append(URLEncoder.encode(key, "UTF-8"));
                sb.append("=");
                sb.append(URLEncoder.encode(value, "UTF-8"));
            } while (i.hasNext());
            return sb.toString();
        } catch (JSONException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
