import java.io.*;
import java.util.Scanner;

public class Imovel {
    static Scanner sc = new Scanner(System.in);
    static final int MAX = 100;
    static String[][] imoveis = new String[MAX][3]; // [][0] = endereco, [][1] = aluguel, [][2] = idProprietario
    static int totalImoveis = 0;

    public static void carregarImoveis() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("imoveis.txt"));
            String linha;
            while ((linha = br.readLine()) != null && totalImoveis < MAX) {
                String[] dados = linha.split(";");
                if (dados.length == 3) {
                    imoveis[totalImoveis][0] = dados[0]; // Endereço
                    imoveis[totalImoveis][1] = dados[1]; // Aluguel
                    imoveis[totalImoveis][2] = dados[2]; // ID do proprietário
                    totalImoveis++;
                }
            }
            br.close();
        } catch (IOException ignored) {}
    }

    public static void salvarImoveisEmArquivo() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("imoveis.txt"));
            for (int i = 0; i < totalImoveis; i++) {
                bw.write(imoveis[i][0] + ";" + imoveis[i][1] + ";" + imoveis[i][2]);
                bw.newLine();
            }
            bw.close();
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
        String aluguel = sc.nextLine();
        System.out.print("ID do proprietário: ");
        String idProprietario = sc.nextLine();

        imoveis[totalImoveis][0] = endereco; 
        imoveis[totalImoveis][1] = aluguel; 
        imoveis[totalImoveis][2] = idProprietario; 
        totalImoveis++;

        salvarImoveisEmArquivo();
        System.out.println("Imóvel cadastrado com sucesso!");
    }

    public static void visualizarImoveis() {
        if (totalImoveis == 0) {
            System.out.println("Nenhum imóvel cadastrado.");
            return;
        }
        for (int i = 0; i < totalImoveis; i++) {
            System.out.println("Imóvel " + (i + 1) + ":");
            System.out.println("Endereço: " + imoveis[i][0]);
            System.out.println("Aluguel: R$" + imoveis[i][1]);
            System.out.println("ID do proprietário: " + imoveis[i][2]);
            System.out.println();
        }
    }

    public static void editarImovel() {
        visualizarImoveis();
        System.out.print("Digite o número do imóvel que deseja editar(0 para sair): ");
        int indice = sc.nextInt() - 1;
        sc.nextLine();

        if(indice > 0){
            if (indice >= 0 && indice < totalImoveis) {
                System.out.print("Novo endereço: ");
                imoveis[indice][0] = sc.nextLine();
                System.out.print("Novo valor do aluguel: ");
                imoveis[indice][1] = sc.nextLine();

                salvarImoveisEmArquivo();
                System.out.println("Imóvel atualizado com sucesso!");
            } else {
                System.out.println("Índice inválido.");
            }
        }
    }

    public static void excluirImovel() {
        visualizarImoveis();
        System.out.print("Digite o número do imóvel que deseja excluir: ");
        int indice = sc.nextInt() - 1;
        sc.nextLine();

        if (indice >= 0 && indice < totalImoveis) {
            
            for (int i = indice; i < totalImoveis - 1; i++) {
                imoveis[i][0] = imoveis[i + 1][0];
                imoveis[i][1] = imoveis[i + 1][1];
                imoveis[i][2] = imoveis[i + 1][2];
            }
            totalImoveis--;
            salvarImoveisEmArquivo();
            System.out.println("Imóvel excluído com sucesso!");
        } else {
            System.out.println("Índice inválido.");
        }
    }

    public static void visualizarImoveisPorProprietario(String idProprietario) {
        boolean encontrado = false;
        for (int i = 0; i < totalImoveis; i++) {
            if (imoveis[i][2].equals(idProprietario)) {
                System.out.println("Imóvel " + (i + 1) + ":");
                System.out.println("Endereço: " + imoveis[i][0]);
                System.out.println("Aluguel: R$" + imoveis[i][1]);
                System.out.println();
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("Nenhum imóvel encontrado para o ID informado.");
        }
    }
}