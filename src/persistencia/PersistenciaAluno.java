package persistencia;
import modelo.Aluno;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PersistenciaAluno {
    public static void salvarAlunos(List<Aluno> alunos) {
        try (PrintWriter writer = new PrintWriter("alunos.csv")) {
            for (Aluno aluno : alunos) {
                writer.println(aluno.getNome() + "," + aluno.getMatricula() + "," + aluno.getCurso());
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar alunos: " + e.getMessage());
        }
    }
    public static List<Aluno> carregarAlunos() {
        List<Aluno> alunos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("alunos.csv"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(",");
                if (partes.length >= 3) {
                    String nome = partes[0];
                    String matricula = partes[1];
                    String curso = partes[2];
                    alunos.add(new Aluno(nome, matricula, curso));
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar alunos: " + e.getMessage());
        }
        return alunos;
    }
}