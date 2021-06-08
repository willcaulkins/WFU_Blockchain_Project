/*
Group1
Will Caulkins
Caitlin Kelly
Kristen Kovach
 */

/* Fairly standard class to initialize a Artefact object then get/set members of that object */

public class Artefact {
    private String id;
    private String name;
    private Stakeholder legalOwner;
    private Stakeholder currentOwner;
    private int stakeOfLegalOwner;

    public Artefact() {
        this.id = null;
        this.name = null;
        this.legalOwner = null;
        this.currentOwner = null;
        this.stakeOfLegalOwner = 40;
    }

    public Artefact(String id, String name, Stakeholder legalOwner, Stakeholder currentOwner) {
        this.id = id;
        this.name = name;
        this.legalOwner = legalOwner;
        this.currentOwner = currentOwner;
        this.stakeOfLegalOwner = 40;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String Name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return this.name;
    }

    public Stakeholder getLegalOwner() {
        return legalOwner;
    }

    public void setLegalOwner(Stakeholder legalOwner) {
        this.legalOwner = legalOwner;
    }

    public Stakeholder getCurrentOwner() {
        return currentOwner;
    }

    public void setCurrentOwner(Stakeholder currentOwner) {
        this.currentOwner = currentOwner;
    }

    public int getStakeOfLegalOwner() {
        return stakeOfLegalOwner;
    }

    public void setStakeOfLegalOwner(int stakeOfLegalOwner) {
        this.stakeOfLegalOwner = stakeOfLegalOwner;
    }

    @Override
    public String toString() {
        String result = "ID: " + this.id;
        result += "\nName: " + this.name;
        result += "\nBailor: " + this.legalOwner.toString();
        result += "\nOwner: \n" + this.legalOwner.toString();
        return result;
    }

    public String hashToString() {
        String result = "ID: " + this.id;
        result += "\nName: " + this.name;
        result += "\nBailor: " + this.legalOwner.toString();
        return result;
    }
}
