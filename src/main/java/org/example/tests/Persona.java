package org.example.tests;

public class Persona {

    public String nome;
    public String cognome;
    public int eta;




    // numero argomenti, ordine argomenti, tipo di argomenti

    public Persona(String nome, int etaAttributo){
        this.nome = nome;
        eta = etaAttributo;
    }

    public Persona(int etaAttributo, String nome){
        this.nome = nome;
        eta = etaAttributo;
    }

    public Persona(String nome, String cognome, int eta){
        this(nome, eta);
        this.cognome = cognome;
    }

    public void saluta() throws Exception{
        IO.println("ciao sono il padre");
    }

    public void stampaNome(){
        if(this.nome == null) {
            return;
        }
        IO.println(this.nome);
    }





}
