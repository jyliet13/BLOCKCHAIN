package org.blockchain;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class KeyManager {

    private PrivateKey privateKey;
    private PublicKey publicKey;

    /**
     * Constructor de la clase KeyManager.
     * Genera automáticamente un par de claves RSA de 2048 bits.
     */
    public KeyManager() {
        generateKeyPair();
    }

    /**
     * Devuelve la clave privada.
     *
     * @return La clave privada.
     */
    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    /**
     * Devuelve la clave pública.
     *
     * @return La clave pública.
     */
    public PublicKey getPublicKey() {
        return publicKey;
    }

    /**
     * Devuelve la clave pública codificada en Base64 como una cadena.
     *
     * @return La clave pública como cadena.
     */
    public String getPublicKeyAsString() {
        if (publicKey == null) {
            throw new IllegalStateException("La clave pública no ha sido generada.");
        }
        return Base64.getEncoder().encodeToString(publicKey.getEncoded());
    }

    /**
     * Devuelve la clave privada codificada en Base64 como una cadena.
     *
     * @return La clave privada como cadena.
     */
    public String getPrivateKeyAsString() {
        if (privateKey == null) {
            throw new IllegalStateException("La clave privada no ha sido generada.");
        }
        return Base64.getEncoder().encodeToString(privateKey.getEncoded());
    }

    /**
     * Genera un par de claves RSA de 2048 bits.
     */
    private void generateKeyPair() {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(2048); // Longitud de 2048 bits para mayor seguridad
            KeyPair keyPair = keyGen.generateKeyPair();
            this.privateKey = keyPair.getPrivate();
            this.publicKey = keyPair.getPublic();
        } catch (Exception e) {
            // Registra el error antes de lanzar la excepción
            System.err.println("Error al generar el par de claves: " + e.getMessage());
            throw new RuntimeException("Error al generar el par de claves", e);
        }
    }

    /**
     * Decodifica una clave pública desde una cadena Base64.
     *
     * @param publicKeyStr La clave pública codificada en Base64.
     * @return La clave pública decodificada.
     * @throws Exception Si ocurre un error durante la decodificación.
     */
    public static PublicKey decodePublicKey(String publicKeyStr) throws Exception {
        if (publicKeyStr == null || publicKeyStr.isEmpty()) {
            throw new IllegalArgumentException("La cadena de clave pública no puede ser nula o vacía.");
        }

        byte[] keyBytes = Base64.getDecoder().decode(publicKeyStr);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePublic(spec);
    }

    /**
     * Decodifica una clave privada desde una cadena Base64.
     *
     * @param privateKeyStr La clave privada codificada en Base64.
     * @return La clave privada decodificada.
     * @throws Exception Si ocurre un error durante la decodificación.
     */
    public static PrivateKey decodePrivateKey(String privateKeyStr) throws Exception {
        if (privateKeyStr == null || privateKeyStr.isEmpty()) {
            throw new IllegalArgumentException("La cadena de clave privada no puede ser nula o vacía.");
        }

        byte[] keyBytes = Base64.getDecoder().decode(privateKeyStr);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePrivate(spec);
    }
}