import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Random;

public class Pagamentos {

    public static void registrarPagamento(int idCompra, float valorPago) {

        System.out.println("Opções de pagamento: \n1 - Credito \n2 - Debito \n3 - Pix \n4 - Boleto"); // inserir no banco
        System.out.print("\nDigite sua opção de pagamento: ");
        int idTipoPagamento = Input.teclado.nextInt();
        Input.teclado.nextLine();  // consumir nova linha
        int numTitulo = new Random().nextInt(99998) + 1;

        String sql = "INSERT INTO Pagamento VALUES (NULL, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.getConexao(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idCompra);
            stmt.setInt(2, idTipoPagamento);
            stmt.setDouble(3, valorPago);
            stmt.setString(4, Validacao.setarData());

            stmt.setInt(5, numTitulo);

            stmt.executeUpdate();
            System.out.println("\nPagamento registrado com sucesso!\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}