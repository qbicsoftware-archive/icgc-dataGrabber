
import controllers.IcgcDataBaseController;

import models.IcgcDataBaseModel;
import models.IcgcDonorModel;
import models.donor.Donor;
import models.qwizard.QWizardRow;
import models.qwizard.QWizardRowFactory;
import views.IcgcGrabberView;

import java.util.ArrayList;
import java.util.List;
import models.qwizard.QWizardRowFactory;

/**
 * Created by svenfillinger on 29.10.15.
 */
public class IcgcDataGrabber {

    public static void main(String[] args) {

        IcgcDataBaseModel model = new IcgcDataBaseModel();
        IcgcGrabberView view = new IcgcGrabberView();
        IcgcDonorModel donorModel = new IcgcDonorModel();
        IcgcDataBaseController controller = new IcgcDataBaseController(model, view, donorModel);

        // Get the information from the ICGC project as JSON object
        view.printOnConsole("Getting Donor information from project...");
        controller.makeGetRequest("https://dcc.icgc.org:443/api/v1/projects/OV-AU/donors?size=10000");

        // Extract the donorIDs
        List<String> donorList = controller.donorIDsfromProject();
        List<Donor> donorModelList = new ArrayList<>();
        view.printOnConsole("Getting specimen information for each donor...");
        /*
        Query the information for every donor
         */
        for (String donor : donorList){
            controller.makeGetRequest("https://dcc.icgc.org:443/api/v1/donors/" + donor + "?include=specimen");
            donorModelList.add(controller.extractSpecimenInfofromJson());
        }


        try{
            controller.getOutputStream().flush();
        } catch (Exception e){
            System.err.println(e.getStackTrace());
        }


        /*
        Iterate over every Donor object and parse it to QWizard style :)
         */
        List<QWizardRow> qWizardRowList = new ArrayList<>();
        QWizardRowFactory qWizardRowFactory = new QWizardRowFactory();
        int donorCounter = 1;
        for (Donor donor : donorModelList){
            QWizardRow entity = qWizardRowFactory.getWizardRow("ENTITY");
            entity.setEntityNumber(donorCounter);
            entity.setSecondaryName(removeQuotes(donor.getDonorID()));
            entity.setSpace("ICGC_CANCER_SPACE");
            System.out.println(entity.toString());

            donorCounter++;
        }

    }

    /**
     * Remove quotes from Strings, as they may occur in JSON fields
     * @param string
     * @return
     */
    public static String removeQuotes(String string){
        return string.replaceAll("\"", "");
    }
}



