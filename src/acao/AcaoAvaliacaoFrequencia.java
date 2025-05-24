package acao;
import main.*;
import modelo.*;
import java.util.*;

public class AcaoAvaliacaoFrequencia {

    // Lançamento de notas e frequência
    public void calcularMediasEFrequencias(Turma turma, Map<String, double[]> notas, Map<String, Integer> presencas, int totalAulas) {
        turma.setNotas(notas);
        turma.setPresencas(presencas);
        turma.setTotalAulas(totalAulas);

        Map<String, ResultadoFinal> resultados = new HashMap<>();

        for (Aluno aluno : turma.getAlunosMatriculados()) {
            double[] n = notas.get(aluno.getMatricula());
            int freq = presencas.get(aluno.getMatricula());

            Avaliacao avaliacao = turma.getFormaAvaliacao().equalsIgnoreCase("Simples")
                    ? new AvaliacaoSimples()
                    : new AvaliacaoPonderada();

            double media = avaliacao.calcularMedia(n[0], n[1], n[2], n[3], n[4]);
            double frequencia = (double) freq / totalAulas;

            String situacao;
            if (frequencia < 0.75) {
                situacao = "Reprovado por falta";
            } else if (media >= 5) {
                situacao = " Aprovado";
            } else {
                situacao = " Reprovado por nota";
            }

            ResultadoFinal resultado = new ResultadoFinal(media, frequencia, situacao);
            resultados.put(aluno.getMatricula(), resultado);

            System.out.println("\n Aluno: " + aluno.getNome() + " (" + aluno.getMatricula() + ")");
            System.out.println(resultado); // usa toString()
        }

        turma.setResultadosFinais(resultados);
        System.out.println("\n Notas e presenças lançadas com sucesso para a turma " + turma.getCodigoTurma());
    }



    public void lancarNotasFrequencia(Turma turma) {
        Scanner scanner = new Scanner(System.in);
        Map<String, double[]> notas = new HashMap<>();
        Map<String, Integer> presencas = new HashMap<>();

        System.out.println("\nTurma: " + turma.getCodigoTurma());
        System.out.print(" Total de aulas dadas: ");
        int totalAulas = Integer.parseInt(scanner.nextLine());

        for (Aluno aluno : turma.getAlunosMatriculados()) {
            System.out.println(" Notas do aluno " + aluno.getNome() + ": ");
            double[] n = new double[5];
            System.out.print(" P1: "); n[0] = Double.parseDouble(scanner.nextLine());
            System.out.print(" P2: "); n[1] = Double.parseDouble(scanner.nextLine());
            System.out.print(" P3: "); n[2] = Double.parseDouble(scanner.nextLine());
            System.out.print(" Listas: "); n[3] = Double.parseDouble(scanner.nextLine());
            System.out.print(" Seminário: "); n[4] = Double.parseDouble(scanner.nextLine());
            notas.put(aluno.getMatricula(), n);

            System.out.print(" Número de presenças: ");
            presencas.put(aluno.getMatricula(), Integer.parseInt(scanner.nextLine()));
        }

        calcularMediasEFrequencias(turma, notas, presencas, totalAulas);
    }

    public void calcularMediasEFrequenciasExistentes(Scanner scanner) {
        System.out.print("Código da turma: ");
        String codTurma = scanner.nextLine();

        List<Turma> turmas = SistemaAcademico.acaoDisciplinaTurma.getTurmas();

        for (Turma turma : turmas) {
            if (turma.getCodigoTurma().equalsIgnoreCase(codTurma)) {
                System.out.println("\nTurma: " + turma.getCodigoTurma());


                if (turma.getNotas() == null || turma.getPresencas() == null || turma.getTotalAulas() == 0) {
                    System.out.println("Notas, presenças ou total de aulas não lançados ainda para essa turma.");
                    return;
                }


                Map<String, ResultadoFinal> resultados = turma.getResultadosFinais();

                for (Aluno aluno : turma.getAlunosMatriculados()) {
                    ResultadoFinal resultado = resultados.get(aluno.getMatricula());
                    if (resultado != null) {
                        System.out.printf("\nAluno: %s (%s)\n", aluno.getNome(), aluno.getMatricula());
                        System.out.printf("Média: %.2f, Frequência: %.2f%%, Situação: %s\n",
                                resultado.getMedia(),
                                resultado.getFrequencia() * 100,
                                resultado.getSituacao());
                    } else {
                        System.out.printf("\nAluno: %s (%s) - Resultado não encontrado.\n",
                                aluno.getNome(), aluno.getMatricula());
                    }
                }
                return;
            }
        }
        System.out.println("Turma não encontrada.");
    }


