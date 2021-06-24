/*
Group1
Will Caulkins
Caitlin Kelly
Kristen Kovach
 */

/* Fairly standard class to initialize a Artefact object then get/set members of that object */

import java.util.Date;

public class Artefact {
    private String id;
    private String name;
    private Stakeholder legalOwner;
    private Stakeholder currentOwner;
    private int stakeOfLegalOwner;
    private Stakeholder artist;
    private Long dateOfCreation;
    private String creationLocation;
    private String medium;
    private double dimensionX;
    private double dimensionY;
    private double dimensionZ;
    private double weightLbs;
    private double appraisedValue;

    public Artefact() {
        this.id = null;
        this.name = null;
        this.legalOwner = null;
        this.currentOwner = null;
        this.stakeOfLegalOwner = 40;
        this.artist = null;
        this.dateOfCreation = null;
        this.creationLocation = null;
        this.medium = null;
        this.dimensionX = 0.0;
        this.dimensionY = 0.0;
        this.dimensionZ = 0.0;
        this.weightLbs = 0.0;
        this.appraisedValue = 0.0;
    }

    public Artefact(String id, String name, Stakeholder legalOwner, Stakeholder currentOwner) {
        this.id = id;
        this.name = name;
        this.legalOwner = legalOwner;
        this.currentOwner = currentOwner;
        this.stakeOfLegalOwner = 40;
    }


    public Artefact(String id, String name, Stakeholder legalOwner, Stakeholder currentOwner, Stakeholder artist, Long dateOfCreation, String creationLocation, String medium, double dimensionX, double dimensionY, double dimensionZ, double weightLbs, double appraisedValue) {
        this.id = id;
        this.name = name;
        this.legalOwner = legalOwner;
        this.currentOwner = currentOwner;
        this.stakeOfLegalOwner = 40;
        this.artist = artist;
        this.dateOfCreation = dateOfCreation;
        this.creationLocation = creationLocation;
        this.medium = medium;
        this.dimensionX = dimensionX;
        this.dimensionY = dimensionY;
        this.dimensionZ = dimensionZ;
        this.weightLbs = weightLbs;
        this.appraisedValue = appraisedValue;
    }

    public Stakeholder getArtist() {
        return artist;
    }

    public void setArtist(Stakeholder artist) {
        this.artist = artist;
    }

    public Long getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Long dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public String getCreationLocation() {
        return creationLocation;
    }

    public void setCreationLocation(String creationLocation) {
        this.creationLocation = creationLocation;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public double getDimensionX() {
        return dimensionX;
    }

    public void setDimensionX(double dimensionX) {
        this.dimensionX = dimensionX;
    }

    public double getDimensionY() {
        return dimensionY;
    }

    public void setDimensionY(double dimensionY) {
        this.dimensionY = dimensionY;
    }

    public double getDimensionZ() {
        return dimensionZ;
    }

    public void setDimensionZ(double dimensionZ) {
        this.dimensionZ = dimensionZ;
    }

    public double getWeightLbs() {
        return weightLbs;
    }

    public void setWeightLbs(double weightLbs) {
        this.weightLbs = weightLbs;
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

    public double getAppraisedValue() {
        return appraisedValue;
    }

    public void setAppraisedValue(double appraisedValue) {
        this.appraisedValue = appraisedValue;
    }

    @Override
    public String toString() {
        String result = "ID: " + this.id;
        result += "\nName: " + this.name;
        result += "\nBailor: " + this.legalOwner.getName();
        result += "\nOwner: " + this.legalOwner.getName();
        if (artist != null) {
            result += "\nArtist: " + this.artist.getName();
        } else {
            result += "\nArtist: unknown";
        }
        if (dateOfCreation != null) {
            result += "\nDate of Creation: " + new Date(this.dateOfCreation);
        } else {
            result += "\nDate of Creation: unknown";
        }
        if (creationLocation != null) {
            result += "\nLocation of Creation: " + this.creationLocation;
        } else {
            result += "\nLocation of Creation: unknown";
        }
        if (medium != null) {
            result += "\nMedium: " + this.medium;
        } else {
            result += "\nMedium: unknown";
        }
        if (appraisedValue != 0.0) {
            result += "\nAppraised Value: " + this.appraisedValue;
        } else {
            result += "\nAppraised Value: unknown";
        }
        if (dimensionX==0.0 || dimensionY==0.0 || dimensionZ==0.0) {
            result += "\nDimensions: unknown";
        } else {
            result += "\nDimensions: " + dimensionX + " x " + dimensionY + " x " + dimensionZ;
        }
        if (weightLbs!=0.0) {
            result += "\nWeight: " + weightLbs;
        } else {
            result += "\nWeight: unknown";
        }

        return result;
    }

    public String hashToString() {
        String result = "ID: " + this.id;
        result += "\nName: " + this.name;
        result += "\nBailor: " + this.legalOwner.toString();
        return result;
    }
}
