package pe.com.globaltics.jardin.Clases.Views.MisPlantas;

public class Plantas {
    private String nombre,foto,altura,color;
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

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getAltura() {
        return altura;
    }

    public String getColor() {
        return color;
    }
}
