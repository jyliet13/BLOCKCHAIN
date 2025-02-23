package org.blockchain;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Cryp {

    /**
     * Genera un hash SHA-256 a partir de una cadena de texto.
     *
     * @param input La cadena de texto que se va a hashear.
     * @return El hash SHA-256 en formato hexadecimal.
     */
    public static String sha256(String input) {
        try {
            // Crea una instancia de MessageDigest para el algoritmo SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Calcula el hash de la entrada en formato byte[] usando UTF-8
            byte[] hashBytes = digest.digest(input.getBytes("UTF-8"));

            // Convierte los bytes en una representación hexadecimal
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                hexString.append(String.format("%02x", b));
            }

            return hexString.toString();

        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            // Este error solo ocurre si el algoritmo "SHA-256" no está disponible
            throw new RuntimeException("Error al obtener el algoritmo SHA-256", e);
        }
    }
}