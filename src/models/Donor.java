package models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fillinger on 11/20/15.
 */
public class Donor {

    private String donorID;  // i.e. "DO2546" (ICGC donor id)

    private List<Specimen> specimenList;

    public Donor(String donorID, List<Specimen> specimenList) {
        this.donorID = donorID;
        this.specimenList = specimenList;
    }

    public Donor(String donorID){
        this.donorID = donorID;
        this.specimenList = new ArrayList<>();
    }

    public String getDonorID() {
        return donorID;
    }

    public void setDonorID(String donorID) {
        this.donorID = donorID;
    }

    public List<Specimen> getSpecimenList() {
        return specimenList;
    }

    public void setSpecimenList(List<Specimen> specimenList) {
        this.specimenList = specimenList;
    }
}
