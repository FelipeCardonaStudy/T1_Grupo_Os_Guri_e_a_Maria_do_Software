public class App {
    
    public void executa() {
        Dados dados = new Dados();
        
        if (menuUsuario(dados) == true)
        {
            menuOp();
        }
    }









    private void menuOp() {
        System.out.println("MENU\n");
        // TODO
    }



    // Escreve lista de usuários do sistema
    private boolean menuUsuario(Dados dados) {
        System.out.println("\nEscolha o Usuário:");
        System.out.println("-----------------------------");
        
        try {
            for (int i = 0; i < dados.getUserLength(); i++)
            {
                System.out.println("("+ i+1 +") User @" + dados.getUserNameByIndex(i));
            }
        } catch (NullPointerException e) {
            System.out.println("Ainda não há nenhum usuário (!)");
            return false; 
        }
       return true;
    }



}
