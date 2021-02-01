package com.company;

public class task4 {
    public static void main(String[] args)
    {
        boolean isEqualStrings = false, initialCheck = false;
        for (int i = 0; i < args[1].length(); i++)
            if (args[1].charAt(i) == '*')
                initialCheck = true;
            else if (args[0].length() == args[1].length())
                initialCheck = true;
            else
                initialCheck = false;
        if (initialCheck) {
            int length = args[0].length() < args[1].length() ? args[0].length() : args[1].length();
            int currentChar = 0;
            while (!isEqualStrings && currentChar < length) {
                if (args[1].charAt(currentChar) == '*') {
                    isEqualStrings = true;
                    break;
                }
                if (args[0].charAt(currentChar) == args[1].charAt(currentChar)) {
                    if (currentChar == length - 1) {
                        isEqualStrings = true;
                        break;
                    }
                    currentChar++;
                    continue;
                }
            }
        }
        if (isEqualStrings)
            System.out.println("OK");
        else
            System.out.println("KO");
    }
}
