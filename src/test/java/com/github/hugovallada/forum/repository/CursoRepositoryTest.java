package com.github.hugovallada.forum.repository;

import com.github.hugovallada.forum.modelo.Curso;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class CursoRepositoryTest {

    @Autowired
    private CursoRepository repository;

    @Autowired
    private TestEntityManager em;

    @Test
    public void deveriaCarregarUmCursoAoBuscaRPeloSeuNome(){
        String nomeCurso = "HTML 5";
        Curso html7 = new Curso();
        html7.setNome(nomeCurso);
        html7.setCategoria("Prod");
        em.persist(html7);

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
