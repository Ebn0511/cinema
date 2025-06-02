package br.com.cinema.cinema.infrastructure.persistence.jpa;

import br.com.cinema.cinema.domain.entities.Sessao;
import br.com.cinema.cinema.domain.repositories.SessaoRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class SessaoRepositoryImpl implements SessaoRepository {

    private final Map<Long, Sessao> bancoEmMemoria = new HashMap<>();

    @Override
    public Optional<Sessao> buscarPorId(Long id) {
        return Optional.ofNullable(bancoEmMemoria.get(id));
    }

    @Override
    public void salvar(Sessao sessao) {
        bancoEmMemoria.put(sessao.getId(), sessao);
    }
}
