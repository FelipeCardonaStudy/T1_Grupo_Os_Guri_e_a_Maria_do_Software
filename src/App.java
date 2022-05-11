import java.util.Scanner;

public class App {

    public void executa() {
        Dados dados = new Dados();
        // TODO (pré-cadastro)

        //if (menuUsuario(dados) == true) {
            menuOperacoes();
        //}

    }

    private void menuOperacoes() {
        Scanner teclado = new Scanner(System.in);
        int acao = 7; //criar a variavel acao com um valor que faca com que o while seja executado

        while (acao != 6) {
            System.out.println("Digite o número que corresponde à ação desejada: ");
            System.out.println("1: Escolher o usuário que está ativo.");
            System.out.println("2: Listar todas as postagens.");
            System.out.println("3: Excluir postagens ou comentários.");
            System.out.println("4: Pesqisar postagens ou comentários a partir de TAGS ou palavras-chave.");
            System.out.println("5: Salvar todas as suas postagens em um arquivo CSV.");
            System.out.println("6: Encerrar o programa.");

            //garantir que seja digitado um valor valido, um numero entre 1 e 6
            boolean isNumber;
            do {
                if (teclado.hasNextInt()) {
                    acao = teclado.nextInt();
                    isNumber = true;
                    if (acao == 6) {
                        break;
                    }
                    if (acao < 1 || acao > 6) {
                        System.out.println("Valor digitado inválido, digite um número que corresponda à ação desejada: ");
                        isNumber = false;
                    }
                } else {
                    System.out.println("Valor digitado inválido, digite um número que corresponda à ação desejada: ");
                    isNumber = false;
                    teclado.next();
                }
            } while (!isNumber);

            while (acao <= 5) {
                switch (acao) {
                    case 1: escolheUsuarioAtivo();
                    case 2: listaPostagens();
                    case 3: excluiPostagem();
                    case 4: pesquisaPostagem();
                    case 5: salvaPostagensCSV();
                }
                acao = 7; // mudar o valor da variavel acao para retornar para o loop do menu
            }
        }
    }

    private void escolheUsuarioAtivo(){
        // TODO
    }

    public void listaPostagens(){
        // TODO
    }

    public void excluiPostagem(){
        // TODO
    }

    public void pesquisaPostagem(){
        // TODO
    }

    public void salvaPostagensCSV(){
        // TODO
    }

    // Escreve lista de usuários do sistema
    private boolean menuUsuario(Dados dados) {
        System.out.println("\nEscolha o Usuário:");
        System.out.println("-----------------------------");

        try {
            for (int i = 0; i < dados.getUserLength(); i++) {
                System.out.println("(" + i + 1 + ") User @" + dados.getUserNameByIndex(i));
            }
        } catch (NullPointerException e) {
            System.out.println("Ainda não há nenhum usuário (!)");
            return false;
        }
        return true;
    }

}
