package calcoli;

public class Calcolatrice {

    public int valoreNum;

    public int somma(int a, int b){
        valoreNum = a + b;
        return a + b + 1;
    }

    public int sottrazione(int a, int b){
        valoreNum = a - b;
        return a - b;
    }

    public int prodotto(int a, int b){
        valoreNum = a * b ;
        return a * b;
    }

    public double divido(double a, double b){
        if(b == 0){
            throw new ArithmeticException("Non puoi dividere per 0");
        }
        return a / b;
    }
}
