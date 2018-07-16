# Variables: Storing Information

Variables let us store information inside our program.

Variables can be printed just like text.

#### Creating a Variable (Declaration)

Variable declarations require a **type** and a **name**. The code below creates a variable that can store a whole number (like 5, or 3, but not 3.5).

```java
int myNumber;
```

There are a large number of built-in types. You'll be able to create your own custom types later on. You can name your variables whatever you want.

```java
double decimalNumber;
String name;
float anotherKindOfDecimal;
CustomType myCustomVar;
Scanner readyMcReadFace;
```

#### Setting a Variable (Initialization)

To set a variable, use `=`. Sometimes you can type out the text form of the data, other times you'll need to create a new *instance* of an object.

```java
decimalNumber = 3.5;
name = "JetpackJerome";
anotherKindOfDecimal = 3.5;
myCustomVar = new CustomType();
readyMcReadFace = new Scanner(System.in);
```

Once you declare (create) a variable, you can use it as many times as you want.

#### Important Things

Step 1 - Declare the Variable.  
Step 2 - Use the variable.

**Remember to Declare your Variables.**

Trick: Declare and initialize your variables at the same time.

```java
String playerName = "Jetpack Jerome";
```