    public void exibirBoletimPorAluno(Scanner scanner) {
        System.out.print(" Digite a matrícula do aluno: ");
        String matricula = scanner.nextLine();

        List<Turma> turmas = SistemaAcademico.acaoDisciplinaTurma.getTurmas();
        boolean encontrou = false;

        for (Turma turma : turmas) {
            for (Aluno aluno : turma.getAlunosMatriculados()) {
                if (aluno.getMatricula().equals(matricula)) {
                    encontrou = true;
                    System.out.println("\nBoletim - Semestre " + turma.getSemestre());
                    System.out.println(" Disciplina: " + turma.getDisciplina().getNome());
                    System.out.println(" Professor: " + turma.getProfessor().getNome());
                    System.out.println(" Presencial: " + (turma.isPresencial() ? "Sim" : "Não"));
                    System.out.println(" Carga horária: " + turma.getDisciplina().getCargaHoraria());

                    double[] notas = turma.getNotas().get(matricula);
                    Integer presencas = turma.getPresencas().get(matricula);
                    int totalAulas = turma.getTotalAulas();

                    if (notas != null && presencas != null) {
                        System.out.printf(" Notas: P1=%.2f, P2=%.2f, P3=%.2f, Listas=%.2f, Seminário=%.2f\n",
                                notas[0], notas[1], notas[2], notas[3], notas[4]);
                        double freq = (double) presencas / totalAulas * 100;
                        System.out.printf(" Frequência: %.2f%% (%d/%d aulas)\n", freq, presencas, totalAulas);
                    } else {
                        System.out.println(" Notas e/ou presenças ainda não lançadas.");
                    }
                }
            }
        }

        if (!encontrou) {
            System.out.println(" Aluno não encontrado em nenhuma turma.");
        }
    }



    public void relatorioPorTurma(Scanner scanner) {
        System.out.print(" Digite o código da turma: ");
        String codTurma = scanner.nextLine();

        List<Turma> turmas = SistemaAcademico.acaoDisciplinaTurma.getTurmas();
        for (Turma turma : turmas) {
            if (turma.getCodigoTurma().equalsIgnoreCase(codTurma)) {
                System.out.println("\nRelatório da Turma " + codTurma);
                Map<String, ResultadoFinal> resultados = turma.getResultadosFinais();

                for (Aluno aluno : turma.getAlunosMatriculados()) {
                    ResultadoFinal resultado = resultados.get(aluno.getMatricula());

                    if (resultado != null) {
                        System.out.printf("- %s (%s) | Média: %.2f | Situação: %s\n",
                                aluno.getNome(),
                                aluno.getMatricula(),
                                resultado.getMedia(),
                                resultado.getSituacao());
                    } else {
                        System.out.printf("- %s (%s) | Resultados não disponíveis.\n",
                                aluno.getNome(),
                                aluno.getMatricula());
                    }
                }
                return;
            }
        }

        System.out.println(" Turma não encontrada.");
    }

    public void relatorioPorDisciplina(Scanner scanner) {
        System.out.print(" Digite o código da disciplina: ");
        String codDisc = scanner.nextLine();

        List<Turma> turmas =SistemaAcademico.acaoDisciplinaTurma.getTurmas();
        for (Turma turma : turmas) {
            if (turma.getDisciplina().getCodigo().equalsIgnoreCase(codDisc)) {
                System.out.println("\nTurma: " + turma.getCodigoTurma());
                System.out.println(" Professor: " + turma.getProfessor().getNome());
                System.out.println(" Alunos:");
                for (Aluno aluno : turma.getAlunosMatriculados()) {
                    System.out.println("- " + aluno.getNome());
                }
            }
        }
    }

    public void relatorioPorProfessor(Scanner scanner) {
        System.out.print(" Digite a matrícula do professor: ");
        String matProf = scanner.nextLine();

        List<Turma> turmas = SistemaAcademico.acaoDisciplinaTurma.getTurmas();
        for (Turma turma : turmas) {
            if (turma.getProfessor().getMatricula().equalsIgnoreCase(matProf)) {
                System.out.println("\nTurma: " + turma.getCodigoTurma() + " - Disciplina: " + turma.getDisciplina().getNome());
                System.out.println(" Semestre: " + turma.getSemestre());
                System.out.println(" Alunos:");
                for (Aluno aluno : turma.getAlunosMatriculados()) {
                    System.out.println("- " + aluno.getNome());
                }
            }
        }
    }



}


