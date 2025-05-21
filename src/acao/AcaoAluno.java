package acao;
import modelo.*;
import java.util.*;

public class AcaoAluno {
    private List<Aluno> alunos = new ArrayList<>();

    public Aluno cadastrarAluno(String nome, String matricula, String curso, boolean especial){
        Aluno aluno = especial ? new AlunoEspecial(nome, matricula, curso) : new Aluno(nome, matricula, curso);

        for (Aluno a : alunos) {
            if (a.getMatricula().equals(matricula)) {
                System.out.println("A Matricula está duplicada!");
                return null;
            }
        }

        alunos.add(aluno);
        System.out.println("Cadastro de Aluno feito com sucesso!");
        return aluno;
    }

    public void listarAlunos(){
        for (Aluno aluno : alunos) {
            System.out.println(aluno);
        }
    }
    public boolean trancarAluno(String matricula) {
        Aluno aluno = buscarAlunoporMatricula(matricula);
        if (aluno != null) {
            aluno.trancarMatricula();
            System.out.println("Aluno trancado com sucesso.");
            return true;
        } else {
            System.out.println("Aluno não encontrado.");
            return false;
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
