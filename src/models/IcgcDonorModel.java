package models;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;

import java.util.List;

/**
 * Created by svenfillinger on 12.11.15.
 */
public class IcgcDonorModel {

    private String donorID;

    private String submittedSpecimenID;

    private String specimenType;


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

        System.out.println(jsonObject.get("specimen").toString());

        // Get all the specimen extracted from donor (tumor, healthy tissue et)
        JsonArray specimenList = jsonObject.get("specimen").getAsJsonArray();

        for(JsonElement specimen : specimenList){
            System.out.println("Submitted ID: " + specimen.getAsJsonObject().get("submittedId"));
            System.out.println("");
            JsonArray samples = specimen.getAsJsonObject().getAsJsonObject().get("samples").getAsJsonArray();
            JsonArray availableRawData = samples.get(0).getAsJsonObject().get("availableRawSequenceData").getAsJsonArray();

            if (availableRawData.size() == 0){ // if there is now raw sequence data available
                continue;
            } else{
                for(JsonElement rawData  : availableRawData){
                    System.out.println("RawData: " + rawData.getAsJsonObject().get("libraryStrategy"));
                }
            }


        }

        //TODO: Implement additional field extractions

        return null;
    }
}
