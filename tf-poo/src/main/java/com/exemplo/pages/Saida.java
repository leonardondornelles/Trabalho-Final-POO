package com.exemplo.pages;

import com.exemplo.models.*;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.notification.Notification;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


@Route(value="saida", layout = MainLayout.class)
public class Saida extends VerticalLayout {
    ComboBox<String> nomeUsuario;
    ComboBox<String> placasEstacionadas;
    Button confirmar;
    Button voltar;

    public Saida(){
        add(new H1("Saída"), new Hr());
        nomeUsuario = new ComboBox<>("Nome");
        nomeUsuario.setItems(listarUsuariosEstacionados());

        placasEstacionadas = new ComboBox<>("Placa");
        placasEstacionadas.setVisible(false);

        confirmar = new Button("Confirmar");
        confirmar.setVisible(false);

        voltar = new Button("Voltar");
        voltar.addClickListener(e -> UI.getCurrent().navigate("inicial"));

        add(nomeUsuario,placasEstacionadas,confirmar,voltar);

        nomeUsuario.addValueChangeListener(e -> {
            Usuario usuario = GerenciadorUsuarios.acharUsuarioPorNome(nomeUsuario.getValue());
            if (usuario instanceof Empresa){
                placasEstacionadas.clear();
                placasEstacionadas.setItems(((Empresa) usuario).getPlacasEstacionadas());
                placasEstacionadas.setVisible(true);
            } else {
                confirmar.setVisible(true);
                placasEstacionadas.clear();
                placasEstacionadas.setVisible(false);
            }

        });

        placasEstacionadas.addValueChangeListener(e -> {
           confirmar.setVisible(true);
        });

        confirmar.addClickListener(e -> {
           Usuario usuario = GerenciadorUsuarios.acharUsuarioPorNome(nomeUsuario.getValue());
           if (usuario instanceof Empresa){

                Acesso acesso = Estacionamento.carrosEstacionados.get("p-" + placasEstacionadas.getValue());
                Estacionamento.carrosEstacionados.remove("p-" + placasEstacionadas.getValue());
                acesso.setDataSaida(LocalDateTime.now());
                ((Empresa) usuario).adicionarValorMensal(acesso.getDataEntrada(), acesso.getDataSaida());
                Estacionamento.carrosAcessosConcluidos.put("p-" + placasEstacionadas.getValue(), acesso);

           } else {

               Acesso acesso = Estacionamento.carrosEstacionados.get("n-" + nomeUsuario.getValue());
               Estacionamento.carrosEstacionados.remove("n-" + nomeUsuario.getValue());
               acesso.setDataSaida(LocalDateTime.now());
               Estacionamento.carrosAcessosConcluidos.put("n-" + nomeUsuario.getValue(), acesso);

               if (usuario instanceof Estudante) {
                   ((Estudante) usuario).pagarEstacionamento();
               }
           }
           Notification.show("Carro retirado", 3000, Notification.Position.MIDDLE);
           resetForm();
        });
    }

    public Set<String> listarUsuariosEstacionados(){
        Set<String> nomesComEstacionados = new HashSet<>();
        for (String string : Estacionamento.carrosEstacionados.keySet()){
            switch (string.charAt(0)){
                case 'p' ->{
                    string = string.substring(2);
                    String nome = GerenciadorUsuarios.acharNomePorPlaca(string);
                    nomesComEstacionados.add(nome);
                }
                case 'n' -> {
                    string = string.substring(2);
                    nomesComEstacionados.add(string);
                }
            }
        }

        return nomesComEstacionados;
    }


    public void resetForm() {
        Set<String> nomesAtualizados = listarUsuariosEstacionados();
        nomeUsuario.setItems(new ArrayList<>(nomesAtualizados));
        nomeUsuario.setValue(null);


        placasEstacionadas.clear();
        placasEstacionadas.setItems(new ArrayList<>());
        placasEstacionadas.setVisible(false);

        confirmar.setVisible(false);

        try {
            nomeUsuario.getDataProvider().refreshAll();
        } catch (Exception ignored) { }
    }


}
