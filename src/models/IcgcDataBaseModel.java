package models;

import com.google.gson.*;
import com.sun.xml.internal.bind.v2.TODO;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by svenfillinger on 29.10.15.
 */
public class IcgcDataBaseModel {

    private HttpClient client = HttpClientBuilder.create().build();

    private HttpGet request = new HttpGet();

    private final String USER_AGENT = "Mozilla/5.0";

    /*
     * The GET request path for retreaving all projects
     * on ICGC
     */
    private String httpRequestUrlProjects = "https://dcc.icgc.org:443/api/v1/projects?filters=%7B%7D&&&sort=totalLiveDonorCount&order=desc";

    /*
     * The GET request path for retreving all donors from one
     * project
     */
    private String httpRequestUrlDonorsAll = "";

    /*
     * The GET request path for retreaving information of a specific
     * donor
     */
    private String httpRequestUrlDonor = "https://dcc.icgc.org:443/api/v1/donors/";


    /**
     * Make the project query on ICGC, returns the project overview as JSON string for ALL ICGC
     * projects!
     * @return
     * @throws IOException
     * @throws JsonParseException
     */
    public JsonObject getInfoFromICGC(String httpRequestUrl) throws IOException, JsonParseException {
        this.request = new HttpGet(httpRequestUrl);
        this.request.addHeader("User-Agent", USER_AGENT);

        HttpResponse response = client.execute(this.request);

        JsonParser jsonParser = new JsonParser();

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuilder jsonString = new StringBuilder();
        String currLine = "";
        while((currLine = reader.readLine()) != null){
            jsonString.append(currLine);
        }

        JsonObject jsonObject = (JsonObject) jsonParser.parse(jsonString.toString());

        return jsonObject;
    }


    /**
     *
     * @param jsonObject
     * @return
     * @throws JsonParseException
     */
    public List<String> extractProjectIDsFromJson(JsonObject jsonObject) throws JsonParseException{
        ArrayList<String> idList = new ArrayList();

        for(JsonElement element : jsonObject.get("hits").getAsJsonArray()){
            JsonObject jsonObjectNested = (JsonObject) element;
            String id = (jsonObjectNested.get("id").toString());
            idList.add(id.replaceAll("\"", ""));
            System.out.println(id.replaceAll("\"", ""));
        }

        return idList;
    }





    public List<String> extractDonorsFromProject(JsonObject jsonObject) throws JsonParseException{

        List<String> donorList = new ArrayList();

        JsonArray donors = jsonObject.get("hits").getAsJsonArray();

        for (JsonElement donor : donors){
            String donorID = "";
            donorID = donor.getAsJsonObject().get("id").toString();
            if(!donorID.isEmpty()){
                donorList.add(donorID.replace("\"", ""));
                System.out.println("donorID: " + donorID);
            }
        }

        return donorList;
    }


}



