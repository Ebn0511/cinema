package br.com.cinema.cinema.application.usecases;

import org.springframework.stereotype.Service;
import br.com.cinema.cinema.domain.entities.Sessao;
import br.com.cinema.cinema.domain.enums.StatusSessao;
import br.com.cinema.cinema.domain.repositories.SessaoRepository;

@Service
public class ReservarAssentoUseCase {

    private final SessaoRepository sessaoRepository;

    public ReservarAssentoUseCase(SessaoRepository sessaoRepository) {
        this.sessaoRepository = sessaoRepository;
    }

    public void executar(Long sessaoId, String codigoAssento) {
        Sessao sessao = sessaoRepository.buscarPorId(sessaoId)
                .orElseThrow(() -> new IllegalArgumentException("Sessão não encontrada"));

        // ⛔ Regra de negócio: impedir reserva se sessão estiver lotada
        if (sessao.getStatus() == StatusSessao.LOTADA) {
            throw new IllegalStateException("Não é possível reservar. Sessão está lotada.");
        }

        sessao.reservarAssento(codigoAssento);
        sessaoRepository.salvar(sessao);
    }
}

