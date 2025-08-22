package utils;

import models.CharFlyweight;

public class CommonUtils {

    public static String getFlyweightString(char ch, String fontName, int fontSize, boolean isBold, boolean isItalic) {

        StringBuilder sb = new StringBuilder()
                .append(ch).append("-")
                .append(fontName).append("-")
                .append(fontSize).append("-")
                .append(isBold).append("-")
                .append(isItalic);

        return sb.toString();
    }
}
