package acao;
import modelo.*;
import java.util.*;

public class AcaoDisciplinaTurma {
    private List<Disciplina> disciplinas = new ArrayList<>();
    private List<Turma> turmas = new ArrayList<>();

    public void cadastrarDisciplina(String nome, String codigo, int cargaHoraria) {
        disciplinas.add(new Disciplina(nome, codigo, cargaHoraria));
        System.out.println("Disciplina cadastrada com sucesso!");
    }

    public void criarTurma(String codigoTurma, String codigoDisciplina, Professor prof, String semestre, String formaAvaliacao, boolean presencial, String sala, String horario, int capacidade) {
        Disciplina d = buscarDisciplina(codigoDisciplina);
        if (d == null) {
            System.out.println("Disciplina não encontrada!");
            return;
        }
    Turma turma = new Turma(codigoTurma, d, prof, semestre, formaAvaliacao, presencial, sala, horario, capacidade);
        turmas.add(turma);
        System.out.println("Turma criada com sucesso");
    }

    public Disciplina buscarDisciplina(String codigo) {
        for (Disciplina d : disciplinas) {
            if (d.getCodigo().equals(codigo)) return d;
        }
        return null;
    }

    public List<Turma> getTurmas() {
        return turmas;
    }

    public void listarTurmas() {
        for (Turma t : turmas) {
            System.out.println(t);
        }
    }
}
