import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Postagem
{
    private Usuario usuario;
    private String data;
    private String texto;
    private TagsPostagem tag;
    private ArrayList<String> palavrasProibidas = new ArrayList<>(Arrays.asList("Merda"));
    private Autorizacao autorizacao;
    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");

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

//    Parte do código que adiciona palavras proibidas em uma arraylist, porém não temos usuário ativo ainda.
//    Fazer o if quando tivermos o usuario ativo e testar se ele é administrador, caso seja, sera possivel adicionar palavras.
//
//    public boolean addPalavraProibida(String palavra)
//    {
//        if()
//        {
//            palavrasProibidas.add(palavra);
//            return true;
//        }
//        return false;
//    }

}
