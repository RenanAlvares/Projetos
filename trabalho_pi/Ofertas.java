import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Ofertas {

    

    public static void Vender(){

        Connection conn = null;
        PreparedStatement psSelTable = null;
        PreparedStatement psinsert = null;
        //ResultSet rsInsert = null;
        ResultSet rs_table = null;

        final int qtdvenda;
        System.out.println("Digite a quantidade de créditos ofertados");
        qtdvenda = Input.teclado.nextInt();
        Input.teclado.nextLine();

        try {
            
            String selprice = "SELECT e.valor FROM estados as e WHERE e.id = ?";
            
            conn = Conexao.getConexao();
            psSelTable = conn.prepareStatement(selprice);
            psSelTable.setInt(1, Usuarios.getID_UF());
            rs_table = psSelTable.executeQuery();
            
            float price = -1;
            if(rs_table.next()){
                price = rs_table.getFloat("valor");
            }  else {

                System.out.println("Valor não encontrado");
            }
            
            // faz a oferta de vendas
            String addvenda = "INSERT INTO ofertas VALUES (NULL, ?, ?, ?, ?, ?, ?)";
            

            psinsert = conn.prepareStatement(addvenda);
            psinsert.setInt(1, Usuarios.getIDforn());
            psinsert.setFloat(2, price);

            psinsert.setString(3, Validacao.setarData());

            psinsert.setInt(4, qtdvenda);
            psinsert.setInt(5, 1);
            psinsert.setInt(6, qtdvenda);

            psinsert.executeUpdate();
            System.out.println("══════════════════════════════════════");
            System.out.println("Oferta criada com sucesso!");
            System.out.println("══════════════════════════════════════");
           

        } catch (SQLException e) {
            e.printStackTrace(); // ou logar em sistema

            // TODO: handle exception
        }
    }


}   
