package org.blockchain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Block {

    private String previousHash; // Hash del bloque anterior
    private List<Transaction> transactions; // Lista de transacciones
    private String hash; // Hash del bloque actual
    private long timestamp; // Timestamp del bloque
    private int nonce; // Valor utilizado en el proceso de minado

    /**
     * Constructor de la clase Block.
     *
     * @param previousHash Hash del bloque anterior.
     */
    public Block(String previousHash) {
        this.previousHash = previousHash;
        this.transactions = new ArrayList<>();
        this.timestamp = new Date().getTime(); // Establece el timestamp actual
        this.nonce = 0; // Inicializa el nonce en 0
        this.hash = calculateHash(); // Calcula el hash inicial
    }

    /**
     * Agrega una transacción al bloque.
     *
     * @param transaction La transacción a agregar.
     */
    public void addTransaction(Transaction transaction) {
        if (transaction == null) return;
        transactions.add(transaction);
    }

    /**
     * Calcula el hash del bloque usando SHA-256.
     *
     * @return El hash generado.
     */
    String calculateHash() {
        StringBuilder data = new StringBuilder();

        // Agregar el hash del bloque anterior
        data.append(previousHash);

        // Agregar todas las transacciones
        for (Transaction tx : transactions) {
            data.append(tx.getTxid());
        }

        // Agregar el timestamp
        data.append(Long.toString(timestamp));

        // Agregar el nonce (para el proceso de minado)
        data.append(Integer.toString(nonce));

        // Generar el hash usando SHA-256
        return Cryp.sha256(data.toString());
    }

    /**
     * Minar el bloque ajustando el nonce hasta cumplir con la dificultad.
     *
     * @param difficulty Nivel de dificultad (número de ceros al inicio del hash).
     */
    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0'); // Crea una cadena con "difficulty" ceros
        while (!hash.substring(0, difficulty).equals(target)) {
            nonce++; // Incrementa el nonce
            hash = calculateHash(); // Recalcula el hash
        }
        System.out.println("Bloque minado: " + hash);
    }

    // Getters
    public String getPreviousHash() {
        return previousHash;
    }

    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public String getHash() {
        return hash;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public int getNonce() {
        return nonce;
    }
}