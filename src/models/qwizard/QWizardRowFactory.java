package models.qwizard;

import models.barcode.BarcodeProducer;

/**
 * Created by fillinger on 11/22/15.
 */
public class QWizardRowFactory {

    private final BarcodeProducer barcodeFactory = BarcodeProducer.getInstance("QICGC", 1, 'A');

    public AbstractQWizardRow getWizardRow(RowTypes rowType) {
        AbstractQWizardRow requestedRowType;
        switch (rowType){
            case ENTITY:
                requestedRowType = new EntityRow(barcodeFactory);
                break;
            case BIO_SAMPLE:
                requestedRowType = new BioSampleRow(barcodeFactory);
                break;
            case TEST_SAMPLE:
                requestedRowType = new TestSampleRow(barcodeFactory);
                break;
            case SINGLE_SAMPLE_RUN:
                requestedRowType = new SingleSampleRunRow(barcodeFactory);
                break;
            default:
                requestedRowType = null;
                break;
        }
        return requestedRowType;
    }

}
