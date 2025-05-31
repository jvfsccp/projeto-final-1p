import java.io.*;
import java.util.Scanner;

public class Imovel {
        static Scanner sc = new Scanner(System.in);

        public static void cadastrarImovel() {
            try {
                System.out.print("Endereço: ");
                String endereco = sc.nextLine();
                System.out.print("Valor do aluguel: ");
                double aluguel = sc.nextDouble();
                sc.nextLine();

                BufferedWriter bw = new BufferedWriter(new FileWriter("imoveis.txt", true));
                bw.write(endereco + ";" + aluguel);
                bw.newLine();
                bw.close();

                System.out.println("Imóvel cadastrado com sucesso!");
            } catch (IOException e) {
                System.out.println("Erro ao salvar imóvel.");
            }
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
