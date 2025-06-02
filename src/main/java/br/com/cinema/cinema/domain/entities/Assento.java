// Pacote: domain.entities
package br.com.cinema.cinema.domain.entities;

import br.com.cinema.cinema.domain.enums.StatusAssento;

public class Assento {
    private String codigo; // exemplo: "A10"
    private StatusAssento status;

    public Assento(String codigo) {
        this.codigo = codigo;
        this.status = StatusAssento.DISPONIVEL;
    }

    public String getCodigo() {
        return codigo;
    }

    public StatusAssento getStatus() {
        return status;
    }

    public void reservar() {
        if (status != StatusAssento.DISPONIVEL) {
            throw new IllegalStateException("Assento não está disponível para reserva");
        }
        this.status = StatusAssento.RESERVADO;
    }

    public void ocupar() {
        if (status != StatusAssento.RESERVADO) {
            throw new IllegalStateException("Assento precisa estar reservado para ser ocupado");
        }
        this.status = StatusAssento.OCUPADO;
    }

    public void liberar() {
        this.status = StatusAssento.DISPONIVEL;
    }
}
