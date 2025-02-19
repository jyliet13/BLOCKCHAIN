package org.juliet;

import java.util.List;

/*
block in blockchain
 */
public class block {

    private String previusHash;
    private List<transaction> transactions;
    private String hash;

    public block(List<transaction> transactions, String previusHash) {
        this.transactions = transactions;
        this.previusHash = previusHash;
        this.hash = generateHash();


       // Object[] contens = {Arrays.hashCode(transactions), previusHash};
      //  this.hash = Arrays.hashCode(contens);

    }


    private String generateHash() {
        String data = previusHash + transactions.toString();
        return cryp.sha256(data);
    }


    public String getPreviusHash() {
        return previusHash;
    }

    public void setPreviusHash(String previusHash) {
        this.previusHash = previusHash;
    }

    public List<transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<transaction> transactions) {
        this.transactions = transactions;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}




