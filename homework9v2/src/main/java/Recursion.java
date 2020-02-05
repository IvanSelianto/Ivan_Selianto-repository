import java.util.Arrays;


public class Recursion {


    public String printAllNumberToCurrentNumber(int number) {

        if (number == 1) {
            return "1";
        }
        return printAllNumberToCurrentNumber(number - 1) + " " + number;
    }

    public int sum(int number) {

        int[] ints = intToArray(number);
        if (ints.length == 1) {
            return ints[0];
        }


        return ints[0] + sum(arrayToInt(Arrays.copyOfRange(ints, 1, ints.length)));

    }

    private int[] intToArray(int number) {
        String stringNumber = Integer.toString(number);
        int[] intArray = new int[stringNumber.length()];
        for (int i = 0; i < stringNumber.length(); i++) {
            intArray[i] = Character.getNumericValue(stringNumber.charAt(i));
        }
        return intArray;
    }

    private int arrayToInt(int[] array) {
        return Arrays.stream(array).reduce((num1, num2) -> Integer.parseInt(num1 + "" + num2)).getAsInt();
    }

    public static boolean checkPalindrome(String str) {
        if (str.length() == 1) {
            return true;
        }
        char[] chars = str.toCharArray();

        if (chars[0] == chars[chars.length - 1]) {
            str = new String(Arrays.copyOfRange(chars, 1, chars.length - 1));
            return checkPalindrome(str);
        }
        return false;
    }

}
