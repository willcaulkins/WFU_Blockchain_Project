/*
Group1
Will Caulkins
Caitlin Kelly
Kristen Kovach
 */

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import static java.nio.charset.StandardCharsets.UTF_8;

public class Block {
    private String previousHash;
    private Transaction data;
    private Long timestamp;
    private int nonce;
    private String currentHash;

    public String calculateBlockHash() { // Hashing method used as a temporary replacement for blockchain mining
        String dataToHash = previousHash
                + Long.toString(timestamp)
                + Integer.toString(nonce)
                + data.toString();
        MessageDigest digest = null;
        byte[] bytes = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
            bytes = digest.digest(dataToHash.getBytes(UTF_8));
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("The encoding is not supported");
        }
        StringBuffer buffer = new StringBuffer();
        for (byte b : bytes) {
            buffer.append(String.format("%02x", b));
        }
        return buffer.toString();
    }

    public void mineBlock(int prefix) { // Additional method used as a temporary replacement for blockchain mining
        if (Main.TreatySC(data)) {
            String hash = calculateBlockHash();
            String hashPrefix = hash.substring(0,prefix);
            while (!"0000".equals(hashPrefix)) {
                this.nonce = this.nonce+1;
                hash = calculateBlockHash();
                hashPrefix = hash.substring(0,prefix);
            }
            this.currentHash = hash;
        } else {
            System.out.println("Transaction does not meet stakeholder agreement");
        }
    }

    public Block() {
        this.previousHash = null;
        this.data = null;
        this.timestamp = null;
        this.nonce = 0;
        this.currentHash = null;
    }

    public Block(Transaction data, String previousHash, Long timestamp) { // New block constructor
        this.previousHash = previousHash;
        this.data = data;
        this.timestamp = timestamp;
    }

/*    public Block(Transaction data, String previousHash, Long timestamp, int nonce) { // New Block Constructor
        this.previousHash = previousHash;
        this.data = data;
        this.timestamp = timestamp;
        this.nonce = nonce;
    }*/

    // Getters and setters for Block members

    public String getPreviousHash() {
        return previousHash;
    }

    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }

    public Transaction getData() {
        return data;
    }

    public void setData(Transaction data) {
        this.data = data;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public int getNonce() {
        return nonce;
    }

    public void setNonce(int nonce) {
        this.nonce = nonce;
    }

    public String getCurrentHash() {
        return currentHash;
    }

    public void setCurrentHash(String currentHash) {
        this.currentHash = currentHash;
    }
}
