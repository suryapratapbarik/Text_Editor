package service;

import factory.CharFlyweightFactory;
import lombok.Getter;
import models.CharFlyweight;
import models.TextRow;
import repositories.TextFile;

@Getter
public class TextFileManager {

    private final TextFile textFile;
    private final CharFlyweightFactory charFlyweightFactory;

    public TextFileManager(TextFile textFile, CharFlyweightFactory factory) {
        this.textFile = textFile;
        this.charFlyweightFactory = factory;
    }

    public boolean addCharacter(int row, int column, char ch, String fontName, int fontSize, boolean isBold, boolean isItalic) {
        while(row >= this.textFile.getRowList().size()) {
            this.textFile.getRowList().add(new TextRow());
        }
        CharFlyweight charFlyweight = charFlyweightFactory.getFlyweight(ch, fontName, fontSize, isBold, isItalic);
        return textFile.getRowList().get(row).add(column, charFlyweight);
    }

    public String getStyle(int row, int col) {

        if (!validateRowCol(row, col)) return "";
        return textFile.getRowList().get(row).getFlyweightList().get(col).toString();
    }

    public String deleteCharacter(int row, int col) {
        if (!validateRowCol(row, col)) {
            System.out.println("Delete operation failed!");
            return "";
        }
        CharFlyweight removedElement = textFile.getRowList().get(row).getFlyweightList().remove(col);
        return removedElement.toString();
    }

    private boolean validateRowCol(int row, int col) {
        if (row<0 || row >= textFile.getRowList().size()
                || col < 0 || col >= textFile.getRowList().get(row).getFlyweightList().size()) {
            System.out.println("This index is out of bounds");
            return false;
        }
        return true;
    }
}
