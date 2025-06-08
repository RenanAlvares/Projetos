// Colocar UF em maiusculo na leitura

public class CadastroInterface {

    static int tipo;
    static String senhaCadastro;
    static String confirmaSenha;
    public static void cadastrarDados(){
        final Usuarios user = new Usuarios();

        System.out.println("╔════════════════════════════════════╗");
        System.out.println("║              Cadastro              ║");
        System.out.println("╚════════════════════════════════════╝");

        System.out.println("Tipo de usuário: \n1- Sou Cliente \n2- Sou Fornecedor");
        System.out.print("Escolha uma opção: ");
        tipo = Input.teclado.nextInt();
        Input.teclado.nextLine();
        
        if(tipo != 1 && tipo != 2){
            tipo = Validacao.validarTipo();
        }

        while (true) {

            System.out.print("Nome Completo: ");
            user.setNomeCadastro(Input.teclado.nextLine());
            if (Validacao.validarNomeCompleto(Usuarios.getNomeCadastro())) {
                break;
            }
            System.out.println("══════════════════════════════════════════════════════");
            System.out.println("Nome incompleto!");
            System.out.println("Por favor, insira seu nome completo (nome e sobrenome).");
            System.out.println("══════════════════════════════════════════════════════\n");
        }


        while (true) {

            System.out.println("Tipo de pessoa: \n1 - Pessoa Física \n2 - Pessoa Jurídica");
            System.out.print("Escolha Uma Opção: ");
            int respostaUsuario = Input.teclado.nextInt();
            Input.teclado.nextLine();

            if(respostaUsuario == 1){
                System.out.print("CPF: ");
                user.setdocCadastro(Input.teclado.next());
                if (Validacao.validarCPF(Usuarios.getdocCadastro())) {
                    break;
                }                
                System.out.println("══════════════════════════════════════════════════════");
                System.out.println("CPF inválido!");
                System.out.println("Por favor, insira um CPF válido utilizando apenas números.");
                System.out.println("Exemplo de formato correto: 12345678900");
                System.out.println("Não use pontos, traços ou barras.");
                System.out.println("══════════════════════════════════════════════════════\n");

            }else if(respostaUsuario == 2){
                System.out.print("CNPJ: ");
                user.setdocCadastro(Input.teclado.next());
                if (Validacao.validarCNPJ(Usuarios.getdocCadastro())) {
                    break;
                }

                System.out.println("══════════════════════════════════════════════════════");
                System.out.println("CNPJ inválido!");
                System.out.println("Por favor, insira um CNPJ válido utilizando apenas números.");
                System.out.println("Exemplo de formato correto: 12345678000195");
                System.out.println("Não use pontos, traços ou barras.");
                System.out.println("══════════════════════════════════════════════════════\n");

            }else{

                System.out.println("══════════════════════════════════════════════════════");
                System.out.println("Tipo de pessoa inválida!");
                System.out.println("Por favor, insira uma opção válida para cadastrar o tipo de documento.");
                System.out.println("══════════════════════════════════════════════════════\n");
            }
        }

        /*System.out.print("CEP: ");
        user.setCEP(teclado.next());*/
        
         while (true) {
            System.out.print("CEP: ");
            user.setCEP(Input.teclado.next().trim());
            if (Validacao.validarCEP(Usuarios.getCEP())) {
                break; // Sai do loop se o CEP for válido
            }
                System.out.println("══════════════════════════════════════════════════════");
                System.out.println("CEP inválido!");
                System.out.println("Por favor, insira um CEP válido utilizando apenas números.");
                System.out.println("Exemplo de formato correto: 12345678");
                System.out.println("Não use pontos, traços ou barras.");
                System.out.println("══════════════════════════════════════════════════════\n");
        }

        System.out.print("Digite o número do endereço: ");
        user.setNum_casa(Input.teclado.nextInt());
        Input.teclado.nextLine();

        /*System.out.print("Email: ");
        user.setEmailCadastro(teclado.next());*/

        while (true) {
            System.out.print("Email: ");
            Usuarios.setEmailCadastro(Input.teclado.next());
            if (Validacao.validarEmail(Usuarios.getEmailCadastro())) {
                break; // Sai do loop se o email for válido
            }
            System.out.println("══════════════════════════════════════════════════════");
            System.out.println("E-mail inválido!");
            System.out.println("O e-mail deve conter a terminação \".com\".");
            System.out.println("Exemplo: usuario@exemplo.com");
            System.out.println("══════════════════════════════════════════════════════\n");
        }

        /*System.out.print("Senha: ");
        user.setSenhaCadastro(teclado.next());

        System.out.println("Confirme a senha: ");
        user.setConfirmaSenha(teclado.next());*/


        while (true) {
            System.out.print("Senha: ");
            senhaCadastro = Input.teclado.next();
            if (!Validacao.validarSenha(senhaCadastro)) {
                System.out.println("══════════════════════════════════════════════════════");
                System.out.println("Senha inválida!");
                System.out.println("A senha deve conter no mínimo 6 caracteres.");
                System.out.println("══════════════════════════════════════════════════════\n");
                continue; // Volta para pedir a senha novamente
            }
            System.out.print("Confirme a senha: ");
            confirmaSenha = Input.teclado.next();

            if (senhaCadastro.equals(confirmaSenha)) {
                Usuarios.setSenhaCadastro(senhaCadastro);
                break; // Sai do loop se as senhas coincidem
            } else {
                System.out.println("══════════════════════════════════════════════════════");
                System.out.println("As senhas não coincidem!");
                System.out.println("Certifique-se de digitar a mesma senha nos dois campos.");
                System.out.println("══════════════════════════════════════════════════════\n");
            }
        }

        user.setTipouser(tipo);
        CadastroDB.cadastrarUser();
       

        if(tipo == 1){ // é cliente

            CadastroDB.selCli();
            System.out.print("Digite o Telefone Pessoal: ");
            user.setTelcli(Input.teclado.next());
            
            Input.teclado.nextLine();

            System.out.print("Adicione uma descrição ao seu cadastro: "); // sout para perguntar se quer adicionar alguma descricao no cadastro
            user.setDesc(Input.teclado.nextLine());
            CadastroDB.cadastrarCli();

            Cliente.MenuCompras();


        } else if(tipo == 2){ // é fornecedor
            // sout para solicitar os dados

            CadastroDB.selFor();
            System.out.print("Digite o Telefone Comercial: ");
            user.setTelfor(Input.teclado.next());

            // fazer ficar tudo maiusculo
            System.out.print("Digite a UF de origem: ");
            Usuarios.setUF(Input.teclado.next().toUpperCase());

            System.out.print("Digite a Razão Social: ");
            Input.teclado.nextLine();
            user.setRazaosoc(Input.teclado.nextLine());

            CadastroDB.cadastrarFor();
            CadastroDB.selCli();
            Fornecedor.MenuVendas();
            
        } 
    }
} 