package com.github.hugovallada.forum.controller;

import com.github.hugovallada.forum.controller.dto.DetalhesTopicoDto;
import com.github.hugovallada.forum.controller.dto.TopicoDto;
import com.github.hugovallada.forum.controller.form.TopicoForm;
import com.github.hugovallada.forum.modelo.Curso;
import com.github.hugovallada.forum.modelo.Topico;
import com.github.hugovallada.forum.repository.CursoRepository;
import com.github.hugovallada.forum.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

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

    @GetMapping
    public List<TopicoDto> lista(String nomeCurso) {
        if(nomeCurso == null) {
            List<Topico> topicos = topicoRepository.findAll();
            return TopicoDto.converter(topicos);
        } else {
            List<Topico> topicos = topicoRepository.findByCursoNome(nomeCurso);
            return TopicoDto.converter(topicos);
        }
    }

    @PostMapping
    public ResponseEntity<TopicoDto> cadastrar(@RequestBody @Valid TopicoForm topicoForm, UriComponentsBuilder uriBuilder){
        Topico topico = topicoRepository.save(topicoForm.converter(cursoRepository));
        URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();

        return ResponseEntity.created(uri).body(new TopicoDto(topico));
    }

    @GetMapping("/{id}")
    public DetalhesTopicoDto detalhar(@PathVariable Long id){
        Topico topico = topicoRepository.getOne(id);

        return new DetalhesTopicoDto(topico);

    }

}
