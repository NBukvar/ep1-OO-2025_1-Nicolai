package modelo;
import java.util.List;

public class Disciplina {
    private final String nome;
    private final String codigo;
    private final int cargaHoraria;
    private final List<String> preRequisitos;

    public Disciplina(String nome, String codigo, int cargaHoraria,List<String> preRequisitos) {
        this.nome = nome;
        this.codigo = codigo;
        this.cargaHoraria = cargaHoraria;
        this.preRequisitos = preRequisitos;
    }
   //getters
    public String getNome() {
        return nome;
    }
    public String getCodigo() {
        return codigo;
    }
    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public String toString() {
        return nome + "("+ codigo + ") - " + cargaHoraria + " horas";
    }
}
