package com.company;

public class task1 {


    private static String itoBase(int nb, String base)
    {   String result ="";
        int len = base.length();
        while (nb > 0)
        {
            result = base.charAt(nb%len) + result;
            nb =nb/len;
        }
        return result;
    }
    private static String itoBase(String nb, String baseSrc, String baseDst)
    {
        int lenSrc = baseSrc.length();
        int length = nb.length();
        int base = 1;
        int decimal = 0;
        for (int i = length-1; i >= 0; i--)
        {
            decimal = decimal + Character.getNumericValue(nb.charAt(i))*base;
            //System.out.println("decimal " + decimal );
            //System.out.println("base " + base );
            base = base*lenSrc;

        }
        return task1.itoBase(decimal,baseDst);
    }

    private static boolean isNumber(String s)
    {
        for (int i = 0; i < s.length(); i++)
            if (Character.isDigit(s.charAt(i)) == false)
                return false;

        return true;
    }

    private static void Usage()
    {
        System.out.println("Incorrect number of arguments or wrong input of arguments. " + System.lineSeparator() +
                "First argument decimal number, second - alphabet of desired numeral system or " + System.lineSeparator() +
                "First argument number,  second - alphabet of given number's numeral system, third -  alphabet of " +
                System.lineSeparator() + "desired numeral system");

    }

    public static void main(String[] args)
    {
        switch (args.length)
        {
            case 2:
                if (isNumber(args[0]))
                    System.out.println(task1.itoBase(Integer.parseInt(args[0]), args[1]));
                else Usage();
                break;
            case 3:
                System.out.println(task1.itoBase(args[0], args[1], args[2]));
                break;
            default:
                Usage();
                break;
        }
    }
}
