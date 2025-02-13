package org.juliet;

import java.util.ArrayList;
import java.util.List;

public class blockchain {

    private List<block> ListaBloques;

    public blockchain() {
        ListaBloques = new ArrayList<>();
        ListaBloques.add(blockGenesis());
    }

    public boolean ListaValidada(){
        for(int i = 1; i < ListaBloques.size(); i++){
            block currentB = ListaBloques.get(i);
            block previousB = ListaBloques.get(i-1);

            //validacion de hash
            if(!currentB.getHash().equals(previousB.getHash())){
                return false;
            }

            //validar bloque con su anterior
            if(!currentB.getPreviusHash().equals(previousB.getHash())){
                return false;
            }
        }
        return true;
    }

    public List<block> getListaBloques() {
        return ListaBloques;
    }


    /**
     * creacion primer bloque genesis
     * @return
     */
    private block blockGenesis(){
        return new block(new ArrayList<>(), "0");
    }

    /**
     * ultimo bloque
     */

    public block ultimoBloque(){
        return ListaBloques.get(ListaBloques.size()-1);
    }

    public void addblock(block b){
        ListaBloques.add(b);
    }


}
