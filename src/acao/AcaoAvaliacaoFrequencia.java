package acao;

import modelo.*;
import java.util.*;

public class AcaoAvaliacaoFrequencia {
    public void lancarNotasFrequencia(Turma turma, Map<String, double[]> notas, Map<String, Integer> presencas, int totalAulas){
        for (Aluno aluno : turma.getAlunosMatriculados()) {
            double[] n = notas.get(aluno.getMatricula());
            int freq = presencas.get(aluno.getMatricula());

            Avaliacao avaliacao = turma.getFormaAvaliacao().equals("Simples") ?
                    new AvaliacaoSimples() : new AvaliacaoPonderada();

            double media = avaliacao.calcularMedia(n[0], n[1], n[2], n[3], n[4]);
            double frequencia = (double) freq / totalAulas;

            System.out.println(aluno + " - Média: " + media + " \n- Frequência: " + (frequencia * 100) + "%");

            if (frequencia < 0.75) {
                System.out.println("Situação: Reprovado por falta");
            }
            else if (media >=5){
                System.out.println("Situação: Aprovado");
            }
            else {
                System.out.println("Situação: Reprovado por nota");
            }
        }
    }
}
