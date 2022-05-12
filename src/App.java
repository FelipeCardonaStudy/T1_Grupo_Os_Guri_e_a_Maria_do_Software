import java.io.IOException;
import java.util.Scanner;

public class App {
    Dados dados = new Dados();

    public void executa() throws IOException {
        // TODO (pré-cadastro)
        dados.inicializa();

        // if (menuUsuario(dados) == true) {
        menuOperacoes();
        // }

    }

    private void menuOperacoes() throws IOException {
        Scanner teclado = new Scanner(System.in);
        String acao;
        boolean encerrarPrograma = false;

        do {
            apresentaMenuOperacoes();
            acao = teclado.nextLine();
            switch (acao) {
                case "1":
                    escolheUsuarioAtivo();
                    break;
                case "2":
                    listaPostagens();
                    break;
                case "3":
                    System.out.println("Digite o identificador da postagem que deseja excluir: ");
                    int identificador = teclado.nextInt();
                    teclado.nextLine();
                    
                    if (excluiPostagem(identificador) == true) {
                        System.out.println("Postagem excluida com sucesso!");
                        excluiPostagem(identificador);
                        break;

                    } else {
                        System.out.println("Erro, não foi possível excluir a postagem");
                    }

                    break;
                case "4":
                    pesquisaPostagem();
                    break;
                case "5":
                    dados.toCSV();
                    break;
                case "6":
                    encerrarPrograma = true;
                    break;
                default:
                    System.out.println("Valor digitado incorreto, tente novamente:");
                    break;
            }
        } while (!encerrarPrograma);
    }

    public void apresentaMenuOperacoes() {
        System.out.println("Digite o número que corresponde à ação desejada: ");
        System.out.println("1: Escolher o usuário que está ativo.");
        System.out.println("2: Listar todas as postagens.");
        System.out.println("3: Excluir postagens ou comentários.");
        System.out.println("4: Pesquisar postagens ou comentários a partir de TAGS ou palavras-chave.");
        System.out.println("5: Salvar todas as suas postagens em um arquivo CSV.");
        System.out.println("6: Encerrar o programa.\n");
    }

    private void escolheUsuarioAtivo() {
        System.out.println("Digite o identificador do usuário:");
        Scanner teclado = new Scanner(System.in);

        String identificadorUsuario = teclado.nextLine();
        int identificadorUsuarioConvertido = Integer.parseInt(identificadorUsuario);

        for (int i = 0; i < dados.usuarios.size(); i++) {
            if (identificadorUsuarioConvertido == dados.usuarios.get(i).getIdentificao()) {
                dados.usuarioAtivo = dados.usuarios.get(i);
            }
        }
        if (dados.usuarioAtivo != null) {
            System.out.println("Usuário ativo: " + dados.usuarioAtivo.getNome() + "\n");
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }

    public void listaPostagens() {
        for (int i = dados.postagensAutorizadas.size() - 1; i > -1; i--) {
            Postagem temp = dados.postagensAutorizadas.get(i);
            System.out.println(temp.toString());
        }
    }

    // Método que faz a exclusão da postagem
    public boolean excluiPostagem(int idPostagem) {

        for (int i = 0; i < dados.postagensAutorizadas.size(); i++) {
            Postagem p = dados.postagensAutorizadas.get(i);

            if (dados.usuarioAtivo.getFuncao() == Usuario.FuncaoUsuario.Administrador) {
                if (idPostagem == p.getIdentificador()) {
                    dados.postagensAutorizadas.remove(i);
                    return true;
                }

            } else if (dados.usuarioAtivo.getIdentificao() == p.getUsuario().getIdentificao()) {
                if (idPostagem == p.getIdentificador()) {
                    dados.postagensAutorizadas.remove(i);
                    return true;
                }
            }

        }
        return false;
    }

    public void pesquisaPostagem() {
        // TODO
    }

    public void salvaPostagensCSV() {
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
