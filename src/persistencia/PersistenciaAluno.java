
package persistencia;

import modelo.Aluno;
import modelo.Turma;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PersistenciaAluno{

    private static final String CAMINHO_ARQUIVO = "alunos_simples.csv";

    // Salvar alunos: cada linha tem nome, matricula, curso, trancada, turmas (códigos separados por ;)
    public static void salvarAlunos(List<Aluno> alunos) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAMINHO_ARQUIVO))) {
            for (Aluno aluno : alunos) {
                StringBuilder linha = new StringBuilder();
                linha.append(aluno.getNome()).append(",");
                linha.append(aluno.getMatricula()).append(",");
                linha.append(aluno.getCurso()).append(",");
                linha.append(aluno.isMatriculaTrancada() ? "sim" : "nao").append(",");
                // turmas codigos separados por ;
                for (Turma turma : aluno.getTurmasMatriculadas()) {
                    linha.append(turma.getCodigoTurma()).append(";");
                }
                writer.write(linha.toString());
                writer.newLine();
            }
            System.out.println("Alunos salvos com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar alunos: " + e.getMessage());
        }
    }

    // Carregar alunos: cria objeto Aluno e guarda só códigos das turmas (não monta objetos Turma)
    public static List<Aluno> carregarAlunos() {
        List<Aluno> alunos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(CAMINHO_ARQUIVO))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(",", -1);
                if (partes.length < 4) continue; // linha inválida, pula

                String nome = partes[0];
                String matricula = partes[1];
                String curso = partes[2];
                boolean trancada = partes[3].equalsIgnoreCase("sim");

                Aluno aluno = new Aluno(nome, matricula, curso);
                if (trancada) aluno.trancarMatricula();

                // Códigos turmas (parte 4), só para mostrar, não cria objetos
                if (partes.length > 4 && !partes[4].isEmpty()) {
                    String[] codigosTurmas = partes[4].split(";");
                    System.out.print("Aluno " + nome + " matriculado em turmas: ");
                    for (String codigo : codigosTurmas) {
                        System.out.print(codigo + " ");
                    }
                    System.out.println();
                }

                alunos.add(aluno);
            }
            System.out.println("Alunos carregados com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao carregar alunos: " + e.getMessage());
        }
        return alunos;
    }
}
