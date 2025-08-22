package repositories;

import lombok.Getter;
import models.CharFlyweight;
import models.TextRow;

import java.util.ArrayList;
import java.util.List;

@Getter
public class TextFile {

    private List<TextRow> rowList;

    public TextFile() {
        this.rowList = new ArrayList<>();
    }
}
