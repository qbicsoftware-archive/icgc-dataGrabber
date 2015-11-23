package models.Barcode;


/**
 * Created by fillinger on 11/22/15.
 */
public class BarcodeProducer{

    private static String _prefix; // i.e. 'QICGC'

    private static int _uniqueID;  // i.e. '999'

    private static Character _letterCounter;  // i.e. 'A'

    private static BarcodeProducer barcodeFactory = new BarcodeProducer();

    private BarcodeProducer(){}

    public static BarcodeProducer getInstance(String prefix, int uniqueID, Character letterCounter){
        _prefix = prefix;
        _uniqueID = uniqueID;
        _letterCounter = letterCounter;
        return barcodeFactory;
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
