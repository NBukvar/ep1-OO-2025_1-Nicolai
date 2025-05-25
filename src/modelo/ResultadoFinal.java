package modelo;

public class ResultadoFinal {
    private final double media;
    private final double frequencia;
    private final String situacao;

    public ResultadoFinal(double media, double frequencia, String situacao) {
        this.media = media;
        this.frequencia = frequencia;
        this.situacao = situacao;
    }

    public double getMedia() {
        return media;
    }

    public double getFrequencia() {
        return frequencia;
    }

    public String getSituacao() {
        return situacao;
    }

    @Override
    public String toString() {
        return String.format("Média: %.2f, Frequência: %.2f%%, Situação: %s", media, frequencia * 100, situacao);
    }
}
