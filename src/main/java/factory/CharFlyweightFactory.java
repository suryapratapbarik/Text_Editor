package factory;

import models.CharFlyweight;
import utils.CommonUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CharFlyweightFactory {

    private static final Map<String, CharFlyweight> map = new ConcurrentHashMap<>();

    private static CharFlyweightFactory SINGLETON_INSTANCE;

    public static CharFlyweightFactory getSingletonInstance() {
        if (SINGLETON_INSTANCE == null) {
            synchronized (CharFlyweightFactory.class) {
                SINGLETON_INSTANCE = new CharFlyweightFactory();
            }
        }
        return SINGLETON_INSTANCE;
    }

    public CharFlyweight getFlyweight(char ch, String fontName, int fontSize, boolean isBold, boolean isItalic) {

        String key = CommonUtils.getFlyweightString(ch,fontName,fontSize,isBold,isItalic);
        return map.computeIfAbsent(key, k -> new CharFlyweight(ch,fontName,fontSize,isBold,isItalic));
    }
}
