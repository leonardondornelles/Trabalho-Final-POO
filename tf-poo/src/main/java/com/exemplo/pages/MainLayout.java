package com.exemplo.pages;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLink;

public class MainLayout extends AppLayout {

    public MainLayout() {
        createHeader();
        createDrawer();
    }

    private void createHeader() {
        H1 logo = new H1("Sistema de Acesso");
        logo.getStyle().set("font-size", "var(--lumo-font-size-l)")
                .set("margin-left", "var(--lumo-space-m)");

        addToNavbar(new DrawerToggle(), logo);
    }

    private void createDrawer() {
        VerticalLayout menuItems = new VerticalLayout(
                new RouterLink(" Inicial (Status)", Inicial.class),
                new RouterLink(" Cadastrar Usuário", CadastrarUsuario.class),
                new RouterLink(" Entrada Veículo", Entrada.class),
                new RouterLink(" Saída Veículo", Saida.class),
                new RouterLink(" Efetuar Pagamento", Pagamento.class),
                new RouterLink(" Ver Informações", Informacao.class),
                new RouterLink(" Editar Placa", EditarPlaca.class)
        );

        addToDrawer(menuItems);
    }
}