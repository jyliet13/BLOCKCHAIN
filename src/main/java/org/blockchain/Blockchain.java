package org.blockchain;

import java.util.ArrayList;
import java.util.List;

public class Blockchain {
    private List<Block> blocks;

    public Blockchain() {
        blocks = new ArrayList<>();
        createGenesisBlock();
    }

    /**
     * Crea el bloque génesis.
     *
     * @return El bloque génesis.
     */
    private Block createGenesisBlock() {
        Block genesisBlock = new Block("0");
        genesisBlock.mineBlock(4);
        //genesisBlock.addTransaction(new Transaction("Emisor", "Receptor", 100));
        blocks.add(genesisBlock);
        return genesisBlock;
    }

    /**
     * Agrega una transacción a la cadena.
     *
     * @param transaction La transacción a agregar.
     */
    public void addTransaction(Transaction transaction) {
        Block latestBlock = getLatestBlock();
        latestBlock.addTransaction(transaction);
    }

    /**
     * Minar el último bloque pendiente.
     */
    public void minePendingTransactions(int difficulty) {
        Block latestBlock = getLatestBlock();
        latestBlock.mineBlock(difficulty);
        blocks.add(latestBlock);
    }


    /**
     * Verifica la integridad de la cadena de bloques.
     *
     * @return True si la cadena es válida, false en caso contrario.
     */
    public boolean isChainValid() {
        for (int i = 1; i < blocks.size(); i++) {
            Block currentBlock = blocks.get(i);
            Block previousBlock = blocks.get(i - 1);

            // Verifica si el hash actual coincide con el calculado
            if (!currentBlock.getHash().equals(currentBlock.calculateHash())) {
                System.out.println("Hash actual no coincide con el calculado.");
                return false;
            }

            // Verifica si el hash anterior coincide con el hash del bloque anterior
            if (!previousBlock.getHash().equals(currentBlock.getPreviousHash())) {
                System.out.println("Hash anterior no coincide con el hash del bloque anterior.");
                return false;
            }
        }
        return true;
    }

    /**
     * Obtiene el último bloque de la cadena.
     *
     * @return El último bloque.
     */
    public Block getLatestBlock() {
        return blocks.get(blocks.size() - 1);
    }

    /**
     * Agrega un nuevo bloque a la cadena.
     *
     * @param newBlock El bloque a agregar.
     */
    public void addBlock(Block newBlock) {
        newBlock.setPreviousHash(getLatestBlock().getHash());
        newBlock.mineBlock(4);
        blocks.add(newBlock);
    }

    public List<Block> getBlocks() {
        return blocks;
    }

}
