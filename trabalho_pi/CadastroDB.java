
    // OK

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class CadastroDB{

    public static int selID_user(String email){

        
        try {
            PreparedStatement sID = null;
            ResultSet resID = null;
            Connection conn = null;
            String selID = "SELECT id FROM Usuarios WHERE email = ?";
            conn = Conexao.getConexao();
            sID = conn.prepareStatement(selID);
            sID.setString(1, email);

            resID = sID.executeQuery();

            int IDuser = -1;
            if (resID.next()) {
                IDuser = resID.getInt("id");
                System.out.println("");
                Usuarios.setIDuser(IDuser);

                return IDuser;
            }

            
        } catch (Exception e) {
            // TODO: handle exception
        }
        
        return 0;

    }


    public static void selUF(){
        
        
        try {
        PreparedStatement sUF = null;
        ResultSet resUF = null;
        Connection conn = null;

        String sel_uf = "SELECT fr.id_estado FROM fornecedores AS fr WHERE fr.id = ?";

        conn = Conexao.getConexao();
        sUF = conn.prepareStatement(sel_uf);
        sUF.setInt(1, Usuarios.getIDforn());

        resUF = sUF.executeQuery();

        int iduf = -1;
        if (resUF.next()) {
            iduf = resUF.getInt("id_estado");
            System.out.println("");
            Usuarios.setID_UF(iduf);

        }

        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
    }

    public static void selFor(){
        
        try {
            String selForn = "SELECT id FROM fornecedores WHERE id_usuariof = ?";

            selID_user(Usuarios.getEmailCadastro());
    
            PreparedStatement sFor = null;
            ResultSet idfor = null;
            Connection conn = null;

            conn = Conexao.getConexao();
            sFor = conn.prepareStatement(selForn);
            sFor.setInt(1, Usuarios.getIDuser());

            idfor = sFor.executeQuery();
            
            int ID_FORN = -1;
            if (idfor.next()) {
                ID_FORN = idfor.getInt("id");
                Usuarios.setIDforn(ID_FORN);
                
            }

        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }

    }


    // selecionar o id do cliente ---------------
    
    public static void selCli(){
        
        try {
            String selForn = "SELECT id FROM clientes WHERE id_usuarioc = ?";
    
            PreparedStatement sCli = null;
            ResultSet idcli = null;
            Connection conn = null;

            conn = Conexao.getConexao();
            sCli = conn.prepareStatement(selForn);
            sCli.setInt(1, Usuarios.getIDuser());

            idcli = sCli.executeQuery();

            int ID_CLI = -1;
            if (idcli.next()) {
                ID_CLI = idcli.getInt("id");
                System.out.println("");
                Usuarios.setIDclient(ID_CLI);

            }

        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
        // SELECT tp.valor FROM tabela_preco as tp, estados as es WHERE ? = tp.UF
        // SELECT Nome_UF FROM estados 
    }


    // -------------------LOGIN---------------------
    public static int fazerLogin(String email, String passw) {

        
        try {
        String sql = "SELECT id_tipo FROM usuarios WHERE email = ? AND senha = ?";
    
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;

        conn = Conexao.getConexao();
        ps = conn.prepareStatement(sql);
        ps.setString(1, email);
        ps.setString(2, passw);

        rs = ps.executeQuery();

        if (rs.next()) {
            int idTipo = rs.getInt("id_tipo");
            System.out.println("");
            System.out.println("\n╔══════════════════════════════════════╗");
            System.out.println("║      LOGIN EFETUADO COM SUCESSO!     ║");
            System.out.println("╚══════════════════════════════════════╝");

            if (idTipo == 1) { // é cliente
                selID_user(email);
                selCli();
                return 1;

            } else if (idTipo == 2) { // é fornecedor
                selID_user(email);
                selFor();
                selUF();
                return 2;

            }
        } 
        return 0;

    } catch (Exception e) {
        e.printStackTrace();
        return -1;
    }
}


 
 
    // funcao de cadastrar dados gerais de usuarios
    public static void cadastrarUser(){
        
        try {
            
            String sql = "INSERT INTO Usuarios VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = null;
                // nome, email, documento, cep, num casa, senha

            ps = Conexao.getConexao().prepareStatement(sql);

            ps.setString(1,null);
            ps.setInt(2, Usuarios.getTipouser());

            Usuarios.setIDuser(selID_user(Usuarios.getEmailCadastro())); // set id do usuario no setIDuser
            ps.setString(3, Usuarios.getNomeCadastro()); 
            
            ps.setString(4, Usuarios.getEmailCadastro());
            ps.setString(5, Usuarios.getdocCadastro()); 
            ps.setString(6, Usuarios.getCEP());
            ps.setInt(7, Usuarios.getNum_casa());
            ps.setString(8, Usuarios.getSenhaCadastro());

            ps.execute();  
            //System.out.println("\nUsuario cadastrado com sucesso!\n");

            System.out.println("╔══════════════════════════════════════╗");
            System.out.println("║   USUÁRIO CADASTRADO COM SUCESSO!    ║");
            System.out.println("╚══════════════════════════════════════╝");

        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }      
    }



    // funcao de cadastrar dados especificos de clientes
    public static void cadastrarCli() {
        
        try {
        
        Connection conexao = null;
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        ResultSet rs = null;
        conexao = Conexao.getConexao();

        // Primeiro: pegar o ID do usuário com base no nome
        String sqlSelect = "SELECT id FROM Usuarios WHERE Documento = ?";

        ps = conexao.prepareStatement(sqlSelect);
        ps.setString(1, Usuarios.getdocCadastro());
        rs = ps.executeQuery();

        int idUsuario = -1;
        if (rs.next()) {
            idUsuario = rs.getInt("id");
            Usuarios.setIDuser(idUsuario);
        } else {
            System.out.println("Usuário não encontrado!");
            return;
        }

        // Segundo: inserir cliente com o ID do usuário
        String insertCli = "INSERT INTO Clientes VALUES (NULL, ?, ?, ?)";

        ps2 = conexao.prepareStatement(insertCli);
        ps2.setInt(1, idUsuario);
        ps2.setString(2, Usuarios.getTelcli());
        ps2.setString(3, Usuarios.getDesc());

        ps2.executeUpdate();
        /*System.out.println("\nCliente cadastrado com sucesso.");
        System.out.println("══════════════════════════════════════\n");*/

        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║   CLIENTE CADASTRADO COM SUCESSO!    ║");
        System.out.println("╚══════════════════════════════════════╝");

        selCli();

    } catch (SQLException e) {
        e.printStackTrace();
    }
    }



public static void cadastrarFor() {

    
    
    try {
        Connection conexao = null;
        PreparedStatement psSelectUsuario = null;
        PreparedStatement psInsertFornecedor = null;
        PreparedStatement psSelectTabela = null;
        ResultSet rsUsuario = null;
        ResultSet rsEstado = null;
        conexao = Conexao.getConexao();

        // 1. Buscar ID do usuário pelo email
        String sqlSelectUsuario = "SELECT id FROM Usuarios WHERE Email = ?";

        psSelectUsuario = conexao.prepareStatement(sqlSelectUsuario);
        psSelectUsuario.setString(1, Usuarios.getEmailCadastro());
        rsUsuario = psSelectUsuario.executeQuery();

        int idUsuario = -1;
        if (rsUsuario.next()) {
            idUsuario = rsUsuario.getInt("id");
        } else {
            System.out.println("Usuário não encontrado!");
            return;
        }

        String sqlInsertEstado = "SELECT id FROM estados WHERE Nome_uf = ?";
        psSelectTabela = conexao.prepareStatement(sqlInsertEstado);

        psSelectTabela.setString(1, Usuarios.getUF());
        rsEstado = psSelectTabela.executeQuery(); // CORRETO para INSERT

        int idUF = -1;
        if (rsEstado.next()) {
            idUF = rsEstado.getInt(1);
            Usuarios.setID_UF(idUF);

        } else {
            throw new SQLException("Falha ao obter o ID da UF inserida.");
        }

        // 3. Inserir o fornecedor
        String sqlInsertFornecedor = "INSERT INTO Fornecedores VALUES (NULL, ?, ?, ?, ?)";
        psInsertFornecedor = conexao.prepareStatement(sqlInsertFornecedor);
        psInsertFornecedor.setInt(1, idUsuario);
        psInsertFornecedor.setInt(2, Usuarios.getID_UF());
        psInsertFornecedor.setString(3, Usuarios.getRazaosoc());
        psInsertFornecedor.setString(4, Usuarios.getTelfor());

        psInsertFornecedor.executeUpdate(); // CORRETO para INSERT

        /*System.out.println("\nFornecedor cadastrado com sucesso.");
        System.out.println("══════════════════════════════════════\n");*/

        selFor();

        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║  FORNECEDOR CADASTRADO COM SUCESSO!  ║");
        System.out.println("╚══════════════════════════════════════╝");

    } catch (SQLException e) {
        e.printStackTrace();

    } 
}

}