package org.blockchain;

import java.security.*;
import java.util.Base64;
import java.util.UUID;

public class Transaction {

    private String txid; // Identificador único de la transacción
    private String CPemisor; // Clave pública del emisor
    private String CPreceptor; // Clave pública del receptor
    private double amount; // Monto transferido
    private String firma; // Firma digital

    /**
     * Constructor de la clase Transaction.
     *
     * @param CPemisor   Clave pública del emisor.
     * @param CPreceptor Clave pública del receptor.
     * @param amount     Monto transferido.
     */
    public Transaction(String CPemisor, String CPreceptor, double amount) {
        this.CPemisor = CPemisor;
        this.CPreceptor = CPreceptor;
        this.amount = amount;
        this.txid = generateTXID(); // Genera automáticamente el txid
        this.firma = null; // La firma se establece posteriormente
    }

    /**
     * Genera un hash criptográfico único que representa la transacción.
     *
     * @return El hash generado.
     *
     * private String generateTXID() {
     *         String data = CPemisor + "," + CPreceptor + "," + amount;
     *         return Cryp.sha256(data);
     *     }
     */
    private String generateTXID() {
        String data = CPemisor + "," + CPreceptor + "," + amount + "," + UUID.randomUUID().toString();
        return Cryp.sha256(data);
    }

    /**
     * Devuelve los datos que se firman en la transacción.
     *
     * @return Los datos formateados como cadena.
     */
    private String getSignatureData() {
        return CPemisor + "," + CPreceptor + "," + amount;
    }

    /**
     * Verifica si la firma de la transacción es válida.
     *
     * @return True si la firma es válida, false en caso contrario.
     */
    public boolean isSignatureValid() {
        try {
            PublicKey publicKey = KeyManager.decodePublicKey(CPemisor);
            Signature verifier = Signature.getInstance("SHA256withRSA");
            verifier.initVerify(publicKey);
            verifier.update(getSignatureData().getBytes());
            byte[] signatureBytes = Base64.getDecoder().decode(firma);
            return verifier.verify(signatureBytes);
        } catch (Exception e) {
            throw new RuntimeException("Error al validar la firma", e);
        }
    }

    /**
     * Firma la transacción con la clave privada del emisor.
     *
     * @param privateKey Clave privada del emisor.
     */
    public void sign(PrivateKey privateKey) {
        try {
            Signature signer = Signature.getInstance("SHA256withRSA");
            signer.initSign(privateKey);
            signer.update(getSignatureData().getBytes());
            byte[] signatureBytes = signer.sign();
            this.firma = Base64.getEncoder().encodeToString(signatureBytes);
        } catch (Exception e) {
            throw new RuntimeException("Error al firmar la transacción", e);
        }
    }

    // Getters
    public String getTxid() {
        return txid;
    }

    public String getCPemisor() {
        return CPemisor;
    }

    public String getCPreceptor() {
        return CPreceptor;
    }

    public double getAmount() {
        return amount;
    }

    public String getFirma() {
        return firma;
    }
}