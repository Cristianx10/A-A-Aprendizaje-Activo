package ObjetosList;

public class OForo {

    public String titulo;
    public String descripcion;
    public String autor;
    public String fecha;
    public String uid;

    public OForo() {}

    public OForo(String titulo, String descripcion, String autor, String fecha, String uid) {
        this.titulo = titulo;
        this.autor = autor;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.uid = uid;
    }
}
