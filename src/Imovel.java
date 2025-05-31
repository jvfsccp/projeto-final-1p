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
}
