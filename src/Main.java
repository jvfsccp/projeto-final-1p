public class Main {

    public static void main(String[] args) {
        Pessoa.carregarPessoas();
        Imovel.carregarImoveis();
        Gasto.carregarGastos();
        System.out.println("Sejam bem vindos ao Casa Caiu!");

        if(!PessoaCadastrada.possuiCadastro()) {
            System.out.print("Nenhuma pessoa cadastrada ainda. Por favor, efetue seu cadastro.");
            Pessoa.cadastrarPessoa();
        }
        MenuCadastro.menuCadastro();
    }
} 
