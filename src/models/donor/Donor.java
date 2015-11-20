package models.donor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fillinger on 11/20/15.
 */
public class Donor {

    private String donorID;  // i.e. "DO2546" (ICGC donor id)

    private List<Specimen> specimenList;

    public Donor(){
        this.specimenList = new ArrayList<>();
    }

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

    public void addSpecimen(Specimen specimen){
        this.specimenList.add(specimen);
    }

    /**
     * Created by fillinger on 11/20/15.
     */
    public static class Sample {

        private String sampleID;  //  i.e. "SA84632" (ICGC sample id)

        private String analysedID;

        private List<AnalysisLibrary> libraryStrategyList;  // e.g. "RNA"

        public Sample(){
            this.libraryStrategyList = new ArrayList<>();
        }

        public String getSampleID() {
            return sampleID;
        }

        public void setSampleID(String sampleID) {
            this.sampleID = sampleID;
        }

        public String getAnalysedID() {
            return analysedID;
        }

        public void setAnalysedID(String analysedID) {
            this.analysedID = analysedID;
        }

        public List<AnalysisLibrary> getLibraryStrategyList() {
            return libraryStrategyList;
        }

        public void setLibraryStrategy(AnalysisLibrary libraryStrategyList) {
            this.libraryStrategyList.add(libraryStrategyList);
        }

        public Sample(String sampleID, List<AnalysisLibrary> libraryStrategyList) {

            this.sampleID = sampleID;
            this.libraryStrategyList = libraryStrategyList;
        }

        public Sample(String sampleID) {
            this.sampleID = sampleID;
            this.libraryStrategyList = new ArrayList<>();
        }

        public void addLibraryToList(AnalysisLibrary lib){
            this.libraryStrategyList.add(lib);
        }
    }

    /**
     * Created by fillinger on 11/20/15.
     */
    public static class Specimen {

        private String specimenID;  // i.e. "SP15182" (ICGC specimen id)

        private String specimenType;  // i.e. "Primary tumor" (specimen type)

        private ArrayList<Sample> sampleList;

        public Specimen(){
            this.sampleList = new ArrayList<>();
        }

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

    /**
     * Created by fillinger on 11/20/15.
     */
    public static class AnalysisLibrary {

        private String libraryType;

        public AnalysisLibrary(String libraryType) {
            this.libraryType = libraryType;
        }

        public AnalysisLibrary(){}

        public String getLibraryType() {
            return libraryType;
        }

        public void setLibraryType(String libraryType) {
            this.libraryType = libraryType;
        }

    }
}
