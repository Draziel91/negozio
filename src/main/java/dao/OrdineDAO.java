package dao;

import database.DatabaseManager;
import model.Cliente;
import model.Ordine;
import model.Prodotto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrdineDAO implements BaseDAO <Ordine, Integer>{

    DatabaseManager db;
    ClienteDAO cd = new ClienteDAO();
    ProdottoDAO pd = new ProdottoDAO();

    @Override
    public Ordine findById(Integer id) {
        String query = "select * from ordini where id = ?";
        try {
            Connection conn = db.getConnection("negozio");
            PreparedStatement stm = conn.prepareStatement(query);

            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            Ordine o = null;

            while (rs.next()) {
                o = new Ordine(rs.getInt("id"), cd.findById(rs.getInt("id_cliente")), pd.findById(rs.getInt("id_prodotto")), rs.getInt("quantita"), rs.getDate("data_ordine"));
            }

            return o;


        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Ordine findById(Integer integer, Connection conn) {
        return null;
    }

    @Override
    public ArrayList<Ordine> findAll() {
        String query = "select * from ordini";
        ArrayList<Ordine> ordini = new ArrayList<>();
        try {
            Connection conn = db.getConnection("negozio");
            PreparedStatement stm = conn.prepareStatement(query);

            ResultSet rs = stm.executeQuery();
            Ordine o = null;

            while (rs.next()) {
                ordini.add(new Ordine(rs.getInt("id"), cd.findById(rs.getInt("id_cliente")), pd.findById(rs.getInt("id_prodotto")), rs.getInt("quantita"), rs.getDate("data_ordine")));
            }

            return ordini;


        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int insert(Ordine entity) {
        String query = "insert into ordini (id_cliente, id_prodotto, quantita, data_ordine) values (?, ?, ?, ?)";
        try (Connection conn = db.getConnection("negozio");
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, entity.getCliente().getId());
            ps.setInt(2, entity.getProdotto().getId());
            ps.setInt(3, entity.getQuantita());
            ps.setDate(4, entity.getData());

            int result = ps.executeUpdate();
            if (result > 0) {
                IO.println("Ordine inserito correttamente.");
                return result;
            }


        } catch (SQLException e) {
            IO.println("Qualcosa è andato storto.");
            e.printStackTrace();
        }
        return 0;
    }


    public int insert(Ordine entity, Connection conn) {
        String query = "insert into ordini (id_cliente, id_prodotto, quantita, data_ordine) values (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, entity.getCliente().getId());
            ps.setInt(2, entity.getProdotto().getId());
            ps.setInt(3, entity.getQuantita());
            ps.setDate(4, entity.getData());

            int result = ps.executeUpdate();
            if (result > 0) {
                IO.println("Ordine inserito correttamente.");
                return result;
            }


        } catch (SQLException e) {
            IO.println("Qualcosa è andato storto.");
            e.printStackTrace();
        }
        return 0;
    }

    public Ordine findOrdineByClienteIdProdottoId(Cliente cliente, Prodotto prodotto) {
        String query = "select * from ordini where id_cliente = ? and id_prodotto = ?";
        try {
            Connection conn = db.getConnection("negozio");

            PreparedStatement stm = conn.prepareStatement(query);


            stm.setInt(1, cliente.getId());
            stm.setInt(2, prodotto.getId());

            ResultSet rs = stm.executeQuery();

            Ordine o = null;

            while (rs.next()) {
                o = new Ordine(rs.getInt("id"), cliente, prodotto, rs.getInt("quantita"), rs.getDate("data_ordine"));
            }

            return o;


        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
