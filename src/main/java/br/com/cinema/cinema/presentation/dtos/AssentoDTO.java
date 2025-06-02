package br.com.cinema.cinema.presentation.dtos;

public class AssentoDTO {
    private String codigo;
    private String status;

    public AssentoDTO(String codigo, String status) {
        this.codigo = codigo;
        this.status = status;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getStatus() {
        return status;
    }
}
