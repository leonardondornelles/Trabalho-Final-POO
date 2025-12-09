package com.exemplo;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.exemplo.models.Empresa;
import com.exemplo.models.Estudante;
import com.exemplo.models.Funcionario;
import com.exemplo.models.GerenciadorUsuarios;
import com.exemplo.models.Usuario;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    /**
     * Este método roda automaticamente assim que o Spring Boot termina de iniciar.
     * É o lugar perfeito para carregar dados de teste.
     */
    @Bean
    public CommandLineRunner carregarDadosIniciais() {
        return (args) -> {
            // --- ESTUDANTES ---
            Estudante e1 = new Estudante("Nicolas", "21103040");
            Estudante e2 = new Estudante("Leonardo Dornelles", "21180200");
            Estudante e3 = new Estudante("Eduardo", "21200100");
            Estudante e4 = new Estudante("Rodrigo", "21200200");
            Estudante e5 = new Estudante("João", "21200300");
            Estudante e6 = new Estudante("Nicolas Azambuja", "21200400");

            // Registrando veículos dos estudantes
            e1.registrarVeiculo("IJK-1920");
            e2.registrarVeiculo("LND-2025");
            e3.registrarVeiculo("EDU-8080");
            e5.registrarVeiculo("JOA-9999");

            // --- EMPRESAS ---
            Empresa em1 = new Empresa("Dell Technologies", "99.999.001/0001-01");
            Empresa em2 = new Empresa("SAP Labs", "88.888.002/0001-02");
            Empresa em3 = new Empresa("HP Inc.", "77.777.003/0001-03");

            // Registrando veículos das empresas
            em1.registrarVeiculo("DEL-0001");
            em1.registrarVeiculo("DEL-0002");
            em1.registrarVeiculo("DEL-0003");
            em1.registrarVeiculo("SER-VER1");

            em2.registrarVeiculo("SAP-1001");
            em2.registrarVeiculo("SAP-1002");
            em2.registrarVeiculo("SAP-1003");
            em2.registrarVeiculo("CLO-UD01");

            // --- CRIANDO FUNCIONÁRIOS PUCRS ---
            Funcionario f1 = new Funcionario("Karina", "FUNC-001");
            Funcionario f2 = new Funcionario("Lucia Giraffa", "FUNC-002");
            Funcionario f3 = new Funcionario("Marlon", "FUNC-003");
            Funcionario f4 = new Funcionario("Mrs. Alest", "FUNC-004");

            // Registrando veículos dos funcionários
            f1.registrarVeiculo("PUC-1111");
            f3.registrarVeiculo("PUC-3333");

            
            // Usando uma lista temporária para adicionar tudo de uma vez com um loop
            List<Usuario> todosUsuarios = Arrays.asList(
                e1, e2, e3, e4, e5, e6,
                em1, em2, em3,
                f1, f2, f3, f4
            );

            for (Usuario u : todosUsuarios) {
                GerenciadorUsuarios.gerenciadorUsuario.add(u);
            }
        };
    }
}