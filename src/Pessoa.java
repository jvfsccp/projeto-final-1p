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
    public static void editarPessoa() {
        try {
            System.out.println("Digite o nome da pessoa que deseja editar: ");
            String nomeAlvo = sc.next();

            File inputFile = new File("pessoas.txt");
            File tempFile = new File("pessoas_temp.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String linha;
            boolean encontrado = false;

            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                String nome = dados[0];

                if (nome.equalsIgnoreCase(nomeAlvo)) {
                    System.out.println("Digite o novo nome: ");
                    String novoNome = sc.next();

                    String novoTipo;
                    do {
                        System.out.println("Digite o novo tipo (F - Pessoa Física | J - Pessoa Jurídica): ");
                        novoTipo = sc.next().toUpperCase();
                        if (!novoTipo.equals("F") && !novoTipo.equals("J")) {
                            System.out.println("Opção inválida. Digite 'F' para Pessoa Física ou 'J' para Pessoa Jurídica.");
                        }
                    } while (!novoTipo.equals("F") && !novoTipo.equals("J"));

                    writer.write(novoNome + ";" + novoTipo);
                    writer.newLine();
                    encontrado = true;
                } else {
                    writer.write(linha);
                    writer.newLine();
                }
            }

            writer.close();
            reader.close();

            inputFile.delete();
            tempFile.renameTo(inputFile);

            if (encontrado) {
                System.out.println("Pessoa editada com sucesso.");
            } else {
                System.out.println("Pessoa não encontrada.");
            }
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao tentar editar a pessoa. Por favor, tente novamente!");
        }
    }

    public static void excluirPessoa() {
        try {
            System.out.println("Digite o nome da pessoa que deseja remover: ");
            String nomeAlvo = sc.next();

            File inputFile = new File("pessoas.txt");
            File tempFile = new File("pessoas_temp.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String linha;
            boolean removido = false;

            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                String nome = dados[0];

                if (nome.equalsIgnoreCase(nomeAlvo)) {
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
                System.out.println("Pessoa removida com sucesso.");
                return;
            } else {
                System.out.println("Pessoa não encontrada.");
            }
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao tentar remover a pessoa. Por favor, tente novamente!");
        }
    }
}

