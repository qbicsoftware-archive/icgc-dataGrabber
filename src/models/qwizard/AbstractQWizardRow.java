package models.qwizard;

import models.barcode.BarcodeProducer;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by fillinger on 11/22/15.
 */
public abstract class AbstractQWizardRow{

    protected final List<String> columnFields;
    protected BarcodeProducer barcodeFactory;

    public AbstractQWizardRow(BarcodeProducer barcodeFactory){
        this.columnFields = Arrays.asList(new String[12]).stream().map(value -> "").collect(Collectors.toList());
        this.barcodeFactory = barcodeFactory;
    }

    public void setEntityNumber() {
        this.columnFields.set(0, barcodeFactory.getBarcode());
    }

    public void setEntityNumber(int number) {
        this.columnFields.set(0, "QICGCENTITY-" + number);
    }

    public String getEntity(){
        return this.columnFields.get(0);
    }

    public void setSampleType(String string){
        this.columnFields.set(1, string);
    }

    public void setSpace(String space) {
        this.columnFields.set(2, space);
    }

    protected void setExperiment(String string){
        this.columnFields.set(3, string);
    }

    public void setSecondaryName(String string) {
        this.columnFields.set(4, string);
    }

    public void setParent(String string){
        this.columnFields.set(5, string);
    }

    public String getParent(){
        return this.columnFields.get(5);
    }

    public void setPrimaryTissue(String string){
        this.columnFields.set(6, string);
    }

    public void setTissueDetailed(String string){
        this.columnFields.set(7, string);
    }

    public String getTissueDetailed(){
        return this.columnFields.get(7);
    }

    public void setOrganismId(String string){
        this.columnFields.set(10, string);
    }

    public void setQSampleType(String string){
        this.columnFields.set(11, string);
    }

    public String getQSampleType(){
        return this.columnFields.get(11);
    }


    public String toString(){
        return String.join("\t", columnFields);
    }

}
