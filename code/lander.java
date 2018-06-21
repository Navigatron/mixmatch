// GUIs need heckin' LOTS of imports.
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.canvas.*;
import javafx.scene.layout.Pane;
import javafx.animation.AnimationTimer;

// Extends Application means that this Blueprint is an add-on to the Application blueprint.
public class lander extends Application{

    // Is space pressed?
    boolean spaceIsPressed = false;

    // Final means these won't change. How big is the screen?
    final int WIDTH = 800;
    final int HEIGHT = 600;

    // Some decimals to keep track of the player.
    double playery = 0;
    double velocity = 0;
    double gravity = 0.01;

    public void GameLogicBro(Canvas canvas){
        // Step One - Keyboard Listeners
        canvas.setOnKeyPressed(e -> { // Key pressed DOWN
            switch(e.getCode()){
                case SPACE:
                    spaceIsPressed = true;
                    break;
                case W:
                    // They're pressing W
                    break;
                case UP:
                    // They're pressing up.
                    // other codes include A, S, D, DOWN, LEFT, etc.
                    break;
            }
        });
        canvas.setOnKeyReleased(e -> { // Key Lifted UP
            switch(e.getCode()){
                case SPACE:
                    spaceIsPressed = false;
            }
        });
        // Get the Graphics thing so we can draw on it.
        GraphicsContext gc = canvas.getGraphicsContext2D();
        // This is hard to explain, basically it just runs many times in a row.
        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {

                // Set color to Black and fill the screen.
                gc.setFill(Color.BLACK);
                gc.fillRect(0,0,WIDTH,HEIGHT);

                // Apply some Physics to the Player
                // Make the player fall down.
                velocity += gravity;
                // But if they're pressing space, go UP.
                if(spaceIsPressed){
                    velocity-=gravity*2;
                }
                playery+=velocity;
                // Make them bounce off the ground.
                if(playery>=HEIGHT-10){
                    playery=HEIGHT-10;
                    velocity *=-0.98;
                }
                // Draw the player (green)
                gc.setFill(Color.GREEN);
                gc.fillRect(WIDTH/2-20, (int)playery-20, 40, 40);
                // If they're pressing space, draw the rocket part.
                if(spaceIsPressed){
                    gc.setFill(Color.RED);
                    gc.fillRect(WIDTH/2-10, (int)playery+10, 20, 20);
                }
            }
        }.start();
    }

    // This runs to create the window.
    public void start( Stage stage ) {
        // Make the Canvas
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        // Configure the Canvas
        canvas.setFocusTraversable(true);
        // Make the Pane to put it in
        Pane root = new Pane();
        // Slam it in there
        root.getChildren().add(canvas);
        // Make the Scene
        Scene scene = new Scene(root);
        // Throw it all onto the stage, pack and ship to the user.
        stage.setScene(scene);
        stage.setTitle("Them blocky bois b Movin'");
        stage.setMinWidth(WIDTH);
        stage.setMinHeight(HEIGHT);
        stage.show();
        // Don't forget to activate the Game Logic.
        GameLogicBro(canvas);
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
