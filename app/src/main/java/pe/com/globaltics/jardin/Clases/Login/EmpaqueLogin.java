package pe.com.globaltics.jardin.Clases.Login;

import com.kosalgeek.android.md5simply.MD5;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;

class EmpaqueLogin {
    private String usuario,password;

    public EmpaqueLogin(String usuario, String password) {
        this.usuario = usuario;
        this.password = password;
    }
    String packageData() {
        JSONObject jo = new JSONObject();
        StringBuilder sb = new StringBuilder();
        String accion= "login";
        try{
            jo.put("accion",accion);
            jo.put("username",usuario);
            jo.put("password",password);
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
        }catch (JSONException | UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return null;
    }
}
