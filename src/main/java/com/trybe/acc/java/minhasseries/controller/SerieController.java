package com.trybe.acc.java.minhasseries.controller;

import com.trybe.acc.java.minhasseries.model.Serie;
import com.trybe.acc.java.minhasseries.service.SerieService;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/series")
public class SerieController {
  @Autowired
  SerieService serieService;

  /**
   * Método post.
   */

  @PostMapping
  public ResponseEntity<Serie> post(@RequestBody Map<String, String> map) {
    Serie serie = new Serie(map.get("nome"));
    Serie result = serieService.post(serie);
    return ResponseEntity.ok(result);
  }

  /**
   * Método findAll.
   */

  @GetMapping
  public ResponseEntity<List<Serie>> findAll() {
    List<Serie> series = serieService.findAll();
    return ResponseEntity.ok(series);
  }

  /**
   * Método delete.
   */

  @DeleteMapping("/{id}")
  public ResponseEntity delete(@PathVariable Integer id) {
    serieService.delete(id);
    return ResponseEntity.ok("Deleted");
  }
}
