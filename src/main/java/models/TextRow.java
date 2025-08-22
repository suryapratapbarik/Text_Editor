package models;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class TextRow {

    private List<CharFlyweight> flyweightList;

    public TextRow() {
        this.flyweightList = new ArrayList<>();
    }

    public boolean add(int column, CharFlyweight charFlyweight) {
        if (column < 0 || column > flyweightList.size()) {
            throw new IllegalArgumentException("Invalid column "+column);
        }
        flyweightList.add(column, charFlyweight);
        return true;
    }
}
