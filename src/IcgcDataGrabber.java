
import com.sun.rowset.internal.Row;
import controllers.IcgcDataBaseController;

import models.IcgcDataBaseModel;
import models.IcgcDonorModel;
import models.donor.Donor;
import models.qwizard.AbstractQWizardRow;
import models.qwizard.QWizardRowFactory;
import models.qwizard.RowTypes;
import views.IcgcGrabberView;

import java.util.ArrayList;
import java.util.List;


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
        List<AbstractQWizardRow> qWizardRowList = new ArrayList<>();
        QWizardRowFactory qWizardRowFactory = new QWizardRowFactory();
        int donorCounter = 1;
        final String SPACE = "ICGC_CANCER_SPACE";
        for (Donor donor : donorModelList){
            AbstractQWizardRow entity = qWizardRowFactory.getWizardRow(RowTypes.ENTITY);
            entity.setEntityNumber(donorCounter);
            entity.setSecondaryName(removeQuotes(donor.getDonorID()));
            entity.setSpace(SPACE);
            System.out.println(entity);
            for (Donor.Specimen specimen : donor.getSpecimenList()){
                AbstractQWizardRow bioSample = qWizardRowFactory.getWizardRow(RowTypes.BIO_SAMPLE);
                bioSample.setEntityNumber();
                bioSample.setSpace(SPACE);
                bioSample.setSecondaryName(removeQuotes(specimen.getSpecimenID()));
                bioSample.setParent(entity.getEntity());
                bioSample.setPrimaryTissue(specimen.getSpecimenType());
                System.out.println(bioSample);
                for (Donor.Sample sample: specimen.getSampleList()){
                    AbstractQWizardRow testSample = qWizardRowFactory.getWizardRow(RowTypes.TEST_SAMPLE);
                    AbstractQWizardRow singleSampleRun = qWizardRowFactory.getWizardRow(RowTypes.SINGLE_SAMPLE_RUN);
                    testSample.setEntityNumber();
                    testSample.setSpace(SPACE);
                    testSample.setSecondaryName(removeQuotes(sample.getSampleID()));
                    testSample.setParent(bioSample.getEntity());
                    testSample.setQSampleType(sample.getLibraryTypes());
                    System.out.println(testSample);
                    singleSampleRun.setEntityNumber();
                    singleSampleRun.setSpace(SPACE);
                    singleSampleRun.setSecondaryName(sample.getAnalysedID());
                    singleSampleRun.setParent(testSample.getEntity());
                    System.out.println(singleSampleRun);

                }

            }
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



