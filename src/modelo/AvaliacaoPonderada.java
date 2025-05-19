package modelo;

public class AvaliacaoPonderada implements Avaliacao {
    @Override
    public double calcularMedia(double p1, double p2, double p3, double listas, double seminario){
        return (p1+p2 * 2 + p3 * 3 + listas + seminario)/ 8.0;
    }
}
