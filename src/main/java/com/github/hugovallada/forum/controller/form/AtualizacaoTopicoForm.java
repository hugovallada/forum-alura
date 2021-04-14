package com.github.hugovallada.forum.controller.form;

import com.github.hugovallada.forum.modelo.Topico;
import com.github.hugovallada.forum.repository.TopicoRepository;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

public class AtualizacaoTopicoForm {

    @NotEmpty
    @Length(min = 5, max = 50)
    private String titulo;

    @NotEmpty @Length(min = 10, max = 100)
    private String mensagem;

    public AtualizacaoTopicoForm() {
    }

    public AtualizacaoTopicoForm(String titulo, String mensagem) {
        this.titulo = titulo;
        this.mensagem = mensagem;
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

    public Topico atualizar(Long id, TopicoRepository topicoRepository) {
        Topico topico = topicoRepository.getOne(id);
        topico.setTitulo(this.titulo);
        topico.setMensagem(this.mensagem);

        return topico;
    }
}
