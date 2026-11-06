import calcoli.Calcolatrice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class CalcolatriceTest {


    @BeforeEach
    public void before(){
        IO.println("Inizio test");
    }

    @Test
    @DisplayName("Somma due numeri")
    public void testSomma(){
        Calcolatrice c = new Calcolatrice();
        int a = 3;
        int b = 8;

        int numero = c.somma(a, b);

        // valore atteso, valore ottenuto, messaggio in caso di errore
        Assertions.assertEquals(11, numero, "mi aspettavo 11");
    }

    @Test
    @DisplayName("Differenza di 2 numeri")
    public void testSottrazione(){
        Calcolatrice c = new Calcolatrice();
        int a = 3;
        int b = 1;

        int numero = c.sottrazione(a, b);

        Assertions.assertEquals(1, numero, "Mi aspettavo valore 1");
    }

    @Test
    @DisplayName("Prodotto di 2 numeri")
    public void testMoltiplicazione(){
        Calcolatrice c = new Calcolatrice();
        int a = 3;
        int b = 2;

        int numero = c.prodotto(a, b);


        Assertions.assertEquals(6, numero, "Mi aspettavo valore 6");
    }

    @Test
    @DisplayName("Divione di 2 numeri")
    public void testDivisione(){
        Calcolatrice c = new Calcolatrice();
        double a = 3;
        double b = 2;

        double numero = c.divido(a, b);

        Assertions.assertEquals(1.5, numero, "Mi aspettavo valore 1,5");
    }

    @Test
    @DisplayName("Divisione per 0")
    public void testDivisionePer0(){
        Calcolatrice c = new Calcolatrice();
        double a = 3;
        double b = 0;

        Assertions.assertThrows(ArithmeticException.class, () -> c.divido(a, b));
    }
}
