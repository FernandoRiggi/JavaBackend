package PracticalExercises.Taxes.model;

public sealed abstract class Gasto permits GastoEducacao, GastoSaude{
    private long id;
    private String descricao;
    private double valor;
    private String cnpj;

    public Gasto(long id, String descricao, double valor, String cnpj) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.cnpj = cnpj;
    }

    public long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getCnpj() {
        return cnpj;
    }

    public double getValor() {
        return valor;
    }
}
