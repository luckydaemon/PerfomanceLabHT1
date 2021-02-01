package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class task2 {

    static double sphereRadius = 0.0;
    static double[] sphereCenter = {0.0,0.0,0.0};
    static double[][] linePoints = {{0.0,0.0,0.0},{0.0,0.0,0.0}};
    static double a = 0.0, b = 0.0, c = 0.0;

    private static void extractCoordinates(String str)
    {
        int lineStartsAt = str.indexOf("line")+8;
        int firstPointEndsAt = str.indexOf("]",lineStartsAt);
        int secondPointStartsAt = firstPointEndsAt+4;
        int secondPointEndsAt = str.indexOf("]",secondPointStartsAt);
        String strPoint1[] = str.substring( lineStartsAt,firstPointEndsAt).split(",\\s");
        String strPoint2[] = str.substring( secondPointStartsAt,secondPointEndsAt).split(",\\s");
        String strCenter[] = str.substring(str.indexOf("center")+9, str.indexOf("]",str.indexOf("center")+9)).split(",\\s");
        for (int i = 0; i < strCenter.length;i++)
        {
            sphereCenter[i] = Double.parseDouble(strCenter[i]);
            linePoints[0][i] = Double.parseDouble(strPoint1[i]);
            linePoints[1][i] = Double.parseDouble(strPoint2[i]);
        }
        int radiusStartsAt = str.indexOf("radius")+8;
        int radiusEndsAt = 0;
        if (str.indexOf(",",radiusStartsAt ) > str.indexOf("}",radiusStartsAt ))
            radiusEndsAt = str.indexOf("}",radiusStartsAt );
        else
            radiusEndsAt = str.indexOf(",",radiusStartsAt );
        String strRadius = str.substring(radiusStartsAt ,radiusEndsAt);
        sphereRadius = Double.parseDouble(strRadius);
    }

    private static void calculateParameters() {
        a = Math.pow((linePoints[1][0] - linePoints[0][0]), 2) + Math.pow((linePoints[1][1] - linePoints[0][1]), 2)
                + Math.pow((linePoints[1][2] - linePoints[0][2]), 2);
        b = -2 * ((linePoints[1][0] - linePoints[0][0]) * (sphereCenter[0] - linePoints[0][0]) +
                (linePoints[1][1] - linePoints[0][1]) * (sphereCenter[1] - linePoints[0][1]) +
                (linePoints[1][2] - linePoints[0][2]) * (sphereCenter[2] - linePoints[0][2]));
        c = Math.pow((sphereCenter[0] - linePoints[0][0]), 2) + Math.pow((sphereCenter[1] - linePoints[0][1]), 2)
                + Math.pow((sphereCenter[2] - linePoints[0][2]), 2) - Math.pow(sphereRadius, 2);
        //System.out.println(a);
    }
    private static boolean checkIntersection()
    {
        calculateParameters();
        if ((Math.pow(b,2) - 4*a*c< 0) || ( a == 0.0))
            return false;
        else
            return true;
    }

    private static void calculatePoint()
    {
        double tplus = (-b + Math.sqrt(Math.pow(b,2) - 4*a*c))/(2*a);
        double[][] result = {{0.0,0.0,0.0},{0.0,0.0,0.0}};
        if (tplus == 0)
        {
            result[0][0] = linePoints[0][0];
            result[0][1] = linePoints[0][1];
            result[0][2] = linePoints[0][2];
            for ( int i = 0; i < 3; i++)
                System.out.println(result[0][i]);
        }
        else
        {
            double tminus = (-b - Math.sqrt(Math.pow(b, 2) - 4 * a * c)) / (2 * a);
            result[0][0] = linePoints[0][0] + (linePoints[1][0] - linePoints[0][0]) * tplus;
            result[0][1] = linePoints[0][1] + (linePoints[1][1] - linePoints[0][1]) * tplus;
            result[0][2] = linePoints[0][2] + (linePoints[1][2] - linePoints[0][2]) * tplus;
            result[1][0] = linePoints[0][0] + (linePoints[1][0] - linePoints[0][0]) * tminus;
            result[1][1] = linePoints[0][1] + (linePoints[1][1] - linePoints[0][1]) * tminus;
            result[1][2] = linePoints[0][2] + (linePoints[1][2] - linePoints[0][2]) * tminus;
            for ( int i = 0; i < 3; i++)
                System.out.println(result[0][i]);
            for ( int i = 0; i < 3; i++)
                System.out.println(result[1][i]);
        }
    }


    public static void main(String[] args)
    {
        String data ="";
        try {
            File f = new File(args[0]);
            Scanner reader = new Scanner(f);
            while (reader.hasNextLine()) {
                data = reader.nextLine();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
        extractCoordinates(data);
        if(!checkIntersection())
            System.out.println("Коллизий не найдено");
        else calculatePoint();
    }
}
