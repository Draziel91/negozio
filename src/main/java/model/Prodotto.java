package model;

public class Prodotto {
    private int id;
    private String nome;
    private String cagtegoria;
    private double prezzo;
    private int quantita;

    public Prodotto(){

    }

    public Prodotto(int id, String nome, String cagtegoria, double prezzo, int quantita) {
        this.id = id;
        this.nome = nome;
        this.cagtegoria = cagtegoria;
        this.prezzo = prezzo;
        this.quantita = quantita;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCagtegoria() {
        return cagtegoria;
    }

    public void setCagtegoria(String cagtegoria) {
        this.cagtegoria = cagtegoria;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }
}
