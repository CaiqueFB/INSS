public class ValidadorCEP {
    public static boolean validarCEP(String cep) {
        return cep.matches("\\d{5}-?\\d{3}");
    }
}
