import java.io.*;
import java.util.Scanner;

public class Pessoa {
    static Scanner sc = new Scanner(System.in);
    static final int MAX = 100;
    static String[][] pessoas = new String[MAX][3]; // [][0] = nome, [][1] = tipo, [][2] documento
    public static int totalPessoas = 0;

    public static void carregarPessoas() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("pessoas.txt"));
            String linha;
            while ((linha = br.readLine()) != null && totalPessoas < MAX) {
                String[] dados = linha.split(";");
                if (dados.length == 4) {
                    pessoas[totalPessoas][0] = dados[1]; // nome
                    pessoas[totalPessoas][1] = dados[2]; // tipo
                    pessoas[totalPessoas][2] = dados[3];
                    totalPessoas++;
                }
            }
            br.close();
        } catch (IOException ignored) {}
    }

    public static void salvarPessoasEmArquivo() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("pessoas.txt"));
            for (int i = 0; i < totalPessoas; i++) {
                int id = i + 1;
                bw.write(id + ";" + pessoas[i][0] + ";" + pessoas[i][1] + ";" + pessoas[i][2]);
                bw.newLine();
            }
            bw.close();
        } catch (IOException ignored) {}
    }

    public static void visualizarPessoas() {
        if (totalPessoas == 0) {
            System.out.println("Nenhuma pessoa cadastrada.");
            return;
        }
        for (int i = 0; i < totalPessoas; i++) {
            System.out.printf("Pessoa %d: %s (%s)%n", i + 1, pessoas[i][0], pessoas[i][1], pessoas[i][2]);
        }
    }

    public static void cadastrarPessoa() {
        if (totalPessoas >= MAX) {
            System.out.println("Limite de pessoas atingido.");
            return;
        }

        System.out.println("Digite seu nome: ");
        String nome = sc.next();
        String documento;
        String tipo;
        do {
            System.out.println("Tipo (F - Pessoa Física | J - Pessoa Jurídica): ");
            tipo = sc.next().toUpperCase();
            if (!tipo.equals("F") && !tipo.equals("J")) {
                System.out.println("Opção inválida. Digite 'F' ou 'J'.");
            }
        } while (!tipo.equals("F") && !tipo.equals("J"));

        if (tipo.equals("F")) {
            tipo = "Pessoa Física";
            System.out.println("Digite seu CPF: ");
        } else {
            tipo = "Pessoa Jurídica";
            System.out.println("Digite o CNPJ da sua empresa: ");
        }
        documento = sc.next();


        pessoas[totalPessoas][0] = nome;
        pessoas[totalPessoas][1] = tipo;
        pessoas[totalPessoas][2] = documento;
        totalPessoas++;

        salvarPessoasEmArquivo();
        System.out.println("Cadastro realizado com sucesso!");
    }

    public static void editarPessoa() {
        visualizarPessoas();
        System.out.print("Digite o número da pessoa que deseja editar(0 para sair): ");
        int id = sc.nextInt();
        sc.nextLine();

        if(id > 0){
            if (id < 1 || id > totalPessoas) {
                System.out.println("Pessoa não encontrada.");
                return;
            }

            int indice = id - 1;
            System.out.print("Novo nome: ");
            String novoNome = sc.next();

            String novoTipo;
            do {
                System.out.print("Novo tipo (F - Pessoa Física | J - Pessoa Jurídica): ");
                novoTipo = sc.next().toUpperCase();
            } while (!novoTipo.equals("F") && !novoTipo.equals("J"));

            novoTipo = novoTipo.equals("F") ? "Pessoa Física" : "Pessoa Jurídica";

            pessoas[indice][0] = novoNome;
            pessoas[indice][1] = novoTipo;
            salvarPessoasEmArquivo();
            System.out.println("Pessoa editada com sucesso.");
        } else {
            System.out.println("Você saiu da edição de cadastro");
        }
    }

    public static void excluirPessoa() {
        visualizarPessoas();
        System.out.print("Digite o número da pessoa que deseja remover: ");
        int id = sc.nextInt();
        sc.nextLine();

        if (id < 1 || id > totalPessoas) {
            System.out.println("Pessoa não encontrada.");
            return;
        }

        int indice = id - 1;
        for (int i = indice; i < totalPessoas - 1; i++) {
            pessoas[i][0] = pessoas[i + 1][0];
            pessoas[i][1] = pessoas[i + 1][1];
        }
        totalPessoas--;
        salvarPessoasEmArquivo();
        System.out.println("Pessoa removida com sucesso.");
    }
}
