package org.blockchain;

import java.security.PrivateKey;

public class Main {
    public static void main(String[] args) {
        // Crear blockchain
        Blockchain blockchain = new Blockchain();

        // Generar claves
        KeyManager keyManager = new KeyManager();
        PrivateKey privateKey = keyManager.getPrivateKey();
        String publicKey = keyManager.getPublicKeyAsString();

        // Crear transacciones
        Transaction tx1 = new Transaction(publicKey, publicKey, 50);
        Transaction tx2 = new Transaction(publicKey, publicKey, 25);

        // Firmar transacciones
        tx1.sign(privateKey);
        tx2.sign(privateKey);

        Block block1 = new Block(blockchain.getLatestBlock().getHash());
        // Agregar transacciones al bloque
        blockchain.addTransaction(tx1);
        blockchain.addTransaction(tx2);

        blockchain.addBlock(block1);

        // Validar la cadena
        System.out.println("Es valida?? " + blockchain.isChainValid());
    }
}