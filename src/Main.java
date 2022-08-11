
import settings.Game;
import settings.GameEnvironment;
import settings.SpriteCollection;


/**
 * Main class - driver code.
 */
public class Main {
    /**
     * Main function to start the program.
     * @param args String[] - ignored.
     */
    public static void main(String[] args) {
        System.out.println("test");
        SpriteCollection sprites = new SpriteCollection();
        GameEnvironment environment = new GameEnvironment();
        Game game = new Game(sprites, environment);
        game.initialize();
        game.run();
    }


}
