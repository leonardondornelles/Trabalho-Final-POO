package com.exemplo.pages;

import com.exemplo.models.*;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route(value="editar-placa", layout = MainLayout.class)
public class EditarPlaca extends VerticalLayout {

    private ComboBox<String> nomeComboBox;
    private ComboBox<String> opcaoComboBox;
    private TextField placaField;
    private ComboBox<String> placaComboBox;
    private Button confirmar;
    private Button backButton;

    private Usuario usuarioAtual;

    public EditarPlaca() {
        add(new H1("Editar Placa"), new Hr());
        nomeComboBox = new ComboBox<>("Nome");
        nomeComboBox.setItems(GerenciadorUsuarios.listaDeNomes());

        opcaoComboBox = new ComboBox<>("O que deseja fazer");
        opcaoComboBox.setItems("Adicionar placa", "Remover placa");
        opcaoComboBox.setVisible(false);

        placaField = new TextField("Placa");
        placaField.getElement().executeJs("this.inputElement.style.textTransform = 'uppercase'");
        placaField.setVisible(false);

        placaComboBox = new ComboBox<>("Placa");
        placaComboBox.setVisible(false);

        confirmar = new Button("Confirmar");
        confirmar.setVisible(false);

        backButton = new Button("Voltar", e -> UI.getCurrent().navigate("inicial"));

        add(nomeComboBox, opcaoComboBox, placaField, placaComboBox, confirmar, backButton);


        nomeComboBox.addValueChangeListener(e -> {
            usuarioAtual = GerenciadorUsuarios.acharUsuarioPorNome(nomeComboBox.getValue());

            opcaoComboBox.setVisible(true);
            placaField.setVisible(false);
            placaComboBox.setVisible(false);
            confirmar.setVisible(false);

            placaField.clear();
            placaComboBox.clear();
            opcaoComboBox.clear();
        });


        opcaoComboBox.addValueChangeListener(e -> {
            if (usuarioAtual == null) return;

            String opcao = opcaoComboBox.getValue();

            placaField.setVisible("Adicionar placa".equals(opcao));
            placaComboBox.setVisible("Remover placa".equals(opcao));
            confirmar.setVisible(true);

            if ("Remover placa".equals(opcao)) {
                if (usuarioAtual.getPlacas().isEmpty()) {
                    Notification.show("Usuário não possui carros cadastrados", 3000, Notification.Position.MIDDLE);
                    resetForm();
                } else {
                    placaComboBox.setItems(usuarioAtual.getPlacas());
                }
            } else {
                placaField.clear();
            }
        });


        confirmar.addClickListener(ev -> {
            String opcao = opcaoComboBox.getValue();
            if ("Adicionar placa".equals(opcao)) {
                String placa = placaField.getValue().trim().toUpperCase();

                if (usuarioAtual.getPlacas().size() >= 2 &&
                        (usuarioAtual instanceof Estudante || usuarioAtual instanceof Funcionario)) {
                    Notification.show("Usuário já atingiu seu limite de carros", 3000, Notification.Position.MIDDLE);
                    resetForm();
                    return;
                }

                if (GerenciadorUsuarios.contemPlaca(placa)) {
                    Notification.show("Placa já cadastrada", 3000, Notification.Position.MIDDLE);
                    resetForm();
                    return;
                }

                usuarioAtual.registrarVeiculo(placa);
                Notification.show("Placa cadastrada com sucesso", 3000, Notification.Position.MIDDLE);
                resetForm();

            } else if ("Remover placa".equals(opcao)) {
                String placaSelecionada = placaComboBox.getValue();
                if (placaSelecionada == null) {
                    Notification.show("Selecione uma placa para remover", 3000, Notification.Position.MIDDLE);
                    return;
                }

                usuarioAtual.removerVeiculo(placaSelecionada);
                Notification.show("Carro removido com sucesso", 3000, Notification.Position.MIDDLE);
                resetForm();
            }
        });
    }

    private void resetForm() {
        nomeComboBox.clear();
        placaField.clear();
        placaComboBox.clear();
        opcaoComboBox.clear();

        opcaoComboBox.setVisible(false);
        placaField.setVisible(false);
        placaComboBox.setVisible(false);
        confirmar.setVisible(false);
    }
}
