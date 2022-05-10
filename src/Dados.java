import java.util.ArrayList;

public class Dados {

    private final int MAX_SIZE_USERS = 10;
    private final int MAX_SIZE_POSTS = 100;
    private Usuario[] user = new Usuario[MAX_SIZE_USERS];
    private Postagem[] post = new Postagem[MAX_SIZE_POSTS];


    ArrayList<Usuario> usuarios = new ArrayList<>(); //Arraylist de usuários.
    ArrayList<Postagem> pnao = new ArrayList<>(); //Arraylist de postagens não autorizadas.
    ArrayList<Postagem> psim = new ArrayList<>(); //Arraylist de postagens autorizadas.


    public void inicializa(){

        Usuario admin = new Usuario(1, "Fulano", Usuario.FuncaoUsuario.Administrador);
        Usuario user1 = new Usuario(2,"Ciclano", Usuario.FuncaoUsuario.Funcionario);
        Usuario user2 = new Usuario(3,"Robson", Usuario.FuncaoUsuario.Funcionario);
        Usuario user3 = new Usuario(4,"Jaqueline", Usuario.FuncaoUsuario.Funcionario);


        Postagem p1 = new Postagem(user1,"Eu gosto de bananas!", Postagem.TagsPostagem.Comida);
        Postagem p2 = new Postagem(user1,"Vou adotar um papagaio.", Postagem.TagsPostagem.Animais);
        Postagem p3 = new Postagem(user2,"Vou viajar para a Itália amanhã!!", Postagem.TagsPostagem.Viagem);
        Postagem p4 = new Postagem(user2,"Lagoa Azul é meu filme preferido!", Postagem.TagsPostagem.Filmes);
        Postagem p5 = new Postagem(user3,"Alguém quer jogar basquete amanhã??", Postagem.TagsPostagem.Esportes);
        Postagem p6 = new Postagem(user3,"Hello World!! :)");
        Postagem p7 = new Postagem(admin,"Amanhã será lançada uma atualização do aplicativo.");


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

}
