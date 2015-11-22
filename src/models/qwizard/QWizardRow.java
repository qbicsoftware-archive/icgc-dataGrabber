package models.qwizard;

import models.Barcode.BarcodeFactory;

import java.util.Arrays;
import java.util.List;

/**
 * Created by fillinger on 11/22/15.
 */
public class QWizardRow {

    protected final List<String> columnFields;
    protected BarcodeFactory barcodeFactory;

    public QWizardRow(BarcodeFactory barcodeFactory){
        this.columnFields = Arrays.asList(new String[11]);
        this.barcodeFactory = barcodeFactory;
    }

    public void setEntityNumber(int number){
    }

    public void setSpace(String space){
    }

    public void setSecondaryName(String string){
    }

    public String getIdentifier(){
        return this.columnFields.get(0);
    }

    public String toString(){
        return String.join("\t", columnFields);
    }
}
