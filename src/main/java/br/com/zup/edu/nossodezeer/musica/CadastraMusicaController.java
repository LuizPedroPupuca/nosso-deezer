package br.com.zup.edu.nossodezeer.musica;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;

@RestController
public class CadastraMusicaController {

    private final MusicaRepository repository;

    public CadastraMusicaController(MusicaRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/musica")
    @Transactional
    public ResponseEntity<?> cadastra(@RequestBody MusicaRequest musicaRequest, UriComponentsBuilder uriComponentsBuilder){

        Musica musica = musicaRequest.toModel();
        repository.save(musica);

        URI location = uriComponentsBuilder.path("/video/{id}")
                .buildAndExpand(musica.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/{id}/musica")
    @Transactional
    public ResponseEntity<?> atualiza(@RequestBody MusicaRequest musicaRequest, @PathVariable Long id){

        Musica musica = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Musica nao cadastrada no sistema."));

        musica.atualiza(musicaRequest);

        repository.save(musica);

        return ResponseEntity.noContent().build();
    }
}
