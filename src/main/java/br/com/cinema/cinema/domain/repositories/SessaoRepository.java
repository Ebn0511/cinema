// Pacote: domain.repositories
package br.com.cinema.cinema.domain.repositories;

import br.com.cinema.cinema.domain.entities.Sessao;

import java.util.Optional;

public interface SessaoRepository {
    Optional<Sessao> buscarPorId(Long id);
    void salvar(Sessao sessao);
}
