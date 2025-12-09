package com.exemplo;

import com.exemplo.models.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        Estudante e1 = new Estudante("Nicolas", "AA");
        Estudante e2 = new Estudante("Leonardo", "BB");
        Estudante e3 = new Estudante("Eduardo", "CC");
        Estudante e4 = new Estudante("Rodrigo", "DD");
        Estudante e5 = new Estudante("João", "EE");
        Estudante e6 = new Estudante("Nicolas Azambuja", "FF");

        Empresa em1 = new Empresa("Dell", "GG");
        Empresa em2 = new Empresa("SAP", "HH");
        Empresa em3 = new Empresa("HP", "II");

        Funcionario f1 = new Funcionario("Karina", "JJ");
        Funcionario f2 = new Funcionario("Giraffa", "KK");
        Funcionario f3 = new Funcionario("Marlon", "LL");
        Funcionario f4 = new Funcionario("Mrs Alest", "NN");

        e1.registrarVeiculo("MM");
        e2.registrarVeiculo("OO");
        e3.registrarVeiculo("PP");
        e5.registrarVeiculo("QQ");

        em1.registrarVeiculo("XX");
        em1.registrarVeiculo("SSSS");
        em1.registrarVeiculo("OOOO");
        em1.registrarVeiculo("LLLL");

        em2.registrarVeiculo("JJJJJJ");
        em2.registrarVeiculo("UUUUU");
        em2.registrarVeiculo("BBBBB");
        em2.registrarVeiculo("QQQQQQ");

        f1.registrarVeiculo("UU");
        f3.registrarVeiculo("VV");

        GerenciadorUsuarios.gerenciadorUsuario.add(e1);
        GerenciadorUsuarios.gerenciadorUsuario.add(e2);
        GerenciadorUsuarios.gerenciadorUsuario.add(e3);
        GerenciadorUsuarios.gerenciadorUsuario.add(e4);
        GerenciadorUsuarios.gerenciadorUsuario.add(e5);
        GerenciadorUsuarios.gerenciadorUsuario.add(e6);
        GerenciadorUsuarios.gerenciadorUsuario.add(em1);
        GerenciadorUsuarios.gerenciadorUsuario.add(em2);
        GerenciadorUsuarios.gerenciadorUsuario.add(em3);
        GerenciadorUsuarios.gerenciadorUsuario.add(f1);
        GerenciadorUsuarios.gerenciadorUsuario.add(f2);
        GerenciadorUsuarios.gerenciadorUsuario.add(f3);
        GerenciadorUsuarios.gerenciadorUsuario.add(f4);

        SpringApplication.run(App.class, args);
    }
}
