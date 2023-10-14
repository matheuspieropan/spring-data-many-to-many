package com.pieropan.springdatamanytomany.config;

import com.pieropan.springdatamanytomany.entity.Conta;
import com.pieropan.springdatamanytomany.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class StarterConfig {

    @Autowired
    private ContaRepository contaRepository;

    @Bean
    public void start() {
        Iterable<Conta> contas = contaRepository.findAll();

        if (!contas.iterator().hasNext()) {
            contaRepository.saveAll(criarContas());
        }
    }

    private List<Conta> criarContas() {
        return Arrays.asList(
                Conta.builder().conta("102030").agencia("0001").saldo(500.00).build(),
                Conta.builder().conta("405060").agencia("0002").saldo(1000.00).build());
    }
}