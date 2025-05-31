import java.util.Scanner;

public class MenuCadastro {
    public static void menuCadastro() {
        Scanner sc = new Scanner(System.in);
        int opcao;
        do {
            System.out.println("--- Casa Caiu ---");
            System.out.println("Sejam bem vindos ao melhor lugar para o controle do seu imóvel!");
            System.out.println("Por favor, escolha o que deseja cadastrar!");
            System.out.println("1 - Cadastrar Pessoa");
            System.out.println("2 - Cadastrar Imóvel");
            System.out.println("3 - Cadastrar Gasto");
            System.out.println("4 - Visualizar Dados");
            System.out.println("5 - Calcular Lucro de um Imóvel");
            System.out.println("0 - Sair");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    Pessoa.cadastrarPessoa();
                    Pessoa.salvarPessoasEmArquivo();
                    break;
                case 2:
                    Imovel.cadastrarImovel();
                    break;
                case 3:
                    Gasto.cadastrarGasto();
                    break;
                case 4:
                    Util.visualizarDados();
                    break;
                case 5:
                    Gasto.calcularLucro();
                    break;
                case 0:
                    System.out.print("Espero te ver de volta por aqui! Até logo.");

                    break;
                default:
                    System.out.println("Opção inválida. Por favor, tente novamente!");
            }

        } while (opcao != 0);
    }

}
