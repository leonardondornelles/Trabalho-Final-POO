package com.exemplo.models;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Empresa extends Usuario{

    private double valorTotalMensal = 0;

    public Empresa(String nome, String placa) {
        super(nome, placa);
    }

    public double getValorTotalMensal(){
        return valorTotalMensal;
    }

    public void adicionarValorMensal(LocalDateTime l1, LocalDateTime l2){
        long horasEstacionadas = ChronoUnit.HOURS.between(l1, l2);
        valorTotalMensal += (horasEstacionadas + 1) * 1.5;
    }

    @Override
    public boolean registrarVeiculo(String placa) {
        if (super.getPlacas().contains(placa)) return false;
        super.getPlacas().add(placa);
        return true;
    }

    public List<String> getPlacasEstacionadas(){
        return getPlacas().stream().filter(p -> Estacionamento.carrosEstacionados.containsKey("p-" + p)).toList();
    }

    @Override
    public double calculaValorEstacionamento(Acesso acesso) {
        return 0;
    }
}
