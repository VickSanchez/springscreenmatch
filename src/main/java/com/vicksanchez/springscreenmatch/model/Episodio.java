package com.vicksanchez.springscreenmatch.model;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Episodio {

    private Integer temporada;
    private String titulo;
    private Integer numerEpisodio;
    private Double evaluacion;
    private LocalDate fechaDeLanzamiento;

    public Episodio(Integer numero, DatosEpisodio episodio) {

            this.temporada = numero;
            this.titulo = episodio.titulo();
            this.numerEpisodio = episodio.numeroEpisodio();
        try {
            this.evaluacion =  Double.valueOf(episodio.evaluacion());
        }catch(NumberFormatException e) {
            this.evaluacion= 0.0;
        }
        try{
            this.fechaDeLanzamiento = LocalDate.parse(episodio.fechaDeLanzamiento());
        }catch (DateTimeParseException e){
            this.fechaDeLanzamiento = null;
        }
    }

    public LocalDate getFechaDeLanzamiento() {
        return fechaDeLanzamiento;
    }

    public void setFechaDeLanzamiento(LocalDate fechaDeLanzamiento) {
        this.fechaDeLanzamiento = fechaDeLanzamiento;
    }

    public Double getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(Double evaluacion) {
        this.evaluacion = evaluacion;
    }

    public Integer getNumerEpisodio() {
        return numerEpisodio;
    }

    public void setNumerEpisodio(Integer numerEpisodio) {
        this.numerEpisodio = numerEpisodio;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getTemporada() {
        return temporada;
    }

    public void setTemporada(Integer temporada) {
        this.temporada = temporada;
    }

    @Override
    public String toString() {
        return  "temporada=" + temporada +
                ", titulo='" + titulo + '\'' +
                ", numerEpisodio=" + numerEpisodio +
                ", evaluacion=" + evaluacion +
                ", fechaDeLanzamiento=" + fechaDeLanzamiento ;
    }
}
