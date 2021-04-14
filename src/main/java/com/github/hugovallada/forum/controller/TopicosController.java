package com.github.hugovallada.forum.controller;

import com.github.hugovallada.forum.controller.dto.DetalhesTopicoDto;
import com.github.hugovallada.forum.controller.dto.TopicoDto;
import com.github.hugovallada.forum.controller.form.AtualizacaoTopicoForm;
import com.github.hugovallada.forum.controller.form.TopicoForm;
import com.github.hugovallada.forum.modelo.Curso;
import com.github.hugovallada.forum.modelo.Topico;
import com.github.hugovallada.forum.repository.CursoRepository;
import com.github.hugovallada.forum.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

//    @RequestMapping("/topicos")
//    public List<TopicoDto> lista(){
//        List<Topico> topicos = topicoRepository.findAll();
//        return TopicoDto.converter(topicos);
//    }

//    @GetMapping
//    public Page<TopicoDto> lista(
//            @RequestParam(required = false) String nomeCurso,
//            @RequestParam int pagina,
//            @RequestParam int qtd,
//            @RequestParam String ordenacao
//    ) {
//
//        //Pageable paginacao = PageRequest.of(pagina, qtd, Sort.Direction.DESC, ordenacao);
//
//        if(nomeCurso == null) {
//            Page<Topico> topicos = topicoRepository.findAll(paginacao);
//            return TopicoDto.converter(topicos);
//        } else {
//            Page<Topico> topicos = topicoRepository.findByCursoNome(nomeCurso, paginacao);
//            return TopicoDto.converter(topicos);
//        }
//    }

    @GetMapping
    public Page<TopicoDto> lista(
            @RequestParam(required = false) String nomeCurso,
            @PageableDefault(sort = "id", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable paginacao
    ) {

        //Pageable paginacao = PageRequest.of(pagina, qtd, Sort.Direction.DESC, ordenacao);

        if(nomeCurso == null) {
            Page<Topico> topicos = topicoRepository.findAll(paginacao);
            return TopicoDto.converter(topicos);
        } else {
            Page<Topico> topicos = topicoRepository.findByCursoNome(nomeCurso, paginacao);
            return TopicoDto.converter(topicos);
        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity<TopicoDto> cadastrar(@RequestBody @Valid TopicoForm topicoForm, UriComponentsBuilder uriBuilder){
        Topico topico = topicoRepository.save(topicoForm.converter(cursoRepository));
        URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();

        return ResponseEntity.created(uri).body(new TopicoDto(topico));
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<DetalhesTopicoDto> detalhar(@PathVariable Long id){
        Optional<Topico> topicoOpt = topicoRepository.findById(id);

        if(topicoOpt.isPresent()){
            return ResponseEntity.ok(new DetalhesTopicoDto(topicoOpt.get()));
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Transactional // faz o commit
    public ResponseEntity<TopicoDto> atualizar(@RequestBody @Valid AtualizacaoTopicoForm topicoForm, @PathVariable Long id){
       Optional<Topico> topicoOpt = topicoRepository.findById(id);

       if(topicoOpt.isPresent()){
           Topico topico = topicoForm.atualizar(id, topicoRepository);
           return ResponseEntity.ok(new TopicoDto(topico));
       }

        return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id){
        Optional<Topico> topicoOptional = topicoRepository.findById(id);
        if(topicoOptional.isPresent()){
            topicoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();

    }

}
