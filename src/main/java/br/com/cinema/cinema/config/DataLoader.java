package br.com.cinema.cinema.config;

import br.com.cinema.cinema.domain.entities.Sessao;
import br.com.cinema.cinema.domain.repositories.SessaoRepository;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;

@Service
public class DataLoader {

    private final SessaoRepository sessaoRepository;

    public DataLoader(SessaoRepository sessaoRepository) {
        this.sessaoRepository = sessaoRepository;
    }

    @PostConstruct
    public void carregarDados() {
        Sessao sessao = new Sessao(
                1L,
                LocalDateTime.now().plusDays(1),
                Arrays.asList("A1", "A2", "A3", "B1", "B2", "B3")
        );
        sessaoRepository.salvar(sessao);

        System.out.println("Sessão inicial carregada com assentos A1–B3.");
    }
}

