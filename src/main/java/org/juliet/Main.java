package org.juliet;

import java.util.ArrayList;
import java.util.List;

public class Main {

    // ArrayList to store the blocks

    ArrayList<block> blocks = new ArrayList<>();

    public static void main(String[] args) {

       blockchain blockchain = new blockchain();

       claves senderclave = new claves();
       claves receiverclave = new claves();

        // Crear transacciones

        String txID1 = transaction.generateTXID(senderclave.getPublicKeyAsString(), receiverclave.getPublicKeyAsString(), 50);

        List<transaction> transactions1 = new ArrayList<>();
        transactions1.add(new transaction(txID1, senderclave.getPublicKeyAsString(), receiverclave.getPublicKeyAsString(), 50, "", senderclave.getPrivateKey()));

        List<transaction> transactions2 = new ArrayList<>();
        transactions2.add(new transaction(txID1, senderclave.getPublicKeyAsString(), receiverclave.getPublicKeyAsString(), 50, "", senderclave.getPrivateKey()));

        //crear bloques
        block block1 = new block(transactions1,blockchain.ultimoBloque().getHash());
        blockchain.addblock(block1);

        block block2 = new block(transactions2,blockchain.ultimoBloque().getHash());
        blockchain.addblock(block2);

        //imprimir bloques
        for (block b : blockchain.getListaBloques()){
            System.out.println("hash del bloque" + b.getHash());
            System.out.println( " anterior hash" + b.getPreviusHash());
            System.out.println( " transaccion" + b.getTransactions());
        }
        System.out.println("validacion de blockchain " + blockchain.ultimoBloque().getHash());
    }
}