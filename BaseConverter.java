import java.lang.Math;

public class BaseConverter {
    
    static String[] baseChars = {"0", "1", "2", "3", "4,", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "+", "/"};
    
    // Takes a String num, written in base base1, and returns its representation in base base2
    public static String baseToBase(int base1, int base2, String num) {
        

        // Ensures that both bases are positive integers, returns an error otherwise
        if (base1 < 1 || base2 < 1) {
            return "Error: Both bases must be a positive number.";
        }
        
        // Ensures that num is written in base base1, returns an error otherwise
        for (int i = num.length() - 1; i >= 0; i--) {
            boolean correctBase = false;
            for (int j = 0; j < base1; j++) {
                if (baseChars[j].equals(num.substring(i,i+1))) {
                    correctBase = true;
                }
            }
            if (!correctBase) {
                return "Error: Number not written in first base.";
            }
        }
        
        return (decimalToBase(base2, baseToDecimal(base1, num)));
    }
    
    // Converts a positive number written in base base1 to base 10
    public static int baseToDecimal(int base1, String num) {
        

        // Ensures that the starting base is positive, returns -1 and prints an error otherwise
        if (base1 < 1) {
            System.out.println("Error: Original base must be a positive number.");
            return -1;
        }
        
        // Ensures that num is written in base base1, returns -1 and prints an error otherwise
        for (int i = num.length() - 1; i >= 0; i--) {
            boolean correctBase = false;
            for (int j = 0; j < base1; j++) {
                if (baseChars[j].equals(num.substring(i,i+1))) {
                    correctBase = true;
                }
            }
            if (!correctBase) {
                System.out.println("Error: Number not written in first base.");
                return -1;
            }
        }
        
        int power = 0;
        int sum = 0;
        int decimalDigit = 0;
        for (int i = num.length() - 1; i >= 0; i--) {
            for (int j = 0; j < baseChars.length; j++) {
                if (baseChars[j].equals(num.substring(i,i+1))) {
                    decimalDigit = j;
                }
            }
            sum += decimalDigit * (Math.pow(base1, power));
            power++;
        }
        return sum;
    }
    
    // Converts a positive number written in decimal to the specified base
    public static String decimalToBase(int base, int num) {
        
        // Ensures that the starting base is positive, returns an error otherwise
        if (base < 1) {
            return "Error: new base must be a positive number";
        }
        
        String result = "";
        
        // Prevents an infinite loop from occurring on line 92 if base is 1
        if (base == 1) {
            for (int i = 1; i <= num; i++) {
                result += "0";
            }
            return result;
        }
        
        int power = 0;
        
        while (Math.pow(base,power + 1) <= num) {
            //System.out.println("power = " + power);
            power++;
        }
        
        while (power >= 0) {
            int baseDigit = num / ((int) Math.pow(base,power));
            num -= (num / ((int) Math.pow(base,power))) * ((int) Math.pow(base,power));
            result = result + baseChars[baseDigit];
            power--;
        }
        return result;
    }
}
