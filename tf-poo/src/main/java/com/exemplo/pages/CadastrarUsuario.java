package com.exemplo.pages;

import com.exemplo.models.Empresa;
import com.exemplo.models.Estudante;
import com.exemplo.models.Funcionario;
import com.exemplo.models.GerenciadorUsuarios;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.notification.Notification;





@Route(value="cadastrar-usuario", layout = MainLayout.class)
public class CadastrarUsuario extends VerticalLayout {
    TextField nomeUsuario;
    ComboBox<String> tipoUsuario;
    TextField placa;
    Button confirmar;


    public CadastrarUsuario(){
        add(new H1("Cadastrar Usuário"), new Hr());
        nomeUsuario = new TextField("Nome: ");
        tipoUsuario = new ComboBox<>("Tipo de usuário");
        tipoUsuario.setItems("Funcionário", "Estudante", "Empresa");
        placa = new TextField("Placa: ");
        placa.getElement().executeJs("this.inputElement.style.textTransform = 'uppercase'");

        confirmar = new Button("Confirmar", e ->{
            if (tipoUsuario.isEmpty()||nomeUsuario.isEmpty()||placa.isEmpty()){
                Notification.show("Insira todos os valores",3000, Notification.Position.BOTTOM_CENTER);
                return;
            }
           String tipo = tipoUsuario.getValue();
           String nome = nomeUsuario.getValue();
           String stringPlaca = placa.getValue().trim().toUpperCase();
           switch (tipo){
               case "Funcionário" -> {
                   Funcionario funcionario = new Funcionario(nome,stringPlaca);
                   if (GerenciadorUsuarios.contemNome(nome) || GerenciadorUsuarios.contemPlaca(stringPlaca)){
                       Notification.show("Não é possível repetir nome ou placa.", 3000,
                               Notification.Position.MIDDLE);
                       return;
                   }
                   GerenciadorUsuarios.gerenciadorUsuario.add(funcionario);
                   Notification.show("Funcionário cadastrado com sucesso!",
                           3000, Notification.Position.MIDDLE);
                   nomeUsuario.clear();
                   placa.clear();
                   tipoUsuario.clear();
               }
               case "Estudante" -> {
                   Estudante estudante = new Estudante(nome,stringPlaca);
                   if (GerenciadorUsuarios.contemNome(nome) || GerenciadorUsuarios.contemPlaca(stringPlaca)){
                       Notification.show("Não é possível repetir nome ou placa.", 3000,
                               Notification.Position.MIDDLE);
                       return;
                   }
                   GerenciadorUsuarios.gerenciadorUsuario.add(estudante);
                   Notification.show("Estudante cadastrado com sucesso!", 3000,
                           Notification.Position.MIDDLE);
                   nomeUsuario.clear();
                   placa.clear();
                   tipoUsuario.clear();
               }
               case "Empresa" -> {
                   Empresa empresa = new Empresa(nome,stringPlaca);
                   if (GerenciadorUsuarios.contemNome(nome) || GerenciadorUsuarios.contemPlaca(stringPlaca)){
                       Notification.show("Não é possível repetir nome ou placa.", 3000,
                               Notification.Position.MIDDLE);
                       return;
                   }
                   GerenciadorUsuarios.gerenciadorUsuario.add(empresa);
                   Notification.show("Empresa cadastrado com sucesso!", 3000,
                           Notification.Position.MIDDLE);
                   nomeUsuario.clear();
                   placa.clear();
                   tipoUsuario.clear();
               }
           }

        });

        add(nomeUsuario,tipoUsuario,placa, confirmar);
        Button backButton = new Button("Voltar");
        backButton.addClickListener(e -> UI.getCurrent().navigate("inicial"));
        add(backButton);
    }
}
