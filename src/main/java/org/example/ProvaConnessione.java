package org.example;

import dao.ClienteDAO;
import database.DatabaseManager;
import database.DatabaseQuery;
import database.DatabaseService;
import model.Cliente;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProvaConnessione {
    void main() {
        DatabaseQuery dq = new DatabaseQuery();
        DatabaseService ds = new DatabaseService();

        ClienteDAO cd = new ClienteDAO();
//        cd.aggiungiSaldo(1, 10);

        ds.acquistaProdotti(1,2, 2);
    }
}
