import java.util.Date;
import java.text.SimpleDateFormat;

public class Postagem
{
    private Usuario usuario;
    private String data;
    private String texto;
    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    public Postagem(Usuario usuario, String texto)
    {
        this.usuario = usuario;
        this.data = formato.format(new Date());
        this.texto = texto;
    }

    @Override
    public String toString() {
        return "Postagem{" +
                "usuario=" + usuario +
                ", data='" + data + '\'' +
                ", texto='" + texto + '\'' +
                '}';
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}
