import java.util.ArrayList;
import java.io.*;

public class Dados {

    Usuario usuarioAtivo;

    ArrayList<Usuario> usuarios = new ArrayList<>(); //Arraylist de usuários.
    ArrayList<Postagem> postagensNaoAutorizadas = new ArrayList<>(); //Arraylist de postagens não autorizadas.
    ArrayList<Postagem> postagensAutorizadas = new ArrayList<>(); //Arraylist de postagens autorizadas.

    public void inicializa(){

        Usuario admin = new Usuario("Fulano", Usuario.FuncaoUsuario.Administrador);
        Usuario user1 = new Usuario("Ciclano", Usuario.FuncaoUsuario.Funcionario);
        Usuario user2 = new Usuario("Robson", Usuario.FuncaoUsuario.Funcionario);
        Usuario user3 = new Usuario("Jaqueline", Usuario.FuncaoUsuario.Funcionario);

        usuarios.add(admin);usuarios.add(user1); usuarios.add(user2); usuarios.add(user3);

        Postagem p1 = new Postagem(user1,"Eu gosto de bananas!", Postagem.TagsPostagem.Comida); //Posts normais
        Postagem p2 = new Postagem(user1,"Vou adotar um papagaio.", Postagem.TagsPostagem.Animais);
        Postagem p3 = new Postagem(user2,"Vou viajar para a Italia amanha!!", Postagem.TagsPostagem.Viagem);
        Postagem p4 = new Postagem(user2,"Lagoa Azul é meu filme preferido!", Postagem.TagsPostagem.Filmes);
        Postagem p5 = new Postagem(user3,"Alguem quer jogar basquete amanha??", Postagem.TagsPostagem.Esportes);
        Postagem p6 = new Postagem(user3,"Hello World!! :)");

        Postagem p7 = new Postagem(admin,"Amanha será lançada uma atualizacao do aplicativo."); //Post admin

        Postagem p8 = new Postagem(user1,"Que dia merda."); // Post a ser proibido

        addPost(p1);
        addPost(p2);
        addPost(p3);
        addPost(p4);
        addPost(p5);
        addPost(p6);
        addPost(p7);

        addPost(p8);
    }

    public boolean addPost(Postagem p){

        if(p.getAutorizacao() == Postagem.Autorizacao.NaoAutorizada){
            postagensNaoAutorizadas.add(p);
            return false;
        }
        postagensAutorizadas.add(p);
        return true;
    }


    public int postagensAutorizadasLength() {
        return postagensAutorizadas.size();
    }


    public int postagensNaoAutorizadasLength() {
        return postagensNaoAutorizadas.size();
    }


    public void mostraListaUsuarios() {
        for (Usuario usuario : usuarios) {
            System.out.println(usuario.toString());
        }
    }

    public void toCSV() throws IOException {
        FileWriter fileWrt = new FileWriter("POSTAGENS.csv");
        BufferedWriter bf = new BufferedWriter(fileWrt);
        Postagem temp;
        for(int i = 0; i < postagensAutorizadas.size(); i++){
            //bf.write(String.valueOf(psim.get(i)));
            temp = postagensAutorizadas.get(i);
            String str = temp.toString();
            bf.write(str);
        }

    }

}
