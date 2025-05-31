import java.io.*;
import java.util.Scanner;

public class Pessoa {
    static Scanner sc = new Scanner(System.in);

    public static void cadastrarPessoa() {
        try {
            System.out.println("Digite seu nome: ");
            String nome = sc.next();

            System.out.println("Tipo (F - Pessoa Física | J - Pessoa Jurídica): ");
            String tipo = sc.next();

            BufferedWriter bw = new BufferedWriter(new FileWriter("pessoas.txt", true));
            bw.write(nome + ";" + tipo);
            bw.newLine();
            bw.close();

            System.out.println("Seu cadastro foi realizado com sucesso!");
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao tentar efetuar seu cadastro. Por favor, tente novamente!");
        }
    }
}
