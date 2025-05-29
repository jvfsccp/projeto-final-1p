import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    private static Formatter arqSaida; // envia uma saída de texto para um arquivo
    private static Scanner arqEnt;

    public static void main(String[] args) {
        abreArqEscrita();
        adicionaRegistro();
        fechaArqEsc();
        abreArqLeitura();
        leRegistro();
        fechaArqLeit();
    }

    // abre o arquivo clientes.txt para escrita
    public static void abreArqEscrita() {
        try {
            arqSaida = new Formatter("clientes.txt"); // abre o arquivo
        }
        catch (SecurityException securityException) {
            System.err.println("Permissao de Escrita Negado. Fechando...");
            System.exit(1); // termina o programa
        }
        catch (FileNotFoundException fileNotFoundException) {
            System.err.println("Erro ao abrir o arquivo. Fechando...");
            System.exit(1); // termina o programa
        }
    }

    // abre o arquivo clientes.txt para leitura
    public static void abreArqLeitura() {
        try {
            arqEnt = new Scanner(new File("clientes.txt"));
        }
        catch(FileNotFoundException fileNotFoundException) {
            System.err.println("Erro na abertura do arquivo");
            System.exit(1);
        }
    }

    // adiciona registros ao arquivo
    public static void adicionaRegistro() {
        Scanner input = new Scanner(System.in);
        System.out.printf("%s%n%s%n? ",
                "Digite o numero da conta, primeiro nome, sobrenome e saldo.",
                "Digite <crtl>Z para finalizar.");
        while (input.hasNext()) {
            try {
                // gera saída do novo registro para o arquivo; supõe entrada válida
                arqSaida.format("%d %s %s %.2f%n", input.nextInt(),
                        input.next(), input.next(), input.nextDouble());
            }
            catch (FormatterClosedException formatterClosedException) {
                System.err.println("Erro de escrita no arquivo. Finalizando...");
                break;
            }
            catch (NoSuchElementException elementException) {
                System.err.println("Entrada invalida. Digite novamente.");
                input.nextLine(); // descarta entrada para o usuário tentar de novo
            }
            System.out.print("? ");
        }
        input.close();
    }

    public static void leRegistro() {
        int conta;
        String nome, sobrenome;
        float saldo;
        System.out.printf("%-10s %-12s %-12s %10s\n", "Conta", "Nome", "Sobrenome", "Saldo");
        try {
            while (arqEnt.hasNext()){
                conta = arqEnt.nextInt();
                nome = arqEnt.next();
                sobrenome = arqEnt.next();
                saldo = arqEnt.nextFloat();
                System.out.printf("%-10d %-12s %-12s %10.2f\n", conta, nome, sobrenome, saldo);
            }
        }
        catch(NoSuchElementException elementException) {
            System.err.println("Arquivo corrompido");
            arqEnt.close();
            System.exit(1);
        }
        catch(IllegalStateException stateException) {
            System.err.println("Erro na leitura do arquivo");
            System.exit(1);
        }
    }

    // fecha o arquivo
    public static void fechaArqEsc() {
        if (arqSaida != null)
            arqSaida.close();
    }

    public static void fechaArqLeit() {
        if (arqEnt != null) {
            arqEnt.close();
        }
    }
} 
