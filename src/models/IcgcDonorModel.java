package models;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

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
    public List<String> extractSpecimenInfoFromJson(JsonObject jsonObject) throws JsonParseException{

        //System.out.println(jsonObject.get("specimen").toString());
        System.out.println("Donor ID: " + jsonObject.get("id"));
        // Get all the specimen extracted from donor (tumor, healthy tissue et)
        JsonArray specimenList = jsonObject.get("specimen").getAsJsonArray();

        for(JsonElement specimen : specimenList){
            System.out.println("Specimen ID: " + specimen.getAsJsonObject().get("id"));
            System.out.println("Type: " + specimen.getAsJsonObject().get("type"));


            JsonArray samples = specimen.getAsJsonObject().getAsJsonObject().get("samples").getAsJsonArray();

            for(JsonElement sample : samples){
                JsonArray availableRawData = sample.getAsJsonObject().get("availableRawSequenceData").getAsJsonArray();
                System.out.println("\tanalyzed ID: " + sample.getAsJsonObject().get("analyzedId"));
                if (availableRawData.size() == 0){ // if there is now raw sequence data available
                    continue;
                } else{
                    for(JsonElement rawData  : availableRawData){
                        System.out.println("\tRawData: " + rawData.getAsJsonObject().get("libraryStrategy"));
                    }
                }

            }


        }



        //TODO: Implement additional field extractions
        System.out.println("--------------------------------------");
        return null;
    }
}
