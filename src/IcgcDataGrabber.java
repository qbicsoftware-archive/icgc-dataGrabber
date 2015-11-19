import controllers.IcgcDataBaseController;
import models.IcgcDataBaseModel;
import views.IcgcGrabberView;

import java.util.List;

/**
 * Created by svenfillinger on 29.10.15.
 */
public class IcgcDataGrabber {

    public static void main(String[] args) {

        IcgcDataBaseModel model = new IcgcDataBaseModel();

        IcgcGrabberView view = new IcgcGrabberView();

        IcgcDataBaseController controller = new IcgcDataBaseController(model, view);

        //controller.makeGetRequest("https://dcc.icgc.org:443/api/v1/donors/DO6358?include=specimen");
        controller.makeGetRequest("https://dcc.icgc.org:443/api/v1/projects/OV-AU/donors?size=10000");


        List<String> donorList = controller.donorIDsfromProject();

        for (String donor : donorList){
            System.out.println("https://dcc.icgc.org:443/api/v1/donors/" + donor + "?include=specimen");

            controller.makeGetRequest("https://dcc.icgc.org:443/api/v1/donors/" + donor + "?include=specimen");
            break;
        }

    }
}



