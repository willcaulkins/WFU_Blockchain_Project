/*
Group1
Will Caulkins
Caitlin Kelly
Kristen Kovach
 */

/* Fairly standard class to initialize a Transaction object then get/set members of that object */

import java.util.Date;

public class Transaction {
    private Artefact artefact;
    private Long timestamp;
    private Stakeholder seller;
    private Stakeholder buyer;
    private Stakeholder auctionHouse;
    private double price;

    public Transaction() {
        this.artefact = null;
        this.timestamp = new Date().getTime();
        this.seller = null;
        this.buyer = null;
        this.auctionHouse = null;
        this.price = 0.0;
    }

    public Transaction(Artefact artefact, Long timestamp, Stakeholder seller, Stakeholder buyer, Stakeholder auctionHouse, double price) {
        this.artefact = artefact;
        this.timestamp = timestamp;
        this.seller = seller;
        this.buyer = buyer;
        this.auctionHouse = auctionHouse;
        this.price = price;
    }

    public void setArtefact(Artefact artefact) {
        this.artefact = artefact;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public void setSeller(Stakeholder seller) {
        this.seller = seller;
    }

    public void setBuyer(Stakeholder buyer) {
        this.buyer = buyer;
    }

    public void setAuctionHouse(Stakeholder auctionHouse) {
        this.auctionHouse = auctionHouse;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Artefact getArtefact() {
        return artefact;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public Stakeholder getSeller() {
        return seller;
    }

    public Stakeholder getBuyer() {
        return buyer;
    }

    public Stakeholder getAuctionHouse() {
        return auctionHouse;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        Date time = new Date(this.timestamp);
        String result = "Artefact: \n" + this.artefact.toString();
        result += "\nTransaction Timestamp: " + time;
        result += "\nBailee: " + this.seller.getName();
        result += "\nBailor: " + this.artefact.getLegalOwner().getName();
        result += "\nBuyer: " + this.buyer.getName();
        result += "\nAuction House: " + this.auctionHouse.getName();
        result += "\nPrice: $" + this.price;
        return result;
    }
}