# Getting Information Out

```java
System.out.println("Hello World!");
```

Output: `Hello World!`

#### Understanding Access

When you visit `mail.google.com`, your web browser first asks for `com`. Once it finds `com`, it asks `com` for `google`. Once it finds `google`, it asks `google` for `mail`. Once it finds `mail`, it asks `mail` for the webpage to show you your email.

Java operates in the opposite order.
- Find `System`
- Find `Out` in `System`
- Find `println` in `out`
- Give `println` some stuff to work with, such as `"Hello World!"`

`println` will then put that text onto the console so you can see it.

#### Sample Program

```java
public class CowSaysHello
{
    public static void main(String[] args)
    {
        System.out.println(" ______________                ");
        System.out.println("< Hello World! >                ");
        System.out.println(" --------------                 ");
        System.out.println("        \\   ^__^               ");
        System.out.println("         \\  (oo)\\_______      ");
        System.out.println("            (__)\\       )\\/\\ ");
        System.out.println("                ||----w |       ");
        System.out.println("                ||     ||       ");
    }
}
```

Output:

```
 ______________
< Hello World! >
 --------------
        \   ^__^
         \  (oo)\_______
            (__)\       )\/\
                ||----w |
                ||     ||
```
