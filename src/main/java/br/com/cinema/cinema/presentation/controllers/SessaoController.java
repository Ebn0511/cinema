// Pacote: presentation.controllers
package br.com.cinema.cinema.presentation.controllers;

import br.com.cinema.cinema.application.usecases.ReservarAssentoUseCase;
import br.com.cinema.cinema.domain.entities.Assento;
import br.com.cinema.cinema.domain.entities.Sessao;
import br.com.cinema.cinema.domain.repositories.SessaoRepository;
import br.com.cinema.cinema.presentation.dtos.AssentoDTO;
import br.com.cinema.cinema.presentation.dtos.SessaoStatusDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sessoes")
public class SessaoController {

    private final ReservarAssentoUseCase reservarAssentoUseCase;
    private final SessaoRepository sessaoRepository;

    public SessaoController(ReservarAssentoUseCase reservarAssentoUseCase, SessaoRepository sessaoRepository) {
        this.reservarAssentoUseCase = reservarAssentoUseCase;
        this.sessaoRepository = sessaoRepository;
    }

    @PostMapping("/{sessaoId}/assentos/{codigoAssento}/reservar")
    public ResponseEntity<String> reservarAssento(@PathVariable Long sessaoId,
                                                  @PathVariable String codigoAssento) {
        reservarAssentoUseCase.executar(sessaoId, codigoAssento);
        return ResponseEntity.ok("Assento reservado com sucesso.");
    }

    @PostMapping("/{sessaoId}/assentos/{codigoAssento}/confirmar")
    public ResponseEntity<String> confirmarAssento(@PathVariable Long sessaoId,
                                                   @PathVariable String codigoAssento) {
        Sessao sessao = sessaoRepository.buscarPorId(sessaoId)
                .orElseThrow(() -> new IllegalArgumentException("Sessão não encontrada"));

        sessao.ocuparAssento(codigoAssento);
        sessaoRepository.salvar(sessao);

        return ResponseEntity.ok("Assento confirmado como ocupado.");
    }

    @PostMapping("/{sessaoId}/assentos/{codigoAssento}/cancelar")
    public ResponseEntity<String> cancelarAssento(@PathVariable Long sessaoId,
                                                  @PathVariable String codigoAssento) {
        Sessao sessao = sessaoRepository.buscarPorId(sessaoId)
                .orElseThrow(() -> new IllegalArgumentException("Sessão não encontrada"));

        sessao.liberarAssento(codigoAssento);
        sessaoRepository.salvar(sessao);

        return ResponseEntity.ok("Assento liberado com sucesso.");
    }

    @GetMapping("/{sessaoId}/assentos")
    public ResponseEntity<List<AssentoDTO>> listarAssentos(@PathVariable Long sessaoId) {
        Sessao sessao = sessaoRepository.buscarPorId(sessaoId)
                .orElseThrow(() -> new IllegalArgumentException("Sessão não encontrada"));

        List<AssentoDTO> assentosDTO = sessao.getAssentos().stream()
                .map(assento -> new AssentoDTO(assento.getCodigo(), assento.getStatus().name()))
                .toList();

        return ResponseEntity.ok(assentosDTO);
    }
    @GetMapping("/{sessaoId}/status")
    public ResponseEntity<SessaoStatusDTO> consultarStatus(@PathVariable Long sessaoId) {
        Sessao sessao = sessaoRepository.buscarPorId(sessaoId)
                .orElseThrow(() -> new IllegalArgumentException("Sessão não encontrada"));

        return ResponseEntity.ok(new SessaoStatusDTO(sessao.getStatus().name()));
    }
}


