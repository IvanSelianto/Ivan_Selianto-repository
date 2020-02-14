import java.math.BigDecimal;
import java.math.RoundingMode;

public class UnitConversionFileSize {
    public String unitConversion(long size) {
        double convertedSize;
        if (size > Math.pow(2, 10) && size < Math.pow(2, 20)) {
            convertedSize = (double) size / Math.pow(2, 10);
            return roundingNumber(convertedSize, 2) + " Kb";
        }
        if (size > Math.pow(2, 20) && size < Math.pow(2, 30)) {
            convertedSize = (double) size / Math.pow(2, 20);
            return roundingNumber(convertedSize, 2) + " Mb";
        }
        convertedSize = (double) size / Math.pow(2, 30);
        return roundingNumber(convertedSize, 2) + "Gb";

    }
    private double roundingNumber(double value, int places) {
        return new BigDecimal(Double.toString(value)).setScale(places, RoundingMode.HALF_UP).doubleValue();
    }
}
