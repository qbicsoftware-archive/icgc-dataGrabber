package models.qwizard;

import models.Barcode.BarcodeFactory;

/**
 * Created by fillinger on 11/22/15.
 */
public class QWizardRowFactory {

    private final BarcodeFactory barcodeFactory = BarcodeFactory.initFactory("QICGC", 1, 'A');

    public QWizardRow getWizardRow(String rowType){
        if(rowType == null){
            return null;
        }
        if(rowType.equalsIgnoreCase("ENTITY")){
            return new EntityRow(barcodeFactory);
        }

        return null;
    }

}
