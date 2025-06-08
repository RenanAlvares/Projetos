import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validacao {

    public static boolean validarNomeCompleto(String nome) {
        // Garante que não é nulo, não está vazio, contém um espaço (para nome e sobrenome)
        // E agora, que não contém dígitos (números)
        return nome != null && !nome.trim().isEmpty() && nome.trim().contains(" ") && !nome.matches(".\\d.");
    }

    public static boolean validarCPF(String cpf) {
        if (cpf == null) return false;
        cpf = cpf.replaceAll("[^0-9]", ""); // Remove qualquer caractere que não seja número

        // Simplesmente verifica se tem 11 dígitos e não é uma sequência de dígitos iguais
        if (cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        // Tenta converter para Long para garantir que são apenas números,
        // embora o replaceAll já cuide da maioria dos casos.
        try {
            Long.parseLong(cpf);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static boolean validarCidade(String cidade) {
        return cidade != null && !cidade.trim().isEmpty();
    }

    public static boolean validarEstado(String estado) {
        // Verifica se o estado não é nulo e tem exatamente 2 caracteres (UF)
        return estado != null && estado.length() == 2;
    }

    public static boolean validarEmail(String email) {
        if (email == null || email.trim().isEmpty()) return false;
        // Regex mais robusta para validação de email
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean validarCEP(String cep) {
        if (cep == null) return false;
        cep = cep.replaceAll("[^0-9]", ""); // Remove qualquer caractere que não seja número
        return cep.length() == 8;
    }

    public static boolean validarSenha(String senha) {
        // Requisito mínimo de 6 caracteres
        return senha != null && senha.length() >= 6;
    }

    public static boolean validarCNPJ(String cnpj) {
        if (cnpj == null) return false;
        cnpj = cnpj.replaceAll("[^0-9]", ""); // Remove qualquer caractere que não seja número

        // verifica se tem 14 dígitos e não é uma sequência de dígitos iguais
        if (cnpj.length() != 14 || cnpj.matches("(\\d)\\1{13}")) {
            return false;
        }

        // Tenta converter para Long para garantir que são apenas números.
        try {
            Long.parseLong(cnpj);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static int validarTipo(){

        int validar;

        while (true) { 
            System.out.println("Tipo invalido, Por favor insira um tipo valido!");
            validar = Input.teclado.nextInt();
            if(validar == 1 || validar == 2){
                return 1;
            }
            
        }
    }

    public static String setarData(){

        LocalDateTime dataExpiracaoOfertaAtual = LocalDateTime.now();
        String data = dataExpiracaoOfertaAtual.format(DateTimeFormatter.ofPattern("MM/yy"));

        return data;
    } 





}