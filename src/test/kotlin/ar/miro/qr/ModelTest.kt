package ar.miro.qr

import org.junit.Test
import kotlin.test.assertEquals

class ModelTest {

    @Test
    fun testElMato(){
        assertEquals("Él Mató a un Policía Motorizado",
            Disc("La Dinastía Scorpio", "Él Mató a un Policía Motorizado",2012)
                .processedArtistName)
    }

    @Test
    fun testTheThe(){
        assertEquals("The, The",
            Disc("The Blue Album", "The The", 1983).processedArtistName)
    }

    @Test
    fun testLosBrujos(){
        assertEquals("Brujos, Los",
            Disc("Fin de Semana Salvaje", "Los Brujos", 1992).processedArtistName)
    }
}
