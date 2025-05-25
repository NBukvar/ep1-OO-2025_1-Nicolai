package acao;
import modelo.*;
import java.util.*;
import main.*;
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

    public static void editarTurmasAluno(Scanner scanner){
        System.out.print("Digite a matrícula do aluno: ");
        String matricula = scanner.nextLine();

        Aluno aluno = SistemaAcademico.AcaoAluno.buscarAlunoporMatricula(matricula);

        if (aluno == null) {
            System.out.println("Aluno não encontrado.");
            return;
        }

        boolean editando = true;
        while (editando) {
            System.out.println("\n--- Editar Turmas do Aluno ---");
            System.out.println("1. Adicionar turma");
            System.out.println("2. Remover turma");
            System.out.println("3. Voltar");
            System.out.print(" : ");
            int opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1:
                    System.out.print(" Digite o código da turma a adicionar: ");
                    String codAdicionar = scanner.nextLine();
                    Turma turmaAdicionar = SistemaAcademico.acaoDisciplinaTurma.buscarTurmaPorCodigo(codAdicionar);

                    if (turmaAdicionar != null) {
                        if (!aluno.getTurmasMatriculadas().contains(turmaAdicionar)) {
                            aluno.getTurmasMatriculadas().add(turmaAdicionar);
                            turmaAdicionar.getAlunosMatriculados().add(aluno); // sincronização
                            System.out.println(" Turma adicionada com sucesso.");
                        } else {
                            System.out.println(" O aluno já está matriculado nessa turma.");
                        }
                    } else {
                        System.out.println(" Turma não encontrada.");
                    }
                    break;

                case 2:
                    System.out.print(" Digite o código da turma a remover: ");
                    String codRemover = scanner.nextLine();
                    Turma turmaRemover = SistemaAcademico.acaoDisciplinaTurma.buscarTurmaPorCodigo(codRemover);

                    if (turmaRemover != null && aluno.getTurmasMatriculadas().contains(turmaRemover)) {
                        aluno.getTurmasMatriculadas().remove(turmaRemover);
                        turmaRemover.getAlunosMatriculados().remove(aluno); // sincronização
                        System.out.println(" Turma removida com sucesso.");
                    } else {
                        System.out.println(" Turma não encontrada ou o aluno não está nela.");
                    }
                    break;

                case 3:
                    editando = false;
                    break;

                default:
                    System.out.println(" Opção inválida.");
            }
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
    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public List<Aluno> getAlunos() {

        return alunos;
    }

}
