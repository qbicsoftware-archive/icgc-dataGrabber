package models.qwizard;

import models.barcode.BarcodeProducer;

/**
 * Created by fillinger on 11/22/15.
 */
public class EntityRow extends AbstractQWizardRow {

    private final String SAMPLE_TYPE = "Q_BIOLOGICAL_ENTITY";

    private final String EXPERIMENT_TYPE = "QICGCE1";

    public EntityRow(BarcodeProducer barcodeFactory) {
        super(barcodeFactory);
        this.setSampleType(SAMPLE_TYPE);
        this.setExperiment(EXPERIMENT_TYPE);
        this.setOrganismId("9606");  // NCBI ORGANISM id
    }


}
