package br.com.zup.edu.nossodezeer.musica;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

public class MusicaRequest {

    @NotBlank
    private String nome;

    public MusicaRequest(String nome) {
        this.nome = nome;
    }

    public MusicaRequest() {
    }

    public Musica toModel() {
        return new Musica(nome);
    }

    public String getNome() {
        return nome;
    }
}
