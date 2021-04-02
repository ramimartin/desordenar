package ar.miro.qr

import com.google.api.client.auth.oauth2.Credential
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.client.util.store.FileDataStoreFactory
import com.google.api.services.sheets.v4.SheetsScopes
import java.io.File
import java.io.FileNotFoundException
import java.io.InputStreamReader
import java.util.*

object Authentication {


    private val SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS_READONLY)
    val credential = createCredential(GoogleObjects.TRANSPORT)

    private fun createCredential(transport: NetHttpTransport): Credential {
        val input = object {}.javaClass.getResourceAsStream("/credentials.json") ?: throw FileNotFoundException()
        val secret = GoogleClientSecrets.load(GoogleObjects.JSON_FACTORY, InputStreamReader(input))
        val flow = GoogleAuthorizationCodeFlow.Builder(
            transport, GoogleObjects.JSON_FACTORY, secret, SCOPES
        )
            .setDataStoreFactory(FileDataStoreFactory(File("tokens")))
            .setAccessType("offline")
            .build()
        val receiver = LocalServerReceiver.Builder().setPort(8080).build()
        return AuthorizationCodeInstalledApp(flow, receiver).authorize("ramiro.martin@gmail.com")
    }
}

object GoogleObjects{
    val JSON_FACTORY = JacksonFactory.getDefaultInstance()
    val TRANSPORT = GoogleNetHttpTransport.newTrustedTransport()
}