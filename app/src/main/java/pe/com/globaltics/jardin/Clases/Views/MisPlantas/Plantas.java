package pe.com.globaltics.jardin.Clases.Views.MisPlantas;

public class Plantas {
    private String nombre,foto;
    private Integer estado;
    private Integer codigo;
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public String getFoto() {
        return foto;
    }

    public Integer getEstado() {
        return estado;
    }

    public Integer getCodigo() {
        return codigo;
    }

}
