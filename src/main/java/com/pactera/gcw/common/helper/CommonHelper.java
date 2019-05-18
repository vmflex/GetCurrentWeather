package com.pactera.gcw.common.helper;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

public class CommonHelper {

    public static void main(String[] args) {
        DecimalFormat df = new DecimalFormat("-123&&^&#,###.###abc%");
        df.getDecimalFormatSymbols().setGroupingSeparator('|');
        char separator = df.getDecimalFormatSymbols().getDecimalSeparator();

        Number raw = -1239877.23;
        String f = df.format(raw);
        Number myNum = null;
        try {
            myNum = df.parse(f);
            Number f2 = df.parse("--123&&^&222,987,723abc%");
            System.out.println("parsed for f2: " + f2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println(f);
        System.out.println("parsed: " + myNum);

        //        DateTimeFormatter f25 = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ssZ");
        //        DateTime dt = DateTime.now();
        //        String dat = dt.toString("yyyy-MM-dd'T'HH:mm:ssZ");
        //        System.out.println(dat);
        //
        //        String s = "/";
        //        int pos = s.indexOf("/");
        //        s = s.substring(pos + 1);
        //
        //        System.out.println(s);

        NumberFormat df2 = NumberFormat.getInstance();
        df2.setMaximumFractionDigits(10);
        df2.setMinimumFractionDigits(0);
        System.out.println(df2.format(raw));
    }

}
