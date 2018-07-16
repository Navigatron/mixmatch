# Information In

Scanner allows your program to recieve input from the user when running. This enables you to make your programs interactive.

To use `println`, one must use `System.out.println`. To use `Scanner`, you'll have to type `java.util.Scanner`. However, there's a trick.

#### Importing Scanner

The following line of code should go **above** the class declaration, and below the package declaration (if you have one).

```java
import java.util.Scanner;
```

This allows us to use just `Scanner`, rather than `java.util.Scanner`.

#### Creating a Scanner Variable

Just like in the Variables section, you'll need a type and a name.
Initialize it to a new Scanner, and give it `System.in` (the Keyboard) to read from.

```java
Scanner thisIsMyScanner;
thisIsMyScanner = new Scanner(System.in);
```

#### Using your Scanner Variable

Your brand new Scanner object comes with some built-in functions. `nextInt()` will get an integer, and `nextLine()` will get a String. **Note**: To avoid the *Double Enter Problem*, always use a `nextLine()` after a `nextInt()`.

Consider the following code.
```java
Scanner input;
int choice;
input = new Scanner(System.in);
choice = input.nextInt();
input.nextLine(); // Avoid the Double Enter Problem
```

#### Examples

The following asks the user for their name, then says it back.

```java
import java.util.Scanner;
public class NameyBoi{
    public static void main(String[] args){
        System.out.print("Enter your name here: ");
        Scanner input = new Scanner(System.in);
        String name = input.nextLine();
        System.out.println("Hello " + name + "! Nice to meet you.");
    }
}
```

The following code acts as the start for a basic menu system. For more, see the next module about If statements and making decisions.

```java
import java.util.Scanner;
public class MenuExample{
    public static void main(String[] args) {
        System.out.println("1.) Jetpack Jerome");
        System.out.println("2.) Exit");
        System.out.print("Choice: ");
        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();
        input.nextLine(); // Avoid the Double Enter Problem
        System.out.println("You chose option " + choice + ".");
    }
}
```
