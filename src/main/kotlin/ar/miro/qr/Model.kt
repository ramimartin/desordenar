package ar.miro.qr

import java.text.Collator
import java.text.spi.CollatorProvider
import java.util.*
import java.util.regex.Pattern


data class Disc(val name: String, val artist: String, val year: Int) : Comparable<Disc> {

    companion object {
        val processors = listOf(
            Processor("The"),
            Processor("El"),
            Processor("Los"),
            Processor("Las")
        )

        val collator = Collator.getInstance(Locale("es"))

    }

    val processedArtistName: String = processors.foldRight(artist) { p, n -> p.processName(n) }

    override fun compareTo(other: Disc): Int {

        val artistComparison = collator.compare(this.processedArtistName.toLowerCase(),
            other.processedArtistName.toLowerCase())
        return if ( artistComparison== 0)
            this.year.compareTo(other.year)

        else artistComparison

    }


}

data class Processor(private val prefix: String) {
    private val pattern = Pattern.compile("^$prefix (.*)")

    fun processName(name: String): String {
        val matcher = pattern.matcher(name)
        return if (matcher.matches())
            matcher.group(1) + ", $prefix"
        else
            name
    }

}