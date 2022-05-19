package br.com.zup.edu.nossodezeer.musica;

import javax.persistence.*;

@Entity
public class NumeroDeOuvintes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer qtde;

    @OneToOne
    private Musica musica;

    @Version
    private int version;

    @Deprecated
    public NumeroDeOuvintes() {
    }

    public NumeroDeOuvintes(Musica musica) {
        this.musica = musica;
        this.qtde = 0;
    }

    public void incrementa(){
        this.qtde++;
    }

}