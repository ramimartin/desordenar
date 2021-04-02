package ar.miro.qr

import com.google.api.services.sheets.v4.Sheets
import io.javalin.Javalin;


fun main(args: Array<String>) {
    val app = Javalin.create().start(7000);
    app.get("/vinilos") { ctx ->
        ctx.redirect(
            "https://docs.google.com/spreadsheets/d/147kmZKP08Xeh0JF3XIslulYOgJsQrxapW3BMCfBjwLg/edit#gid=1275389552",
            301
        )
    }
    //get("/hello") { req, res -> "Hello World" }
    val credentials = Authentication.credential


}

