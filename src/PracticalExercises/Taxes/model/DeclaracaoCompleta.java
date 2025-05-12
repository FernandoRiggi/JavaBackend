package PracticalExercises.Taxes.model;

import java.util.ArrayList;
import java.util.List;

public final class DeclaracaoCompleta extends Declaracao {
    private final List<Gasto> gastos = new ArrayList<>();

    public DeclaracaoCompleta(double ganhoTributavel, double valorPago) {
        super(ganhoTributavel, valorPago);
    }

    @Override
    public double getValorImposto() {
        double imposto = 0;
        if(ganhoTributavel>55976.16){
            imposto += (ganhoTributavel - 55976.16) * 0.275;
        }
        if(ganhoTributavel>45012.72){
            imposto += (ganhoTributavel - 45012.72) * 0.225;
        }
        if(ganhoTributavel>33919.93){
            imposto += (ganhoTributavel - 33919.93) * 0.15;
        }
        if(ganhoTributavel>22847.88){
            imposto += (ganhoTributavel - 22847.88) * 0.075;
        }

        imposto -= valorPago;
        imposto -= getDespesaDedutivel();
        return Math.max(0, imposto);
    }

    public double getDespesaDedutivel(){
        return gastos.stream()
                .mapToDouble(gasto -> {
                    if(gasto instanceof GastoSaude){
                        return Math.min(gasto.getValor(), GastoSaude.DEDUCAO_MAX_SAUDE);
                    } else if(gasto instanceof GastoEducacao){
                        return Math.min(gasto.getValor(), GastoEducacao.DEDUCAO_MAX_EDUCA);
                    } else {
                        return 0;
                    }
                })
                .sum();
    }

    public void addGasto(Gasto gasto) {
        gastos.add(gasto);
    }

    public void removeGasto(Gasto gasto) {
        gastos.remove(gasto);
    }
}
