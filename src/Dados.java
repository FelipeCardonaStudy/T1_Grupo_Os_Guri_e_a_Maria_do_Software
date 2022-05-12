import java.util.ArrayList;
import java.io.*;

public class Dados {

    private final int MAX_SIZE_USERS = 10;
    private final int MAX_SIZE_POSTS = 100;
    private Usuario[] user = new Usuario[MAX_SIZE_USERS];
    private Postagem[] post = new Postagem[MAX_SIZE_POSTS];

    Usuario usuarioAtivo;

    ArrayList<Usuario> usuarios = new ArrayList<>(); //Arraylist de usuários.
    ArrayList<Postagem> postagensNaoAutorizadas = new ArrayList<>(); //Arraylist de postagens não autorizadas.
    ArrayList<Postagem> postagensAutorizadas = new ArrayList<>(); //Arraylist de postagens autorizadas.

    public void inicializa(){

        Usuario admin = new Usuario("Fulano", Usuario.FuncaoUsuario.Administrador);
        Usuario user1 = new Usuario("Ciclano", Usuario.FuncaoUsuario.Funcionario);
        Usuario user2 = new Usuario("Robson", Usuario.FuncaoUsuario.Funcionario);
        Usuario user3 = new Usuario("Jaqueline", Usuario.FuncaoUsuario.Funcionario);
        Usuario user4 = new Usuario("Sergio", Usuario.FuncaoUsuario.Administrador);
        Usuario user5 = new Usuario("Hector", Usuario.FuncaoUsuario.Funcionario);
        Usuario user6 = new Usuario("Marcelo", Usuario.FuncaoUsuario.Funcionario);
        Usuario user7 = new Usuario("Isabela", Usuario.FuncaoUsuario.Funcionario);
        Usuario user8 = new Usuario("Marcia", Usuario.FuncaoUsuario.Funcionario);
        Usuario user9 = new Usuario("Paola Oliveira", Usuario.FuncaoUsuario.Administrador);
        Usuario user10 = new Usuario("Roberto", Usuario.FuncaoUsuario.Funcionario);


        usuarios.add(admin);usuarios.add(user1); usuarios.add(user2); usuarios.add(user3); usuarios.add(user4);
        usuarios.add(user5); usuarios.add(user6); usuarios.add(user7); usuarios.add(user8); usuarios.add(user9);
        usuarios.add(user10);

        Postagem p1 = new Postagem(user1,"Eu gosto de bananas!", Postagem.TagsPostagem.Comida); //Posts normais
        Postagem p2 = new Postagem(user1,"Vou adotar um papagaio.", Postagem.TagsPostagem.Animais);
        Postagem p3 = new Postagem(user2,"Vou viajar para a Italia amanha!!", Postagem.TagsPostagem.Viagem);
        Postagem p4 = new Postagem(user2,"Lagoa Azul é meu filme preferido!", Postagem.TagsPostagem.Filmes);
        Postagem p5 = new Postagem(user3,"Alguem quer jogar basquete amanha??", Postagem.TagsPostagem.Esportes);
        Postagem p6 = new Postagem(user3,"Hello World!! :)");
        Postagem p7 = new Postagem(admin,"Amanha será lançada uma atualizacao do aplicativo."); //Post admin
        Postagem p8 = new Postagem(user1,"Que dia merda."); // Post a ser proibido
        Postagem p9 = new Postagem(user9, "O oitavo andar é incrivel!");
        Postagem p10 = new Postagem(user5, "Eu adoro estudar no predio 32!");
        Postagem p11 = new Postagem(user5, "Sempre que tento pegar o elevador ele está cheio. Aff...");
        Postagem p12 = new Postagem(user5, "Quem que sempre aperta o botao do alarme no elevador??");
        Postagem p13 = new Postagem(user5, "Estou ansiosa para o fim do semestre!");
        Postagem p14 = new Postagem(user5, "Alguém quer jogar Valorant amanhã?");

        addPost(p1); addPost(p2); addPost(p3); addPost(p4); addPost(p5); addPost(p6); addPost(p7); addPost(p8);
        addPost(p9); addPost(p10); addPost(p11); addPost(p12); addPost(p13); addPost(p14);
    }

    public boolean addPost(Postagem p){

        if(p.getAutorizacao() == Postagem.Autorizacao.NaoAutorizada){
            postagensNaoAutorizadas.add(p);
            return false;
        }
        postagensAutorizadas.add(p);
        return true;
    }


    /**
     * Return integer representative of user's array length
     * @return int user.length
     */
    public int getUserLength() {
        return user.length;
    }

    /**
     * Return integer representative of post's array length
     * @return int post.length
     */
    public int getPostLength() {
        return post.length;
    }

    /**
     * Return String representative of user's name attribute
     * @return String Usuario.nome
     */
    public String getUserNameByIndex(int index) {
        return user[index].getNome();
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
