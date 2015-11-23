package models.qwizard;

import models.Barcode.BarcodeProducer;

/**
 * Created by fillinger on 11/22/15.
 */
public class QWizardRowFactory {

    private final BarcodeProducer barcodeFactory = BarcodeProducer.getInstance("QICGC", 1, 'A');

    public AbstractQWizardRow getWizardRow(String rowType){
        if(rowType == null){
            return null;
        }
        if(rowType.equalsIgnoreCase("ENTITY")){
            return new EntityRow(barcodeFactory);
        }
        if(rowType.equalsIgnoreCase("BIO_SAMPLE")){
            return new BioSampleRow(barcodeFactory);
        }

        return null;
    }

}
