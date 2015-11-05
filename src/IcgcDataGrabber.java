import controllers.IcgcDataBaseController;
import models.IcgcDataBaseModel;
import views.IcgcGrabberView;

/**
 * Created by svenfillinger on 29.10.15.
 */
public class IcgcDataGrabber {
    public static void main(String args[]) {

        IcgcDataBaseModel model = new IcgcDataBaseModel();

        IcgcGrabberView view = new IcgcGrabberView();

        IcgcDataBaseController controller = new IcgcDataBaseController(model, view);

        controller.makeGetRequest();
    }

}



