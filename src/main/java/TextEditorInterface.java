public interface TextEditorInterface {

    /**
     * start application
     */
    void init();

    /**
     * - Adds character at the given row and column.
     * - Existing characters are pushed to right.
     * - e.g. row-0 is "abcd" ,
     *     after addCharacter(0,2,'p',"Tahoma",12,false, true)
     *     row-0 becomes "abpcd"
     * - 0<=row,column<=1000
     * - 1<=row*column<=1000,000
     * - ch will be from 'a'-'z', '0'-'9', ','(comma), ' '(space)
     * - Add more rows if currently there are fewer rows than row
     * - If there are fewer columns in row than column then,
     *     simply append character after the rightmost column.
     * @param row
     * @param column
     * @param ch
     * @param fontName
     * @param fontSize
     * @param isBold
     * @param isItalic
     * @return true if success
     */
    boolean addCharacter(int row, int column, char ch, String fontName, int fontSize, boolean isBold, boolean isItalic);

    /**
     * - returns all characters which are in row as string
     * - return example : "abcd"
     * - if there are not characters added in row then
     *     empty string "" is returned.
     * @param row
     * @param col
     * @return
     */
    String getStyle(int row, int col);

    /**
     * - returns true if there is a character at row,col and
     * it is successfully deleted
     * - returns false otherwise.
     * - characters to the left of column are moved 1 place left.
     * e.g. row-0 is "abcde", and after deleteCharacter(0, 2)
     * it becomes "abde"
     *
     * @param row
     * @param col
     * @return
     */
    String deleteCharacter(int row, int col);
}
