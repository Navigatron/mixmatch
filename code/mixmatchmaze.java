// Import many things to make a window
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.canvas.*;
import javafx.scene.layout.Pane;
import javafx.animation.AnimationTimer;
//
import java.util.ArrayList;

// Extends Application means that this Blueprint is an add-on to the Application blueprint.
public class mixmatchmaze extends Application{

    // Settings
    static final int WIDTH = 800;
    static final int HEIGHT = 600;

    // This is a blueprint to make a Game.
    static class Game extends Canvas{

        // Settings
        Color backgroundColor = Color.BLACK;
        double playerSpeed = 1;

        // This is where we will store the things in the game.
        ArrayList<Rectangle> world = new ArrayList<Rectangle>();

        // The camera, used to draw the world to the screen.
        Camera camera;

        // The Player, just a Rectangle.
        Rectangle player;

        // What follows is Defenitions for things, like what exactly is a Camera?

        // This is just a container for some variables.
        static class Keys{
            static boolean w = false;
            static boolean a = false;
            static boolean s = false;
            static boolean d = false;
        }

        // This defines a pair of values
        class Point{
            double x;
            double y;
            public Point(double x, double y){
                this.x = x;
                this.y = y;
            }
        }

        // This Blueprint defines a Rectangle.
        class Rectangle{
            Point position;
            Point size;
            Color color = Color.WHITE;
            public Rectangle(double x, double y, double w, double h){
                this.position = new Point(x, y);
                this.size = new Point(w, h);
            }
            public Rectangle(double x, double y, double w, double h, Color color){
                this(x, y, w, h);
                this.color = color;
            }
        }

        // This is what makes a Camera.
        class Camera{
            // Camera Position
            Point position;
            public Camera(double x, double y){
                this.position = new Point(x, y);
            }
            public Camera(Point position){
                this.position = position;
            }
            public Camera(){
                this(0, 0);
            }
            // The camera can turn world points into screen points. This is used for drawing.
            public Point worldToScreen(Point point){
                return new Point(
                    WIDTH/2-(this.position.x-point.x),
                    HEIGHT/2+(this.position.y-point.y)
                );
            }
        }


        // Make the Game
        public Game(){
            // setup the canvas, which shows the game.
            super(WIDTH, HEIGHT);
            // Build the World
            buildWorld();
            // Activate Key Listeners
            keyListen();
            // Start the game loop
            gameLoop();
        }

        private void buildWorld(){
            // Create the Camera and Player
            player = new Rectangle(0, 0, 50, 50, Color.GREEN);
            camera = new Camera();
            // Add the player to the world
            world.add(player);
            // Add a bunch of Blocks to the world.
            world.add(new Rectangle(-50, -50, 100, 25));
            world.add(new Rectangle(200, 100, 200, 25));
        }

        public void gameLoop(){
            // This will run over and over again
            new AnimationTimer(){
                public void handle(long currentNanoTime){
                    // Step One - Do Physics
                    physics();
                    // Step Two - Draw.
                    draw();
                }
            }.start();
        }

        private void physics(){
            if(Keys.w){
                player.position.y += playerSpeed;
            }
            if(Keys.a){
                player.position.x -= playerSpeed;
            }
            if(Keys.s){
                player.position.y -= playerSpeed;
            }
            if(Keys.d){
                player.position.x += playerSpeed;
            }
        }

        private void draw(){
            // Get the 'Graphics Context' so we can draw on it.
            GraphicsContext gc = this.getGraphicsContext2D();
            // Clear the Screen.
            gc.setFill(backgroundColor);
            gc.fillRect(0,0,WIDTH,HEIGHT);
            // Draw all the things in the world
            for(Rectangle thing : world){
                gc.setFill(thing.color);
                Point location = camera.worldToScreen(thing.position);
                gc.fillRect(
                    location.x,
                    location.y,
                    thing.size.x,
                    thing.size.y
                );
            }
        }

        private void keyListen(){
            this.setOnKeyPressed(e -> {
                switch(e.getCode()){
                    case W:
                        Keys.w = true;
                        break;
                    case A:
                        Keys.a = true;
                        break;
                    case S:
                        Keys.s = true;
                        break;
                    case D:
                        Keys.d = true;
                        break;
                }
            });
            this.setOnKeyReleased(e -> {
                switch(e.getCode()){
                    case W:
                        Keys.w = false;
                        break;
                    case A:
                        Keys.a = false;
                        break;
                    case S:
                        Keys.s = false;
                        break;
                    case D:
                        Keys.d = false;
                        break;
                }
            });
        }

    }

    // This runs to create the window.
    public void start( Stage stage ) {
        // Make the Game
        Game game = new Game();
        game.setFocusTraversable(true);
        // Make a Panel
        Pane root = new Pane();
        root.getChildren().add(game);
        // Make a Scene
        Scene scene = new Scene(root);
        // Setup and show the Stage - AKA the window.
        stage.setScene(scene);
        stage.setTitle("MixMatchMaze");
        stage.setMinWidth(WIDTH);
        stage.setMinHeight(HEIGHT);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
