package acao;
import modelo.*;
import java.util.*;

public class AcaoAluno {
    private List<Aluno> alunos = new ArrayList<>();

    public void cadastrarAluno(String nome, String matricula, String curso, boolean especial){
        Aluno aluno = especial ? new AlunoEspecial(nome, matricula) : new Aluno(nome, matricula, curso);

        for (Aluno a : alunos) {
            if (a.getMatricula().equals(matricula)) {
                System.out.println("A Matricula está duplicada!");
                return;
            }
        }

        alunos.add(aluno);
        System.out.println("Cadastro de Aluno feito com sucesso!");
    }

    public void listarAlunos(){
        for (Aluno aluno : alunos) {
            System.out.println(aluno);
        }
    }

    public Aluno buscarAlunoporMatricula(String matricula){
        for (Aluno aluno : alunos) {
            if (aluno.getMatricula().equals(matricula)) {
                return aluno;
            }
        }
        return null;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

}
