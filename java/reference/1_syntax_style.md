# Syntax and Style

#### Comments

double slashes `//` indicate a comment in the code. Comments don't run or execute, they're very useful for keeping things organized.

#### Code blocks
- code surrounded by {}
- Match Braces!
- Things inside a code block should be indented

```java
// Code Block Here
{
    // Things in the Code Block are Indented
    // What if there's another one?
    {
        // Indent this more also
        // Note how the closing one is below the opening.
        // non-matching braces is the most common mistake.
    }
}
```

#### Code Style

Braces on the same line or not? Your choice.

Both of the following are perfectly okay.

```java
if(true){
    // Opening after if
}

if(true)
{
    // Opening on next line
}
```

#### TL;DR

Use comments everywhere. Indent everything inside of a code block. Make sure you open and close your code blocks - no stray curly braces.
