package com.example.practicajunit5;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
//@RunWith(MockitoJUnitRunner.class)
class ClaseImplementacionTest {

    @Mock
    ClaseServicio servicio;

    @InjectMocks
    ClaseImplementacion claseImplementacion;

    ArrayList<String> estados = new ArrayList<>();

    @BeforeEach
    void initMetodoTest() {
       /* estados.add("Aguascalientes");
        estados.add("Yucatan");
        estados.add("Guanajuato");
        estados.add("Nuevo Leon");
        estados.add("Jalisco");*/
    }

    @DisplayName("Concatenación con when")
    @Test
    @Ignore
    void concatenacion() {

        when(servicio.obtenerNOmbreId(anyInt())).thenReturn("Juan");
        String nombre = claseImplementacion.concatenacion(Mockito.anyInt());
        assertEquals(nombre, "el nombre es: Juan");


    }

    @DisplayName("Concatenación con excepcion, thenTrow")
    @Test
    void concatenacionThrow() {

        when(servicio.obtenerNOmbreId(Mockito.anyInt())).thenThrow(NullPointerException.class);
        String nombre = claseImplementacion.concatenacion(Mockito.anyInt());
        assertEquals(nombre, "");
    }

    @DisplayName("Test del do answer")
    @Test
    void testDoAnswer() {
        //para realizar una respuesta mas personalizada y no solo setear un dato

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {

                return "algo";
            }
        }).when(servicio).obtenerNOmbreId(anyInt());

        String nombre = claseImplementacion.concatenacion(anyInt());
        assertEquals(nombre, "el nombre es: algo");

        //-------------------------------

        doAnswer(respuesta -> {
            return "algo2";
        }).when(servicio).obtenerNOmbreId(anyInt());

        String nombre2 = claseImplementacion.concatenacion(anyInt());
        assertEquals(nombre2, "el nombre es: algo2");

    }

    @DisplayName("Test del do Real Call Method")
    @Test
    void testDoRealCallMethod() {

        doCallRealMethod().when(servicio).obtenerNOmbreId(anyInt());
        String nombre2 = claseImplementacion.concatenacion(anyInt());
        assertEquals(nombre2, "el nombre es: ");

    }

    @DisplayName("Test del do Nothing")
    @Test
    void testDoNothing() {
        //ClaseImplementacion claseImp = spy(ClaseImplementacion.class);
        ClaseServicio claseServ = spy(ClaseServicio.class);
        ClaseImplementacion clase = new ClaseImplementacion(claseServ);

        doNothing().when(claseServ).validacion(anyInt());
        doNothing().when(claseServ).codigoTerceros();

        String nombre2 = clase.validacionesExtra(anyInt());

        assertEquals(nombre2, "algun valor");

    }

    @DisplayName("test in Order")
    @Test
    void testInOrder() {

        when(servicio.concatenacion1()).thenReturn("valo1 ");
        when(servicio.concatenacion2()).thenReturn("valor2");

        String cadena = claseImplementacion.concatenaciones();
        assertEquals(cadena, "valo1 valor2");

        //ordenamiento de los mocks
        InOrder inOrder = inOrder(servicio);
        inOrder.verify(servicio).concatenacion1();
        inOrder.verify(servicio).concatenacion2();

        //verifica cuantas veces se llama un metodo
        verify(servicio,times(1)).concatenacion1();
        verify(servicio,atLeast(1)).concatenacion2();

    }

    @DisplayName("another test")
    @Test
    void testanotherTest() {

        when(servicio.concatenacion1()).thenReturn("");
        boolean bandera = claseImplementacion.validacionNumero("2");
        assertTrue(bandera);
        assertFalse(claseImplementacion.validacionNumero("2a"));

        verify(servicio,times(2)).concatenacion1();
    }

}