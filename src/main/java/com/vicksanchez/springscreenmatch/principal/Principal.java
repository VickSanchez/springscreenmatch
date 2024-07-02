package com.vicksanchez.springscreenmatch.principal;

import com.vicksanchez.springscreenmatch.model.DatosEpisodio;
import com.vicksanchez.springscreenmatch.model.DatosSerie;
import com.vicksanchez.springscreenmatch.model.DatosTemporada;
import com.vicksanchez.springscreenmatch.model.Episodio;
import com.vicksanchez.springscreenmatch.service.ConsumoAPI;
import com.vicksanchez.springscreenmatch.service.ConvierteDatos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

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
//        System.out.println(json);
        var datos = conversor.obtenerDatos(json, DatosSerie.class);
        System.out.println(datos);
        // Busca los datos de todas las temporadas
        List<DatosTemporada> temporadas = new ArrayList<>();
        if (datos.totalDeTemporadas() == null){
            System.out.println("Serie no Encontrada");
            return;
        }

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
//        temporadas.forEach(temporada -> temporada.episodios().forEach( episodio -> System.out.println(episodio.titulo())));

        // Convertir a una lista del tipo Datos episodio

//        List<DatosEpisodio> datosEpisodios = temporadas.stream()
//                .flatMap(temporada -> temporada.episodios().stream())
//                .collect(Collectors.toList());
//        System.out.println("Top 5 Episodios");
//        datosEpisodios.stream()
//                .filter(episodio -> !episodio.evaluacion().equalsIgnoreCase("N/A"))
//                .peek(episodio -> System.out.println("Soy un peek"))
//                .sorted(Comparator.comparing(DatosEpisodio::evaluacion).reversed())
//                .limit(5)
//                .forEach(System.out::println);




        // Convirtiendo los datos a una lista de episodios del tipo Episodio
        System.out.println("Todos los Episodios");
        List<Episodio> episodios = temporadas.stream()
                .flatMap(temporada -> temporada.episodios().stream()
                        .map(episodio -> new Episodio(temporada.numero(), episodio))
                )
                .collect(Collectors.toList());
//
//        episodios.forEach(System.out::println);

        // Busqueda de episodios a partir de X año
//        System.out.println("Por favor indica el año a partir del cual deseas ver los episodios");
//        var fecha = teclado.nextInt();
//        teclado.nextLine();
//
//        LocalDate fechaBusqueda = LocalDate.of(fecha,1,1);
//
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//
//        episodios.stream()
//                .filter(episodio -> episodio.getFechaDeLanzamiento() != null && episodio.getFechaDeLanzamiento().isAfter(fechaBusqueda))
//                .forEach(episodio -> {
//                    System.out.println(
//                            "Temporada: " + episodio.getTemporada() +
//                            ", Episodio: " + episodio.getTitulo() +
//                            ", Fecha de Lanzamiento: " + episodio.getFechaDeLanzamiento().format(dtf)
//                    );
//                });

        // Buscar un episodio por titulo
//        System.out.println("Por favor escriba el titulo del episodio que deseas ver");
//        var tituloIngresado = teclado.nextLine();
//        Optional<Episodio> episodioBuscado = episodios.stream()
//                .filter(episodio -> episodio.getTitulo().toUpperCase().contains(tituloIngresado.toUpperCase()))
//                .findFirst();
//        if (episodioBuscado.isPresent()){
//            System.out.println(" Episodio Encontrado");
//            System.out.println(episodioBuscado.get());
//        }else {
//            System.out.println("Episodio no encontrado");
//        }

        Map<Integer, Double> evaluacionesPorTemporada = episodios.stream()
                .filter(episodio -> episodio.getEvaluacion() > 0.0)
                .collect(Collectors.groupingBy(Episodio::getTemporada, Collectors.averagingDouble(Episodio::getEvaluacion)));
        System.out.println(evaluacionesPorTemporada);

        DoubleSummaryStatistics est = episodios.stream()
                .filter(episodio -> episodio.getEvaluacion() > 0.0)
                .collect(Collectors.summarizingDouble(Episodio::getEvaluacion));
        System.out.println("Media: " + est.getAverage());
        System.out.println("Episodio Mejor Evaluado: " + est.getMax());
        System.out.println("Episodio Menor Evaluado: " + est.getMin());

    }
}
