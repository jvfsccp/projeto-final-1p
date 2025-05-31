import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;

public class PessoaCadastrada {
    public static boolean possuiCadastro() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("pessoas.txt"));
            String linha = br.readLine();
            br.close();
            return linha != null;
        } catch (IOException e) {
            return false;
        }
    }
}
