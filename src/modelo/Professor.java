package modelo;

public class Professor {
    private final String nome;
    private final String matricula;

    public Professor(String nome, String matricula) {
        this.nome = nome;
        this.matricula = matricula;
    }
    //getters
    public String getNome() {
        return nome;
    }
    public String getMatricula() {
        return matricula;
    }

    public String toString() {
        return nome + "(" + matricula + ")";
    }

}
