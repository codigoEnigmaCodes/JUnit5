package com.example.practicajunit5;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.condition.DisabledOnJre;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;


class UtilsTest {

    private Utils utils;

    @BeforeEach
    void initMetodoTest() {
        //this.cuenta = new Cuenta("Andres", new BigDecimal("1000.12345"));
        System.out.println("iniciando el metodo.");
        utils = new Utils();
        //String path =  new File("").getAbsolutePath()+"\\test\\java\\com\\example\\practicajunit5\\resources\\curp.csv";
        //System.out.println("f "+path+"\\test\\java\\com\\example\\practicajunit5\\resources\\curp.csv");

    }

    @Tag("numero")
    @DisplayName("Valores numericos 0 - 9")
    @Test
    void testDebitoCuenta() {
        /*assert sencillo*/
        assertEquals(2, 1 + 1);

        assertTrue(utils.numero("2"));
    }


    @DisplayName("Probando")
    @RepeatedTest(value = 20, name = "{displayName} - ciclo numero {currentRepetition} de {totalRepetitions}")
    public void testNumeroRepeticion(RepetitionInfo info) {
        String val = String.valueOf(info.getCurrentRepetition());
        if (info.getCurrentRepetition() == 3) {
            System.out.println("estamos en la repeticion " + info.getCurrentRepetition());
            assertEquals(info.getCurrentRepetition(), 3);

        }
        if (info.getCurrentRepetition() <= 9) {
            assertTrue(utils.numero(val));
        }

        if (info.getCurrentRepetition() > 9) {
            assertFalse(utils.numero(val));
        }
    }

    @Tag("param")
    @Nested
    class PruebasParametrizadasTest {

        @ParameterizedTest(name = "numero {index} ejecutando con valor {argumentsWithNames}")
        @ValueSource(strings = {"0", "1", "100", "200", "300", "500", "700", "900", "353", "627"})
        void testNumeroCorrecto(String monto) {
            assertTrue(utils.numeros(monto));
        }

        @ParameterizedTest(name = "numero {index} ejecutando con valor {argumentsWithNames}")
        @ValueSource(strings = {"0.1", "0.0", "-10", "200.1", "300a"})
        void testNumeroIncorrecto(String monto) {
            assertFalse(utils.numeros(monto));
        }

        @ParameterizedTest(name = "numero {index} ejecutando con valor {0} - {argumentsWithNames}")
        @CsvSource({"caracter,s|mbolo$", "palabra,?¡¡", "día,dia|", "ñuñez,7854", "áéíóú,-", "introdiccón,$%&/"})
        void testCaracteres(String cadValida, String cadInvalida) {

            assertTrue(utils.caracteres(cadValida));
            assertFalse(utils.caracteres(cadInvalida));
        }

        @ParameterizedTest
        @CsvSource({
                "apple,         1",
                "banana,        2",
                "'lemon, lime', 0xF1",
                "strawberry,    700_000"
        })
        void testWithCsvSource(String fruit, int rank) {
            assertNotNull(fruit);
            assertNotEquals(0, rank);
        }

        @ParameterizedTest(name = "numero {index} ejecutando con valor {0} - {argumentsWithNames}")
        @CsvFileSource(files = "C:/Users/jduranca/AndroidStudioProjects/PracticaJunit5/app/src/test/java/com/example/practicajunit5/resources/curp.csv")
        void testCurpCsvFileSource(String curp) {


        }

        @ParameterizedTest(name = "numero {index} ejecutando con valor {0} - {argumentsWithNames}")
        @CsvFileSource(resources = "/numero.csv")
        void testCurpCsvFilSource(Integer numero) {

            assertEquals(numero, 12);
        }

        @ParameterizedTest(name = "numero {index} ejecutando con valor {0} - {argumentsWithNames}")
        @CsvFileSource(resources = "/curp.csv")
        void testCurpCsvFileResourse(String curp) {

            assertTrue(utils.curp(curp));
        }


    }

    @Tag("param")
    @ParameterizedTest(name = "numero {index} ejecutando con valor {0} - {argumentsWithNames}")
    @MethodSource("montoList")
    void testMontosSource(String monto) {

        assertTrue(utils.moneda(monto));
    }

    static List<String> montoList() {
        return Arrays.asList("$100,000", "$200,000", "$3,000,000", "$500.00", "$700", "$1,000.12");
    }

    @Order(1)
    @DisplayName("Variables del sistema")
    @Nested
    class SistemaOperativoTest {
        /*Se activa la prueba deacuerdo a la condición si es sistema operativo u otras opciones*/
        @Test
        @EnabledOnOs(OS.WINDOWS)
        void testSoloWindows() {
        }

        @Test
        @EnabledOnOs({OS.LINUX, OS.MAC})
        void testSoloLinuxMac() {
        }

        @Test
        @DisabledOnOs(OS.WINDOWS)
        void testNoWindows() {
        }

        @Test
        @EnabledOnJre(JRE.JAVA_8)
        void soloJdk8() {
        }

        @Test
        @EnabledOnJre(JRE.JAVA_15)
        void soloJDK15() {
        }

        @Test
        @DisabledOnJre(JRE.JAVA_15)
        void testNoJDK15() {
        }
    }
    @Test
    @Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
    void failsIfExecutionTimeExceeds500Milliseconds() {
        System.out.println("milisegundos");
    }
}