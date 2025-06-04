
public class Cliente {
    
    public static void MenuCompras() {
        int resposta;

        while (true) {
            System.out.println("\n╔════════════════════════════════════╗");
            System.out.println("║          Menu de Compras           ║");
            System.out.println("╚════════════════════════════════════╝");
                System.out.println("1 - Comprar");
                System.out.println("2 - Relatórios");
                System.out.println("3 - Finalizar o sistema");
                System.out.print("Escolha uma opção: ");
                //resposta = teclado.nextInt();
                resposta = Input.teclado.nextInt();
                Input.teclado.nextLine();
                if (resposta == 1) {

                    Compra.comprar(); // arrumar aqui -------------------------------------

                } else if (resposta == 2) {

                    Relatorios.relatorioClientes();
                    break;

                } else if (resposta == 3) {

                    System.out.println("\nFinalizando o sistema...\n");
                    System.exit(0); // Encerra a JVM

                } else {
                    System.out.println("Opção inválida! Por favor, digite uma opção válida.");
                }
            } 
        }
    }