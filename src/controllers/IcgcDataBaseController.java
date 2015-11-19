package controllers;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import models.IcgcDataBaseModel;

import views.IcgcGrabberView;

import java.io.IOException;
import java.util.List;


/**
 * Created by svenfillinger on 29.10.15.
 */
public class IcgcDataBaseController {

    private IcgcDataBaseModel model;

    private IcgcGrabberView view;

    private JsonObject getRequest;

    public IcgcDataBaseController(IcgcDataBaseModel model, IcgcGrabberView view){
        this.model = model;
        this.view = view;
    }

    public void makeGetRequest(String request){
        this.view.printOnConsole("Performing GET request...");

        try {
            getRequest = model.getInfoFromICGC(request);
        } catch (IOException e){
            view.printOnConsole("Sorry, could not execute http GET request");
            view.printErrorMessage(e.toString());
        } catch (JsonParseException e){
            view.printOnConsole("Sorry, content could not be parsed to JSON");
            view.printErrorMessage(e.toString());
        }
    }

    public List<String> donorIDsfromProject(JsonObject jsonObject){
        return model.extractDonorsFromProject(jsonObject);
    }

    public List<String> donorIDsfromProject(){
        return model.extractDonorsFromProject(this.getRequest);
    }




}
