/*
Group1
Will Caulkins
Caitlin Kelly
Kristen Kovach
 */

/* Fairly standard class to initialize a Stakeholder object then get/set members of that object */

public class Stakeholder {
    private String id;
    private String name;
    private String address;
    private double balance;
    private int ownership;

    public Stakeholder() {
        this.id = null;
        this.name = null;
        this.address = null;
        this.balance = 0.0;
    }

    public Stakeholder(String id, String name, String address, double balance) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.balance = balance;
    }

    public Stakeholder(String id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getAddress() {
        return address;
    }

    public double getBalance() {
        return balance;
    }

    public int getOwnership() {
        return ownership;
    }

    public void setOwnership(int ownership) {
        this.ownership = ownership;
    }

    @Override
    public String toString() {
        String result = "ID: " + this.id;
        result += "\nName: " + this.name;
        result += "\nAddress: " + this.address;
        result += "\nBalance: " + this.balance;
        return result;
    }
}