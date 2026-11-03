package dao;

import database.DatabaseManager;
import model.Prodotto;

import java.sql.*;
import java.util.ArrayList;

public class ProdottoDAO implements BaseDAO<Prodotto, Integer> {

    DatabaseManager db;


    @Override
    public Prodotto findById(Integer id) {
        String query = "select * from prodotti where id = ?";

        try {
            Connection conn = db.getConnection("negozio");

            PreparedStatement stm = conn.prepareStatement(query);


            stm.setInt(1, id);

            IO.println(query);

            ResultSet rs = stm.executeQuery();

            Prodotto p = null;

            while (rs.next()) {
                IO.println("prodotto trovato: " + rs.getString("nome") + ", quantita: " + rs.getInt("quantita"));
                p = new Prodotto(id, rs.getString("nome"), rs.getString("categoria"), rs.getDouble("prezzo"), rs.getInt("quantita"));
            }

            return p;


        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Prodotto findById(Integer id, Connection conn) {
        String query = "select * from prodotti where id = ?";

        try {
            PreparedStatement stm = conn.prepareStatement(query);


            stm.setInt(1, id);

            IO.println(query);

            ResultSet rs = stm.executeQuery();

            Prodotto p = null;

            while (rs.next()) {
                IO.println("prodotto trovato: " + rs.getString("nome") + ", quantita: " + rs.getInt("quantita"));
                p = new Prodotto(id, rs.getString("nome"), rs.getString("categoria"), rs.getDouble("prezzo"), rs.getInt("quantita"));
            }

            return p;


        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<Prodotto> findAll() {
        String query = "select * from prodotti";
        ArrayList<Prodotto> prodotti = new ArrayList<>();

        try {
            Connection conn = db.getConnection("negozio");

            Statement stm = conn.createStatement();

            ResultSet rs = stm.executeQuery(query);

            Prodotto p = null;

            while (rs.next()) {
                IO.println("prodotto trovato: " + rs.getString("nome") + ", quantita: " + rs.getInt("quantita"));
                prodotti.add(new Prodotto(rs.getInt("id"), rs.getString("nome"), rs.getString("categoria"), rs.getDouble("prezzo"), rs.getInt("quantita")));
            }

            return prodotti;


        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int insert(Prodotto entity) {
        String query = "insert into prodotti (nome, categoria, prezzo) values (?, ?, ?)";
        try (Connection conn = db.getConnection("negozio");
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, entity.getNome());
            ps.setString(2, entity.getCagtegoria());
            ps.setDouble(3, entity.getPrezzo());

            int result = ps.executeUpdate();
            if (result > 0) {
                IO.println("Prodotto inserito correttamente.");
            }


        } catch (SQLException e) {
            IO.println("Qualcosa è andato storto.");
            e.printStackTrace();
        }
        return 0;
    }

    public int aggiornaQuantita(int id, int quantita, Connection conn) throws SQLException {
        String query = "update prodotti set quantita = ? where id = ?";

        try(PreparedStatement ps = conn.prepareStatement(query);){
            ps.setInt(1, quantita);
            ps.setInt(2, id);
            int result = ps.executeUpdate();
            if (result > 0) {
                IO.println("quantita prodotto modificato correttamente");
                return result;
            } else {
                throw new SQLException("errore durante l aggiornamento prodotto");
            }
        }catch(SQLException e){
            throw new SQLException("errore durante l'aggiornamento prodotti", e);
        }
    }


    public void caricaProdotti(int id, int quantita){
        String query = "update prodotti set quantita = quantita + ? where id = ?";
        try (Connection conn = db.getConnection("negozio");
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, quantita);
            ps.setInt(2, id);

            int result = ps.executeUpdate();
            if(result > 0){
                IO.println("Prodotto aggiornato correttamente");
            }else{
                IO.println("Prodotto non trovato!");
            }
        }catch (SQLException e){
            IO.println("Qualcosa è andato storto");
            e.printStackTrace();
        }

    }
}
