import java.io.*;


public class Util {
    public static void visualizarDados() {
        System.out.println("\n--- Pessoas Cadastradas ---");
        lerArquivo("pessoas.txt");

        System.out.println("\n--- Imóveis Cadastrados ---");
        lerArquivo("imoveis.txt");

        System.out.println("\n--- Gastos Registrados ---");
        lerArquivo("gastos.txt");
    }

    public static void lerArquivo(String nomeArquivo) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(nomeArquivo));
            String linha;
            while ((linha = br.readLine()) != null) {
                System.out.println(linha);
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Arquivo " + nomeArquivo + " não encontrado ou vazio.");
        }
    }
}

