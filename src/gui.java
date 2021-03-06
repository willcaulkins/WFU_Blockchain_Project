/*
Group1
Will Caulkins
Caitlin Kelly
Kristen Kovach
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class gui extends JPanel{ //Gui through which the blockchain runs
    public DefaultComboBoxModel buyerModel;
    public DefaultComboBoxModel donorModel;
    public DefaultComboBoxModel museumModel;
    public DefaultComboBoxModel sellerModel;
    public DefaultComboBoxModel artistModel;
    public DefaultComboBoxModel auctionHouseModel;
    public DefaultComboBoxModel artefactModel;
    public DefaultComboBoxModel COOModel;
    public static JLabel messages;
    public gui() {
        super(new GridLayout(1, 1));

        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel mainPane = new JPanel();
        tabbedPane.addTab("Purchased Artefact", mainPane);
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
        JLabel lBuy = new JLabel("Buyer");
        buyerModel = new DefaultComboBoxModel(Main.stakeholderArr.toArray());
        JComboBox buyer = new JComboBox(buyerModel);
        JLabel lAuctionHouse = new JLabel("Auction House");
        sellerModel = new DefaultComboBoxModel(Main.stakeholderArr.toArray());
        JComboBox currentOwner4 = new JComboBox(sellerModel);
        auctionHouseModel = new DefaultComboBoxModel(Main.stakeholderArr.toArray());
        JComboBox auctionHouse = new JComboBox(auctionHouseModel);
        JLabel lArtefact = new JLabel("Artefact");
        artefactModel = new DefaultComboBoxModel(Main.artefactArr.toArray());
        JComboBox artefact = new JComboBox(artefactModel);
        JLabel lPrice = new JLabel("Price (X.XX):");
        JTextField price = new JTextField(20);
        JLabel lDate = new JLabel("Date of Transaction(yyyy/MM/dd HH:mm:ss.SSS):");
        JTextField date = new JTextField(20);
        JButton addBlock = new JButton("Add Transaction");
        messages = new JLabel("Messages: ");

        mainPane.add(lBuy);
        mainPane.add(buyer);
        mainPane.add(lAuctionHouse);
        mainPane.add(auctionHouse);
        mainPane.add(lArtefact);
        mainPane.add(artefact);
        mainPane.add(lPrice);
        mainPane.add(price);
        mainPane.add(lDate);
        mainPane.add(date);
        mainPane.add(addBlock);
        mainPane.add(messages);

        mainPane.setLayout(new BoxLayout(mainPane, BoxLayout.Y_AXIS));

        /* Lines 23-61 set the layout for the Block tab in the gui*/

        addBlock.addActionListener(new ActionListener() { // ActionListener for addBlock button
                                       @Override
                                       public void actionPerformed(ActionEvent e) {
                                           double priceGui = Double.parseDouble(price.getText()); // get price of artefact
                                           String currBuy = buyer.getSelectedItem().toString(); // get buyer of artefact
                                           Stakeholder buyerGui = new Stakeholder();
                                           int j=0;
                                           for (Stakeholder b : Main.stakeholders) {
                                               if (b.getName().equals(currBuy)) {
                                                   buyerGui = Main.stakeholders.get(j); /* Matches current buyer name to
                                                   * the corresponding buyer in the list of Stakeholders*/
                                               }
                                               j++;
                                           }
                                           j=0; // reset the counting variable (for convenience)

                                           String currArt = artefact.getItemAt(artefact.getSelectedIndex()).toString();
                                           Artefact artefactGui = new Artefact();
                                           for (Artefact d : Main.artefacts) {
                                               if (d.getName().equals(currArt)) {
                                                   artefactGui = Main.artefacts.get(j); /* Matches current artefact name to
                                                    * the corresponding artefact in the list of artefacts*/
                                               }
                                               j++;
                                           }
                                           j=0;

                                           String currHouse = auctionHouse.getItemAt(auctionHouse.getSelectedIndex()).toString();
                                           Stakeholder houseGui = new Stakeholder();
                                           for (Stakeholder f : Main.stakeholders) {
                                               if (f.getName().equals(currHouse)) {
                                                   houseGui = Main.stakeholders.get(j); /* Matches current auction house name to
                                                    * the corresponding auction house in the list of Stakeholders*/
                                               }
                                               j++;
                                           }
                                           Long finalDate = null;
                                           if (!date.getText().equals("")) { /* If date of transaction is provided, the
                                               * the input is converted to a long variable type for inclusion in the new block*/
                                               String myDate = date.getText();
                                               SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
                                               Date date1 = null;
                                               try {
                                                   date1 = sdf.parse(myDate);
                                               } catch (ParseException parseException) {
                                                   parseException.printStackTrace();
                                               }
                                               finalDate = date1.getTime();
                                           } else { // if no date is provided, the current date and time is recorded
                                               finalDate = new Date().getTime();
                                           }

                                           Transaction transGui = new Transaction(artefactGui, finalDate, artefactGui.getCurrentOwner(), buyerGui, houseGui, priceGui);

                                           Block guiBlock = new Block(transGui, Main.blockchain.get(Main.blockchain.size() - 1).getCurrentHash(), new Date().getTime());
                                           guiBlock.mineBlock(4);
                                           if (guiBlock.getCurrentHash().substring(0, 4).equals("0000") /*&&  Main.verify_Blockchain(Main.blockchain)*/) {
                                               Main.blockchain.add(guiBlock);
                                               messages.setText("Block added successfully!");
                                               System.out.println("\n ------- New Block -------\n");
                                               System.out.println(Main.blockchain.get(Main.blockchain.size()-1).getData().toString());
                                               System.out.println();
                                               System.out.println();
                                           } else {
                                               System.out.println("Malicious block, not added to the chain");
                                               messages.setText("Malicious block, not added to the chain");
                                           }
                                           /* Above code block verifies the transaction with the input information from the GUI*/

                                       }
                                   }
        );

        JPanel panel0 = new JPanel();
        tabbedPane.addTab("Donated Artefact", panel0);
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
        JLabel lDonor = new JLabel("Donor");
        donorModel = new DefaultComboBoxModel(Main.stakeholderArr.toArray());
        JComboBox donor = new JComboBox(donorModel);
        JLabel lMuseum = new JLabel("Museum");
        museumModel = new DefaultComboBoxModel(Main.stakeholderArr.toArray());
        JComboBox museum = new JComboBox(museumModel);
        JLabel lArtefact0 = new JLabel("Artefact");
        artefactModel = new DefaultComboBoxModel(Main.artefactArr.toArray());
        JComboBox artefact0 = new JComboBox(artefactModel);
        JLabel lDate0 = new JLabel("Date of Donation(yyyy/MM/dd HH:mm:ss.SSS):");
        JTextField date0 = new JTextField(20);
        JButton addDonation = new JButton("Add Donation");
        messages = new JLabel("Messages: ");

        panel0.add(lDonor);
        panel0.add(donor);
        panel0.add(lMuseum);
        panel0.add(museum);
        panel0.add(lArtefact0);
        panel0.add(artefact0);
        panel0.add(lDate0);
        panel0.add(date0);
        panel0.add(addBlock);
        panel0.add(messages);

        panel0.setLayout(new BoxLayout(panel0, BoxLayout.Y_AXIS));

        /* Lines 23-61 set the layout for the Block tab in the gui*/

        addBlock.addActionListener(new ActionListener() { // ActionListener for addBlock button
                                       @Override
                                       public void actionPerformed(ActionEvent e) {
                                           String currDonor = donor.getSelectedItem().toString(); // get buyer of artefact
                                           Stakeholder donorGui = new Stakeholder();
                                           int j=0;
                                           for (Stakeholder b : Main.stakeholders) {
                                               if (b.getName().equals(currDonor)) {
                                                   donorGui = Main.stakeholders.get(j); /* Matches current buyer name to
                                                    * the corresponding buyer in the list of Stakeholders*/
                                               }
                                               j++;
                                           }
                                           j=0; // reset the counting variable (for convenience)

                                           String currArt = artefact.getItemAt(artefact.getSelectedIndex()).toString();
                                           Artefact artefactGui = new Artefact();
                                           for (Artefact d : Main.artefacts) {
                                               if (d.getName().equals(currArt)) {
                                                   artefactGui = Main.artefacts.get(j); /* Matches current artefact name to
                                                    * the corresponding artefact in the list of artefacts*/
                                               }
                                               j++;
                                           }
                                           j=0;

                                           String currMuseum = museum.getItemAt(museum.getSelectedIndex()).toString();
                                           Stakeholder museumGui = new Stakeholder();
                                           for (Stakeholder f : Main.stakeholders) {
                                               if (f.getName().equals(currMuseum)) {
                                                   museumGui = Main.stakeholders.get(j); /* Matches current auction house name to
                                                    * the corresponding auction house in the list of Stakeholders*/
                                               }
                                               j++;
                                           }
                                           Long finalDate = null;
                                           if (!date.getText().equals("")) { /* If date of transaction is provided, the
                                            * the input is converted to a long variable type for inclusion in the new block*/
                                               String myDate = date.getText();
                                               SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
                                               Date date1 = null;
                                               try {
                                                   date1 = sdf.parse(myDate);
                                               } catch (ParseException parseException) {
                                                   parseException.printStackTrace();
                                               }
                                               finalDate = date1.getTime();
                                           } else { // if no date is provided, the current date and time is recorded
                                               finalDate = new Date().getTime();
                                           }

                                           Transaction transGui = new Transaction(artefactGui, finalDate, artefactGui.getCurrentOwner(), museumGui, null, 0.0);

                                           Block guiBlock = new Block(transGui, Main.blockchain.get(Main.blockchain.size() - 1).getCurrentHash(), new Date().getTime());
                                           guiBlock.mineBlock(4);
                                           if (guiBlock.getCurrentHash().substring(0, 4).equals("0000") /*&&  Main.verify_Blockchain(Main.blockchain)*/) {
                                               Main.blockchain.add(guiBlock);
                                               messages.setText("Block added successfully!");
                                               System.out.println("\n ------- New Block -------\n");
                                               System.out.println(Main.blockchain.get(Main.blockchain.size()-1).getData().toString());
                                               System.out.println();
                                               System.out.println();
                                           } else {
                                               System.out.println("Malicious block, not added to the chain");
                                               messages.setText("Malicious block, not added to the chain");
                                           }
                                           /* Above code block verifies the transaction with the input information from the GUI*/

                                       }
                                   }
        );

        JPanel panel1 = new JPanel();
        tabbedPane.addTab("Buyer", panel1);
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_2);
        JLabel lId = new JLabel("ID:");
        JTextField id = new JTextField(20);
        JLabel lName = new JLabel("Name:");
        JTextField name = new JTextField(20);
        JLabel lAddress = new JLabel("Address:");
        JTextField address = new JTextField(20);
        JLabel lBalance = new JLabel("Balance:");
        JTextField balance = new JTextField(20);
        JButton button1 = new JButton("Add Buyer");
        JLabel status1 = new JLabel("Messages:",JLabel.CENTER);
        panel1.add(lId);
        panel1.add(id);
        panel1.add(lName);
        panel1.add(name);
        panel1.add(lAddress);
        panel1.add(address);
        panel1.add(lBalance);
        panel1.add(balance);
        panel1.add(button1);
        panel1.add(status1);
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));

        // Above sets the format for the Buyer panel in the GUI

        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { //ActionListener for the "Add Buyer" button to create new buyer in the system
                Stakeholder newBuyer = new Stakeholder(id.getText(),name.getText(), address.getText(), Double.parseDouble(balance.getText()));
                Main.stakeholders.add(newBuyer);
                Main.stakeholderArr.add(newBuyer.getName());
                buyerModel = new DefaultComboBoxModel(Main.stakeholderArr.toArray());
                buyer.removeAllItems();
                buyer.setModel(buyerModel);
                status1.setText("Buyer added Successfully!");
                messages.setText("Buyer added Successfully!");
            }
        });

        JPanel panel2 = new JPanel();
        tabbedPane.addTab("Bailee", panel2);
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_3);
        JLabel lId2 = new JLabel("ID:");
        JTextField id2 = new JTextField(20);
        JLabel lName2 = new JLabel("Name:");
        JTextField name2 = new JTextField(20);
        JLabel lAddress2 = new JLabel("Address:");
        JTextField address2 = new JTextField(20);
        JLabel lBalance2 = new JLabel("Balance:");
        JTextField balance2 = new JTextField(20);
        JButton button2 = new JButton("Add Bailee");
        JLabel status2 = new JLabel("Messages:",JLabel.CENTER);
        panel2.add(lId2);
        panel2.add(id2);
        panel2.add(lName2);
        panel2.add(name2);
        panel2.add(lAddress2);
        panel2.add(address2);
        panel2.add(lBalance2);
        panel2.add(balance2);
        panel2.add(button2);
        panel2.add(status2);
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));

        // Above sets the format for the Seller (referred to as the "Bailee" here) panel in the GUI

        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { //ActionListener for the "Add Bailee" button to create new bailee in the system
                Stakeholder newSeller = new Stakeholder(id2.getText(),name2.getText(), address2.getText(), Double.parseDouble(balance2.getText()));
                Main.stakeholders.add(newSeller);
                Main.stakeholderArr.add(newSeller.getName());
                sellerModel = new DefaultComboBoxModel(Main.stakeholderArr.toArray());
                currentOwner4.removeAllItems();
                currentOwner4.setModel(sellerModel);
                status2.setText("Seller added successfully!");
                messages.setText("Seller added successfully!");
            }
        });

        JPanel panel3 = new JPanel();
        //tabbedPane.addTab("Auction House", panel3);
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_4);
        JLabel lId3 = new JLabel("ID:");
        JTextField id3 = new JTextField(20);
        JLabel lName3 = new JLabel("Name:");
        JTextField name3 = new JTextField(20);
        JLabel lAddress3 = new JLabel("Address:");
        JTextField address3 = new JTextField(20);
        JLabel lBalance3 = new JLabel("Balance:");
        JTextField balance3 = new JTextField(20);
        JButton button3 = new JButton("Add Auction House");
        JLabel status3 = new JLabel("Messages:",JLabel.CENTER);
        panel3.add(lId3);
        panel3.add(id3);
        panel3.add(lName3);
        panel3.add(name3);
        panel3.add(lAddress3);
        panel3.add(address3);
        panel3.add(lBalance3);
        panel3.add(balance3);
        panel3.add(button3);
        panel3.add(status3);
        panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));

        // Above sets the format for the Auction House panel in the GUI

        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { //ActionListener for the "Add Auction House" button to create new auction house in the system
                Stakeholder newAuctionHouse = new Stakeholder(id3.getText(),name3.getText(), address3.getText(), Double.parseDouble(balance3.getText()));
                Main.stakeholders.add(newAuctionHouse);
                Main.stakeholderArr.add(newAuctionHouse.getName());
                auctionHouseModel = new DefaultComboBoxModel(Main.stakeholderArr.toArray());
                auctionHouse.removeAllItems();
                auctionHouse.setModel(auctionHouseModel);
                status3.setText("Auction house added successfully!");
                messages.setText("Auction house added successfully!");
            }
        });

        JPanel panel4 = new JPanel();
        tabbedPane.addTab("Artefact", panel4);
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_5);
        artefactModel = new DefaultComboBoxModel(Main.artefactArr.toArray());
        buyerModel = new DefaultComboBoxModel(Main.stakeholderArr.toArray());
        sellerModel = new DefaultComboBoxModel(Main.stakeholderArr.toArray());
        artistModel = new DefaultComboBoxModel(Main.stakeholderArr.toArray());
        JLabel lId4 = new JLabel("ID:");
        JTextField id4 = new JTextField(20);
        JLabel lName4 = new JLabel("Name:");
        JTextField name4 = new JTextField(20);
        JLabel lartist = new JLabel("Artist:");
        JComboBox artist = new JComboBox(artistModel);
        JLabel lCreationDate = new JLabel("Date of Creation:");
        JTextField creationDate = new JTextField(20);
        JLabel lCreationLocation = new JLabel("Creation Location:");
        JTextField creationLocation = new JTextField(20);
        JLabel lAppraisedValue = new JLabel("Appraised Value:");
        JTextField appraisedValue = new JTextField(20);
        JLabel lMedium = new JLabel("Medium:");
        JTextField medium = new JTextField(20);
        JLabel lDimensionX = new JLabel("X Dimension:");
        JTextField dimensionX = new JTextField(20);
        JLabel lDimensionY = new JLabel("Y Dimension:");
        JTextField dimensionY = new JTextField(20);
        JLabel lDimensionZ = new JLabel("Z Dimension:");
        JTextField dimensionZ = new JTextField(20);
        JLabel lWeight = new JLabel("Weight:");
        JTextField weight = new JTextField(20);
        JLabel lCOO4 = new JLabel("Bailor:");
        JComboBox cOO4 = new JComboBox(buyerModel);
        JLabel lCurrentOwner4 = new JLabel("Current Owner:");
        JButton button4 = new JButton("Add Artefact");
        JLabel status4 = new JLabel("Messages:",JLabel.CENTER);
        panel4.add(lId4);
        panel4.add(id4);
        panel4.add(lName4);
        panel4.add(name4);
        panel4.add(lartist);
        panel4.add(artist);
        panel4.add(lCreationDate);
        panel4.add(creationDate);
        panel4.add(lCreationLocation);
        panel4.add(creationLocation);
        panel4.add(lAppraisedValue);
        panel4.add(appraisedValue);
        panel4.add(lMedium);
        panel4.add(medium);
        panel4.add(lDimensionX);
        panel4.add(dimensionX);
        panel4.add(lDimensionY);
        panel4.add(dimensionY);
        panel4.add(lDimensionZ);
        panel4.add(dimensionZ);
        panel4.add(lWeight);
        panel4.add(weight);
        panel4.add(lCOO4);
        panel4.add(cOO4);
        panel4.add(lCurrentOwner4);
        panel4.add(currentOwner4);
        panel4.add(button4);
        panel4.add(status4);
        panel4.setLayout(new BoxLayout(panel4, BoxLayout.Y_AXIS));

        // Above sets the format for the Artefact panel in the GUI

        button4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { //ActionListener for the "Add Artefact" button to create new artefact in the system
                String currOwn = currentOwner4.getItemAt(currentOwner4.getSelectedIndex()).toString();
                String cOO = cOO4.getItemAt(cOO4.getSelectedIndex()).toString();
                String currArt = artist.getItemAt(artist.getSelectedIndex()).toString();
                Stakeholder currOwner = new Stakeholder();
                Stakeholder countryOfO = new Stakeholder();
                Stakeholder currArtist = new Stakeholder();
                int i=0;
                for (Stakeholder s : Main.stakeholders) {
                    if (s.getName().equals(currOwn)) {
                        currOwner = Main.stakeholders.get(i);
                    }
                    if (s.getName().equals(cOO)) {
                        countryOfO = Main.stakeholders.get(i);
                    }
                    if (s.getName().equals(currArt)) {
                        currArtist = Main.stakeholders.get(i);
                    }
                    i++;
                }
                Artefact newArtefact = new Artefact(id4.getText(),name4.getText(), countryOfO, currOwner);
                newArtefact.setArtist(currArtist);
                if (!creationDate.getText().equals("")) {
                    newArtefact.setDateOfCreation(Long.parseLong(creationDate.getText()));
                }
                if (!creationLocation.getText().equals("")) {
                    newArtefact.setCreationLocation(creationLocation.getText());
                }
                if (!appraisedValue.getText().equals("")) {
                    newArtefact.setAppraisedValue(Double.parseDouble(appraisedValue.getText()));
                }
                if (!medium.getText().equals("")) {
                    newArtefact.setMedium(medium.getText());
                }
                if (!dimensionX.getText().equals("")) {
                    newArtefact.setDimensionX(Double.parseDouble(dimensionX.getText()));
                }
                if (!dimensionY.getText().equals("")) {
                    newArtefact.setDimensionY(Double.parseDouble(dimensionY.getText()));
                }
                if (!dimensionZ.getText().equals("")) {
                    newArtefact.setDimensionZ(Double.parseDouble(dimensionZ.getText()));
                }
                if (!weight.getText().equals("")) {
                    newArtefact.setWeightLbs(Double.parseDouble(weight.getText()));
                }
                Main.artefacts.add(newArtefact);
                Main.artefactArr.add(newArtefact.getName());
                artefactModel = new DefaultComboBoxModel(Main.artefactArr.toArray());
                artefact.removeAllItems();
                artefact.setModel(artefactModel);
                status4.setText("Artefact added successfully!");
                messages.setText("Artefact added successfully!");
            }
        });

        JPanel panel5 = new JPanel();
        tabbedPane.addTab("Bailor", panel5);
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_6);
        JLabel lId5 = new JLabel("ID:");
        JTextField id5 = new JTextField(20);
        JLabel lName5 = new JLabel("Name:");
        JTextField name5 = new JTextField(20);
        JLabel lAddress5 = new JLabel("Address:");
        JTextField address5 = new JTextField(20);
        JLabel lBalance5 = new JLabel("Balance:");
        JTextField balance5 = new JTextField(20);
        JButton button5 = new JButton("Add Bailor");
        JLabel status5 = new JLabel("Messages:",JLabel.CENTER);
        panel5.add(lId5);
        panel5.add(id5);
        panel5.add(lName5);
        panel5.add(name5);
        panel5.add(lAddress5);
        panel5.add(address5);
        panel5.add(lBalance5);
        panel5.add(balance5);
        panel5.add(button5);
        panel5.add(status5);
        panel5.setLayout(new BoxLayout(panel5, BoxLayout.Y_AXIS));

        // Above sets the format for the Legal Owner (referred to here as the "Bailor") panel in the GUI

        button5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { //ActionListener for the "Add Bailor" button to create new bailor in the system
                Stakeholder newLegalOwner = new Stakeholder(id5.getText(),name5.getText(), address5.getText(), Double.parseDouble(balance5.getText()));
                Main.stakeholders.add(newLegalOwner);
                Main.stakeholderArr.add(newLegalOwner.getName());
                COOModel = new DefaultComboBoxModel(Main.stakeholderArr.toArray());
                cOO4.removeAllItems();
                cOO4.setModel(COOModel);
                status5.setText("Legal Owner added successfully!");
                messages.setText("Legal Owner added successfully!");
            }
        });

        JPanel panel6 = new JPanel();
        tabbedPane.addTab("Additional Revenue", panel6);
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_7);
        JLabel lartefact6 = new JLabel("Artefact:");
        artefactModel = new DefaultComboBoxModel(Main.artefactArr.toArray());
        JComboBox artefact6 = new JComboBox(artefactModel);
        JLabel lrevenue = new JLabel("Revenue Amount:");
        JTextField revenue = new JTextField(20);
        JButton button6 = new JButton("Allocate Revenue");
        JLabel status6 = new JLabel("Messages:",JLabel.CENTER);
        panel6.add(lartefact6);
        panel6.add(artefact6);
        panel6.add(lrevenue);
        panel6.add(revenue);
        panel6.add(button6);
        panel6.add(status6);
        panel6.setLayout(new BoxLayout(panel6, BoxLayout.Y_AXIS));

        // Above sets the format for the Additional Revenue panel in the GUI

        button6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { //ActionListener for the "Allocate Revenue" button to distribute revenue according to corresponding stakes
                int j=0;
                String currArt = artefact6.getItemAt(artefact6.getSelectedIndex()).toString(); //this method implements the fractional ownership and allocation of additional revenues from the artefact
                Artefact artefactGui = new Artefact();
                for (Artefact d : Main.artefacts) {
                    if (d.getName().equals(currArt)) {
                        artefactGui = Main.artefacts.get(j);
                    }
                    j++;
                }
                Stakeholder legalOwner = artefactGui.getLegalOwner();
                Stakeholder currentOwner = artefactGui.getCurrentOwner();
//                System.out.println("Bailor: \n" + legalOwner.toString());
//                System.out.println("Bailee: \n" + currentOwner.toString());
                legalOwner.setBalance(legalOwner.getBalance() + (artefactGui.getStakeOfLegalOwner() * Double.parseDouble(revenue.getText()))/100);
                currentOwner.setBalance(currentOwner.getBalance() + ((100-artefactGui.getStakeOfLegalOwner()) * Double.parseDouble(revenue.getText()))/100);
                status6.setText("Revenue allocated successfully!");
                messages.setText("Revenue allocated successfully!");
                System.out.println("Success");
                System.out.println("Bailor: \n" + legalOwner.toString());
                System.out.println("Bailee: \n" + currentOwner.toString());
            }
        });

        JPanel panel7 = new JPanel();
        tabbedPane.addTab("Retrieve Provenance", panel7);
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_2);
        JLabel lSelect = new JLabel("Select Artefact:");
        artefactModel = new DefaultComboBoxModel(Main.artefactArr.toArray());
        JComboBox artefact7 = new JComboBox(artefactModel);

        JButton button7 = new JButton("Retrieve Provenance");
        JLabel status7 = new JLabel("Messages:",JLabel.CENTER);
        panel7.add(lSelect);
        panel7.add(artefact7);
        panel7.add(button7);
        panel7.add(status7);

        panel7.setLayout(new BoxLayout(panel7, BoxLayout.Y_AXIS));

        button7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { //ActionListener for the "Allocate Revenue" button to distribute revenue according to corresponding stakes
                int j=0;
                String currArt = artefact7.getItemAt(artefact7.getSelectedIndex()).toString(); //this method implements the fractional ownership and allocation of additional revenues from the artefact
                Artefact artefactGui2 = new Artefact();
                for (Artefact d : Main.artefacts) {
                    if (d.getName().equals(currArt)) {
                        artefactGui2 = Main.artefacts.get(j);
                    }
                    j++;
                }
                System.out.println("\n ------ Record of Provenance ------ ");
                int count = 1;
                for (Transaction t : Main.retrieveProvenance(artefactGui2.getId())) {
                    System.out.println("\n --- Transaction " + count + ": ---");
                    System.out.println(t.toString());
                    count++;
                }
                System.out.println("\n ------ End of Provenance Record ------ \n");

                status7.setText("Provenance printed successfully!");
                messages.setText("Provenance printed successfully!");
            }
        });

        add(tabbedPane);

        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    }

    protected JComponent makeTextPanel(String text) {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        return panel;
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();

        frame.add(new gui(), BorderLayout.CENTER);

        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                UIManager.put("swing.boldMetal", Boolean.FALSE);
                createAndShowGUI();
            }
        });
    }

    /* Above are generic commands to create and show the GUI*/
}