package calcoli;

public class ContoBancario {

    public double saldo;

    public ContoBancario (double saldo){
        if(saldo < 0){
            throw new IllegalArgumentException("Salgo negativo non consentito!");
        }
        this.saldo = saldo;
    }

    public void deposita(double valore){
        if(valore < 0){
            throw new IllegalArgumentException("Deposito negativo non consentito!");
        }
        this.saldo += valore;
    }

    public void preleva(double valore){
        if(valore < 0){
            throw new IllegalArgumentException("Preleva negativo non consentito!");
        }
        if(this.saldo - valore < 0){
            throw new ArithmeticException("Saldo non disponibile");
        }
        this.saldo -= valore;
    }
}
