package org.example.tests;

public class Edificio {

    int altezza;
    int numeroPiani;
    String nomeParco;

    public Edificio(int altezza, int numeroPiani, String nomeParco) {
        this.altezza = altezza;
        this.numeroPiani = numeroPiani;
        this.nomeParco = nomeParco;
    }

    public Edificio(int altezza) {
        this.altezza = altezza;
    }

    public static void metodoStatico(){
        IO.println("Sono un metodo statico");
        IO.println();
    }

    public void mostraDati(){
        IO.println("sono un palazzo");
        IO.println(this.altezza);
    }

    // numero degli argomeni, ordine degli argomenti, tipi di argomenti
    public void mostraDati(int altezza, int numeroPiani){
        IO.println("sono un palazzo di "+ altezza +" metri");
    }

    public void mostraDati(String numeropiani, String altezza){
        IO.println("sono un palazzo di "+ altezza +" metri");
    }


    public void mostraDati(int altezza, String numParco){
        IO.println("sono un palazzo di "+ altezza +" metri");
    }

    public void mostraDati(String numParco, int altezza){
        IO.println("sono un palazzo di "+ altezza +" metri");
    }
}
