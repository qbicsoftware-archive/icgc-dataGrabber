package models;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import models.donor.Donor;

import java.io.BufferedWriter;
import java.io.IOException;

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
    public Donor extractDonorInfoFromJson(JsonObject jsonObject, BufferedWriter log) throws JsonParseException{
        // initiate a donor
        Donor donor = new Donor();

        StringBuilder info = new StringBuilder();

        //System.out.println(jsonObject.get("specimen").toString());
        info.append("Donor ID: " + jsonObject.get("id") + "\n");
        donor.setDonorID(jsonObject.get("id").toString());
        // Get all the specimen extracted from donor (tumor, healthy tissue et)
        JsonArray specimenList = jsonObject.get("specimen").getAsJsonArray();

        for(JsonElement specimen : specimenList){
            Donor.Specimen specimenDonor = new Donor.Specimen();

            specimenDonor.setSpecimenID(specimen.getAsJsonObject().get("id").toString());
            specimenDonor.setSpecimenType(specimen.getAsJsonObject().get("type").toString());

            info.append("Specimen ID: " + specimen.getAsJsonObject().get("id") + "\n");
            info.append("Type: " + specimen.getAsJsonObject().get("type") + "\n");

            // Now get for every specimen the samples taken
            JsonArray samples = specimen.getAsJsonObject().getAsJsonObject().get("samples").getAsJsonArray();

            for(JsonElement sample : samples){
                Donor.Sample sampleDonor = new Donor.Sample();

                JsonArray availableRawData = sample.getAsJsonObject().get("availableRawSequenceData").getAsJsonArray();

                sampleDonor.setSampleID(sample.getAsJsonObject().get("id").toString());
                sampleDonor.setAnalysedID(sample.getAsJsonObject().get("analyzedId").toString());

                info.append("\tanalyzed ID: " + sample.getAsJsonObject().get("analyzedId") + "\n");


                if (availableRawData.size() == 0){ // if there is now raw sequence data available
                    continue;
                } else{
                    for(JsonElement rawData  : availableRawData){
                        Donor.AnalysisLibrary library = new Donor.AnalysisLibrary(rawData.getAsJsonObject().get("libraryStrategy").toString());
                        info.append("\tRawData: " + rawData.getAsJsonObject().get("libraryStrategy") + "\n");
                        sampleDonor.addLibraryToList(library);
                    }
                }
                specimenDonor.addSampleToList(sampleDonor);
            }
            donor.addSpecimen(specimenDonor);
        }
        //TODO: Implement additional field extractions
        info.append("--------------------------------------\n");

        try{
            log.write(info.toString());
        } catch (IOException e){
            System.err.println("IO Exception while writing information to log.");
        }

        return donor;
    }
}
