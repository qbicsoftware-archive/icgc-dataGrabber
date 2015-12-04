import controllers.IcgcDataBaseController;

import models.IcgcDataBaseModel;
import models.IcgcDonorModel;
import models.donor.Donor;

import org.apache.commons.cli.*;
import views.IcgcGrabberView;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by svenfillinger on 29.10.15.
 */
public class IcgcDataGrabber {

    public static void main(String[] args) {

        final File filename;

        Options options = new Options();
        options.addOption("h", "help", false, "show this help page");
        options.addOption("o", "output", true, "Writes the ouput in Qwizard readable format.");

        HelpFormatter helpFormatter = new HelpFormatter();

        CommandLineParser parser = new DefaultParser();

        if (args.length == 0){
            System.err.println("You have to pride a path for the output file!");
            helpFormatter.printHelp("IcgcDataGrabber.jar -o <FILE> [-h]", options);
            System.exit(0);
        }

        try{
            CommandLine cmd = parser.parse(options, args);
            if (cmd.hasOption('h')){
                helpFormatter.printHelp("IcgcDataGrabber.jar -o <FILE> [-h]", options);
                System.exit(0);
            }
            if (cmd.hasOption('o')){
                filename = new File(cmd.getOptionValue("o") + File.separator + "icgc_parsed.tsv");
                init(filename);
            }
        } catch (ParseException e){
            System.err.println("Something went wrong reading your command line arguments!\n");
            helpFormatter.printHelp("IcgcDataGrabber.jar -o <FILE> [-h]", options);
            System.exit(1);
        }



    }

    private static void init(File filename){
        IcgcDataBaseModel model = new IcgcDataBaseModel();
        IcgcGrabberView view = new IcgcGrabberView();
        IcgcDonorModel donorModel = new IcgcDonorModel();
        IcgcDataBaseController controller = new IcgcDataBaseController(model, view, donorModel);

        /*
        Fetch problems with the ouput file before the whole algorithm starts
         */
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename))){
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
            Parse the shit
             */
            DonorToQWizardParser.parse(bufferedWriter, donorModelList, "ICGC-data");

        } catch (IOException e){
            System.err.println("Could not open/find file: " + filename);
            System.err.println(e);
            System.exit(1);
        }
    }

}



