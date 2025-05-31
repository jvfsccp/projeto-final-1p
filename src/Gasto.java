import java.io.*;
import java.util.Scanner;

public class Gasto {
        static Scanner sc = new Scanner(System.in);
        static final int MAX = 200;
        static String[] gastos = new String[MAX];
        static int totalGastos = 0;

        public static void carregarGastos() {
            if (totalGastos >= MAX) {
                System.out.println("Limite de gastos atingido.");
                return;
            }
            try {
                BufferedReader br = new BufferedReader(new FileReader("gastos.txt"));
                String linha;
                while ((linha = br.readLine()) != null && totalGastos < MAX) {
                    gastos[totalGastos++] = linha;
                }
                br.close();
            } catch (IOException ignored) {}
        }

        public static void cadastrarGasto() {

                System.out.print("Endereço do imóvel: ");
                String endereco = sc.nextLine();
                System.out.print("Tipo de gasto: ");
                String tipo = sc.nextLine();
                System.out.print("Valor do gasto: ");
                double valor = sc.nextDouble();
                sc.nextLine();
                System.out.print("Mês/ano (Ex: 05/2025): ");
                String mesAno = sc.nextLine();

                gastos[totalGastos++] = endereco + "\n" + tipo + "\n" + valor + "\n" + mesAno;

                System.out.println("Gasto cadastrado com sucesso!");
        }

        public static void calcularLucro() {
            try {
                System.out.print("Endereço do imóvel: ");
                String enderecoBusca = sc.nextLine();
                System.out.print("Mês/ano (Ex: 05/2025): ");
                String mesAnoBusca = sc.nextLine();

                double aluguel = 0.0;
                BufferedReader brImovel = new BufferedReader(new FileReader("imoveis.txt"));
                String linha;

                while ((linha = brImovel.readLine()) != null) {
                    String[] dados = linha.split(";");
                    if (dados[0].equalsIgnoreCase(enderecoBusca)) {
                        aluguel = Double.parseDouble(dados[1]);
                        break;
                    }
                }
                brImovel.close();

                double totalGastos = 0.0;
                BufferedReader brGastos = new BufferedReader(new FileReader("gastos.txt"));

                while ((linha = brGastos.readLine()) != null) {
                    String[] dados = linha.split(";");
                    if (dados[0].equalsIgnoreCase(enderecoBusca) && dados[3].equalsIgnoreCase(mesAnoBusca)) {
                        totalGastos += Double.parseDouble(dados[2]);
                    }
                }
                brGastos.close();

                double lucro = aluguel - totalGastos;
                System.out.printf("Lucro líquido do imóvel '%s' em %s: R$ %.2f\n", enderecoBusca, mesAnoBusca, lucro);
            } catch (IOException e) {
                System.out.println("Erro ao calcular lucro.");
            }
        }
    }


