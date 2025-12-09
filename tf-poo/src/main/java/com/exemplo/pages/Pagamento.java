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

@Route(value="pagamento", layout = MainLayout.class)
public class Pagamento extends VerticalLayout {
    ComboBox<String> nomeEstudante;
    ComboBox<Integer> valor;
    Button confirmar;
    Button voltar;


    public Pagamento(){
        add(new H1("Pagamento"), new Hr());
        nomeEstudante = new ComboBox<>("Nome");
        nomeEstudante.setItems(GerenciadorUsuarios.listaDeEstudantes());
        valor = new ComboBox<>("Valor");
        valor.setItems(15, 50, 100, 150);
        valor.setVisible(false);
        confirmar = new Button("Confirmar");
        confirmar.setVisible(false);

        voltar = new Button("Voltar");
        voltar.addClickListener(e -> UI.getCurrent().navigate("inicial"));
        add(nomeEstudante,valor,confirmar, voltar);

        nomeEstudante.addValueChangeListener(e ->{
            valor.clear();
            valor.setVisible(true);
        });

        valor.addValueChangeListener(e ->{
            confirmar.setVisible(true);
        });

        confirmar.addClickListener(e ->{
            if (nomeEstudante.isEmpty() || valor.isEmpty()) {
                Notification.show("Digite todos dados necessários", 3000, Notification.Position.MIDDLE);
                return;
            }
            Estudante estudante = (Estudante) GerenciadorUsuarios.acharUsuarioPorNome(nomeEstudante.getValue());
            estudante.addSaldo(valor.getValue());
            Notification.show("Saldo adicionado com sucesso " + estudante.getSaldo(), 3000,
                    Notification.Position.MIDDLE);
            nomeEstudante.clear();
            valor.clear();
            confirmar.setVisible(false);
            valor.setVisible(false);
        });
    }
}
