package controllers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import models.IcgcDataBaseModel;

import models.IcgcDonorModel;
import models.donor.Donor;
import views.IcgcGrabberView;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;


/**
 * Created by svenfillinger on 29.10.15.
 */
public class IcgcDataBaseController {

    private BufferedWriter log;

    private IcgcDataBaseModel model;

    private IcgcDonorModel donorModel;

    private IcgcGrabberView view;

    private JsonObject getRequest;

    public IcgcDataBaseController(IcgcDataBaseModel model, IcgcGrabberView view, IcgcDonorModel donorModel){
        this.model = model;
        this.view = view;
        this.donorModel = donorModel;
        this.log = new BufferedWriter(new OutputStreamWriter(System.out), 100*1024);
    }


    public void setOutputBuffer(BufferedWriter outputBuffer){
        this.log = outputBuffer;
    }


    public void makeGetRequest(String request){
        //this.view.printOnConsole("Performing GET request...");

        try {
            getRequest = model.getInfoFromICGC(request);
            log.write("Request was successfull\n");
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


    public JsonObject getJSONrequest(){
        return this.getRequest;
    }

    public Donor extractSpecimenInfofromJson(){
        try{
            Donor donor = donorModel.extractDonorInfoFromJson(this.getRequest, this.log);
            return donor;

        } catch (JsonParseException e){
            view.printErrorMessage("Parsing of JsonObject failed");
            view.printErrorMessage(e.toString());
        }
        return null;
    }

    public BufferedWriter getOutputStream(){
        return this.log;
    }




}
