package com.exemplo.pages;

import com.exemplo.models.*;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.notification.Notification;

import java.time.LocalDateTime;


@Route("entrada")
public class Entrada extends VerticalLayout {
    private ComboBox<String> nomeUsuario;
    private ComboBox<String> placaComboBox;
    private Button confirmar;
    private Button voltar;

    public Entrada(){
        nomeUsuario = new ComboBox<>("Nome");
        nomeUsuario.setItems(GerenciadorUsuarios.listaDeNomes());

        placaComboBox = new ComboBox<>("Placa");
        placaComboBox.setVisible(false);

        confirmar = new Button("Confirmar");
        confirmar.setVisible(false);

        voltar = new Button("Voltar");
        voltar.addClickListener(e -> UI.getCurrent().navigate("inicial"));

        add(nomeUsuario,placaComboBox,confirmar,voltar);

        nomeUsuario.addValueChangeListener(e -> {

            Usuario usuario = GerenciadorUsuarios.acharUsuarioPorNome(nomeUsuario.getValue());
            placaComboBox.clear();
            placaComboBox.setItems(usuario.getPlacas());
            placaComboBox.setVisible(true);
            confirmar.setVisible(false);

        });

        placaComboBox.addValueChangeListener(e -> {
           confirmar.setVisible(true);
        });

        confirmar.addClickListener(e -> {
            if (Estacionamento.carrosEstacionados.size() >= 500){
                Notification.show("Estacionamento cheio", 3000,
                        Notification.Position.MIDDLE);

                nomeUsuario.clear();
                placaComboBox.clear();

                nomeUsuario.setVisible(true);
                placaComboBox.setVisible(false);
                confirmar.setVisible(false);
                return;
            }


            Usuario usuario = GerenciadorUsuarios.acharUsuarioPorNome(nomeUsuario.getValue());



            if (Estacionamento.carrosEstacionados.containsKey("p-" + placaComboBox.getValue())){
                Notification.show("Este carro já está dentro do estacionamento", 3000
                , Notification.Position.MIDDLE);

                nomeUsuario.clear();
                placaComboBox.clear();

                nomeUsuario.setVisible(true);
                placaComboBox.setVisible(false);
                confirmar.setVisible(false);
                return;
            }

            if (Estacionamento.carrosEstacionados.containsKey("n-" + nomeUsuario.getValue())){
                Notification.show("Este usuário já possui um veículo estacionado", 3000
                        , Notification.Position.MIDDLE);

                nomeUsuario.clear();
                placaComboBox.clear();

                nomeUsuario.setVisible(true);
                placaComboBox.setVisible(false);
                confirmar.setVisible(false);
                return;
            }

            if (usuario instanceof Estudante){
                Estudante estudante = (Estudante) usuario;
                if (estudante.getSaldo() < 15){
                    Notification.show("Este aluno não possui saldo o suficiente: " +
                            estudante.getSaldo(), 3000, Notification.Position.MIDDLE);

                    nomeUsuario.clear();
                    placaComboBox.clear();

                    nomeUsuario.setVisible(true);
                    placaComboBox.setVisible(false);
                    confirmar.setVisible(false);
                    return;
                }

                Acesso acesso = new Acesso(estudante, placaComboBox.getValue());
                Estacionamento.carrosEstacionados.put("n-" + estudante.getNome(), acesso);
                Notification.show("Veículo estacionado", 3000,
                Notification.Position.MIDDLE);

                nomeUsuario.clear();
                placaComboBox.clear();

                nomeUsuario.setVisible(true);
                placaComboBox.setVisible(false);
                confirmar.setVisible(false);
                return;
            }

            if (usuario instanceof Funcionario){
                Funcionario funcionario = (Funcionario) usuario;
                Acesso acesso = new Acesso(funcionario, placaComboBox.getValue());
                Estacionamento.carrosEstacionados.put("n-" + funcionario.getNome(), acesso);
                Notification.show("Veículo estacionado", 3000,
                        Notification.Position.MIDDLE);

                nomeUsuario.clear();
                placaComboBox.clear();

                nomeUsuario.setVisible(true);
                placaComboBox.setVisible(false);
                confirmar.setVisible(false);
                return;
            }

            if (usuario instanceof Empresa){
                Empresa empresa = (Empresa) usuario;
                Acesso acesso = new Acesso(empresa, placaComboBox.getValue());
                Estacionamento.carrosEstacionados.put("p-" + placaComboBox.getValue(), acesso);
                Notification.show("Veículo estacionado", 3000,
                        Notification.Position.MIDDLE);


                nomeUsuario.clear();
                placaComboBox.clear();

                nomeUsuario.setVisible(true);
                placaComboBox.setVisible(false);
                confirmar.setVisible(false);
            }


        });
    }

    private void resetForm(){
        nomeUsuario.clear();
        placaComboBox.clear();
        placaComboBox.setVisible(false);
        confirmar.setVisible(false);
    }
}
