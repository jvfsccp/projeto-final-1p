import java.util.Scanner;

public class MenuCadastro {
    public static void menuCadastro() {
        Scanner sc = new Scanner(System.in);

        if (Pessoa.totalPessoas == 0) {
            System.out.println("Nenhuma pessoa cadastrada. Por favor, cadastre-se antes de usar o sistema.");
            Pessoa.cadastrarPessoa();
            Pessoa.salvarPessoasEmArquivo();

            System.out.print("Deseja cadastrar um imóvel agora? (s/n): ");
            String respostaImovel = sc.nextLine();
            if (respostaImovel.equalsIgnoreCase("s")) {
                Imovel.cadastrarImovel();

                System.out.print("Deseja cadastrar um gasto para esse imóvel? (s/n): ");
                String respostaGasto = sc.nextLine();
                if (respostaGasto.equalsIgnoreCase("s")) {
                    Gasto.cadastrarGastoParaImovel(Imovel.totalImoveis - 1);
                }
            }
        }

        int opcao;
        do {
            System.out.println("--- Casa Caiu ---");
            System.out.println("Sistema de Controle de Imóveis");
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Menu de Pessoas");
            System.out.println("2 - Menu de Imóveis");
            System.out.println("3 - Menu de Gastos");
            System.out.println("4 - Visualizar todos os dados");
            System.out.println("0 - Sair");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    menuPessoas();
                    break;
                case 2:
                    menuImoveis();
                    break;
                case 3:
                    menuGastos();
                    break;
                case 4:
                    Util.visualizarDados();
                    break;
                case 0:
                    System.out.println("Saindo do sistema. Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    public static void menuPessoas() {
        Scanner sc = new Scanner(System.in);
        int opcao;
        do {
            System.out.println("--- Menu de Pessoas ---");
            System.out.println("1 - Cadastrar Pessoa");
            System.out.println("2 - Editar Pessoa");
            System.out.println("3 - Excluir Pessoa");
            System.out.println("0 - Voltar ao menu principal");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    Pessoa.cadastrarPessoa();
                    Pessoa.salvarPessoasEmArquivo();
                    break;
                case 2:
                    Pessoa.editarPessoa();
                    break;
                case 3:
                    Pessoa.excluirPessoa();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (true);
    }

    public static void menuImoveis() {
        Scanner sc = new Scanner(System.in);
        int opcao;
        do {
            System.out.println("--- Menu de Imóveis ---");
            System.out.println("1 - Cadastrar Imóvel");
            System.out.println("2 - Editar Imóvel");
            System.out.println("3 - Excluir Imóvel");
            System.out.println("0 - Voltar ao menu principal");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    Imovel.cadastrarImovel();
                    System.out.print("Deseja cadastrar um gasto para esse imóvel? (s/n): ");
                    String respostaGasto = sc.nextLine();
                    if (respostaGasto.equalsIgnoreCase("s")) {
                        Gasto.cadastrarGastoParaImovel(Imovel.totalImoveis - 1);
                    }
                    break;
                case 2:
                    Imovel.editarImovel();
                    break;
                case 3:
                    Imovel.excluirImovel();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (true);
    }

    public static void menuGastos() {
        Scanner sc = new Scanner(System.in);
        int opcao;

        if (Imovel.totalImoveis == 0) {
            System.out.println("É necessário ter imóveis cadastrados para gerenciar gastos.");
            return;
        }

        do {
            System.out.println("--- Menu de Gastos ---");
            System.out.println("1 - Cadastrar Gasto");
            System.out.println("2 - Editar Gasto");
            System.out.println("3 - Excluir Gasto");
            System.out.println("0 - Voltar ao menu principal");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    Imovel.visualizarImoveis();
                    System.out.print("Escolha o número do imóvel para cadastrar o gasto: ");
                    int indice = sc.nextInt();
                    sc.nextLine();
                    if (indice > 0 && indice <= Imovel.totalImoveis) {
                        Gasto.cadastrarGastoParaImovel(indice - 1);
                    } else {
                        System.out.println("Imóvel inválido!");
                    }
                    break;
                case 2:
                    Gasto.editarGasto();
                    break;
                case 3:
                    Gasto.excluirGasto();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (true);
    }
}
