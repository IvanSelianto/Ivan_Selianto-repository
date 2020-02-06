import java.util.Arrays;


public class Recursion {


    public String printAllNumberToCurrentNumber(int number) {

        if (number == 1) {
            return "1";
        }
        return printAllNumberToCurrentNumber(number - 1) + " " + number;
    }

    public int sum(int number) {
        if (number < 10) {
            return number % 10;
        }
        return number % 10 + sum(number / 10);


    }


    public static boolean checkPalindrome(String str) {
        if (str.length() == 1) {
            return true;
        }

        if (str.charAt(0) == str.charAt(str.length() - 1)) {
            return checkPalindrome(str.substring(1, str.length() - 1));
        }

        return false;
    }


}
