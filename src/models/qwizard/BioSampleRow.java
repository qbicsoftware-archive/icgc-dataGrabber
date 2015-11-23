package models.qwizard;

import models.barcode.BarcodeProducer;

/**
 * Created by fillinger on 11/23/15.
 */
public class BioSampleRow extends AbstractQWizardRow {

    private final String SAMPLE_TYPE = "Q_BIOLOGICAL_SAMPLE";

    public BioSampleRow(BarcodeProducer barcodeFactory) {
        super(barcodeFactory);
        this.setSampleType(SAMPLE_TYPE);
        this.setExperiment("QICGCE2");
    }


}
