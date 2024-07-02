package com.vicksanchez.springscreenmatch.principal;

import java.util.Arrays;
import java.util.List;

public class EjemploStreams {

    public void muestraEjemplo() {
        List<String> nombres = Arrays.asList("Brenda", "Luis", "Vick");

        nombres.stream()
                .sorted()
                .filter(nombre -> nombre.startsWith("B"))
//                .map(nombre -> nombre.toUpperCase())
                .map(String::toUpperCase)
                .forEach(System.out::println);
    }
}
