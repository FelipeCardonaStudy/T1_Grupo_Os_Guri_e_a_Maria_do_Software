import java.util.Date;
import java.text.SimpleDateFormat;

public class Postagem
{
    private Usuario usuario;
    private String data;
    private String texto;
    private TagsPostagem tag;
    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    public enum TagsPostagem
    {
        Esportes, Comida, Filmes, Viagem, Animais
    }

    public Postagem(Usuario usuario, String texto)
    {
        this.usuario = usuario;
        this.data = formato.format(new Date());
        this.texto = texto;
    }

    public Postagem(Usuario usuario, String texto, TagsPostagem tag)
    {
        this.usuario = usuario;
        this.data = formato.format(new Date());
        this.texto = texto;
        this.tag = tag;
    }

    @Override
    public String toString() {
        if(tag == null)
        {
            return "Postagem{" +
                    usuario +
                    ", data='" + data + '\'' +
                    ", texto='" + texto + '\'' +
                    '}';
        }
        return "Postagem{" +
                usuario +
                ", data='" + data + '\'' +
                ", texto='" + texto + '\'' +
                ", tag='" + tag + '\'' +
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

    public TagsPostagem getTag(){
        return tag;
    }

    public void setTag(TagsPostagem tag){
        this.tag = tag;
    }
}
