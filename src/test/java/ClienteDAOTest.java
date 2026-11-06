import dao.ClienteDAO;
import database.DatabaseManager;
import model.Cliente;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;

public class ClienteDAOTest {

    private DatabaseManager db;
    private Connection conn;
    private ClienteDAO cd;
    private int id;
    private String nome;

    private Cliente cliente;

    @BeforeEach
    void setup() throws SQLException {

        conn = new DatabaseManager().getConnection("negozio");

        this.conn.setAutoCommit(false);

        this.cd = new ClienteDAO();
        this.id = 1000;
        this.nome = "Claudio";
        String query = "insert into clienti (id, nome, cognome, email, citta, saldo) values (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = this.conn.prepareStatement(query);

        ps.setInt(1, this.id);
        ps.setString(2, this.nome);
        ps.setString(3, "Bianchi");
        ps.setString(4, "claudio@hotmail.it");
        ps.setString(5, "Milano");
        ps.setDouble(6, 100);
        ps.executeUpdate();

        cliente = cd.findById(id, conn);
    }

    @Test
    void findByIdInesistente(){
        Cliente c = cd.findById(9999);

        Assertions.assertNull(c);
    }

    @Test
    void findByIdEsistente(){
        Cliente c = cd.findById(1);

        Assertions.assertNotNull(c);
    }


    @Test
    void controllaNome(){
        Assertions.assertEquals("Claudio", cliente.getNome(), "errore nome cliente");
        Assertions.assertEquals("1", 2, "3");
    }

    @Test
    void clienteDuplicato(){
        int risultato = cd.insert(cliente, conn);
        Assertions.assertEquals(0, risultato);
    }

    @Test
    void controlloInserimento(){
        Assertions.assertEquals(1000, cliente.getId(), "errore id cliente");
        Assertions.assertEquals("Claudio", cliente.getNome(), "errore nome cliente");
        Assertions.assertEquals("Bianchi", cliente.getCognome(), "errore cognome cliente");
        Assertions.assertEquals("claudio@hotmail.it", cliente.getEmail(), "errore email cliente");
        Assertions.assertEquals("Milano", cliente.getCitta(), "errore citta cliente");
        Assertions.assertEquals(100, cliente.getSaldo(), "errore saldo cliente");
    }

    @Test
    void clienteNonEsistente(){
        Cliente cl = cd.findById(9999);

        Assertions.assertNull(cl);
    }

    @Test
    void inserimentoCliente(){
        Cliente c = new Cliente(1001, "Gino", "Bruni", "ginobru@hotmail.it", "Napoli", 100 );
        int risultato = cd.insert(c, conn);

        Assertions.assertEquals(1, risultato, "errore inserimento cliente");
    }

    @Test
    void findAllTest(){
        ArrayList<Cliente> clienti = cd.findAll();

        Assertions.assertEquals(8, clienti.size());
    }

    @Test
    void aggiornaSaldoTest() throws SQLException{
        int nuovoSaldo = 300;
        cd.aggiornaSaldo(cliente.getId(), nuovoSaldo, conn);

        Cliente c = cd.findById(cliente.getId(), conn);

        Assertions.assertEquals(nuovoSaldo, c.getSaldo(), "errore aggiornaSaldo");
    }

    @Test
    void aggiornaSaldoTestException() {
        int nuovoSaldo = 300;
        Assertions.assertThrows(SQLException.class, () -> cd.aggiornaSaldo(9999, nuovoSaldo, conn), "errore aggiornaSaldo");
    }

    @Test
    void aggiungiSaldoTest() throws SQLException{
        int saldoDaAggiungere = 300;
        cd.aggiungiSaldo(cliente.getId(), saldoDaAggiungere, conn);

        Cliente c = cd.findById(cliente.getId(), conn);

        Assertions.assertEquals(400, c.getSaldo(), "errore aggiungiSaldo");
    }

    @Test
    void aggiungiSaldoTestException() throws SQLException{
        int saldoDaAggiungere = 300;
        int risultato = cd.aggiungiSaldo(9999, saldoDaAggiungere, conn);

        Assertions.assertEquals(0, risultato, "errore aggiungiSaldo");
    }

    @Test
    void inserimentoSenzaConn() throws SQLException{
        int idC = 0;
        Cliente c = new Cliente("Gino", "Bruni", "ginobru@hotmail.it", "Napoli", 100 );
        int risultato = cd.insert(c);




        Assertions.assertEquals(1, risultato, "errore inserimento cliente");

        String queryPerID = "select max(id) from clienti";
        Connection con = new DatabaseManager().getConnection("negozio");
        PreparedStatement ps = con.prepareStatement(queryPerID);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            idC = rs.getInt(1);
        }
        String query = "delete from clienti where id = ?";
        PreparedStatement ps2 = con.prepareStatement(query);
        ps2.setInt(1, idC);
        ps2.executeUpdate();



        ps.close();
        ps2.close();
        con.close();
    }

    @AfterEach
    void tearDown() throws SQLException{
        conn.rollback();
        conn.close();
    }
}
