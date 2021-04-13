package com.github.hugovallada.forum.controller.form;

import com.github.hugovallada.forum.modelo.Curso;
import com.github.hugovallada.forum.modelo.Topico;
import com.github.hugovallada.forum.repository.CursoRepository;
import com.github.hugovallada.forum.repository.TopicoRepository;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class TopicoForm {

    @NotEmpty @Length(min = 5, max = 50)
    private String titulo;

    @NotEmpty @Length(min = 10, max = 100)
    private String mensagem;

    @NotEmpty @Length(min = 5, max = 30)
    private String nomeCurso;

    public TopicoForm(String titulo, String mensagem, String nomeCurso) {
        this.titulo = titulo;
        this.mensagem = mensagem;
        this.nomeCurso = nomeCurso;
    }

    public Topico converter(CursoRepository cursoRepository){
        return new Topico(titulo, mensagem, cursoRepository.findByNome(nomeCurso));
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }
}
