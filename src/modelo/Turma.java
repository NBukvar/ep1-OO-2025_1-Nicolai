package modelo;
import java.util.*;

public class Turma {
    private final String codigoTurma;
    private final Disciplina disciplina;
    private final Professor professor;
    private final String semestre;
    private final String formaAvaliacao;
    private final boolean presencial;
    private final String sala;
    private final String horario;
    private int capacidadeMaxima;
    private final List<Aluno> alunosMatriculados;
    private Map<String, double[]> notas;
    private Map<String, Integer> presencas;
    private int totalAulas;
    private Map<String, ResultadoFinal> resultadosFinais = new HashMap<>();


    public Turma(String codigoTurma, Disciplina disciplina, Professor professor, String semestre, String formaAvaliacao, boolean presencial, String sala, String horario, int capacidadeMaxima) {
        this.codigoTurma = codigoTurma;
        this.disciplina = disciplina;
        this.professor = professor;
        this.semestre = semestre;
        this.formaAvaliacao = formaAvaliacao;
        this.presencial = presencial;
        this.sala = sala;
        String sala1 = presencial ? sala : "Remota";
        this.horario = horario;
        this.capacidadeMaxima = capacidadeMaxima;
        this.alunosMatriculados = new ArrayList<>();
        this.notas = new HashMap<>();
        this.presencas = new HashMap<>();
    }

    public String getSemestre() {
        return semestre;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public Professor getProfessor() {
        return professor;
    }

    public String getFormaAvaliacao() {
        return formaAvaliacao;
    }

    public String getCodigoTurma() {
        return codigoTurma;
    }

    public Map<String, double[]> getNotas() {
        return notas;
    }

    public Map<String, Integer> getPresencas() {
        return presencas;
    }

    public int getTotalAulas() {
        return totalAulas;
    }

    public Map<String, ResultadoFinal> getResultadosFinais() {
        return resultadosFinais;
    }
    public String getCapacidadeMaxima() {
        return String.valueOf(capacidadeMaxima);
    }
    public String getHorario() {
        return horario;
    }
    public String getSala() {
        return sala;
    }

    public void setCapacidadeMaxima(int capacidadeMaxima) {
        this.capacidadeMaxima= capacidadeMaxima;
   }
    public void setNotas(Map<String, double[]> notas) {
        this.notas = notas;
    }

    public void setPresencas(Map<String, Integer> presencas) {
        this.presencas = presencas;
    }

    public void setTotalAulas(int totalAulas) {
        this.totalAulas = totalAulas;
    }

    public void setResultadosFinais(Map<String, ResultadoFinal> resultadosFinais) {
        this.resultadosFinais = resultadosFinais;
    }

    public boolean matricularAluno(Aluno aluno) {
        if (alunosMatriculados.size() < capacidadeMaxima && !alunosMatriculados.contains(aluno)) {
            alunosMatriculados.add(aluno);
            return true;
        }
        return false;
    }


    public List<Aluno> getAlunosMatriculados() {
        return alunosMatriculados;
    }

    public String toString() {
        return "Turma: " + codigoTurma +
                "\n - Disciplina: " + disciplina.getCodigo() +
                "\n - Professor: " + professor.getNome() +
                "\n - Semestre: " + semestre +
                "\n - Avaliação: " + formaAvaliacao +
                "\n - Tipo: " + (presencial ? "Presencial" : "Remota") +
                "\n - Sala: " + (presencial ?  sala : "A Turma é Remota")+
                "\n - Horário: " + horario +
                "\n - Capacidade: " + capacidadeMaxima + " alunos";
    }

    public boolean isPresencial() {
        return presencial;
    }

}
