
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Relatorios{


    public static void ofertasCriadas(){

        Connection conn = null;
        PreparedStatement psSel = null;
        ResultSet res = null;

        try {
            
            String sql = "SELECT id, valor_credito, Data_emissao, Quant_ofertada FROM Ofertas WHERE id_fornecedor = ?";
            conn = Conexao.getConexao();

            int idf = Usuarios.getIDforn();

            psSel = conn.prepareStatement(sql);
            psSel.setInt(1, idf);
            res = psSel.executeQuery();

            boolean encontrou = false;

            System.out.println("===== Relatório de ofertas criadas =====\n");
            while (res.next()) {
                encontrou = true;
                int id = res.getInt("id");
                float valor = res.getFloat("valor_credito");
                String data = res.getString("Data_emissao");
                int qtd_oferta = res.getInt("Quant_ofertada");

                //int total = valor*qtd_oferta;

                System.out.println("Número da Oferta: " + id +
                                    " | Valor total da oferta: " + (valor*qtd_oferta) +
                                    " | Mês da oferta: " + data +
                                    " | Quantidade Ofertada: " + qtd_oferta);
            }
            if (!encontrou) {
                System.out.println("Nenhuma compra registrada!");
                return;
            }



        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public static void relCompras(){

        
        try {
            System.out.println("===== Relatorio Compras =====");
            
            Connection conn = null;
            PreparedStatement psSel = null;
            ResultSet res = null;
            
            String selCliente = "SELECT id, id_credito, Quantidade_compra, Data_transacao, Valor_transacao FROM vendas WHERE id_cliente = ?";
            
            conn = Conexao.getConexao();
            psSel = conn.prepareStatement(selCliente);

            psSel.setInt(1, Usuarios.getIDclient());
            
            res = psSel.executeQuery();

            boolean encontrou = false;

            while (res.next()) {
                encontrou = true;
                int id = res.getInt("id");
                int id_credito = res.getInt("id_credito");
                int qtd_compra = res.getInt("Quantidade_compra");
                String data = res.getString("Data_transacao");
                float valor = res.getFloat("Valor_transacao");

                System.out.println("Número da compra: " + id +
                                   " | Código do crédito: " + id_credito +
                                   " | Quantidade comprada: " + qtd_compra +
                                   " | Mês da compra: " + data +
                                   " | Valor da compra: R$" + valor);
            }
            if (!encontrou) {
                System.out.println("Nenhuma compra registrada!");
                return;
            }

        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    public static void relatorioFornecedores(){

        int resposta;

        while (true) {
            //separar esse while para a classe relatorio
            System.out.println("\n╔════════════════════════════════════╗");
            System.out.println("║          Relatórios de Ofertas     ║");
            System.out.println("╚════════════════════════════════════╝");
                System.out.println("1 - Gerar Relatório de Ofertas criadas");
                System.out.println("2 - Voltar para o Menu de Ofertas");
                System.out.println("3 - Sair");
                System.out.print("Escolha uma opção: ");
                resposta = Input.teclado.nextInt();

                System.out.println("------------------------------------------"); // Separador após a escolha
                if (resposta == 1) {

                    System.out.println("Relatorio Vendas:");
                    ofertasCriadas();

                } else if (resposta == 2) {

                    Fornecedor.MenuVendas();
                    break; // Sai deste loop para voltar ao menu anterior

                } else if (resposta == 3) {

                    System.out.println("\nFinalizando o sistema...\n");
                    System.exit(0); // Encerra a JVM
                    break;

                } else {
                    System.out.println("Opção inválida! Por favor, tente novamente.");
                }
            }
            System.out.println("══════════════════════════════════════");
    }

    public static void relatorioClientes(){
        int resposta;


        while (true) {
            System.out.println("\n╔════════════════════════════════════╗");
            System.out.println("║         Relatórios de Compras      ║");
            System.out.println("╚════════════════════════════════════╝");
                System.out.println("1 - Gerar Relatório de Compras");
                System.out.println("2 - Voltar para o Menu de Compras/Vendas");
                System.out.println("3 - Sair");
                System.out.print("Escolha uma opção: ");
                resposta = Input.teclado.nextInt();
                System.out.println("------------------------------------------"); // Separador após a escolha
                if (resposta == 1) {
                    relCompras();

                } else if (resposta == 2) {

                    Cliente.MenuCompras();
                    break; // Sai deste loop para voltar ao menu anterior

                } else if (resposta == 3) {

                    System.out.println("Finalizando o sistema...\n");
                    System.exit(0); // Encerra a JVM

                } else {
                    System.out.println("Opção inválida! Por favor, tente novamente.");
                }
            }
            System.out.println("══════════════════════════════════════");
    }

}
