import java.io.*;
import java.util.Scanner;

public class Imovel {
        static Scanner sc = new Scanner(System.in);
        static final int MAX = 100;
        static String[] imoveis = new String[MAX];
        static int totalImoveis = 0;

        public static void carregarImoveis() {
            try {
                BufferedReader br = new BufferedReader(new FileReader("imoveis.txt"));
                String linha;
                while ((linha = br.readLine()) != null && totalImoveis < MAX) {
                    imoveis[totalImoveis++] = linha;
                }
                br.close();
            } catch (IOException ignored) {}
        }

        public static void cadastrarImovel() {
            if (totalImoveis >= MAX) {
                System.out.println("Limite de imóveis atingido.");
                return;
            }
                System.out.print("Endereço: ");
                String endereco = sc.nextLine();
                System.out.print("Valor do aluguel: ");
                double aluguel = sc.nextDouble();
                sc.nextLine();

                imoveis[totalImoveis++] = "Endereço: " + endereco + "\n" + "Aluguel: " + aluguel + "\n";

                System.out.println("Imóvel cadastrado com sucesso!");
        }

        public static void salvarImoveisEmArquivo() {
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter("imoveis.txt"));
                for (int i = 0; i < totalImoveis; i++) {
                    bw.write(imoveis[i]);
                    bw.newLine();
                }
                bw.close();
            } catch (IOException ignored) {}
        }

        public static void visualizarImoveis(){
            try {

                BufferedReader br = new BufferedReader(new FileReader("imoveis.txt"));
                String linha;
                int contador = 1;
                
                while ((linha = br.readLine()) != null) {
                    String[] dados = linha.split(";");

                    String endereco = dados[0];
                    double aluguel = Double.parseDouble(dados[1]);

                    System.out.println("Imóvel "+ contador +": ");
                    System.out.println("Endereço: "+ endereco);
                    System.out.println("Valor do aluguel: R$"+aluguel);
                    contador++;
                }
                br.close();

            } catch (Exception e) {
                System.out.println("Erro ao ler o arquivo");
            }
        }

        public static void editarImovel() {
            try {
                File inputFile = new File("imoveis.txt");
                File temFile = new File("imoveis_temp.txt");

                BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(temFile));

                Scanner sc = new Scanner(System.in);

                System.out.println("Digite o número do imóvel que deseja editar: ");
                int numeroImovel = sc.nextInt();
                sc.nextLine();

                int contador = 1;
                String linha;

                while ((linha = reader.readLine()) != null) {
                    if (contador == numeroImovel){

                        System.out.print("Novo endereço: ");
                        String novoEndereco = sc.nextLine();
                        System.out.print("Novo valor de aluguel: ");
                        double novoAluguel = sc.nextDouble();

                        sc.nextLine();

                        writer.write(novoEndereco+";"+ novoAluguel);
                        writer.newLine();;
                    } else {
                        writer.write(linha);
                        writer.newLine();
                    }
                    contador++;
                }

                reader.close();
                writer.close();

                if (inputFile.delete()) {
                    temFile.renameTo(inputFile);
                    System.out.println("Imóvel editado com sucesso!");
                } else {
                    System.out.println("Erro ao atualizar o arquivo");
                }
            } catch (Exception e) {
                System.out.println("Erro ao editar imóvel");
            }
        }

        public static void excluirImovel(){
            File arquivo = new File("imoveis.txt");
            if (arquivo.delete()) {
                System.out.println("Arquivo deletado com sucesso!");
            } else {
                System.out.println("Falha ao deletar o arquivo!");
            }
        }
}
