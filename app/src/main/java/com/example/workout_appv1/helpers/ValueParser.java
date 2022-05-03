package com.example.workout_appv1.helpers;

public final class ValueParser {
    /**
     * Function checks if string value is positive integer value
     * @param value string to check
     * @return whether parse is successful
     */
    public static boolean isPositiveInteger(String value){
        if(value.isEmpty()){
            return false;
        }
        try{
            int number_value = Integer.parseInt(value);
            if(number_value<=0){
                return false;
            }

        }catch (NumberFormatException exception){
            return false;
        }
        return true;
    }

    /**
     * Function checks if string value is positive double value
     * @param value string to check
     * @return whether parse is successful
     */
    public static boolean isDouble(String value){
        if(value.isEmpty()){
            return false;
        }
        try{
            double number_value =Double.parseDouble(value);
            if(number_value<=0){
                return false;
            }
        }catch (NumberFormatException e){
            return false;
        }
        return true;
    }
}
