package models.qwizard;

import models.barcode.BarcodeProducer;

/**
 * Created by fillinger on 11/23/15.
 */
public class SingleSampleRunRow extends AbstractQWizardRow{

    private final String SAMPLE_TYPE = "Q_NGS_SINGLE_SAMPLE_RUN";

    private final String EXPERIMENT_TYPE = "QICGCE4";

    public SingleSampleRunRow(BarcodeProducer barcodeFactory) {
        super(barcodeFactory);
        this.setSampleType(SAMPLE_TYPE);
        this.setExperiment(EXPERIMENT_TYPE);

    }
}
