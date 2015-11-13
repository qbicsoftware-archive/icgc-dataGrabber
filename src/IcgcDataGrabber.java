import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
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
public class IcgcDataGrabber {

    public static void main(String[] args) {

        HttpClient client = HttpClientBuilder.create().build();

        HttpGet request = new HttpGet();

        String USER_AGENT = "Mozilla/5.0";

        /**
         * The GET request path for retreaving all projects
         * on ICGC
         */
        String httpRequestUrlProjects = "https://dcc.icgc.org:443/api/v1/projects?filters=%7B%7D&&&sort=totalLiveDonorCount&order=desc";



        /**
         * Make the project query on ICGC, returns the project overview as JSON string for ALL ICGC
         * projects!
         * @return
         * @throws IOException
         * @throws JsonParseException
         */
        public JsonObject getProjectsFromICGC(HttpGet request, String url) throws IOException, JsonParseException {

            request = new HttpGet(url);
            request.addHeader("User-Agent", USER_AGENT);

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

}



