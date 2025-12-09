package com.exemplo.models;

public class Estudante extends Usuario{
    private int saldo;
    public Estudante(String nome, String placa) {
        super(nome, placa);
        saldo = 0;
    }

    public int getSaldo() {
        return saldo;
    }


    public void addSaldo(int valor){
        saldo += valor;
    }

    public void pagarEstacionamento(){
        saldo -= 15;
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
