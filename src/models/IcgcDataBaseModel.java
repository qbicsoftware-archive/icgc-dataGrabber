package models;


import com.google.gson.*;
import controllers.IcgcDataBaseController;

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

    HttpClient client = HttpClientBuilder.create().build();

    HttpGet request = new HttpGet();

    private final String USER_AGENT = "Mozilla/5.0";

    private IcgcDataBaseController controller;
    /**
     * The GET request path for retreaving all projects
     * on ICGC
     */
    private String httpRequestUrlProjects = "https://dcc.icgc.org:443/api/v1/projects?filters=%7B%7D&&&sort=totalLiveDonorCount&order=desc";

    /**
     * The GET request path for retreving all donors from one
     * project
     */
    private String httpRequestUrlDonorsAll = "";

    /**
     * The GET request path for retreaving information of a specific
     * donor
     */
    private String httpRequestUrlDonor = "";


    /**
     * Make the project query on ICGC, returns the project overview as JSON string for ALL ICGC
     * projects!
     * @return
     * @throws IOException
     * @throws JsonParseException
     */
    public JsonObject getProjectsFromICGC() throws IOException, JsonParseException {
        this.request = new HttpGet(httpRequestUrlProjects);
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

        JsonObject projectJSON = (JsonObject) jsonParser.parse(jsonString.toString());

        return projectJSON;
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
            idList.add(jsonObjectNested.get("id").toString());
        }

        System.out.println(idList.get(0));

        return idList;
    }


}



