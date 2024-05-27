import java.util.Locale;
import java.util.Scanner;
import org.json.JSONObject;

public class Main {
  public static void main(String[] args) {
    Locale.setDefault(Locale.US);
    Scanner teclado = new Scanner(System.in);


    System.out.println("Bem vindo ao sistema de calculo de salario!\n");
    
    System.out.println("Informe seu nome: ");
    String nome = teclado.nextLine();
    System.out.println("Informe seu cpf: ");
    int cpf = teclado.nextInt();
    
    System.out.println("Informe seu cep: ");
    String cep = teclado.nextLine();
    
    if (ValidadorCEP.validarCEP(cep)) {
      JSONObject cepInfo = ConsultaCEP.consultarCEP(cep.replace("-", ""));}
    if (cepInfo != null) {
      System.out.println("CEP encontrado:");
      System.out.println("Logradouro: " + cepInfo.getString("logradouro"));
      System.out.println("Bairro: " + cepInfo.getString("bairro"));
      System.out.println("Cidade: " + cepInfo.getString("localidade"));
      System.out.println("Estado: " + cepInfo.getString("uf"));
      } else {
      System.out.println("CEP não encontrado.");
     } if() {
      System.out.println("CEP inválido.");
     }
  
    System.out.print("Digite sua renda anual com salário: ");
    double rendaAnual = teclado.nextDouble();
    System.out.print("Digite sua renda anual com prestação de serviço: ");
    double rendaPrestacao = teclado.nextDouble();
    System.out.print("Digite sua renda anual com ganho de capital: ");
    double rendaCapital = teclado.nextDouble();
    System.out.print("Gastos médicos: ");
    double gastosMedicos = teclado.nextDouble();
    System.out.print("Gastos educacionais: ");
    double gastosEducacionais = teclado.nextDouble();
    
    System.out.println("\nRelatório do Imposto de Renda\n");
    
    double rendaMensal = rendaAnual / 12;
    double impostoAnual=0.0;

    if(rendaMensal >= 1320.00){
      impostoAnual = rendaMensal * 0.075;
      System.out. println("A taxa do INSS foi de 7,5%");
    }else if(rendaMensal >= 1320.01 && rendaMensal <= 2571.29){
      impostoAnual = rendaMensal * 0.09;
        System.out. println("A taxa do INSS foi de 9%");
    }else if(rendaMensal >= 2571.30 &&  rendaMensal <= 3856.94){
      impostoAnual = rendaMensal *0.12;
        System.out. println("A taxa do INSS foi de 12%");
    }else if (rendaMensal >= 3856.95 && rendaMensal > 7507.49){
      impostoAnual = rendaMensal * 0.14;
        System.out. println("A taxa do INSS foi de 14%");
    }
    
    double impostoSalario = rendaAnual * impostoAnual / 100;
    System.out.println("Renda consolidada:");
    System.out.printf("Imposto sobre salário: %.2f ", impostoSalario);

    double impostoPJ;

    if (rendaPrestacao <= 0) {
      impostoPJ = 0;
    } else {
      impostoPJ = 15;
    }

    double impostoPrestacao = rendaPrestacao * impostoPJ / 100;
    System.out.println();
    System.out.printf("Imposto sobre serviços: %.2f ", impostoPrestacao);

    double impostoCapital;

    if (rendaCapital <= 0) {
      impostoCapital = 0;
    } else {
      impostoCapital = 20;
    }

    double impostoCapital2 = rendaCapital * impostoCapital / 100;
    System.out.println();
    System.out.printf("Imposto sobre ganho de capital: %.2f ", impostoCapital2);

    double impostoBruto = (impostoSalario + impostoPrestacao + impostoCapital2);
    System.out.println();

    double maximoDedutivel = impostoBruto * 0.3;
    System.out.println();

    double gastoDedutivel = gastosMedicos + gastosEducacionais;
    System.out.println("DEDUÇÕES:");
    System.out.printf("Máximo dedutível: %.2f%n", maximoDedutivel);
    System.out.printf("Gastos dedutíveis: %.2f", gastoDedutivel);

    System.out.println();
    System.out.println();
    System.out.println("RESUMO:");
    System.out.printf("Imposto bruto total: %.2f%n", impostoBruto);

    double abatimento;

    if (maximoDedutivel > gastoDedutivel) {
      abatimento = gastoDedutivel;
    } else {
      abatimento = maximoDedutivel;
    }

    System.out.printf("Abatimento: %.2f%n", abatimento);
    System.out.printf("Imposto devido: %.2f", impostoBruto - abatimento);
    teclado.close();
  
  
  }
}