import java.util.Arrays;


public class Recursion {
    private int count = 0;
    private int number;


    public int printAllNumberToCurrentNumber(int number) {

        count++;
        if (count == number) {
            return number;
        }
        System.out.println(count);
        return printAllNumberToCurrentNumber(number);
    }

    public int sum(int number) {
        this.number = number;
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
        char[] chars = str.toCharArray();
        char[] revereseСhars = new char[chars.length];
        for (int i = chars.length - 1, j = 0; i > -1; i--, j++) {
            revereseСhars[j] = chars[i];
        }
        if (chars.length == 1 || chars[0] == revereseСhars[0]) {
            return true;
        }
        if (chars[0] == revereseСhars[0]) {
            Arrays.copyOfRange(chars, 1, chars.length);
            Arrays.copyOfRange(revereseСhars, 1, revereseСhars.length);
            checkPalindrome(str);
        } else {
            return false;
        }
        return true;
    }
}
