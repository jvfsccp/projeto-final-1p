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
}
