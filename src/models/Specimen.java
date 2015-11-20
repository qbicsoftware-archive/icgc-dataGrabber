package models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fillinger on 11/20/15.
 */
public class Specimen {

    private String specimenID;  // i.e. "SP15182" (ICGC specimen id)

    private String specimenType;  // i.e. "Primary tumor" (specimen type)

    private ArrayList<Sample> sampleList;

    public Specimen(String specimenID, String specimenType) {
        this.specimenID = specimenID;
        this.specimenType = specimenType;
        this.sampleList = new ArrayList<>();
    }

    public String getSpecimenID() {
        return specimenID;
    }

    public void setSpecimenID(String specimenID) {
        this.specimenID = specimenID;
    }

    public String getSpecimenType() {
        return specimenType;
    }

    public void setSpecimenType(String specimenType) {
        this.specimenType = specimenType;
    }

    public List<Sample> getSampleList(){
        return this.sampleList;
    }

    public void addSampleToList(Sample sample){
        this.sampleList.add(sample);
    }
}
