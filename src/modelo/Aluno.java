package modelo;
import java.util.ArrayList;
import java.util.List;

public class Aluno {
    private String nome;
    private String matricula;
    private String curso;

    protected List<Turma> turmasMatriculadas;
    protected List<Avaliacao> avaliacoes;

    private boolean trancado;
    private boolean matriculaTrancada = false;

    public Aluno(String nome, String matricula, String curso) {
        this.nome = nome;
        this.matricula = matricula;
        this.curso = curso;
        this.turmasMatriculadas = new ArrayList<>();
        this.trancado = false;
        this.avaliacoes = new ArrayList<>();

    }
//getters
    public String getNome() {
        return nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getCurso() {
        return curso;
    }

    public boolean isTrancado() {
        return trancado;
    }
//setters
    public void setTrancado(boolean trancado) {
        this.trancado = trancado;
    }
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    public List<Turma> getTurmasMatriculadas() {
        return turmasMatriculadas;
    }

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    // metodo controle


    public boolean matricularEmTurma(Turma turma) {
        if (matriculaTrancada) {
            System.out.println("O Aluno tem matrícula trancada, logo, não pode se matricular em turmas.");
            return false;
        }
        if (turmasMatriculadas.contains(turma)) {
            System.out.println("Aluno já está matriculado nesta turma.");
            return false;
        }
        if (turma.matricularAluno(this)) {
            turmasMatriculadas.add(turma);
            return true;
        }
        System.out.println("Turma está cheia ou não foi possível matricular.");
        return false;
    }

    public void trancarMatricula() {
        this.matriculaTrancada = true;
        turmasMatriculadas.clear();
    }

    public boolean isMatriculaTrancada() {
        return matriculaTrancada;
    }

    public void trancarTurma(Turma turma) {
        turmasMatriculadas.remove(turma);
    }


    public void adicionarAvaliacao(Avaliacao avaliacao) {
        avaliacoes.add(avaliacao);
    }

    public boolean podeMatricularEmTurma(Turma turma) {
        return true;
    }

    public String toString() {
        String turmasString = "";
        for (Turma turma : turmasMatriculadas) {
            turmasString += turma.getCodigoTurma() + " ";
        }

        return  "\n------------------------" +
                "\n Nome: " + nome +
                "\n Matricula: " + matricula +
                "\n Curso: " + curso +
                "\n Turmas: " + (turmasString.isEmpty() ? "Nenhuma turma" : turmasString.trim()) +
                "\n Matrícula trancada: " +(matriculaTrancada ? "Sim" : "Não") +
                "\n------------------------";
    }





}


