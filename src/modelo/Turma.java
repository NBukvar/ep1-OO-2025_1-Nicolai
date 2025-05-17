package modelo;
import java.util.*;

public class Turma {
    private String codigoTurma;
    private Disciplina disciplina;
    private Professor professor;
    private String semestre;
    private String formaAvaliacao;
    private boolean presencial;
    private String sala;
    private String horario;
    private int capacidadeMaxima;
    private List<Aluno> alunosMatriculados;

    public Turma(String codigoTurma, Disciplina disciplina, Professor professor, String semestre, String formaAvaliacao, boolean presencial, String sala, String horario, int capacidadeMaxima) {
        this.codigoTurma = codigoTurma;
        this.disciplina = disciplina;
        this.professor = professor;
        this.semestre = semestre;
        this.formaAvaliacao = formaAvaliacao;
        this.presencial = presencial;
        this.sala = presencial ? sala : "Remota";
        this.horario = horario;
        this.capacidadeMaxima = capacidadeMaxima;
        this.alunosMatriculados = new ArrayList<>();
    }

    public boolean matricularAluno(Aluno aluno) {
        if (alunosMatriculados.size() < capacidadeMaxima) {
            alunosMatriculados.add(aluno);
            return true;
        }
        return false;
    }

    public List<Aluno> getAlunosMatriculados() {
        return alunosMatriculados;
    }
    public String toString() {
        return "Turma" +codigoTurma +
                " - Disciplina: " + disciplina.getCodigo() +
                " - Professor: " + professor.getNome() +
                " - Semestre: " + semestre +
                " - Avaliação: " + formaAvaliacao +
                " - Tipo: " + (presencial ? "Presencial" : "Remota") +
                " - Horário: " + horario +
                " - Capacidade: " + capacidadeMaxima + " alunos";
    }
}
