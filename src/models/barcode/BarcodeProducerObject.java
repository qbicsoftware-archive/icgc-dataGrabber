package models.barcode;

/**
 * Created by sven on 1/8/16.
 */
public class BarcodeProducerObject {

    private String _prefix; // i.e. 'QICGC'

    private int _uniqueID;  // i.e. '999'

    private Character _letterCounter;  // i.e. 'A'

    public BarcodeProducerObject(String prefix, int uniqueID, Character letterCounter){
        _prefix = prefix;
        _uniqueID = uniqueID;
        _letterCounter = letterCounter;
    }


    public String getBarcode(){
        setNewBarcode();
        StringBuilder barCode = new StringBuilder();
        barCode.append(_prefix);
        barCode.append(String.format("%03d", _uniqueID));
        barCode.append(_letterCounter);
        return barCode.toString();
    }


    private void setNewBarcode(){

        if(_uniqueID == 999){
            _uniqueID = 1;
            _letterCounter++;
        } else{
            _uniqueID += 1;
        }
    }



}
