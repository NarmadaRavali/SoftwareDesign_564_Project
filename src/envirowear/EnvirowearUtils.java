package src.envirowear;

import java.text.DecimalFormat;
import java.util.Random;

public class EnvirowearUtils {

    private static Random RANDOM = new Random();

    public static Double toTwoDecimalPlaces(double value) {
        return Double.valueOf(new DecimalFormat("#.##").format(value));
    }

    public static String toTwoDecimalPlacesInStringFormat(double value) {
        return new DecimalFormat("#.##").format(value);
    }


    public static Double toTwoDecimalPlaces(String value) {
        return Double.valueOf(new DecimalFormat("#.##").format(value));
    }

    public static Double generateRandomDouble(double min, double max) {


        return toTwoDecimalPlaces(min + (max - min) * RANDOM.nextDouble());
    }
}
