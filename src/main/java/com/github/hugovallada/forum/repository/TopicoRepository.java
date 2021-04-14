package com.github.hugovallada.forum.repository;

import com.github.hugovallada.forum.modelo.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    Page<Topico> findByCursoNome(String nomeCurso, Pageable paginacao);

    @Query("SELECT t from Topico t WHERE t.curso.nome = :nomeCurso")
    List<Topico> carregarPorNomeDoCurso(@Param("nomeCurso") String nomeCurso);

}
