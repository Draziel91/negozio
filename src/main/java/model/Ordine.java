package model;


import java.sql.Date;

public class Ordine {
    private int id;
    private Cliente cliente;
    private Prodotto prodotto;
    private int quantita;
    private Date data;

    public Ordine(){

    }

    public Ordine(int id, Cliente cliente, Prodotto prodotto, int quantita, Date data) {
        this.id = id;
        this.cliente = cliente;
        this.prodotto = prodotto;
        this.quantita = quantita;
        this.data = data;
    }

    public Ordine(Cliente cliente, Prodotto prodotto, int quantita, Date data) {
        this.cliente = cliente;
        this.prodotto = prodotto;
        this.quantita = quantita;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Prodotto getProdotto() {
        return prodotto;
    }

    public void setProdotto(Prodotto prodotto) {
        this.prodotto = prodotto;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
