package br.com.fiap.netflix.consumer.repository;

import br.com.fiap.netflix.consumer.entity.Curtida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CurtidaRepository extends JpaRepository<Curtida,Long> {

    @Query(value = "SELECT * FROM curtida WHERE  id_movie = :id",nativeQuery=true)
    Curtida findByIdMovie(@Param("id") Long id);

}
