package com.exemplo.models;

public class Funcionario extends Usuario{


    public Funcionario(String nome, String placa) {
        super(nome, placa);
    }

    @Override
    public boolean registrarVeiculo(String placa) {
        if (super.getPlacas().size() >= 2 || super.getPlacas().contains(placa)) return false;
        super.getPlacas().add(placa);
        return true;
    }

    @Override
    public double calculaValorEstacionamento(Acesso acesso) {
        return 0;
    }
}
