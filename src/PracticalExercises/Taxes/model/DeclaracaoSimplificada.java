package PracticalExercises.Taxes.model;

public final class DeclaracaoSimplificada extends Declaracao {
    public DeclaracaoSimplificada(double ganhoTributavel, double valorPago) {
        super(ganhoTributavel, valorPago);
    }

    @Override
    public double getValorImposto() {
        if(ganhoTributavel<22847.88) return 0;
        double imposto = (ganhoTributavel - 22847.88) * 0.2 - valorPago;
        return Math.max(0, imposto);
    }

    @Override
    public String toString() {
        return "DeclaracaoSimplificada{" +
                "ganhoTributavel=" + ganhoTributavel +
                ", valorPago=" + valorPago +
                '}';
    }
}
