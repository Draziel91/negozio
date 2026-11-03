package dao;

import database.DatabaseManager;
import model.Cliente;

import java.sql.*;
import java.util.ArrayList;

public class ClienteDAO implements BaseDAO <Cliente, Integer>{

    DatabaseManager db = new DatabaseManager();

    @Override
    public Cliente findById(Integer id) {
        Connection conn = db.getConnection("negozio");
        String query = "Select * from clienti where id = ?";
        try {
            PreparedStatement pstm = conn.prepareStatement(query);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();

            boolean clienteTrovato = false;
            Cliente c = null;

            while (rs.next()) {
                clienteTrovato = true;
                IO.println("Il cliente con id: (" + id + ") è: " + rs.getString("nome") + " " + rs.getString("cognome"));
                c = new Cliente(id, rs.getString("nome"), rs.getString("cognome"), rs.getString("email"), rs.getString("citta"), rs.getDouble("saldo"));
            }

            if (!clienteTrovato) {
                IO.println("Nessun cliente trovato con le credenziali inserite. id: " + id);
            }


            if (conn != null) {
                conn.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (rs != null) {
                rs.close();
            }

            return c;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Cliente findById(Integer id, Connection conn) {
        String query = "Select * from clienti where id = ?";
        try {
            PreparedStatement pstm = conn.prepareStatement(query);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();

            boolean clienteTrovato = false;
            Cliente c = null;

            while (rs.next()) {
                clienteTrovato = true;
                IO.println("Il cliente con id: (" + id + ") è: " + rs.getString("nome") + " " + rs.getString("cognome"));
                c = new Cliente(id, rs.getString("nome"), rs.getString("cognome"), rs.getString("email"), rs.getString("citta"), rs.getDouble("saldo"));
            }

            if (!clienteTrovato) {
                IO.println("Nessun cliente trovato con le credenziali inserite. id: " + id);
            }
            if (pstm != null) {
                pstm.close();
            }
            if (rs != null) {
                rs.close();
            }

            return c;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<Cliente> findAll() {
        Connection conn = db.getConnection("negozio");
        String query = "Select * from clienti";
        ArrayList<Cliente> clienti = new ArrayList<>();
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);


            while (rs.next()) {
                IO.println(rs.getString("nome") + " " + rs.getString("cognome") + " " +
                        rs.getString("citta"));
                clienti.add(new Cliente(rs.getInt("id"), rs.getString("nome"), rs.getString("cognome"), rs.getString("email"), rs.getString("citta"), rs.getDouble("saldo")));
            }

            if (conn != null) {
                conn.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (rs != null) {
                rs.close();
            }

            return clienti;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int insert(Cliente cliente) {
        String query = "insert into clienti (nome, cognome, email, citta, saldo) values (?, ?, ?, ?, ?)";
        try (Connection conn = db.getConnection("negozio");
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getCognome());
            ps.setString(3, cliente.getEmail());
            ps.setString(4, cliente.getCitta());
            ps.setDouble(5, cliente.getSaldo());

            int result = ps.executeUpdate();
            if (result > 0) {
                IO.println("Cliente inserito correttamente.");
                return result;
            }


        } catch (SQLException e) {
            IO.println("Qualcosa è andato storto.");
            e.printStackTrace();
        }
        return 0;
    }

    public int aggiornaSaldo(int id, double saldo, Connection conn) throws SQLException {
        String query = "update clienti set saldo = ? where id = ?";
        try(PreparedStatement ps = conn.prepareStatement(query)){
            ps.setDouble(1, saldo);
            ps.setInt(2, id);


            int result = ps.executeUpdate();

            if (result > 0) {
                IO.println("saldo cliente modificato correttamente");
                return result;
            } else {
                throw new SQLException();
            }
        }catch(SQLException e){
            throw new SQLException("Errore durante l aggiornamento cliente", e);
        }
    }

    public void aggiungiSaldo(int id, double saldo){
        String query = "update clienti set saldo = saldo + ? where id = ?";
        try (Connection conn = db.getConnection("negozio");
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setDouble(1, saldo);
            ps.setInt(2, id);

            int result = ps.executeUpdate();
            if (result > 0) {
                IO.println("Saldo cliente aggiornato correttamente");
            }else{
                IO.println("Cliente non trovato!");
            }


        } catch (SQLException e) {
            IO.println("Qualcosa è andato storto.");
            e.printStackTrace();
        }
    }
}
