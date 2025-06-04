    // OK

public class Main {

    
    public static void main(String[] args) {

        Compra.atualizaOferta();

        //int respostaForCli;
        int respostaLogCad;
        String email;
        String senha;

        System.out.println("╔════════════════════════════════════╗");
        System.out.println("║       Bem-vindo ao BugigSolar!     ║");
        System.out.println("╚════════════════════════════════════╝\n");

        while (true) {
          System.out.println("\n╔════════════════════════════════════╗");
            System.out.println("║               Opções               ║");
            System.out.println("╚════════════════════════════════════╝");
            System.out.println("1 - Login");
            System.out.println("2 - Fazer Cadastro");
            System.out.print("Escolha uma opção: ");
            respostaLogCad = Input.teclado.nextInt();
            Input.teclado.nextLine();
            
            // fazer a validação de login
            if (respostaLogCad == 1) {
                while (true) {
                    System.out.print("Digite seu email: ");
                    email = Input.teclado.nextLine();
                    Usuarios.setEmailCadastro(email);

                    System.out.print("Digite sua senha: ");
                    senha = Input.teclado.nextLine();
                    Usuarios.setSenhaCadastro(senha);

                    int loginResult = CadastroDB.fazerLogin(email, senha);

                    if (loginResult == 1) { // é cliente
                        Cliente.MenuCompras();
                        break;
                    } else if (loginResult == 2) { // é fornecedor
                        Fornecedor.MenuVendas();
                        break;
                    } else {
                        System.out.println("Usuário não encontrado, por favor tente novamente.");
                    }
                }
                break;
            } else if (respostaLogCad == 2){

                CadastroInterface.cadastrarDados();
                break;
                
            }else{
                System.out.println("══════════════════════════════════════════════════════");
                System.out.println("Opção Inválida!");
                System.out.println("Tente novamente...");
                System.out.println("══════════════════════════════════════════════════════\n");
            }

        }

        System.out.println("══════════════════════════════════════"); // Linha separadora antes da ação

    }
}
