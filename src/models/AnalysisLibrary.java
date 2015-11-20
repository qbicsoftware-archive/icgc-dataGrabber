package models;

/**
 * Created by fillinger on 11/20/15.
 */
public class AnalysisLibrary {

    private String libraryType;

    private String analyzedID;

    public AnalysisLibrary(String libraryType, String analyzedID) {
        this.libraryType = libraryType;
        this.analyzedID = analyzedID;
    }

    public String getLibraryType() {
        return libraryType;
    }

    public void setLibraryType(String libraryType) {
        this.libraryType = libraryType;
    }

    public String getAnalyzedID() {
        return analyzedID;
    }

    public void setAnalyzedID(String analyzedID) {
        this.analyzedID = analyzedID;
    }
}
