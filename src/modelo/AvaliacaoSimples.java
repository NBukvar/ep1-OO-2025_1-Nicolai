package modelo;

public class AvaliacaoSimples implements Avaliacao {
    @Override
    public double calcularMedia(double p1, double p2, double p3, double listas, double seminario) {
        return (p1 + p2 + p3 + listas + seminario) / 5.0;
    }
}
