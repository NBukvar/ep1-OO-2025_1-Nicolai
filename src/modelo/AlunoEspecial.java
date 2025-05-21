package modelo;

public class AlunoEspecial extends Aluno{

    public AlunoEspecial( String nome, String matricula,String curso ) {
        super(nome, matricula,curso);
    }
    //checando se aluno pode ser especial ou não
    @Override
    public void matricularEmTurma(Turma turma) {
        if (turmasMatriculadas.size() < 2){
            turmasMatriculadas.add(turma);
        }
        else {
            System.out.println("Aluno Especial só pode cursar 2 disciplinas.");
        }
    }

    @Override
    public String toString() {
        return super.toString() + "\nEspecial: Sim";
    }

}
