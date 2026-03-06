package com.daya.homemadepro.domain.encryption

import android.util.Log
import java.security.KeyFactory
import java.security.SecureRandom
import java.security.spec.MGF1ParameterSpec
import java.security.spec.X509EncodedKeySpec
import java.util.Base64
import javax.crypto.Cipher
import javax.crypto.spec.OAEPParameterSpec
import javax.crypto.spec.PSource

class SecurityUtils {

    var TAG: String = "EncryptionUtils"
    var PUBLIC_KEY: String = """
     -----BEGIN PUBLIC KEY-----
     MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAstr8rKRgyzFXO0ORjnXO
     9y3Irf1+HdhFswRdYrzkjkNW7G/H7WznuJhF10jPeJKXBNnVhEo2SXMDi2KW8c5l
     JbE2jW294SBv2v8xGUp0Y4Cb6/O3aXiZbTs7qBIIqzLrzM1fbzBnIG1HyyGYqZqj
     x4CzcQb5orhLNTQDnVnArXTvAgV3yDK45rEU3et54crFnLrsHn0ymTZ5pYEnRNLr
     kTVWyuvhAZSTNB4wUUY+K1zOgD2bLX5wMUoIUBnSXToZ516xV1uvLUbXfB2kSsi9
     62j2N/C5xHz06m1H/77GrOQWdleQeZlwWFgYO0ikpRbE9oDFgmgrgHGGQfJ+pG/I
     hQIDAQAB
     -----END PUBLIC KEY-----
     """.trimIndent()


    //AES
    var secureRandom: SecureRandom? = null
    var IV: String? = null


    /*
     * below function is use to encrypt data using RSA
     * */
    fun encryptData(data: String): String {
        val publicKeyPEM: String =
            getPublicKeyFromPemKey(PUBLIC_KEY)
        var arrayOfEncryptedData: ByteArray? = null
        var encryptedData = ""
        try {
            val publicKeyBytes = Base64.getDecoder().decode(publicKeyPEM)
            val keyFactory = KeyFactory.getInstance("RSA")

            val keySpec = X509EncodedKeySpec(publicKeyBytes)

            val publicKey = keyFactory.generatePublic(keySpec)

            val cipher =
                Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding")
            val oaepSpec = OAEPParameterSpec(
                "SHA-256", "MGF1",
                MGF1ParameterSpec.SHA256, PSource.PSpecified.DEFAULT
            )
            cipher.init(Cipher.ENCRYPT_MODE, publicKey, oaepSpec)


            arrayOfEncryptedData = cipher.doFinal(data.toByteArray())
            encryptedData = Base64.getEncoder().encodeToString(arrayOfEncryptedData)
            Log.e(
                TAG,
                " encrypted data in performing encryption process $encryptedData"
            )
        } catch (e: Exception) {
            Log.e(
                TAG,
                " error in performing encryption process " + e.message
            )
            e.printStackTrace()
        }
        return encryptedData
    }


    fun getPublicKeyFromPemKey(KEY: String): String {
        val publicKeyPEM = KEY
            .replace("-----BEGIN PUBLIC KEY-----", "")
            .replace(System.lineSeparator().toRegex(), "")
            .replace("-----END PUBLIC KEY-----", "")
        return publicKeyPEM
    }

}