package PracticalExercises.Taxes.model;

public final class GastoSaude extends Gasto {
    private final String registroConselho;
    public static double DEDUCAO_MAX_SAUDE = 1500;

    public GastoSaude(long id, String descricao, double valor, String cnpj, String registroConselho) {
        super(id, descricao, valor, cnpj);
        this.registroConselho = registroConselho;
    }
}
