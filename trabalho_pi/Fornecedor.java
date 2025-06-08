

public class Fornecedor {

    public Fornecedor(){        
    }

    public static void MenuVendas() {
        int resposta;

        while (true) {

            CadastroDB.selFor();

            System.out.println("\n╔════════════════════════════════════╗");
            System.out.println("║           Menu de Vendas           ║");
            System.out.println("╚════════════════════════════════════╝");
                System.out.println("1 - Vender");
                System.out.println("2 - Relatórios");
                System.out.println("3 - Finalizar o sistema");
                System.out.print("Escolha uma opção: ");
                resposta = Input.teclado.nextInt();
                Input.teclado.nextLine();
                
                if (resposta == 1) {

                    Ofertas.Vender();
                } else if (resposta == 2) {
                    Relatorios.relatorioFornecedores();
                    break;
                } else if (resposta == 3) {

                    System.out.println("\nFinalizando o sistema...\n");
                    System.exit(0); // Encerra a JVM
                } else {

                    System.out.println("Opção inválida! Por favor, tente novamente.");
                }
            }
            System.out.println("══════════════════════════════════════");
        }
    }
