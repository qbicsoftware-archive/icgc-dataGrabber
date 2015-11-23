package models.qwizard;

import models.Barcode.BarcodeProducer;

/**
 * Created by fillinger on 11/23/15.
 */
public class TestSampleRow extends AbstractQWizardRow {

    private final String SAMPLE_TYPE = "Q_TEST_SAMPLE";

    public TestSampleRow(BarcodeProducer barcodeFactory) {
        super(barcodeFactory);
    }
}
