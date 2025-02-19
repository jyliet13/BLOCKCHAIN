package org.juliet;

import java.security.*;
import java.util.Base64;

public class transaction {

    private String txid; //identificador
    private  String CPemisor; //cp de emisor
    private  String CPreceptor; //cp receptor
    private double amount; //monto
    private String firma; // firma ;


    public transaction(String txid, String CPemisor, String CPreceptor, double amount, String firma, PrivateKey PrivateKey) {
        this.txid = generateTXID(CPemisor, CPreceptor, amount);
        this.CPemisor = CPemisor;
        this.CPreceptor = CPreceptor;
        this.amount = amount;
        this.firma = signTransaction(PrivateKey);
    }

    /**
     * genera
     * Un hash criptográfico único que representa la
     * transacción. usando SHA-256
     */

    public static String generateTXID(String CPemisor, String CPreceptor, double amount ) {
        String data = CPemisor + "," + CPreceptor + "," + amount + System.currentTimeMillis();
        return cryp.sha256(data);
    }

    /**
     *
     * @return datos que se firman en la trans
     */
    private String getSignatureforTrans(){
        return CPemisor + "," + CPreceptor + "," + amount ;
    }

    /**
     * verifica si firma es valida
     * @param publicKey
     * @return
     */
    public boolean SignaturaValid(PublicKey publicKey){
        try {
            Signature valid = Signature.getInstance("SHA256withRSA");
            valid.initVerify(publicKey);
            valid.update(getSignatureforTrans().getBytes());
            byte[] signatureB = Base64.getDecoder().decode(firma);
            return valid.verify(signatureB);
        }catch (Exception e ) {
            throw new RuntimeException("Error validating transaction firma", e);
        }
    }


    /**
     *firma la transaccion con la clv
     * @param privateKey
     * @return
     * @Signature crea y verifica firmas digitales
     */

    private  String signTransaction(PrivateKey privateKey){
        try {
            Signature signer = Signature.getInstance("SHA256withRSA");
            signer.initSign(privateKey);
            signer.update(getSignatureforTrans().getBytes());
            byte[] signatureB = signer.sign();
            return Base64.getEncoder().encodeToString(signatureB);

        } catch (Exception e) {
            throw new RuntimeException(e);

        }

    }


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
