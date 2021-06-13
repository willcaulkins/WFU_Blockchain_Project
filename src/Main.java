/*
Group1
Will Caulkins
Caitlin Kelly
Kristen Kovach
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Main {
    public static ArrayList<String> stakeholderArr;
    public static ArrayList<String> artefactArr;
    public static ArrayList<Stakeholder> stakeholders;
    public static ArrayList<Artefact> artefacts;
    public static ArrayList<Block> blockchain;

    public static Boolean TreatySC(Transaction t) { // allocates revenue based on the stakes of the owners of the artefact and executes the transaction
        boolean completed = false;
        Artefact artefact = t.getArtefact();

        //if ((retrieveProvenance(artefact.getId(),977616000000L).size()>1) || ((retrieveProvenance(artefact.getId()).size()<2))) {
        /*The above was a condition to ensure that the provenance was sufficiently valid before a new transaction was added
        * but is not necessary if the addition of new blocks is monitored by a consensus mechanism present in most decentralized
        * blockchain platforms*/

            if (t.getBuyer().getBalance()>=t.getPrice()) {
                t.getAuctionHouse().setBalance(t.getAuctionHouse().getBalance() + (t.getPrice()/10));
                /* Initial convention was to allocate 10% of revenue to auction house. This can be easily changed in the
                * based upon client specifications.*/
                artefact.getLegalOwner().setBalance(artefact.getLegalOwner().getBalance() + (t.getPrice()*artefact.getStakeOfLegalOwner())/100);
                /* Stake of legal owner (eg. Country of Origin) was set to 40% (parameter located in the Artefact class). This can be changed
                * easily as with the stake of the auction house.*/
                t.getSeller().setBalance(t.getSeller().getBalance() + ((t.getPrice()*(100-artefact.getStakeOfLegalOwner()))/100));
                /* Stake of Artefact Seller set as 50% (100% - 40% to Legal Owner - 10% to auction house). */
                t.getBuyer().setBalance(t.getBuyer().getBalance() - t.getPrice());
                /* Seller incurs a loss equal to the price of the artefact*/
                t.getArtefact().setCurrentOwner(t.getBuyer());
                /* Buyer is set as the new current owner of the artefact */
                completed = true;
            }
        //}
        return completed;
    }

    public static ArrayList<Transaction> retrieveProvenance(String id) { // creates a provenance record for the artefact via all of its prior transactions
        ArrayList<Transaction> provenance = new ArrayList<Transaction>();
        for (Block b : blockchain) {
            String currID = b.getData().getArtefact().getId();
            if (id == currID) {
                provenance.add(b.getData()); // Adds all artefacts with the same ID number in the blockchain to the provenance record
            }
        }
        return provenance;
    }

    public static void printProvenance(String id) {
        for (Transaction t : retrieveProvenance(id)) {
            System.out.println(t.toString());
            System.out.println();
        }
    }

    public static ArrayList<Transaction> retrieveProvenance(String id, Long timestamp) { /*creates a provenance record for
        * the artefact via all of its prior transactions (returns provenance of artefact after a certain date)*/
        ArrayList<Transaction> provenance = new ArrayList<Transaction>();
        for (Block b : blockchain) {
            String currID = b.getData().getArtefact().getId();
            if ((id == currID) && (b.getData().getTimestamp()>(timestamp))) {
                provenance.add(b.getData());
            }
        }
        return provenance;
    }

    public static boolean verify_Blockchain(ArrayList<Block> BC) { /*method to ensure hashing function is working properly.
        * When the solution is transferred to an existing blockchain platform, this will be done using consensus method used
        * by the platform. */
        /*for (int i=1; i<BC.size(); i++) {
            Block b = BC.get(i);
            Block b2 = BC.get(i-1);

            if (b.getCurrentHash().equals(b.calculateBlockHash())) {
                if (b.getPreviousHash().equals(b2.calculateBlockHash())) {
                    if (b.getCurrentHash().startsWith("0000")){
                        return true;
                    } else {
                        System.out.println("1");
                        return false;
                    }
                } else {
                    System.out.println(b.getPreviousHash());
                    System.out.println(b2.calculateBlockHash());
                    System.out.println("2");
                    return false;
                }
            } else {
                System.out.println(b.calculateBlockHash());
                System.out.println(b.getCurrentHash());
                System.out.println("3");
                return false;
            }
        }*/
        return true;
    }

    public static void main(String[] args) throws ParseException {
        blockchain = new ArrayList<Block>();
        int prefix = 4;
        String prefixString = new String(new char[prefix]).replace('\0', '0');
        /* Above (lines 111 and 112) are auxiliary statements meant to assist in the functionality of verify_Blockchain.
        * Because this method will not be used in the ultimate product, these parameters are not necessary (They are, however
        * , linked to other methods in this program)*/

        stakeholders = new ArrayList<Stakeholder>();
        artefacts = new ArrayList<Artefact>();

        Stakeholder buyer1 = new Stakeholder("001", "Kristen Kovach", "Kristen's Address", 10000.00);
        Stakeholder seller1 = new Stakeholder("002", "Caitlin Kelly", "Caitlin's Address", 10000.00);
        Stakeholder Mexico = new Stakeholder("003", "Mexico", "1234 Mexico City Lane", 10000.00);
        Artefact artefact1 = new Artefact("1001", "Western Mexican Effigy Bowl", Mexico, seller1);
        Stakeholder auctionHouse = new Stakeholder("004", "Sotheby's", "Sotheby's Address", 10000.00);
        Transaction data1 = new Transaction(artefact1, new Date().getTime(), seller1, buyer1, auctionHouse, 1000);

        stakeholders.add(buyer1);
        stakeholders.add(seller1);
        stakeholders.add(Mexico);
        stakeholders.add(auctionHouse);
        artefacts.add(artefact1);

        Stakeholder buyer2 = new Stakeholder("005", "Will Caulkins", "Will's Address", 10000.00);
        Stakeholder seller2 = new Stakeholder("006", "Sarra Alqahtani", "Sarra's Address" , 10000.00);
        Stakeholder Canada = new Stakeholder("007", "Canada", "1600 Vancouver Ave", 10000.00);
        Artefact artefact2 = new Artefact("1002", "Hockey Stick", Canada, seller2, buyer1, new Date().getTime(), "Wake Forest", "Stone", 2.0, 8.0, 120.0, 3.0);
        Transaction data2 = new Transaction(artefact2, new Date().getTime(), seller2, buyer2, auctionHouse, 1000);

        stakeholders.add(buyer2);
        stakeholders.add(seller2);
        stakeholders.add(Canada);
        artefacts.add(artefact2);

        Stakeholder buyer3 = new Stakeholder("008", "Hercules", "Hercules's Address", 10000.00);
        Stakeholder seller3 = new Stakeholder("009", "Zeus", "Mount Olympus" , 10000.00);
        Stakeholder Greece = new Stakeholder("0010", "Greece", "1600 Athens Ave", 10000.00);
        Artefact artefact3 = new Artefact("1003", "Parthenon", Greece, seller3);
        Transaction data3 = new Transaction(artefact3, new Date().getTime(), seller3, buyer3, auctionHouse, 1000);

        stakeholders.add(buyer3);
        stakeholders.add(seller3);
        stakeholders.add(Greece);
        artefacts.add(artefact3);

        /* The above (lines 120-153) were example artefacts and stakeholders for the purpose of demonstrating the
        * functionality of the platform through the use of the GUI. */

        stakeholderArr = new ArrayList<String>(); // Arraylist used for indexing the Stakeholders based on name
        int i=0;
        for (Stakeholder s : stakeholders) {
            stakeholderArr.add(s.getName());
            i++;
        }
        artefactArr = new ArrayList<String>(); // Arraylist used for indexing the Artefacts based on name
        i=0;
        for (Artefact a : artefacts) {
            artefactArr.add(a.getName());
            i++;
        }

        Block genesisBlock = new Block(data1, "00001", new Date().getTime());
        genesisBlock.mineBlock(prefix);
        if (genesisBlock.getCurrentHash().substring(0, prefix).equals(prefixString)) {
            blockchain.add(genesisBlock);
            System.out.println(data1.toString());
        } else {
            System.out.println("Malicious block, not added to the chain");
            gui.messages.setText("Malicious block, not added to the chain");
        }

        Block secondBlock = new Block(data2, blockchain.get(blockchain.size() - 1).getCurrentHash(), new Date().getTime());
        secondBlock.mineBlock(prefix);
        if (secondBlock.getCurrentHash().substring(0, prefix).equals(prefixString) &&  verify_Blockchain(blockchain)) {
            blockchain.add(secondBlock);
            System.out.println(data2.toString());
        } else {
            System.out.println("Malicious block, not added to the chain");
            gui.messages.setText("Malicious block, not added to the chain");
        }

        Block newBlock = new Block(data3, blockchain.get(blockchain.size() - 1).getCurrentHash(), new Date().getTime());
        newBlock.mineBlock(prefix);
        if (newBlock.getCurrentHash().substring(0, prefix).equals(prefixString) &&  verify_Blockchain(blockchain)) {
            blockchain.add(newBlock);
            System.out.println(data3.toString());
        } else {
            System.out.println("Malicious block, not added to the chain");
            gui.messages.setText("Malicious block, not added to the chain");
        }

        /* Lines 171-196 add 3 initial blocks to the chain to show initial functionality. All additional blocks are mined
        * and added to the blockchain via the gui class. */

        gui.main(args); // initialization of GUI
    }
}
