import java.io.*;

public class Util {

    public static void visualizarDados() {
        System.out.println("=========== DADOS DO SISTEMA ===========");

        // Pessoas
        System.out.println("\n--- Pessoas Cadastradas ---");
        try (BufferedReader br = new BufferedReader(new FileReader("pessoas.txt"))) {
            String linha;
            int contador = 1;
            while ((linha = br.readLine()) != null) {
                // Supondo que os dados estejam separados por ";"
                String[] dados = linha.split(";");
                System.out.println("Id: " + contador++);
                System.out.println("Nome: " + dados[1]);
                System.out.println("Tipo: " + dados[2]);
                System.out.println("Documento: " + dados[3]);
                System.out.println("-------------------------");
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo de pessoas.");
        }

        // Imóveis
        System.out.println("\n--- Imóveis Cadastrados ---");
        try (BufferedReader br = new BufferedReader(new FileReader("imoveis.txt"))) {
            String linha;
            int contador = 1;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                System.out.println("Imóvel " + contador++);
                System.out.println("Endereço: " + dados[0]);
                System.out.println("Valor do Aluguel: R$ " + dados[1]);
                System.out.println("ID do Proprietário: " + dados[2]);
                System.out.println("-------------------------");
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo de imóveis.");
        }
        // Gastos
        System.out.println("\n--- Gastos Cadastrados ---");
        try (BufferedReader br = new BufferedReader(new FileReader("gastos.txt"))) {
            String linha;
            int contador = 1;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                System.out.println("Gasto " + contador++);
                System.out.println("Descrição: " +dados[1]);
                System.out.println("Valor: R$ " + dados[2]);
                System.out.println("Endereço Imóvel: " + dados[0]);
                System.out.println("-------------------------");
            }
        } catch (IOException e) {
            System.out.println("Não há nenhum gasto cadastrado.");
        }

        System.out.println("\n========================================");
    }

    public static void exportarDadosFormatados() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("exportacao.txt"))) {

            // Exportar pessoas
            bw.write("=========== PESSOAS CADASTRADAS ===========\n");
            try (BufferedReader br = new BufferedReader(new FileReader("pessoas.txt"))) {
                String linha;
                int id = 1;
                while ((linha = br.readLine()) != null) {
                    String[] dados = linha.split(";");
                    if (dados.length >= 3) {
                        bw.write("ID: " + id++ + "\n");
                        bw.write("Nome: " + dados[1] + "\n");
                        bw.write("Tipo: " + dados[2] + "\n");
                        bw.write("----------------------------------------\n");
                    }
                }
            }

            // Exportar imóveis
            bw.write("\n=========== IMÓVEIS CADASTRADOS ===========\n");
            try (BufferedReader br = new BufferedReader(new FileReader("imoveis.txt"))) {
                String linha;
                int id = 1;
                while ((linha = br.readLine()) != null) {
                    String[] dados = linha.split(";");
                    if (dados.length >= 3) {
                        bw.write("Imóvel " + id++ + "\n");
                        bw.write("Endereço: " + dados[0] + "\n");
                        bw.write("Valor do Aluguel: R$ " + dados[1] + "\n");
                        bw.write("ID do Proprietário: " + dados[2] + "\n");
                        bw.write("----------------------------------------\n");
                    }
                }
            }

            // Exportar gastos
            bw.write("\n=========== GASTOS CADASTRADOS ===========\n");
            try (BufferedReader br = new BufferedReader(new FileReader("gastos.txt"))) {
                String linha;
                int id = 1;
                while ((linha = br.readLine()) != null) {
                    String[] dados = linha.split(";");
                    if (dados.length >= 3) {
                        bw.write("Gasto " + id++ + "\n");
                        bw.write("Descrição: " + dados[1] + "\n");
                        bw.write("Valor: R$ " + dados[2] + "\n");
                        bw.write("Data do gasto: " + dados[3] + "\n");
                        bw.write("----------------------------------------\n");
                    }
                }
            }

            bw.write("\n=========== FIM DA EXPORTAÇÃO ===========\n");
            System.out.println("Dados exportados com sucesso para 'exportacao.txt'!");

        } catch (IOException e) {
            System.out.println("Erro ao exportar dados: " + e.getMessage());
        }
    }



}
