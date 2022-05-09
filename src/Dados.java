public class Dados {
    
    private final int MAX_SIZE_USERS = 10;
    private final int MAX_SIZE_POSTS = 100;
    private Usuario[] user = new Usuario[MAX_SIZE_USERS];
    private Postagem[] post = new Postagem[MAX_SIZE_POSTS];


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
