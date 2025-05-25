package persistencia;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import modelo.Professor;

public class PersistenciaProfessor {
    public static void salvarProfessores(List<Professor> professores) {
        try (PrintWriter writer = new PrintWriter("professores.csv")) {
            for (Professor p : professores) {
                writer.println(p.getNome() + "," + p.getMatricula());
            }
        } catch (Exception e) {
            System.out.println("Erro ao salvar professores: " + e.getMessage());
        }
    }

    public static List<Professor> carregarProfessores() {
        List<Professor> professores = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("professores.csv"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(",");
                professores.add(new Professor(partes[0], partes[1]));
            }
        } catch (Exception e) {
            System.out.println("Erro ao carregar professores: " + e.getMessage());
        }
        return professores;
    }
}
