import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Dados dados = new Dados();
        dados.inicializa();
        App app = new App();
        app.executa();
    }
}
