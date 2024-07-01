package com.vicksanchez.springscreenmatch;

import com.vicksanchez.springscreenmatch.model.DatosEpisodio;
import com.vicksanchez.springscreenmatch.model.DatosSerie;
import com.vicksanchez.springscreenmatch.model.DatosTemporada;
import com.vicksanchez.springscreenmatch.principal.Principal;
import com.vicksanchez.springscreenmatch.service.ConsumoAPI;
import com.vicksanchez.springscreenmatch.service.ConvierteDatos;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SpringscreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringscreenmatchApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		System.out.println("Hola mundo");
//		var consumoApi =  new ConsumoAPI();
//		var json = consumoApi.obtenerDatos("https://www.omdbapi.com/?t=game+of+thrones&apikey=daa92f88");
//		System.out.println(json);
//		ConvierteDatos conversor = new ConvierteDatos();
//		var datos = conversor.obtenerDatos(json, DatosSerie.class);
//		System.out.println(datos);
//		json = consumoApi.obtenerDatos("https://www.omdbapi.com/?t=game+of+thrones&Season=1&episode=1&apikey=daa92f88");
//		var datosEpisodio = conversor.obtenerDatos(json, DatosEpisodio.class);
//		System.out.println(datosEpisodio);

		Principal principal = new Principal();
		principal.muestraMenu();
	}
}
