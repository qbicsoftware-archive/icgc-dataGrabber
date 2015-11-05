package controllers;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import models.IcgcDataBaseModel;

import views.IcgcGrabberView;

import java.io.IOException;


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

    public void makeGetRequest(){
        this.view.printOnConsole("Performing GET request...");
        try {
            getRequest = model.getProjectsFromICGC();
        } catch (IOException e){
            view.printOnConsole("Sorry, could not execute http GET request");
            view.printErrorMessage(e.toString());
        } catch (JsonParseException e){
            view.printOnConsole("Sorry, content could not be parsed to JSON");
            view.printErrorMessage(e.toString());
        }

        view.printOnConsole(getRequest.toString());

    }



}
