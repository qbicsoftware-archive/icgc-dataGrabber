package models.qwizard;

import models.Barcode.BarcodeFactory;

/**
 * Created by fillinger on 11/22/15.
 */
public class EntityRow extends QWizardRow {

    private final String SAMPLE_TYPE = "Q_BIOLOGICAL_ENTITY";

    public EntityRow(BarcodeFactory barcodeFactory) {
        super(barcodeFactory);
        this.columnFields.set(1, SAMPLE_TYPE);
        this.columnFields.set(3, "QICGCE1");
        this.columnFields.set(10, "9606");  // NCBI ORGANISM id
    }

    @Override
    public void setEntityNumber(int number){
        this.columnFields.set(0, "QICGCEntity" + number);
    }

    @Override
    public void setSpace(String space){
        this.columnFields.set(2, space);
    }

    @Override
    public void setSecondaryName(String string){
        this.columnFields.set(4, string);
    }

}
