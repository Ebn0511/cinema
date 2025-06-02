// Pacote: domain.entities
package br.com.cinema.cinema.domain.entities;

import br.com.cinema.cinema.domain.enums.StatusAssento;
import br.com.cinema.cinema.domain.enums.StatusSessao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Sessao {
    private Long id;
    private LocalDateTime horario;
    private List<Assento> assentos;
    private StatusSessao status = StatusSessao.DISPONIVEL;

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

    public StatusSessao getStatus() {
        return status;
    }

    public Optional<Assento> buscarAssento(String codigo) {
        return assentos.stream()
                .filter(a -> a.getCodigo().equalsIgnoreCase(codigo))
                .findFirst();
    }

    public void reservarAssento(String codigo) {
        Assento assento = buscarAssento(codigo)
                .orElseThrow(() -> new IllegalArgumentException("Assento não encontrado"));

        if (assento.getStatus() != StatusAssento.DISPONIVEL) {
            throw new IllegalStateException("Assento não está disponível para reserva.");
        }

        assento.reservar();
    }

    public void ocuparAssento(String codigo) {
        Assento assento = buscarAssento(codigo)
                .orElseThrow(() -> new IllegalArgumentException("Assento não encontrado"));

        assento.ocupar();
        atualizarStatusSessao();
    }

    public void liberarAssento(String codigo) {
        Assento assento = buscarAssento(codigo)
                .orElseThrow(() -> new IllegalArgumentException("Assento não encontrado"));

        assento.liberar();
        atualizarStatusSessao();
    }

    private void atualizarStatusSessao() {
        boolean todosOcupados = assentos.stream()
                .allMatch(a -> a.getStatus() == StatusAssento.OCUPADO);
        this.status = todosOcupados ? StatusSessao.LOTADA : StatusSessao.DISPONIVEL;
    }
}