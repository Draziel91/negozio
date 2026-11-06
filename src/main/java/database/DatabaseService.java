package database;

import dao.ClienteDAO;
import dao.OrdineDAO;
import dao.ProdottoDAO;
import model.Cliente;
import model.Ordine;
import model.Prodotto;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class DatabaseService {

    ClienteDAO cd = new ClienteDAO();
    ProdottoDAO pd = new ProdottoDAO();
    OrdineDAO od = new OrdineDAO();
    DatabaseManager db = new DatabaseManager();

    public void acquistaProdotto(int clienteId, int prodottoId) {
        acquistaProdotti(clienteId, prodottoId, 1);
    }

    public void metodoTest(){

    }

    public void acquistaProdotti(int clienteId, int prodottoId, int quantita) {
        if(quantita < 1){
            IO.println("Valore quantita non valido");
            return;
        }

        try (Connection conn = db.getConnection("negozio")) {
            conn.setAutoCommit(false);
            try {

                // 1. ricerca cliente
                Cliente cliente = cd.findById(clienteId, conn);

                // 2. ricerca prodotto
                Prodotto prodotto = pd.findById(prodottoId, conn);

                if (cliente != null && prodotto != null) {
                    if(cliente.getSaldo() < (prodotto.getPrezzo() * quantita)){
                        throw new SQLException("Saldo insufficiente!");
                    }
                    if(prodotto.getQuantita() < quantita){
                        throw new SQLException("Prodotti non disponibili! Disponibilità richiesta: " + quantita + ", disponibilita attuale: " + prodotto.getQuantita());
                    }

                    Ordine ordine = new Ordine(cliente, prodotto, quantita, Date.valueOf(LocalDate.now()));

                    // 3. inserimento ordine
                    int resultOrdine = od.insert(ordine, conn);

                    if (resultOrdine > 0) {
                        // 4. aggiornamento saldo cliente
                        cd.aggiornaSaldo(clienteId, (cliente.getSaldo() - prodotto.getPrezzo() * quantita), conn);

                        // 5. aggiornamento quantita prodotti
                        pd.aggiornaQuantita(prodottoId, (prodotto.getQuantita() - quantita), conn);
                        conn.commit();

                    } else {
                        throw new SQLException("Errore durante l inserimento dell' ordine");
                    }
                } else {
                    throw new SQLException("Errore durante la ricerca di cliente e/o prodotto");
                }
            } catch (SQLException ex) {
                conn.rollback();
                ex.printStackTrace();
            }


        } catch (SQLException exc) {
            exc.printStackTrace();
        }
    }
}
