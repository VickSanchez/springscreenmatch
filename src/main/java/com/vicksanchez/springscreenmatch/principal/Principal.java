package com.vicksanchez.springscreenmatch.principal;

import com.vicksanchez.springscreenmatch.model.DatosEpisodio;
import com.vicksanchez.springscreenmatch.model.DatosSerie;
import com.vicksanchez.springscreenmatch.model.DatosTemporada;
import com.vicksanchez.springscreenmatch.service.ConsumoAPI;
import com.vicksanchez.springscreenmatch.service.ConvierteDatos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {

    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi =  new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private final String URL_BASE = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=daa92f88";

    public void muestraMenu() {
        System.out.println("Por favor Escribe el nombre de la serie que deseas buscar");
        // Busca los datos generales de la serie
        var busquedaSerie = teclado.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE + busquedaSerie.replace(" ", "+") +  API_KEY);
        var datos = conversor.obtenerDatos(json, DatosSerie.class);
        System.out.println(datos);
        // Busca los datos de todas las temporadas
        List<DatosTemporada> temporadas = new ArrayList<>();

        for (int i = 1; i <= datos.totalDeTemporadas(); i++) {
            json = consumoApi.obtenerDatos( URL_BASE + busquedaSerie.replace(" ", "+") + "&Season="+ i + API_KEY);
            var datosTemporadas = conversor.obtenerDatos(json, DatosTemporada.class);
            temporadas.add(datosTemporadas);
        }
        temporadas.forEach(System.out::println);

        // Mostrar solo el titulo de los episodios de las temporadas
//        for (int i = 0; i < datos.totalDeTemporadas(); i++) {
//            List<DatosEpisodio> episodiosTemporada = temporadas.get(i).episodios();
//            for (int j = 0; j < episodiosTemporada.size(); j++) {
//                System.out.println(episodiosTemporada.get(j).titulo());
//            }
//        }

        // Mejorar el codigo con funciones lambda o callbacks
        temporadas.forEach(temporada -> temporada.episodios().forEach( episodio -> System.out.println(episodio.titulo())));
    }
}
