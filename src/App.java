import java.awt.image.AreaAveragingScaleFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;

public class App {
    Dados dados = new Dados();
    private Comentario comentario = new Comentario();
    private Usuario usuario = new Usuario();

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
                    System.out.println("\nDeseja adicionar um comentario?");
                    System.out.println("Se sim, digite 1");
                    System.out.println("Para voltar ao menu principal, digite outra tecla.");

                    String respostaTeclado = teclado.nextLine();
                    int respostaTecladoInteiro = Integer.parseInt(respostaTeclado);

                    if (respostaTecladoInteiro == 1) {
                        adicionaComentario();
                    } else {
                        System.out.println("");
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
                        System.out.println("Erro, não foi possível excluir a postagem.");
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
                    logPostagensProibidas();
                    break;
                case "8":
                        exibePainel();
                    break;
                case "9":
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
        System.out.println("7: Log das postagens probidas(Admin).");
        System.out.println("8: Exibir painel com informacoes dos posts e usuarios. (Exclusivo para administradores)");
        System.out.println("9: Encerrar o programa.\n");
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
        for (int i = 0; i < dados.postagensAutorizadas.size(); i++)
        {
            if (dados.postagensAutorizadas.get(i) != null)
            for (int j = 0; j < dados.postagensAutorizadas.get(i).getQntdComentarios(); j++)
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
        for (int i = 0; i < dados.postagensAutorizadas.size(); i++)
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

    public void logPostagensProibidas(){
        if(dados.usuarioAtivo.getFuncao() == Usuario.FuncaoUsuario.Administrador){
            for(int i = 0; i<dados.postagensNaoAutorizadas.size(); i++){
                Postagem postagem = dados.postagensNaoAutorizadas.get(i);
                System.out.println(postagem);
            }
        }
        else{
            System.out.println("Você não tem permissão para usar essa função.");
        }
    }

    private void adicionaComentario(){
        Scanner teclado = new Scanner (System.in);
        System.out.println("Em qual postagem você deseja adicionar um comentário?");
        System.out.println("(Favor informar identificador da postagem)");
        int idPostagemComentario = teclado.nextInt();

        for (Postagem postagem : dados.postagensAutorizadas){
            if (postagem.getIdentificador() == idPostagemComentario) {
                System.out.println("Digite o comentário:");

                String textoDoComentario = teclado.next();

                boolean comentarioValido = true;

                for (String palavraProibida : postagem.getPalavrasProibidas()) {
                    if (textoDoComentario.toUpperCase().contains(palavraProibida.toUpperCase())){
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

    public void exibePainel(){
        if(dados.usuarioAtivo.getFuncao() != Usuario.FuncaoUsuario.Administrador){
            System.out.println("Voce nao possui permissao para executar a operacao selecionada.\n");
            return;
        }
        int totalPostagens = dados.postagensAutorizadas.size();
        int totalComentarios = 0;
        int totalUsuarios = dados.usuarios.size();



        for(Postagem p : dados.postagensAutorizadas){
            totalComentarios += p.getQntdComentarios();
        }

        System.out.println("--------------------");
        System.out.println("Total de postagens: " + totalPostagens);
        System.out.println("Total de comentarios: " + totalComentarios);
        System.out.println("Total de Usuarios: " + totalUsuarios);
        System.out.println("Top 5 usuarios com mais postagens: ");
        top5UsuariosPostagens();
        System.out.println("\nTop 10 usuarios com mais comentarios: ");
        top10UsuariosComentarios();
        System.out.println("\nTop 5 postagens mais comentadas: ");
        top5PostagensComentarios();
        System.out.println("--------------------");
        }

    public void top5UsuariosPostagens(){
        ArrayList<Usuario> usuariosOrdenados;
        usuariosOrdenados = dados.usuarios;
        Collections.sort(usuariosOrdenados); //ordenar a lista de usuarios em ordem DECRESCENTE de acordo com a quantidade de postagens

        int posicao = 1;
        new Usuario();
        Usuario u;
        for(int i = 0; i <= 5; i++){
            u = usuariosOrdenados.get(i);
            System.out.printf("\t%do Lugar: %s, %d post(s).\n", posicao, u.getNome(), u.getQntPostagens());
            posicao++;
        }
    }

    public void top10UsuariosComentarios(){
        ArrayList<Usuario> usuariosOrdenadosPorComentarios;
        usuariosOrdenadosPorComentarios = dados.usuarios;
        Collections.sort(usuariosOrdenadosPorComentarios);

        int posicao = 1;
        Usuario u;
        for(int i = 0; i < 10; i++){
            u = usuariosOrdenadosPorComentarios.get(i);
            System.out.printf("\t%do Lugar: %s, %d comentario(s).\n", posicao, u.getNome(), u.getQntComentarios());
            posicao++;
        }
    }

    public void top5PostagensComentarios(){
        ArrayList<Postagem> postagensOrdenadasPorComentarios;
        postagensOrdenadasPorComentarios = dados.postagensAutorizadas;
        Collections.sort(postagensOrdenadasPorComentarios); //ordenar a lista de postagens em ordem DECRESCENTE de acordo com a uantidade de comentarios

        int posicao = 1;
        Postagem p = new Postagem();
        for(int i = 0; i <= 5; i++){
            p = postagensOrdenadasPorComentarios.get(i);
            System.out.printf("\t%do Lugar: Identificador: %d (%d comentarios).\n", posicao, p.getIdentificador(), p.getQntdComentarios());
            posicao++;
        }
    }
}
