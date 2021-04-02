package ar.miro.qr

import com.google.api.services.sheets.v4.Sheets

fun main(args:Array<String>){
    val sheetId = "147kmZKP08Xeh0JF3XIslulYOgJsQrxapW3BMCfBjwLg"
    val service = Sheets.Builder(GoogleObjects.TRANSPORT,GoogleObjects.JSON_FACTORY,Authentication.credential)
        .setApplicationName("Prueba").build()
    val values = service.spreadsheets().values().get(sheetId,"Vinilos!A2:C133").execute().getValues()
    values.map { value -> Disc(value.get(1).toString(), value.get(0).toString(), value.get(2).toString().toInt()) }.sorted()
        .forEach { value ->println(value)}

}