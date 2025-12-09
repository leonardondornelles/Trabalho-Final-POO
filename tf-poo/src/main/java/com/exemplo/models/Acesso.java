package com.exemplo.models;

import java.time.LocalDateTime;

public class Acesso {
    private Usuario usuario;
    private String placa;
    private LocalDateTime dataEntrada;
    private LocalDateTime dataSaida;



    public Acesso(Usuario usuario, String placa) {
        this.usuario = usuario;
        this.placa = placa;
        this.dataEntrada = LocalDateTime.now();
    }

    public Usuario getUsuario() { return this.usuario; }

    public LocalDateTime getDataEntrada() { return this.dataEntrada; }

    public LocalDateTime getDataSaida() { return this.dataSaida; }

    public void setDataSaida(LocalDateTime saida) { this.dataSaida = saida; }

    public String getNome(){
        return usuario.getNome();
    }

    public String getPlaca(){
        return placa;
    }
}