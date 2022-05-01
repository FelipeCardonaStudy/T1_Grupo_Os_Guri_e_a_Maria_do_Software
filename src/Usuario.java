public class Usuario {

    private int identificao;
    private String nome;
    private String funcao;

    public Usuario(int identificao, String nome, String funcao) {
        this.identificao = identificao;
        this.nome = nome;
        this.funcao = funcao;
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

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }
}