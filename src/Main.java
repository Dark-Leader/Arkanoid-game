/**
 * Main class - driver code.
 */
public class Main {
    /**
     * Main function to start the program.
     * @param args String[] - ignored.
     */
    public static void main(String[] args) {
        AbstractArtDrawing art = new AbstractArtDrawing(15);
        art.updateDisplay();
    }

}
