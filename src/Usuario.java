public class Usuario implements Comparable<Usuario>{

    private static int count = 1;

    private int identificao;
    private String nome;
    private FuncaoUsuario funcao;

    private int qntPostagens = 0;

    private int qntComentarios = 0;

    public enum FuncaoUsuario {
        Funcionario, Administrador
    }

    public Usuario(String nome, FuncaoUsuario funcao) {
        this.nome = nome;
        this.funcao = funcao;
        identificao = count;
        count++;
    }

    public Usuario(){

    }

    @Override
    public String toString() {
        return "Usuario{" +
                "identificao=" + identificao +
                ", nome='" + nome + '\'' +
                ", funcao='" + funcao + '\'' +
                '}';
    }

    public int getIdentificao() {
        return identificao;
    }

    public void setIdentificao(int identificao) {
        this.identificao = identificao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public FuncaoUsuario getFuncao() {
        return funcao;
    }

    public void setFuncao(FuncaoUsuario funcao) {
        this.funcao = funcao;
    }

    public void incrementaQntdPostagens(){
        qntPostagens++;
    }

    public int getQntPostagens(){
        return qntPostagens;
    }

    @Override
    public int compareTo(Usuario u) {
        return (u.getQntPostagens() - this.getQntPostagens());
    }
}