package org.example.tests;

import database.DatabaseManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

public class Casa extends Edificio{

        public Casa(){
            super(1, 2, "3");
        }

    public Casa(int alt, int num, String altro){
        super(alt, 2, "3");
    }


    public void metodo(){
            try{
                Connection conn = new DatabaseManager().getConnection("negozio");
            }catch(ArithmeticException e){


            }catch(Exception e){

            }finally {

            }

        ArrayList<String> lista = new ArrayList<>();
        Set<String> set = new HashSet<>();

        Map<Integer, String> mappa = new HashMap<>();

        lista.contains("ciao");
    }
}
