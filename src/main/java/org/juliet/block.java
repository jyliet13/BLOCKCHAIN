package org.juliet;

import java.util.Arrays;
import java.util.Date;

/*
block in blockchain
 */
public class block {

    private int previusHash;
    private String[] transactions;
    private int hash;

    public block(String[] transactions, int previusHash) {
        this.transactions = transactions;
        this.previusHash = previusHash;
        this.hash = Arrays.hashCode(transactions);


       // Object[] contens = {Arrays.hashCode(transactions), previusHash};
      //  this.hash = Arrays.hashCode(contens);

    }


    private String generateHash() {
        String data = previusHash + Arrays.toString(transactions);
        return cryp.sha256(data);
    }


    public int getPreviusHash() {
        return previusHash;
    }

    public void setPreviusHash(int previusHash) {
        this.previusHash = previusHash;
    }

    public String[] getTransactions() {
        return transactions;
    }

    public void setTransactions(String[] transactions) {
        this.transactions = transactions;
    }

    public int getHash() {
        return hash;
    }

    public void setHash(int hash) {
        this.hash = hash;
    }
}




