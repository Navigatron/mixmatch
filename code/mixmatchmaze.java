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
        double playerSpeed = 200; // Pixels Per Second
        double jumpForce = 5;
        double gravity = 9.8;
        boolean canJump = true;

        // This is where we will store the things in the game.
        ArrayList<Rectangle> world = new ArrayList<Rectangle>();

        // The camera, used to draw the world to the screen.
        Camera camera;

        // The Player, just a Rectangle.
        PhysicsRectangle player;

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

        class PhysicsRectangle extends Rectangle{
            Point velocity;
            double mass;
            public PhysicsRectangle(double x, double y, double w, double h, double mass){
                super(x, y, w, h);
                this.mass = mass;
                this.velocity = new Point(0,0);
            }
            public PhysicsRectangle(double x, double y, double w, double h, Color color, double mass){
                super(x, y, w, h, color);
                this.mass = mass;
                this.velocity = new Point(0,0);
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
                    HEIGHT/2-(this.position.y-point.y)
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
            player = new PhysicsRectangle(0, 0, 50, 50, Color.GREEN, 1);
            camera = new Camera();
            // Add the player to the world
            world.add(player);
            // Add a bunch of Blocks to the world.
            world.add(new Rectangle(-1000, 1000, 2000, 25));
            world.add(new Rectangle(-1000, -1000, 25, 2000));
            world.add(new Rectangle(1000, -1000, 25, 2000));
            world.add(new Rectangle(-1000, -1000, 2000, 25));
            for (int i=-1000; i<1000; i+=250 ) {
                world.add(new Rectangle(i, -i, 200, 25));
            }
        }

        public void gameLoop(){
            // This will run over and over again
            new AnimationTimer(){
                long lastFrame = System.nanoTime();
                public void handle(long currentNanoTime){
                    // Calculate the Time Difference
                    double delta = ((double)currentNanoTime - (double)lastFrame)/(double)1000000000l;
                    lastFrame = currentNanoTime;
                    // Step One - Do Physics
                    physics(delta);
                    // Step Two - Draw.
                    draw();
                }
            }.start();
        }

        private void physics(double delta){
            // Player Input
            if(Keys.w && canJump){
                // player.position.y -= playerSpeed*delta;
                player.velocity.y = -jumpForce;
                canJump = false;
            }
            if(Keys.a){
                player.position.x -= playerSpeed*delta;
            }
            // if(Keys.s){
            //     player.position.y += playerSpeed*delta;
            // }
            if(Keys.d){
                player.position.x += playerSpeed*delta;
            }

            // Gravity
            for(Rectangle thing : world){
                if(thing instanceof PhysicsRectangle){
                    ((PhysicsRectangle)thing).velocity.y += gravity*delta;
                    ((PhysicsRectangle)thing).position.y += ((PhysicsRectangle)thing).velocity.y;
                }
            }

            // collision Detection - test PhysicsThings against Everything
            for (Rectangle thing : world){
                if(thing instanceof PhysicsRectangle){
                    for(Rectangle other : world){
                        if(thing != other){
                            if(
                                thing.position.x < other.position.x + other.size.x &&
                                thing.position.x + thing.size.x > other.position.x &&
                                thing.position.y < other.position.y + other.size.y &&
                                thing.position.y + thing.size.y > other.position.y
                            ){
                                // thing has physics, and is colliding with something else.
                                if(other instanceof PhysicsRectangle){
                                    // Physics collision - Complicated!
                                    System.out.println("This is hard so I'll write it later");
                                }else{
                                    // Physics thing is hitting a non-moving other.
                                    if(thing == player){
                                        canJump = true;
                                    }
                                    // Remove Velocity
                                    ((PhysicsRectangle)thing).velocity.y = 0;
                                    // Calculate how far to move in each direction to stop collision
                                    double dxl = (other.position.x-thing.size.x)-thing.position.x;
                                    double dxr = (other.position.x+other.size.x)-thing.position.x;
                                    double dyu = (other.position.y-thing.size.y)-thing.position.y;
                                    double dyd = (other.position.y+other.size.y)-thing.position.y;
                                    // Condense: Keep the smallest X change and Y change.
                                    double dx = Math.abs(dxl) < Math.abs(dxr) ? dxl : dxr;
                                    double dy = Math.abs(dyu) < Math.abs(dyd) ? dyu : dyd;
                                    // Convert smallest X or Y into the offset vector
                                    Point offset = Math.abs(dx) < Math.abs(dy) ? new Point(dx, 0) : new Point(0, dy);
                                    // Move the thing out of the collision
                                    thing.position.x += offset.x;
                                    thing.position.y += offset.y;
                                }
                            }
                        }
                    }
                }
            }

            // Make the Camera follow the player.
            camera.position = player.position;

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
