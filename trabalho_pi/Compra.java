import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Compra {

    public static void updateOf(){

        
        try {
            Connection conn = null;
            PreparedStatement psUpdt = null;
            
            String sql = "UPDATE Ofertas SET Disponibilidade = 0 WHERE Data_emissao != ?";
            conn = Conexao.getConexao();
            psUpdt = conn.prepareStatement(sql);
            psUpdt.setString(1, Validacao.setarData());

            psUpdt.executeUpdate();

        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    public static void realizaCompra(int qtd_compra, int id_compra){

        try {
            Connection conn = null;
            PreparedStatement selVal = null;
            ResultSet rsVal = null;
            
            String selvalor = "SELECT Valor_credito FROM ofertas WHERE id = ?";
    
            conn = Conexao.getConexao();
            selVal = conn.prepareStatement(selvalor);
            selVal.setInt(1, id_compra);
            rsVal = selVal.executeQuery();
    
            float valor = -1;
            if(rsVal.next()) {
                valor = rsVal.getFloat("Valor_credito");
    
            } else {
                throw new SQLException("Falha ao obter o ID da UF inserida.");
            }

            PreparedStatement selPgt = null;

            String insPgt = "INSERT INTO Vendas VALUES(NULL, ?, ?, ?, ?, ?)";

            selPgt = conn.prepareStatement(insPgt);
            selPgt.setInt(1, id_compra);
            selPgt.setInt(2, Usuarios.getIDclient());
            selPgt.setInt(3, qtd_compra);
            selPgt.setString(4, Validacao.setarData());
            selPgt.setFloat(5, (valor * qtd_compra));

            selPgt.executeUpdate();
            System.out.println("Compra Solicitada!\n");
            Pagamentos.registrarPagamento(id_compra, valor*qtd_compra);
            System.out.println("Compra realizada com sucesso!");
            System.out.println("══════════════════════════════════════\n");

        } catch (Exception e) {
            e.printStackTrace(); 
            // TODO: handle exception
        }


    }


    public static void atualizaOferta(){
        
        try {
            Connection conn = null;
            PreparedStatement exUpdt = null;
            
            String uptd = "UPDATE ofertas SET disponibilidade = 0 where Quantidade_disp = 0";
            
            conn = Conexao.getConexao();
            exUpdt = conn.prepareStatement(uptd);
            exUpdt.executeUpdate();
            updateOf();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    
    public static void atualizarCompra(int qtd_comprada, int id){
        
        try {
            
            Connection conn = null;
            PreparedStatement exUpdt = null;
            
            String sqlUpdt = "UPDATE ofertas SET Quantidade_disp = (Quantidade_disp - ?) WHERE id = ?";

            conn = Conexao.getConexao();
            exUpdt = conn.prepareStatement(sqlUpdt);
            exUpdt.setInt(1, qtd_comprada);
            exUpdt.setInt(2, id);

            int linhas_afetadas = exUpdt.executeUpdate();
            
            if (linhas_afetadas > 0) {
                System.out.println("");
            } else {
                System.out.println("Erro: Nenhuma oferta foi atualizada. Verifique o ID selecionado.");
            }


        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
    }

    public static void comprar() {

        
        try {
            CadastroDB.selID_user(Usuarios.getEmailCadastro());
            CadastroDB.selCli();
    
            System.out.print("Digite a quantidade mínima que deseja comprar: ");
            int qtd_compra = Input.teclado.nextInt();
            Input.teclado.nextLine();
    
            Connection conn = null;
            PreparedStatement selvend = null;
            ResultSet res = null;
            String selVendas = "SELECT id, Valor_credito, Data_emissao, Quantidade_disp " +
                               "FROM ofertas WHERE Quantidade_disp >= ? AND disponibilidade = 1 ";

            conn = Conexao.getConexao();
            selvend = conn.prepareStatement(selVendas);
            selvend.setInt(1, qtd_compra);
            res = selvend.executeQuery();

            boolean encontrou = false;

            System.out.println("\n===== Ofertas Disponíveis =====");

            while (res.next()) {
                encontrou = true;
                int id = res.getInt("id");
                double valorCredito = res.getDouble("Valor_credito");
                String data = res.getString("Data_emissao");
                int quantidadeDisp = res.getInt("Quantidade_disp");

                System.out.println("Código: " + id +
                                   " | Valor por crédito: R$" + valorCredito +
                                   " | Data: " + data +
                                   " | Quantidade: " + quantidadeDisp);
            }

            if (!encontrou) {
                System.out.println("Nenhuma oferta disponível com a quantidade desejada. Volte mais tarde!\n");
                return;
            }

            System.out.print("\nDigite o código da oferta que deseja comprar: ");
            int id_compra = Input.teclado.nextInt();
            Input.teclado.nextLine();

            realizaCompra(qtd_compra, id_compra);
            
            
            atualizarCompra(qtd_compra, id_compra);

        } catch (Exception e) {
            System.out.println("Erro ao realizar a compra: " + e.getMessage());
        }
        
    }
}
