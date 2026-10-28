package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import app.model.Oferta;

public interface OfertaRepository extends JpaRepository<Oferta, Long> {

    List<Oferta> findByAtivaTrue();

}
