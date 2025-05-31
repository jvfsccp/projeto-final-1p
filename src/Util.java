
public class Util {
    public static void visualizarDados() {
        System.out.println("\n--- Pessoas Cadastradas ---");
        for (int i = 0; i < Pessoa.totalPessoas; i++) {
            System.out.println(Pessoa.pessoas[i][i]);
        }

        System.out.println("\n--- Imóveis Cadastrados ---");
        for (int i = 0; i < Imovel.totalImoveis; i++) {
            System.out.println(Imovel.imoveis[i][i]);
        }

        System.out.println("\n--- Gastos Registrados ---");
        for (int i = 0; i < Gasto.totalGastos; i++) {
            System.out.println(Gasto.gastos[i]);
        }
    }
}

