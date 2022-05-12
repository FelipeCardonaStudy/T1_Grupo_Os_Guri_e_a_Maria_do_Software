import java.io.IOException;
import java.net.SocketPermission;
import java.util.Date;
import java.util.Scanner;

public class App {
    Dados dados = new Dados();

    public void executa() throws IOException {
        // (pré-cadastro)
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
                    System.out.println("Deseja adicionar um comentario?");
                    System.out.println("Se sim, digite 1");
                    System.out.println("Se não, digite 2");

                    int respostaTeclado = teclado.nextInt();

                    if (respostaTeclado == 1) {
                        adicionaComentario();
                    }
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
                        System.out.println("Escolha a opção desejada: ");
                        System.out.println("1: Pesquisar comentários");
                        System.out.println("2: Pesquisar postagens por tag");
                        String op = teclado.nextLine();
                        switch (op) {
                            case "1":
                            System.out.print("Digite uma busca: ");
                            String palavra_chave_1 = teclado.nextLine();
                            if (!pesquisaComentarios(palavra_chave_1))
                            System.out.println("\nNada encontrado com " + palavra_chave_1 + "\n");
                            break;
                            case "2":
                            System.out.print("Digite uma busca: ");
                            String palavra_chave_2 = teclado.nextLine();
                            if (!pesquisaPostagemPorTags(palavra_chave_2))
                            System.out.println("\nNada encontrado com " + palavra_chave_2 + "\n");
                            break;
                            default:
                            System.out.println("Valor digitado incorreto, tente novamente:");
                    }
                    break;
                case "5":
                    dados.toCSV();
                    break;
                case "6":
                    adicionaAdmin();
                    break;
                case "7":
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
        System.out.println("6: Adiciona funcao de administrador para usuario.");
        System.out.println("7: Encerrar o programa.\n");
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

    // Pesquisa posts por comentarios
    public boolean pesquisaComentarios(String palavra_chave) {
        int count = 0;
        for (int i = 0; i < dados.postagensAutorizadasLength(); i++)
        {
            if (dados.postagensAutorizadas.get(i) != null)
            for (int j = 0; j < dados.postagensAutorizadas.get(i).getComentariosLength(); j++)
            {
                if (dados.postagensAutorizadas.get(i).getComentarioAtIndex(j).contains(palavra_chave))
                count++;
                System.out.println(dados.postagensAutorizadas.get(i).getComentarioAtIndex(j).toString());
            }
        }
        if (count > 0)
        return true;
        return false;
    }

    // Pesquisa posts por tags
    public boolean pesquisaPostagemPorTags(String palavra_chave) {
        int count = 0;
        for (int i = 0; i < dados.postagensAutorizadasLength(); i++)
        {
            if (dados.postagensAutorizadas.get(i).getTag() != null)
            {
                if (dados.postagensAutorizadas.get(i).getTag().name().toLowerCase().equals(palavra_chave.toLowerCase()))
                {
                    count++;
                    System.out.println(dados.postagensAutorizadas.get(i).toString());
                }
            }
        }
        if (count > 0)
        return true;
        return false;
    }

    public void salvaPostagensCSV() {
        // TODO
    }

    public void adicionaAdmin() {
        if (dados.usuarioAtivo.getFuncao() == Usuario.FuncaoUsuario.Administrador) {
            System.out.println("---------------------------------");
            System.out.println("Lista de usuarios para escolher:");
            System.out.println("---------------------------------\n");
            dados.mostraListaUsuarios();
            Scanner teclado = new Scanner(System.in);
            System.out.println("\nDigite o identificador do usuário:");
            int identificadorUsuario = Integer.parseInt(teclado.nextLine());
            Usuario usuario;

            for (int i = 0; i < dados.usuarios.size(); i++) {
                usuario = dados.usuarios.get(i);
                if ((identificadorUsuario == usuario.getIdentificao())
                        && (usuario.getFuncao() != Usuario.FuncaoUsuario.Administrador)) {
                    usuario.setFuncao(Usuario.FuncaoUsuario.Administrador);
                }
            }

        } else {
            System.out.println("Voce nao tem permissao para adicionar usuarios.\n");
        }
    }

    private void adicionaComentario(){
        Scanner teclado = new Scanner (System.in);
        System.out.println("Em qual postagem você deseja adicionar um comentário? (informar identificador da postagem)");
        int idPostagemComentario = teclado.nextInt();

        for (Postagem postagem : dados.postagensAutorizadas){
            if (postagem.getIdentificador() == idPostagemComentario) {
                System.out.println("Digite o comentário:");

                String textoDoComentario = teclado.next();

                boolean comentarioValido = true;

                for (String palavraProibida : postagem.getPalavrasProibidas()) {
                    if (textoDoComentario.contains(palavraProibida)){
                        comentarioValido = false;
                        System.out.println("Comentário inválido.\n");
                    }
                }

                if (comentarioValido == true) {
                    Comentario novoComentario = new Comentario(textoDoComentario, dados.usuarioAtivo, new Date());
                    postagem.getComentarios().add(novoComentario);

                    System.out.println("Comentário adicionado com sucesso.\n");
                }
            }
        }
    }
}
