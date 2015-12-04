package models.qwizard;

import java.util.Arrays;
import java.util.List;

/**
 * icgcDataGrabber
 * Description:
 *
 * @author fillinger
 * @version ${VERSION}
 *          Date: 12/4/15
 *          EMail: sven.fillinger@student.uni-tuebingen.de
 */
public class WizardHeader {

    public static String getHeader(){
        List<String> columnList = Arrays.asList(new String[12]);

        columnList.set(0, "Identifier");
        columnList.set(1, "SAMPLE TYPE");
        columnList.set(2, "SPACE");
        columnList.set(3, "EXPERIMENT");
        columnList.set(4, "Q_SECONDARY_NAME");
        columnList.set(5, "PARENT");
        columnList.set(6, "Q_PRIMARY_TISSUE");
        columnList.set(7, "Q_TISSUE_DETAILED");
        columnList.set(8, "Q_ADDITIONAL_INFO");
        columnList.set(9, "Q_NCBI_ORGANISM");
        columnList.set(10, "Q_SAMPLE_TYPE");
        columnList.set(11, "Q_EXTERNALDB_ID");

        return String.join("\t", columnList);

    }

}
