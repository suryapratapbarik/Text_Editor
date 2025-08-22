package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;
import utils.CommonUtils;

@Getter
@AllArgsConstructor
@ToString
public class CharFlyweight {

    private char ch;
    private String fontName;
    private int fontSize;
    private boolean bold;
    private boolean italic;

}
