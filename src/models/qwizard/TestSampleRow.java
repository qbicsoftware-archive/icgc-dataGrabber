package models.qwizard;

import models.barcode.BarcodeProducer;

/**
 * Created by fillinger on 11/23/15.
 */
public class TestSampleRow extends AbstractQWizardRow {

    private final String SAMPLE_TYPE = "Q_TEST_SAMPLE";

    private final String EXPERIMENT_TYPE = "QICGCE3";

    public TestSampleRow(BarcodeProducer barcodeFactory) {
        super(barcodeFactory);
        this.setSampleType(SAMPLE_TYPE);
        this.setExperiment(EXPERIMENT_TYPE);

    }
}
