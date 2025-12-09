package com.exemplo.models;

import com.exemplo.models.Acesso;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


public abstract class Usuario {

    private Set<String> placas;

    private String nome;

    public Usuario(String nome, String placa){
        this.nome = nome;
        placas = new HashSet<>();
        placas.add(placa);
    }


    public abstract boolean registrarVeiculo(String placa);

    public void removerVeiculo(String placa){
        placas.remove(placa);
    }

    public abstract double calculaValorEstacionamento(Acesso acesso);


    public Set<String> getPlacas() {
        return placas;
    }

    public String getNome() {
        return nome;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        return Objects.equals(getNome(), ((Usuario) o).getNome());
    }

    @Override
    public int hashCode() {
        return Objects.hash(placas, nome);
    }
}