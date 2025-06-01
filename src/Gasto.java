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
        } catch (IOException ignored) {
        }
    }

    public static void salvarGastosEmArquivo() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("gastos.txt"));
            for (int i = 0; i < totalGastos; i++) {
                bw.write(gastos[i]);
                bw.newLine();
            }
            bw.close();
        } catch (IOException ignored) {
        }
    }

    public static void cadastrarGastoParaImovel(int indiceImovel) {
        if (indiceImovel < 0 || indiceImovel >= Imovel.totalImoveis) {
            System.out.println("Índice de imóvel inválido.");
            return;
        }

        String endereco = Imovel.imoveis[indiceImovel][0];
        System.out.println("Cadastrando gasto para o imóvel: " + endereco);

        System.out.print("Tipo de gasto: ");
        String tipo = sc.nextLine();
        System.out.print("Valor do gasto: ");
        double valor = sc.nextDouble();
        sc.nextLine();
        System.out.print("Mês/ano (Ex: 05/2025): ");
        String mesAno = sc.nextLine();

        gastos[totalGastos++] = endereco + ";" + tipo + ";" + valor + ";" + mesAno;
        salvarGastosEmArquivo();
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

            double totalGastosMes = 0.0;
            BufferedReader brGastos = new BufferedReader(new FileReader("gastos.txt"));

            while ((linha = brGastos.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados[0].equalsIgnoreCase(enderecoBusca) && dados[3].equalsIgnoreCase(mesAnoBusca)) {
                    totalGastosMes += Double.parseDouble(dados[2]);
                }
            }
            brGastos.close();

            double lucro = aluguel - totalGastosMes;
            System.out.printf("Lucro líquido do imóvel '%s' em %s: R$ %.2f\n", enderecoBusca, mesAnoBusca, lucro);
        } catch (IOException e) {
            System.out.println("Erro ao calcular lucro.");
        }
    }
    public static void editarGasto() {
        try {
            System.out.print("Digite o endereço do imóvel para editar: ");
            String enderecoBusca = sc.nextLine();

            File inputFile = new File("gastos.txt");
            File tempFile = new File("gastos_temp.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String linha;
            boolean encontrado = false;

            System.out.println("O que deseja fazer? (1 - Editar o endereço | 2 - Editar tipo de gasto | 3 - Editar valor gasto | 4 - Editar data)");
            int opcao = sc.nextInt();
            sc.nextLine();

            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados[0].equalsIgnoreCase(enderecoBusca)) {
                    encontrado = true;
                    switch (opcao) {
                        case 1:
                            System.out.print("Digite o novo endereço: ");
                            dados[0] = sc.nextLine();
                            break;
                        case 2:
                            System.out.print("Digite o novo tipo de gasto: ");
                            dados[1] = sc.nextLine();
                            break;
                        case 3:
                            System.out.print("Digite o novo valor gasto: ");
                            dados[2] = String.valueOf(sc.nextDouble());
                            sc.nextLine();
                            break;
                        case 4:
                            System.out.print("Digite a nova data (Mês/ano, ex: 05/2025): ");
                            dados[3] = sc.nextLine();
                            break;
                        default:
                            System.out.println("Opção inválida!");
                    }
                    writer.write(String.join(";", dados));
                } else {
                    writer.write(linha);
                }
                writer.newLine();
            }

            writer.close();
            reader.close();

            inputFile.delete();
            tempFile.renameTo(inputFile);

            if (encontrado) {
                System.out.println("Gasto editado com sucesso!");
            } else {
                System.out.println("Gasto não encontrado.");
            }
        } catch (IOException e) {
            System.out.println("Erro ao editar gastos.");
        }
    }

    public static void excluirGasto() {
        try {
            System.out.print("Digite o endereço do imóvel para remover o gasto: ");
            String enderecoBusca = sc.nextLine();

            File inputFile = new File("gastos.txt");
            File tempFile = new File("gastos_temp.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String linha;
            boolean removido = false;

            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados[0].equalsIgnoreCase(enderecoBusca)) {
                    removido = true;
                    continue;
                }
                writer.write(linha);
                writer.newLine();
            }

            writer.close();
            reader.close();

            inputFile.delete();
            tempFile.renameTo(inputFile);

            if (removido) {
                System.out.println("Gasto removido com sucesso!");
            } else {
                System.out.println("Gasto não encontrado.");
            }
        } catch (IOException e) {
            System.out.println("Erro ao remover gastos.");
        }
    }
}



