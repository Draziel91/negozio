package org.example.tests;

import java.util.ArrayList;

public class mainP {
    void main(){

        int num1 = 10;
        int num2 = num1;

        num2 = 20;

        double d = 10.8;
        int intero = (int) d;
        Integer integer = 10;

        ArrayList<Integer> lista = new ArrayList<>();

        IO.println(num1);


        Persona p = new Persona("mirko", 10);
        Persona p2 = p;
        Persona p3 = p2;

        p.nome = "Sara";

        IO.println(p.nome);
        IO.println(p2.nome);
        IO.println(p3.nome);

        IO.println(p);
        IO.println(p2);

        Animale a = new Animale();

        String query = "insert ....";

        // preparedStatement(query)+
        // ps.executequery





    }
}
