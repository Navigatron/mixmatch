import java.util.Scanner;
public class casino{
    public static void main(String[] args) {
        // Starting Chips
        int chips = 100;
        // Create Scanner
        Scanner scanner = new Scanner(System.in);
        // Keep playing as long as they have chips left.
        while(chips > 0){
            // bet red, black, or green (zero)
            System.out.println();
            System.out.println("You have "+chips+" Chips.");
            // how many chips to bet?
            System.out.println();
            System.out.print("How many chips to bet: ");
            int bet = scanner.nextInt();
            System.out.println("0.) Bet on Zero [1:37]");
            System.out.println("1.) Bet on Odds (black) [1:2]");
            System.out.println("2.) Bet on Evens (red) [1:2]");
            System.out.print("Choice: ");
            int choice = scanner.nextInt();
            // Roulette wheel: Random number 0->36
            System.out.println("Spinning the Wheel...");
            int result = (int) (Math.random()*37);
            System.out.println("Roulette wheel says: "+result);
            // if the user won, give them chips.
            if(result == 0 && choice == 0){
                System.out.println("You've won BIG.");
                chips += bet*37;
            }else if(result%2==1 && choice == 1){
                System.out.println("You bet Odd, result was Odd.");
                chips += bet;
            }else if(result%2==0 && choice == 2){
                System.out.println("You bet Even, result was Even.");
                chips += bet;
            }else{
                System.out.println("You have lost. LOL.");
                chips -= bet;
            }
            // End of While loop, play again.
        }
        System.out.println("Falco's LIT");
    }
}
