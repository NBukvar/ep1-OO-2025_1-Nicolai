package persistencia;
import modelo.Turma;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import modelo.Disciplina;
import modelo.Professor;

public class PersistenciaTurma {
    public static void salvarTurmas(List<Turma> turmas) {
        try (PrintWriter writer = new PrintWriter("turmas.csv")) {
            for (Turma t : turmas) {
                writer.println(
                        t.getCodigoTurma() + "," +
                                t.getDisciplina().getCodigo() + "," +
                                t.getProfessor().getMatricula() + "," +
                                t.getSemestre() + "," +
                                t.getFormaAvaliacao() + "," +
                                t.isPresencial() + "," +
                                t.getSala() + "," +
                                t.getHorario() + "," +
                                t.getCapacidadeMaxima());
            }
        } catch (Exception e) {
            System.out.println("Erro ao salvar turmas: " + e.getMessage());
        }
    }

    public static List<Turma> carregarTurmas(List<Disciplina> disciplinas, List<Professor> professores) {
        List<Turma> turmas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("turmas.csv"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(",");

                String codTurma = partes[0];
                String codDisciplina = partes[1];
                String matriculaProf = partes[2];
                String semestre = partes[3];
                String formaAvaliacao = partes[4];
                boolean presencial = Boolean.parseBoolean(partes[5]);
                String sala = partes[6];
                String horario = partes[7];
                int capacidade = Integer.parseInt(partes[8]);

                Disciplina disc = disciplinas.stream()
                        .filter(d -> d.getCodigo().equals(codDisciplina))
                        .findFirst().orElse(null);
                Professor prof = professores.stream()
                        .filter(p -> p.getMatricula().equals(matriculaProf))
                        .findFirst().orElse(null);

                if (disc != null && prof != null) {
                    turmas.add(new Turma(codTurma, disc, prof, semestre, formaAvaliacao, presencial, sala, horario, capacidade));
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao carregar turmas: " + e.getMessage());
        }
        return turmas;
    }
}
