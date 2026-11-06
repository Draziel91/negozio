import calcoli.ContoBancario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ContoBancarioTest {

    ContoBancario conto;

//    @ParameterizedTest
//    @ValueSource(doubles = {10, 100, 1000})

    @BeforeEach
    void inizializza(){
        conto = new ContoBancario(100);
    }


//    @Test
//    public void aggiungiSaldo(){
//
//
//        Assertions.assertEquals(100, cb.saldo, 0.001);
//    }

    @Test
    public void aggiungiSaldoNegativo(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> new ContoBancario(-10), "Mi aspettavo un IllegalArgumentException");
    }

    @Test
    public void deposita(){
        conto.deposita(100);
        Assertions.assertEquals(200, conto.saldo, 0.001);
    }

    @Test
    public void depositaFallimento(){
        conto.deposita(100);
        Assertions.assertNotEquals(100, conto.saldo, 0.001);
    }

    @Test
    public void depositaException(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> conto.deposita(-100));
    }

    @Test
    public void preleva(){
        conto.preleva(20);
        Assertions.assertEquals(80, conto.saldo, 0.001);
    }

    @Test
    public void prelevaFallimento(){
        conto.deposita(20);
        Assertions.assertNotEquals(100, conto.saldo, 0.001);
    }

    @Test
    public void prelevaExceptionArg(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> conto.preleva(-100));
    }

    @Test
    public void prelevaExceptionArit(){
        Assertions.assertThrows(ArithmeticException.class, () -> conto.preleva(120));
    }
}
