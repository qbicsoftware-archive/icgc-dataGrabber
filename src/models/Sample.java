package models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fillinger on 11/20/15.
 */
public class Sample {

    private String sampleID;  //  i.e. "SA84632" (ICGC sample id)

    private List<String> libraryStrategyList;  // e.g. "RNA"

    public String getSampleID() {
        return sampleID;
    }

    public void setSampleID(String sampleID) {
        this.sampleID = sampleID;
    }

    public List<String> getLibraryStrategy() {
        return libraryStrategyList;
    }

    public void setLibraryStrategy(String libraryStrategyList) {
        this.libraryStrategyList.add(libraryStrategyList);
    }

    public Sample(String sampleID, List<String> libraryStrategyList) {

        this.sampleID = sampleID;
        this.libraryStrategyList = libraryStrategyList;
    }

    public Sample(String sampleID) {
        this.sampleID = sampleID;
        this.libraryStrategyList = new ArrayList<>();
    }
}
