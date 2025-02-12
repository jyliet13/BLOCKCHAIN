package org.juliet;

import java.util.ArrayList;

public class Main {

    // ArrayList to store the blocks

    ArrayList<block> blocks = new ArrayList<>();

    public static void main(String[] args) {

        String[] genesisTransactions = {" conent envio block "};
        block genesisBlock =  new block( genesisTransactions, 0 );

        String[] block2Transactions = {" content envio block2 "};
        block block2 = new block(block2Transactions, genesisBlock.getHash());

        String[] block3Transactions = {" content envio block3 "};
        block block3 = new block(block3Transactions, genesisBlock.getHash());

        //imprime los hashe

        System.out.println(" hash del genesis block ");
        System.out.println(genesisBlock.getHash());
        System.out.println(" hash del block2 ");
        System.out.println(block2.getHash());
        System.out.println(" hash del block3 ");
        System.out.println(block3.getHash());
    }
}