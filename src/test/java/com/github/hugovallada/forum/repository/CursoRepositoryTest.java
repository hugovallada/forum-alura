package com.github.hugovallada.forum.repository;

import com.github.hugovallada.forum.modelo.Curso;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

@DataJpaTest
public class CursoRepositoryTest {

    @Autowired
    private CursoRepository repository;

    @Test
    public void deveriaCarregarUmCursoAoBuscaRPeloSeuNome(){
        String nomeCurso = "HTML 5";
        Curso curso = repository.findByNome(nomeCurso);

        Assertions.assertNotNull(curso);
        Assertions.assertEquals(nomeCurso, curso.getNome()) ;
    }

    @Test
    public void naoDeveriaCarregarUmCursoNaoCadastradoAoBuscaRPeloSeuNome(){
        String nomeCurso = "JPA";
        Curso curso = repository.findByNome(nomeCurso);

        Assertions.assertNull(curso);
    }

}
