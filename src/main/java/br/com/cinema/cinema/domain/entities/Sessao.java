// Pacote: domain.entities
package br.com.cinema.cinema.domain.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Sessao {
    private Long id;
    private LocalDateTime horario;
    private List<Assento> assentos;

    public Sessao(Long id, LocalDateTime horario, List<String> codigosAssentos) {
        this.id = id;
        this.horario = horario;
        this.assentos = new ArrayList<>();
        codigosAssentos.forEach(codigo -> assentos.add(new Assento(codigo)));
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getHorario() {
        return horario;
    }

    public List<Assento> getAssentos() {
        return assentos;
    }

    public Optional<Assento> buscarAssento(String codigo) {
        return assentos.stream()
                .filter(a -> a.getCodigo().equalsIgnoreCase(codigo))
                .findFirst();
    }

    public void reservarAssento(String codigo) {
        Assento assento = buscarAssento(codigo)
                .orElseThrow(() -> new IllegalArgumentException("Assento não encontrado"));
        assento.reservar();
    }

    public void ocuparAssento(String codigo) {
        Assento assento = buscarAssento(codigo)
                .orElseThrow(() -> new IllegalArgumentException("Assento não encontrado"));
        assento.ocupar();
    }

    public void liberarAssento(String codigo) {
        Assento assento = buscarAssento(codigo)
                .orElseThrow(() -> new IllegalArgumentException("Assento não encontrado"));
        assento.liberar();
    }
}

