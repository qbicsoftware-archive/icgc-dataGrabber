package models;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import models.donor.Donor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by svenfillinger on 12.11.15.
 */
public class IcgcDonorModel {


    /**
     * extract all the specimen registered for the donor in the database.
     * Gets information like:
     *      - the submitted ID, which is the link to the raw-data
     *      - The raw data type
     * @param jsonObject
     * @return
     * @throws JsonParseException
     */
    public Donor extractDonorInfoFromJson(JsonObject jsonObject) throws JsonParseException{
        // initiate a donor
        Donor donor = new Donor();

        //System.out.println(jsonObject.get("specimen").toString());
        System.out.println("Donor ID: " + jsonObject.get("id"));
        donor.setDonorID(jsonObject.get("id").toString());
        // Get all the specimen extracted from donor (tumor, healthy tissue et)
        JsonArray specimenList = jsonObject.get("specimen").getAsJsonArray();

        for(JsonElement specimen : specimenList){
            Donor.Specimen specimenDonor = new Donor.Specimen();

            specimenDonor.setSpecimenID(specimen.getAsJsonObject().get("id").toString());
            specimenDonor.setSpecimenType(specimen.getAsJsonObject().get("type").toString());

            System.out.println("Specimen ID: " + specimen.getAsJsonObject().get("id"));
            System.out.println("Type: " + specimen.getAsJsonObject().get("type"));

            // Now get for every specimen the samples taken
            JsonArray samples = specimen.getAsJsonObject().getAsJsonObject().get("samples").getAsJsonArray();

            for(JsonElement sample : samples){
                Donor.Sample sampleDonor = new Donor.Sample();

                JsonArray availableRawData = sample.getAsJsonObject().get("availableRawSequenceData").getAsJsonArray();

                sampleDonor.setSampleID(sample.getAsJsonObject().get("id").toString());
                sampleDonor.setAnalysedID(sample.getAsJsonObject().get("analyzedId").toString());

                System.out.println("\tanalyzed ID: " + sample.getAsJsonObject().get("analyzedId"));


                if (availableRawData.size() == 0){ // if there is now raw sequence data available
                    continue;
                } else{
                    for(JsonElement rawData  : availableRawData){
                        Donor.AnalysisLibrary library = new Donor.AnalysisLibrary(rawData.getAsJsonObject().get("libraryStrategy").toString());
                        System.out.println("\tRawData: " + rawData.getAsJsonObject().get("libraryStrategy"));
                        sampleDonor.addLibraryToList(library);
                    }
                }
                specimenDonor.addSampleToList(sampleDonor);
            }
            donor.addSpecimen(specimenDonor);
        }
        //TODO: Implement additional field extractions
        System.out.println("--------------------------------------");
        return donor;
    }
}
