public class Main {

    public static void main(String[] args) {
        System.out.println("Sejam bem vindos ao Casa Caiu!");

        if(!PessoaCadastrada.possuiCadastro()) {
            System.out.print("Nenhuma pessoa cadastrada ainda. Por favor, efetue seu cadastro.");
            Pessoa.cadastrarPessoa();
        }
        MenuCadastro.menuCadastro();
        Imovel.visualizarImoveis();
        Imovel.editarImovel();
        Imovel.excluirImovel();
    }
} 
