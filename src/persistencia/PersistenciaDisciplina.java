package persistencia;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import modelo.Disciplina;

public class PersistenciaDisciplina {
    public static void salvarDisciplinas(List<Disciplina> disciplinas) {
        try (PrintWriter writer = new PrintWriter("disciplinas.csv")) {
            for (Disciplina d : disciplinas) {
                writer.println(d.getNome() + "," + d.getCodigo() + "," + d.getCargaHoraria());
            }
        } catch (Exception e) {
            System.out.println("Erro ao salvar disciplinas: " + e.getMessage());
        }
    }

    public static List<Disciplina> carregarDisciplinas() {
        List<Disciplina> disciplinas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("disciplinas.csv"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(",");
                disciplinas.add(new Disciplina(partes[0], partes[1], Integer.parseInt(partes[2]), new ArrayList<>()));
            }
        } catch (Exception e) {
            System.out.println("Erro ao carregar disciplinas: " + e.getMessage());
        }
        return disciplinas;
    }
}

