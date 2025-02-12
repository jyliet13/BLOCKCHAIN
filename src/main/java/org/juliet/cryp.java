package org.juliet;

import java.security.MessageDigest;

public class cryp {
    /**
     * genera hash SHA-256
     * @param input texto hasehado
     * @return EN FORMATO hexa
     */
    public static String sha256(String input) {
        try {
            //aplica el hash
            MessageDigest sha = MessageDigest.getInstance("SHA-256");
            byte[] hash = sha.digest(input.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        }catch (Exception e) {
            throw new RuntimeException(e);

    }


    }

}
