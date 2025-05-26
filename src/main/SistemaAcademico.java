package main;
import acao.*;
import modelo.*;
import persistencia.*;
import java.util.*;

    //Definindo os Metodos/Classes que serao utilizados
public class SistemaAcademico {
    private static final Scanner scanner = new Scanner(System.in);
    public static AcaoAluno AcaoAluno = new AcaoAluno();
    public static final AcaoDisciplinaTurma AcaoTurma = new AcaoDisciplinaTurma();
    private static final AcaoAvaliacaoFrequencia AcaoAvaliacao = new AcaoAvaliacaoFrequencia();


        //Main para o Sistema Operacional Geral
    public static void main(String[] args) {
        // Carregar dados dos arquivos
                // 1. Carrega alunos do CSV e envia para a ação
        List<Aluno> alunos = PersistenciaAluno.carregarAlunos();
        AcaoAluno.setAlunos(alunos);

                // 2. Carrega disciplinas
        List<Disciplina> disciplinas = PersistenciaDisciplina.carregarDisciplinas();
        AcaoTurma.setDisciplinas(PersistenciaDisciplina.carregarDisciplinas());

        // 3. Carrega professores
        List<Professor> professores = PersistenciaProfessor.carregarProfessores();
        AcaoTurma.setProfessores(professores);

                // 4. Carrega turmas com base nas disciplinas e professores
        List<Turma> turmas = PersistenciaTurma.carregarTurmas(disciplinas, professores);
        AcaoTurma.setTurmas(turmas);


        boolean executando = true;

        while (executando) {
            System.out.println("\n===== SISTEMA ACADÊMICO FCTE =====");
            System.out.println("1. Modo Aluno");
            System.out.println("2. Modo Disciplina/Turma");
            System.out.println("3. Modo Avaliação/Frequência");
            System.out.println("4. Sair e salvar dados");
            System.out.print(" Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    menuAluno();
                    break;
                case 2:
                    menuDisciplinaTurma();
                    break;
                case 3:
                    menuAvaliacaoFrequencia();
                    break;
                case 4:
                    System.out.println(" Encerrando o sistema com segurança...");
                    //salvar os dados
                    PersistenciaAluno.salvarAlunos(AcaoAluno.getAlunos());
                    PersistenciaDisciplina.salvarDisciplinas(AcaoTurma.getDisciplinas());
                    PersistenciaTurma.salvarTurmas(AcaoTurma.getTurmas());
                    PersistenciaProfessor.salvarProfessores(AcaoTurma.getProfessores());

                    executando = false;
                    break;
                default:
                    System.out.println(" Opção inválida!");
            }
        }
    }
    //No Menu Aluno
private static void menuAluno() {
    boolean voltar = false;

    while (!voltar) {
        System.out.println("\n--- MODO ALUNO ---");
        System.out.println("1. Cadastrar aluno");
        System.out.println("2. Listar alunos");
        System.out.println("3. Trancar matrícula");
        System.out.println("4. Editar Aluno");
        System.out.println("5. Voltar");
        System.out.print(" Escolha: ");

        int opcao = scanner.nextInt();
        scanner.nextLine();
    //Caso 1 - Cadastro do Aluno
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

                boolean continuarMatriculas = true;
                while (continuarMatriculas) {

                    List<Turma> turmasDisponiveis = AcaoTurma.getTurmas();

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
                                turmaEscolhida.matricularAluno(aluno);
                                System.out.println(" Matrícula realizada com sucesso na turma: " + turmaEscolhida.getCodigoTurma());
                            } else {
                                System.out.println(" Não foi possível realizar a matrícula.");
                            }

                        } else {
                            System.out.println(" Código inválido. Tente novamente.");
                            continue;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println(" Entrada inválida. Digite apenas números.");
                        continue;
                    }

                    // Pergunta se o aluno quer continuar se matriculando
                    System.out.print(" Deseja se matricular em outra turma? (s/n): ");
                    String resposta = scanner.nextLine();
                    if (!resposta.equalsIgnoreCase("s")) {
                        continuarMatriculas = false;
                        System.out.println(" Voltando ao menu principal...");
                    }

                }
                break;
            //Caso 2 - Listar todos os alunos
            case 2:
                AcaoAluno.listarAlunos();
                break;
            //Trancar a Matricula do Aluno ( Trancamento Geral)
            case 3:
                System.out.print(" Matrícula do aluno: ");
                String mat = scanner.nextLine();
                Aluno a = AcaoAluno.buscarAlunoporMatricula(mat);
                if (a != null) {
                    a.trancarMatricula();
                    System.out.println(" Aluno trancado com sucesso.");
                } else {
                    System.out.println(" Aluno não encontrado.");
                }
                break;
            //Editar as Turmas na qual o aluno está Matriculado
            case 4:
                acao.AcaoAluno.editarTurmasAluno(scanner);
                break;
            //Voltar ao menu principal
            case 5:
                voltar = true;
                break;
            default:
                System.out.println(" Opção inválida.");
        }
    }
}
    //Modo Discplina/Turma
    private static void menuDisciplinaTurma() {
        boolean voltar = false;

        while (!voltar) {
            System.out.println("\n--- MODO DISCIPLINA/TURMA ---");
            System.out.println("1. Cadastrar disciplina");
            System.out.println("2. Criar turma");
            System.out.println("3. Listar turmas");
            System.out.println("4. Voltar");
            System.out.print(" Escolha: ");

            int opcao = scanner.nextInt(); scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print(" Nome da disciplina: ");
                    String nome = scanner.nextLine();

                    System.out.print(" Código: ");
                    String codigo = scanner.nextLine();

                    System.out.print(" Carga horária: ");
                    int carga = scanner.nextInt();
                    scanner.nextLine();

                    List<String> preRequisitos = new ArrayList<>();
                    boolean continuar = true;

                    while (continuar) {
                        System.out.print(" Adicionar pré-requisito (ou pressione ENTER para terminar): ");
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
                    System.out.print(" Código da turma: ");
                    String codTurma = scanner.nextLine();

                    System.out.print(" Código da disciplina: ");
                    String codDisciplina = scanner.nextLine();

                    System.out.print(" Professor responsável: ");
                    String nomeProf = scanner.nextLine();

                    System.out.print(" Matrícula do Professor: ");
                    String matProf = scanner.nextLine();
                    Professor prof = new Professor(nomeProf, matProf);

                    System.out.print(" Semestre: ");
                    String semestre = scanner.nextLine();

                    System.out.print(" Forma de avaliação (Simples/Ponderada): ");
                    String avaliacao = scanner.nextLine();

                    System.out.print(" É presencial (s/n)? ");
                    boolean presencial = scanner.nextLine().equalsIgnoreCase("s");
                    String sala = "";
                    if (presencial) {
                        System.out.print(" Sala: ");
                        sala = scanner.nextLine();
                    }
                    System.out.print(" Horário: ");
                    String horario = scanner.nextLine();
                    System.out.print(" Capacidade máxima: ");
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
                    System.out.println(" Opção inválida.");
            }
        }
    }
    // === MODO AVALIAÇÃO/FREQUÊNCIA ===
    private static void menuAvaliacaoFrequencia() {
    boolean voltar = false;

        while (!voltar) {
            System.out.println("\n--- MODO AVALIAÇÃO/FREQUÊNCIA ---");
            System.out.println("1. Lançar notas e presença por turma");
            System.out.println("2. Calcular Média e Frequência");
            System.out.println("3. Boletim por aluno");
            System.out.println("4. Relatório da Turma");
            System.out.println("5. Relatório da Disciplina");
            System.out.println("6. Relatório do Professor");
            System.out.println("7. Voltar");
            System.out.print(" Escolha: ");
            int opcao = scanner.nextInt(); scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Código da turma: ");
                    String codigoTurma = scanner.nextLine();
                    Turma turma = SistemaAcademico.AcaoTurma.buscarTurmaPorCodigo(codigoTurma);
                    if (turma != null) {
                        AcaoAvaliacao.lancarNotasFrequencia(turma); // aqui já calcula junto
                    } else {
                        System.out.println("Turma não encontrada.");
                    }
                    break;

                case 2:
                    AcaoAvaliacao.calcularMediasEFrequenciasExistentes(scanner);
                    break;

                case 3:
                AcaoAvaliacao.exibirBoletimPorAluno(scanner);
                break;
            case 4:
                AcaoAvaliacao.relatorioPorTurma(scanner);
                break;
            case 5:
                AcaoAvaliacao.relatorioPorDisciplina(scanner);
                break;
            case 6:
                AcaoAvaliacao.relatorioPorProfessor(scanner);
                break;
            case 7:
                voltar = true;
                break;
            default:
                System.out.println(" Opção inválida.");
        }
    }
    }
    }


