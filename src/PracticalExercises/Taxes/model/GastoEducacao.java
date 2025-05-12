package PracticalExercises.Taxes.model;

public final class GastoEducacao extends Gasto {
    private final String nomeInstituicao;
    public static double DEDUCAO_MAX_EDUCA = 2000;

    public GastoEducacao(long id, String descricao, double valor, String cnpj, String nomeInstituicao) {
        super(id, descricao, valor, cnpj);
        this.nomeInstituicao = nomeInstituicao;
    }
}
