package models.qwizard;

import models.Barcode.BarcodeProducer;

/**
 * Created by fillinger on 11/22/15.
 */
public class EntityRow extends AbstractQWizardRow {

    private final String SAMPLE_TYPE = "Q_BIOLOGICAL_ENTITY";

    public EntityRow(BarcodeProducer barcodeFactory) {
        super(barcodeFactory);
        this.setSampleType(SAMPLE_TYPE);
        this.setExperiment("QICGCE1");
        this.setOrganismId("9606");  // NCBI ORGANISM id
    }


}