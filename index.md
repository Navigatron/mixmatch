# Mixmatch's Java Class

One week of intense java action. Below you'll find **sample code** I've written as well as the **Hall of Fame**, which includes code from current and past students. Further down you'll find a **Code Reference**, with little snippets that are easy to copy/paste.

- [Sample Code](#sample)
- [Hall of Fame](#hof)
- [Code Reference](#reference)

### <a name="sample"></a>Mixmatch's Sample Code
---

The below project will:  
- Create a Window.
- Show all the rectangles defined in the *buildWorld* method.
- Use the Keyboard to control the Green Square.
- Detect collisions between rectangles.

Challenges:
- Change the color of the player square.
- Change the default color of all rectangles.
- Make the camera follow the player. Hint:  `camera.position = player.position;`, somewhere in the *physics* method.

[Get the code Here.](./code/mixmatchmaze.java)

### <a name="hof"></a>Hall of Fame
---

#### Spinning Blue Sphere

This *processing project* creates a window with a blue sphere. Through keyboard controls, the user can rotate the sphere. If you need help setting up a *processing project*, just ask!

[Get the code Here.](./code/ProccesingProject.java)

#### A Bowl of Rice

This *processing project* Will comfortably simulate one thousand or more rice on the screen. With the mouse, the user can push or pull the rice around.

- The code for rice-on-rice collisions is present, though not activated
- Try changing the force variable to change the entire behaviour of the physics!

[Get the code Here.](./code/ABowlofRice.java)

### <a name="reference"></a>Code Reference
---

#### The Basic Blueprint

A class is a *blueprint*, it defines what makes a thing. **main** is the point where your program starts.

*Make sure your class name is the same as the .java file name.*

```java
public class filename{
    public static void main(String[] args){
        // Your Code Here
    }
}
```

#### Printing to the Console

`print()` will not move to a new line after printing, while `println()` will.

```java
System.out.print("Welcome ");
System.out.print("To ");
System.out.println("Camp!");
System.out.print("Hello!");
```

Output:

```
Welcome To Camp!
Hello!
```

Variables can also be printed.

```Java
int number = 5;
double decimal = 3.14;
System.out.println("Number is "+number+", and decimal is "+decimal+".");
```

Output: `Number is 5, and decimal is 3.14.`

#### Using Variables

The basic format for making a variable is `Type name = thing`.

Type can be anything from `int` to `double` to `Scanner` to `GameCamera` if you're making your own data types as classes.

```java
int number = 5;
double pi = 3.1415926;
String name = "Jetpack Jerome";
Scanner readyread = new Scanner(System.in);
```

#### Using Scanner to get user feedback: Menu Sample Code.

To use the Scanner object, we have to `import` it. Then, we can create a `Scanner` object and use it to get input.

The following example uses `println` to show a menu, and then gets the user's choice.

```Java
import java.util.Scanner;
public class MenuExample{
    public static void main(String[] args) {
        Scanner readyread = new Scanner(System.in);
        System.out.println("1.) Option 1");
        System.out.println("2.) Option 2");
        System.out.println("3.) Option 3");
        System.out.print("Choice: ");
        int response = readyread.nextInt();
        System.out.println("You chose Option "+response+".");
    }
}
```

The two scanner methods we'll use most often are `nextInt()` to get a number and `nextLine()` to get a String. If you need a decimal, use `nextDouble()`.

*Note:* If you use `nextInt()` and then `nextLine()`, the newline from the number may trigger the `nextLine()` early. If you encounter this, just ask, and I'll help you fix it.

#### If Statements

`if` statements allow us to make decisions based on user input. The below example builds off of the above Menu example.

```Java
if( response == 4 ){
    System.out.println("Secret Option 4! Bonus mode unlocked!");
}
```

If statements can be chained, and used in conjunction with `else`.
```Java
if(response==1){
    // code for 1
}else if(response==2){
    // code for 2
}else{
    // User selected invalid option
}
```
