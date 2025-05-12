package PracticalExercises.Taxes.model;

import java.util.Objects;
import java.util.UUID;

public sealed abstract class Declaracao permits DeclaracaoSimplificada, DeclaracaoCompleta {
    private final String id;
    protected final double ganhoTributavel;
    protected final double valorPago;

    public Declaracao(double ganhoTributavel, double valorPago) {
        this.id = UUID.randomUUID().toString();
        this.ganhoTributavel = ganhoTributavel;
        this.valorPago = valorPago;
    }

    public double getValorAPagar(){
        return getValorImposto() - valorPago - getDespesaDedutivel();
    }

    public double getDespesaDedutivel(){
        return 0;
    }

    public abstract double getValorImposto();

    @Override
    public String toString() {
        return "Declaracao{" +
                "ganhoTributavel=" + ganhoTributavel +
                ", valorPago=" + valorPago +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Declaracao that = (Declaracao) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public String getId() {
        return id;
    }

    public double getGanhoTributavel() {
        return ganhoTributavel;
    }

    public double getValorPago() {
        return valorPago;
    }
}
