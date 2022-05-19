package br.com.zup.edu.nossodezeer.musica;

import javax.persistence.*;
import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

@Entity
public class Musica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @OneToOne(mappedBy = "musica", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private NumeroDeOuvintes numeroDeOuvintes;

    @OneToOne(mappedBy = "musica", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private QuantidadeDeLikes quantidadeLikes;

    private LocalDateTime criadoEm = now();

    private LocalDateTime atualiazadoEm = now();

    @Version
    private int version;


    public Musica(String nome) {
        this.nome = nome;
        this.numeroDeOuvintes = new NumeroDeOuvintes(this);
        this.quantidadeLikes = new QuantidadeDeLikes(this);
    }

    /**
     * @deprecated construtor de uso exclusivo
     */
    @Deprecated
    public Musica() {
    }


    public void aumentarOuvinte() {
        this.numeroDeOuvintes.incrementa();
    }

    public void aumentarLikes() {
        this.quantidadeLikes.incrementa();
    }

    public Long getId() {
        return id;
    }

    public void atualiza(MusicaRequest musicaRequest) {
        this.nome = musicaRequest.getNome();
    }
}

