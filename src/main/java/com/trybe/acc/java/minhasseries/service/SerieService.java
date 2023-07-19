package com.trybe.acc.java.minhasseries.service;

import com.trybe.acc.java.minhasseries.exception.SerieExistenteException;
import com.trybe.acc.java.minhasseries.model.Episodio;
import com.trybe.acc.java.minhasseries.model.Serie;
import com.trybe.acc.java.minhasseries.repository.SerieRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SerieService {
  @Autowired
  SerieRepository serieRepository;

  /**
   * Método post.
   */

  public Serie post(Serie serie) {
    boolean serieExist = serieRepository.existsByNome(serie.getNome());
    if (serieExist) {
      throw new SerieExistenteException("Série Existente");
    }
    Serie result = serieRepository.save(serie);
    return result;
  }

  /**
   * Método findAll.
   */

  public List<Serie> findAll() {
    List<Serie> series = serieRepository.findAll();
    return series;
  }

  /**
   * Método delete.
   */

  public void delete(Integer id) {
    serieRepository.deleteById(id);
  }

  /**
   * Método add episodio em uma série.
   */

  public Serie addEpisodioAtSerie(Integer id, Episodio episodio) {
    Serie serie = serieRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Value is not present"));
    Episodio episodioAdd =
        new Episodio(
          serie.getEpisodios().size(),
          episodio.getNumero(),
          episodio.getDuracaoEmMinutos(),
          serie);
    serie.adicionarEpisodio(episodioAdd);
    System.out.println(serie.getEpisodios());
    Serie serie2 = serieRepository.save(serie);
    return serie2;
  }

  /**
   * Método para exibir os episódios de uma série.
   */

  public List<Episodio> showEpisodiosbySerie(Integer id) {
    Serie serie = serieRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Value is not present"));
    return serie.getEpisodios();
  }
}
