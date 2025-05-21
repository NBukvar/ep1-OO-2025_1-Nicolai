import acao.*;
import modelo.*;
import java.util.*;

public class SistemaAcademico {
    private static Scanner scanner = new Scanner(System.in);
    private static AcaoAluno AcaoAluno = new AcaoAluno();
    private static AcaoDisciplinaTurma AcaoTurma = new AcaoDisciplinaTurma();
    private static AcaoAvaliacaoFrequencia AcaoAvaliacao = new AcaoAvaliacaoFrequencia();

    public static void main(String[] args) {
        boolean executando = true;

        while (executando) {
            System.out.println("\n===== SISTEMA ACADÊMICO FCTE =====");
            System.out.println("1. Modo Aluno");
            System.out.println("2. Modo Disciplina/Turma");
            System.out.println("3. Modo Avaliação/Frequência");
            System.out.println("4. Sair e salvar dados");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // limpa buffer

            switch (opcao) {
                case 1:
                    menuAluno();
                    break;
                case 2:
                    menuDisciplinaTurma();
                    break;
                case 3:
                    //menuAvaliacao();
                    break;
                case 4:
                    System.out.println("Encerrando o sistema com segurança...");
                    executando = false;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

private static void menuAluno() {
    boolean voltar = false;

    while (!voltar) {
        System.out.println("\n--- MODO ALUNO ---");
        System.out.println("1. Cadastrar aluno");
        System.out.println("2. Listar alunos");
        System.out.println("3. Trancar matrícula");
        System.out.println("4. Voltar");
        System.out.print("Escolha: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1:

                System.out.print("Nome: ");
                String nome = scanner.nextLine();
                System.out.print("Matrícula: ");
                String matricula = scanner.nextLine();
                System.out.print("É especial (s/n)? ");
                boolean especial = scanner.nextLine().equalsIgnoreCase("s");
                System.out.print("Curso: ");
                String curso = scanner.nextLine();


                Aluno aluno = AcaoAluno.cadastrarAluno(nome, matricula, curso, especial);
                AcaoDisciplinaTurma acaoDisciplinaTurma = new AcaoDisciplinaTurma();
                boolean continuarMatriculas = true;
                while (true) {

                    List<Turma> turmasDisponiveis = acaoDisciplinaTurma.getTurmas();

                    if (turmasDisponiveis.isEmpty()) {
                        System.out.println("Nenhuma turma disponível para matrícula.");
                        break;
                    }

                    // Mostra turmas disponíveis
                    System.out.println("\n--- TURMAS DISPONÍVEIS ---");
                    for (int i = 0; i < turmasDisponiveis.size(); i++) {
                        System.out.println((i + 1) + " - " + turmasDisponiveis.get(i));
                    }

                    // Pede escolha do aluno
                    System.out.println("Digite o código da turma que deseja se matricular:");
                    String escolha = scanner.nextLine();

                    try {
                        int indiceEscolhido = Integer.parseInt(escolha);
                        if (indiceEscolhido >= 1 && indiceEscolhido <= turmasDisponiveis.size()) {
                            Turma turmaEscolhida = turmasDisponiveis.get(indiceEscolhido - 1);

                            if (aluno.matricularEmTurma(turmaEscolhida)) {
                                System.out.println("Matrícula realizada com sucesso na turma: " + turmaEscolhida.getCodigoTurma());
                            } else {
                                System.out.println("Não foi possível realizar a matrícula. Turma cheia?");
                            }

                        } else {
                            System.out.println("Código inválido. Tente novamente.");
                            continue;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Entrada inválida. Digite apenas números.");
                        continue;
                    }

                    // Pergunta se o aluno quer continuar se matriculando
                    System.out.print("Deseja se matricular em outra turma? (s/n): ");
                    String resposta = scanner.nextLine();
                    if (!resposta.equalsIgnoreCase("s")) {
                        continuarMatriculas = false;
                        System.out.println("Voltando ao menu principal...");
                    }
                    break;
                }


            case 2:
                AcaoAluno.listarAlunos();
                break;
            case 3:
                System.out.print("Matrícula do aluno: ");
                String mat = scanner.nextLine();
                Aluno a = AcaoAluno.buscarAlunoporMatricula(mat);
                if (a != null) {
                    a.trancarMatricula();
                    System.out.println("Aluno trancado com sucesso.");
                } else {
                    System.out.println("Aluno não encontrado.");
                }
                break;
            case 4:
                voltar = true;
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }
}
    // === MODO DISCIPLINA/TURMA ===
    private static void menuDisciplinaTurma() {
        boolean voltar = false;

        while (!voltar) {
            System.out.println("\n--- MODO DISCIPLINA/TURMA ---");
            System.out.println("1. Cadastrar disciplina");
            System.out.println("2. Criar turma");
            System.out.println("3. Listar turmas");
            System.out.println("4. Voltar");
            System.out.print("Escolha: ");
            int opcao = scanner.nextInt(); scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Nome da disciplina: ");
                    String nome = scanner.nextLine();

                    System.out.print("Código: ");
                    String codigo = scanner.nextLine();

                    System.out.print("Carga horária: ");
                    int carga = scanner.nextInt();
                    scanner.nextLine();

                    List<String> preRequisitos = new ArrayList<>();
                    boolean continuar = true;

                    while (continuar) {
                        System.out.print("Adicionar pré-requisito (ou pressione ENTER para terminar): ");
                        String pre = scanner.nextLine();
                        if (pre.isEmpty()) {
                            continuar = false;
                        } else {
                            preRequisitos.add(pre);
                        }
                    }

                    AcaoTurma.cadastrarDisciplina(nome, codigo, carga, preRequisitos);
                    break;

                case 2:
                    System.out.print("Código da turma: ");
                    String codTurma = scanner.nextLine();

                    System.out.print("Código da disciplina: ");
                    String codDisciplina = scanner.nextLine();

                    System.out.print("Professor responsável: ");
                    String nomeProf = scanner.nextLine();

                    System.out.print("Matrícula do Professor: ");
                    String matProf = scanner.nextLine();
                    Professor prof = new Professor(nomeProf, matProf);

                    System.out.print("Semestre: ");
                    String semestre = scanner.nextLine();

                    System.out.print("Forma de avaliação (Simples/Ponderada): ");
                    String avaliacao = scanner.nextLine();

                    System.out.print("É presencial (s/n)? ");
                    boolean presencial = scanner.nextLine().equalsIgnoreCase("s");
                    String sala = "";
                    if (presencial) {
                        System.out.print("Sala: ");
                        sala = scanner.nextLine();
                    }
                    System.out.print("Horário: ");
                    String horario = scanner.nextLine();
                    System.out.print("Capacidade máxima: ");
                    int capacidade = scanner.nextInt(); scanner.nextLine();

                    AcaoTurma.criarTurma(codTurma, codDisciplina, prof, semestre, avaliacao, presencial, sala, horario, capacidade);
                    break;
                case 3:
                    AcaoTurma.listarTurmas();
                    break;
                case 4:
                    voltar = true;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

}