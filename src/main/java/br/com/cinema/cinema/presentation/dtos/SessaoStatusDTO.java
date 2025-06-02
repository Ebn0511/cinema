package br.com.cinema.cinema.presentation.dtos;

public class SessaoStatusDTO {
    private String status;

    public SessaoStatusDTO(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
