import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Postagem
{
    private static int count = 1;

    private Usuario usuario;
    private String data;
    private String texto;
    private TagsPostagem tag;
    private static ArrayList<String> palavrasProibidas = new ArrayList<>(Arrays.asList("Merda"));
    private Autorizacao autorizacao;
    private int identificador;
    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    private ArrayList<Comentario> comentarios = new ArrayList<>();

    public enum Autorizacao
    {
        Autorizada, NaoAutorizada
    }

    public enum TagsPostagem
    {
        Esportes, Comida, Filmes, Viagem, Animais
    }

    public Postagem(Usuario usuario, String texto)
    {
        this.usuario = usuario;
        this.data = formato.format(new Date());
        this.texto = texto;
        for(int i = 0; i<palavrasProibidas.size(); i++)
        {
            String temp = palavrasProibidas.get(i);
            if(texto.toUpperCase().contains(temp.toUpperCase()))
            {
                autorizacao = Autorizacao.NaoAutorizada;
            }
        }
        if(autorizacao == null)
        {
            autorizacao = Autorizacao.Autorizada;
        }
        identificador = count;
        count++;
    }

    public Postagem(Usuario usuario, String texto, TagsPostagem tag)
    {
        this.usuario = usuario;
        this.data = formato.format(new Date());
        this.texto = texto;
        this.tag = tag;
        for(int i = 0; i<palavrasProibidas.size(); i++)
        {
            String temp = palavrasProibidas.get(i);
            if(texto.toUpperCase().contains(temp.toUpperCase()))
            {
                autorizacao = Autorizacao.NaoAutorizada;
            }
        }
        if(autorizacao == null)
        {
            autorizacao = Autorizacao.Autorizada;
        }
        identificador = count;
        count++;
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

    public Autorizacao getAutorizacao() {
        return autorizacao;
    }

    public void setAutorizacao(Autorizacao autorizacao) {
        this.autorizacao = autorizacao;
    }

    public int getIdentificador() {
        return identificador;
    }
    public void setIdentificador(int identificador){
        this.identificador = identificador;
    }


    // IMPLEMENTAR O METODO NO MENU, VERIFICAR SWITCH PARA ADICIONAR MAIS OPCOES.
    public static boolean addPalavraProibida(String palavra, Usuario user)
    {
        if(user.getFuncao() == Usuario.FuncaoUsuario.Administrador)
        {
            palavrasProibidas.add(palavra);
            return true;
        }
        return false;
    }

    public static ArrayList<String> getPalavrasProibidas()
    {
        return palavrasProibidas;
    }

}
