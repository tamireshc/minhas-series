package com.trybe.acc.java.minhasseries.service;

import com.trybe.acc.java.minhasseries.exception.SerieExistenteException;
import com.trybe.acc.java.minhasseries.model.Serie;
import com.trybe.acc.java.minhasseries.repository.SerieRepository;

import java.util.List;

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
}