
public class Usuarios {
    
    // OK
    
    private static String nomeCadastro; // nome cadastrado
    private static String senhaCadastro; // senha cadastrada
    private static String confirmaSenha; // confirma senha, validação para não haver erro.
    private static String docCadastro;
    private static String emailCadastro;
    private static String CEP;
    private static int num_casa;
    
    private static String telcli; // telefone de cliente
    private static String desc; // descrição cliente

    private static String telfor; // telefone fornecedor
    private static String razaosoc; // Razao social for.

    private static int tipouser; 

    private static int IDforn;
    private static int IDuser;
    private static int IDclient;
    private static int ID_UF;

    public static int getID_UF() {
        return ID_UF;
    }
    public static void setID_UF(int iD_UF) {
        ID_UF = iD_UF;
    }
    private static String UF;

    public static int getIDforn() {
        return IDforn;
    }
    public static void setIDforn(int iDforn) {
        IDforn = iDforn;
    }
    public static int getIDuser() {
        return IDuser;
    }
    public static void setIDuser(int iDuser) {
        IDuser = iDuser;
    }
    public static int getIDclient() {
        return IDclient;
    }
    public static void setIDclient(int iDclient) {
        IDclient = iDclient;
    }
    
    public static String getUF() {
        return UF;
    }
    public static void setUF(String uF) {
        UF = uF;
    }
    public static String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        Usuarios.desc = desc;
    }

    public static String getNomeCadastro() {
        return nomeCadastro;
    }
    public void setNomeCadastro(String nomeCadastro) {
        Usuarios.nomeCadastro = nomeCadastro;
    }
    public static String getSenhaCadastro() {
        return senhaCadastro;
    }
    public static void setSenhaCadastro(String senhaCadastro) {
        Usuarios.senhaCadastro = senhaCadastro;
    }
    public static String getConfirmaSenha() {
        return confirmaSenha;
    }
    public void setConfirmaSenha(String confirmaSenha) {
        Usuarios.confirmaSenha = confirmaSenha;
    }
    public static String getdocCadastro() {
        return docCadastro;
    }
    public void setdocCadastro(String docCadastro) {
        Usuarios.docCadastro = docCadastro;
    }
    public static String getEmailCadastro() {
        return emailCadastro;
    }
    public static void setEmailCadastro(String emailCadastro) {
        Usuarios.emailCadastro = emailCadastro;
    }
    public static String getCEP() {
        return CEP;
    }
    public void setCEP(String cEP) {
        CEP = cEP;
    }
    public static int getNum_casa() {
        return num_casa;
    }
    public void setNum_casa(int num_casa) {
        Usuarios.num_casa = num_casa;
    }
    public static String getTelcli() {
        return telcli;
    }
    public void setTelcli(String telcli) {
        Usuarios.telcli = telcli;
    }
    public static String getTelfor() {
        return telfor;
    }
    public void setTelfor(String telfor) {
        Usuarios.telfor = telfor;
    }
    public static String getRazaosoc() {
        return razaosoc;
    }
    public void setRazaosoc(String razaosoc) {
        Usuarios.razaosoc = razaosoc;
    }
    public static int getTipouser() {
        return tipouser;
    }
    public void setTipouser(int tipouser) {
        Usuarios.tipouser = tipouser;
    }
    
}

