import java.io.*;
import java.util.Scanner;

public class Pessoa {
    static Scanner sc = new Scanner(System.in);
    static final int MAX = 100;
    static String[] pessoas = new String[MAX];
    static int totalPessoas = 0;

    public static void carregarPessoas() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("pessoas.txt"));
            String linha;
            while ((linha = br.readLine()) != null && totalPessoas < MAX) {
                pessoas[totalPessoas++] = linha;
            }
            br.close();
        } catch (IOException ignored) {}
    }

    public static void cadastrarPessoa() {
        if (totalPessoas >= MAX) {
            System.out.println("Limite de pessoas atingido.");
            return;
        }
            System.out.println("Digite seu nome: ");
            String nome = sc.next();

            System.out.println("Tipo (F - Pessoa Física | J - Pessoa Jurídica): ");
            String tipo = sc.next();

            if (tipo.equals("F")) {
                tipo = "Pessoa Física";
            } else if (tipo.equals("J")) {
                tipo = "Pessoa Jurídica";
            }

            pessoas[totalPessoas++] = "Nome: " + nome + "\n" + "Tipo: " + tipo;

            System.out.println("Seu cadastro foi realizado com sucesso!");
    }

    public static void salvarPessoasEmArquivo() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("pessoas.txt"));
            for (int i = 0; i < totalPessoas; i++) {
                bw.write(pessoas[i]);
                bw.newLine();
            }
            bw.close();
        } catch (IOException ignored) {}
    }
}

