package com.exemplo.pages;

import com.exemplo.models.Estacionamento;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.router.Route;

@Route(value = "inicial", layout = MainLayout.class) // Usando o MainLayout
public class Inicial extends VerticalLayout {

    public Inicial() {

        add(new H1("Bem-vindo ao Sistema de Estacionamento PUCRS"));

        // --- Adiciona o Status do Estacionamento
        int ocupadas = Estacionamento.carrosEstacionados.size();
        int total = 500;

        ProgressBar progressBar = new ProgressBar();
        progressBar.setMin(0);
        progressBar.setMax(total);
        progressBar.setValue(ocupadas);
        progressBar.setWidth("300px");

        Span status = new Span(String.format("Vagas Ocupadas: %d / %d", ocupadas, total));

        add(new H1("Status Atual"), status, progressBar);
    }
}