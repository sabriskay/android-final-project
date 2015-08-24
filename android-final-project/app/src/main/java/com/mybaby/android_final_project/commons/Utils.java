package com.mybaby.android_final_project.commons;

import java.math.BigDecimal;
import java.util.Calendar;

/**
 * Created by Paula on 23/08/2015.
 */
public abstract class Utils {

    /**
     *
     * @param birthDate
     * @param controlDate
     * @return amount of months between dates
     */
    public static int monthsBetween(Calendar birthDate, Calendar controlDate){
        Calendar c1 = Calendar.getInstance();
        c1.setTime(birthDate.getTime());
        Calendar c2 = Calendar.getInstance();
        c2.setTime(controlDate.getTime());
        int diff = (c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR)) * 12 + c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);

        return diff;
    }


    /**
     * Round to certain number of decimals
     *
     * @param d
     * @param decimalPlace the numbers of decimals
     * @return
     */

    public static float roundFloat(float d, int decimalPlace) {
        return BigDecimal.valueOf(d).setScale(decimalPlace,BigDecimal.ROUND_HALF_UP).floatValue();
    }

    public static double roundDouble(double d, int decimalPlace) {
        return BigDecimal.valueOf(d).setScale(decimalPlace,BigDecimal.ROUND_HALF_UP).doubleValue();
    }

}
