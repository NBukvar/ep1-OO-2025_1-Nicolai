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
        if (turma.matricularAluno(this)) {
            turmasMatriculadas.add(turma);
            return true;
        }
        return false;
    }
    public void trancarMatricula() {
        trancado = true;
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
        return "Nome: " + nome +
                "\n Matricula: " + matricula +
                "\n Curso: " + curso +
                "\n Turmas: " + turmasMatriculadas ;
    }





}


