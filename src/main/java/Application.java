import factory.CharFlyweightFactory;
import repositories.TextFile;
import service.TextFileManager;

public class Application implements TextEditorInterface{

    private TextFile textFile;
    private TextFileManager textFileManager;

    public static void main(String args[]) {
        Application application =new Application();
        application.init();
        application.simulate();
    }

    @Override
    public void init() {
        textFile = new TextFile();
        textFileManager = new TextFileManager(textFile, CharFlyweightFactory.getSingletonInstance());
    }

    @Override
    public boolean addCharacter(int row, int column, char ch, String fontName, int fontSize, boolean isBold, boolean isItalic) {

        return textFileManager.addCharacter(row,column, ch, fontName, fontSize, isBold, isItalic);
    }

    @Override
    public String getStyle(int row, int col) {
        return textFileManager.getStyle(row, col);
    }

    @Override
    public String deleteCharacter(int row, int col) {
        return textFileManager.deleteCharacter( row,  col);
    }

    /**
     * A simulation to test the text editor functionality and demonstrate the Flyweight pattern.
     */
    private void simulate() {
        System.out.println("--- Starting Simulation ---");

        // Add characters with the same style.
        // The FlyweightStore should only create ONE object for this style.
        System.out.println("\nAdding 'H', 'e', 'l', 'l', 'o' with font 'Arial', size 12.");
        addCharacter(0, 0, 'H', "Arial", 12, false, false);
        addCharacter(0, 1, 'e', "Arial", 12, false, false);
        addCharacter(0, 2, 'l', "Arial", 12, false, false);
        addCharacter(0, 3, 'l', "Arial", 12, false, false);
        addCharacter(0, 4, 'o', "Arial", 12, false, false);

        // Add characters with a different style.
        // A new Flyweight object will be created for this "Bold" style.
        System.out.println("\nAdding ' W' with font 'Arial', size 12, Bold.");
        addCharacter(0, 5, ' ', "Arial", 12, true, false);
        addCharacter(0, 6, 'W', "Arial", 12, true, false);

        // Add characters with the first style again.
        // No new Flyweight objects should be created; the existing one is reused.
        System.out.println("\nAdding 'orld' with font 'Arial', size 12.");
        addCharacter(0, 7, 'o', "Arial", 12, false, false);
        addCharacter(0, 8, 'r', "Arial", 12, false, false);
        addCharacter(0, 9, 'l', "Arial", 12, false, false);
        addCharacter(0, 10, 'd', "Arial", 12, false, false);

        // Add characters with a different style.
        System.out.println("\nAdding 'N', 'e', 'W', ' ', 'l', 'i', 'N', 'e' with font 'Arial', size 13, But resusing earlier letters");
        addCharacter(1, 0, 'N', "Arial", 13, false, true);
        addCharacter(1, 1, 'e', "Arial", 12, false, false); //reuse
        addCharacter(1, 2, 'W', "Arial", 12, true, false); //reuse
        addCharacter(1, 3, ' ', "Arial", 12, true, false); //reuse
        addCharacter(1, 4, 'l', "Arial", 12, false, false);
        addCharacter(1, 5, 'i', "Arial", 13, false, true);
        addCharacter(1, 6, 'N', "Arial", 13, false, true); //reuse
        addCharacter(1, 7, 'e', "Arial", 12, false, false); //reuse

        // Let's check the style of a few characters
        System.out.println("\n--- Checking Styles ---");
        System.out.println("Style at (0, 1) ('e'): " + getStyle(0, 1));
        System.out.println("Style at (0, 6) ('W'): " + getStyle(0, 6));
        System.out.println("Style at (0, 8) ('r'): " + getStyle(0, 8));

        // Let's see the state of our text file visually
        System.out.println("\n--- Current Document State ---");
        printDocument();


        // Delete a character
        System.out.println("\n--- Deleting Characters ---");
        System.out.println("Deleting character at (0, 6) ('W')...");
        System.out.println(deleteCharacter(0, 6));
        System.out.println("Deleting character at (1, 3) (' ')...");
        System.out.println(deleteCharacter(1, 3));
        System.out.println("Deleting character at (1, 7) ('#ERROR')...");
        System.out.println(deleteCharacter(1, 7));

        System.out.println("\nDocument after deletion:");
        printDocument();

        // Try to get style of a deleted character (should fail)
        System.out.println("\nTrying to get style of deleted character at (0, 10):");
        System.out.println(getStyle(0, 10)); // This index is now out of bounds

        System.out.println("\n--- Simulation Finished ---");
    }

    /**
     * Helper method to print the current state of the document.
     */
    private void printDocument() {
        for (int i = 0; i < textFile.getRowList().size(); i++) {
            StringBuilder rowText = new StringBuilder();
            textFile.getRowList().get(i).getFlyweightList().forEach(fw -> rowText.append(fw.getCh()));
            System.out.println("Row " + i + ": " + rowText.toString());
        }
    }
}
