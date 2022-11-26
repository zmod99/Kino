import java.util.Scanner;
import java.util.Arrays;


public class Cinema {
    public static int totalIncome = 0;
    public static int currentIncome = 0;

    public static int getTotalIncome(char[][] seats) {
        int firstHalf, secondHalf, totalSeats;
        totalSeats = seats.length * seats[0].length;
        if (totalSeats <= 60) {
            return totalSeats * 10;
        } else {
            firstHalf = (seats.length/2) * seats[0].length;
            secondHalf = totalSeats - firstHalf;
            return (firstHalf * 10 +  secondHalf * 8);
        }
    }

    public static boolean Menu(char[][] seats) {
        Scanner scanner = new Scanner(System.in);
        int seatNumber, rowNumber;
        int totalSeats = seats.length * seats[0].length;
        totalIncome = getTotalIncome(seats);
        System.out.println("1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit");
        int number = scanner.nextInt();

        switch (number) {

            case 1:
                System.out.println("\nCinema:");
                System.out.print("  ");
                for (int i = 1; i <= seats[0].length; i++) {
                    System.out.print(i + " ");
                }

                for (int i = 0; i < seats.length; i++) {
                    System.out.println();
                    System.out.print(i + 1 + " ");
                    for (int j = 0; j < seats[i].length; j++) {
                        System.out.print(seats[i][j] + " ");
                    }
                }
                System.out.println();
                break;
            case 2:
                boolean isRight = false;
                do {
                    System.out.println("\n\nEnter a row number:");
                    rowNumber = scanner.nextInt();
                    System.out.println("Enter a seat number in that row:");
                    seatNumber = scanner.nextInt();
                    if (inBounds(seats, rowNumber, seatNumber)) {
                        if (seats[rowNumber - 1][seatNumber - 1] == 'B') {
                            System.out.println("That ticket has already been purchased!");
                        } else {
                            seats[rowNumber - 1][seatNumber - 1] = 'B';
                            isRight = true;
                        }
                    }


                }
                while (!isRight);
                if (totalSeats <= 60) {
                    System.out.println("Ticket price: $10\n");
                    currentIncome += 10;
                } else {
                    if (rowNumber <= (seats.length) / 2) {
                        System.out.println("Ticket price: $10\n");
                        currentIncome += 10;

                    } else {
                        System.out.println("Ticket price: $8\n");
                        currentIncome += 8;
                    }
                }
                break;
            case 3:
                System.out.println("\nNumber of purchased tickets: " + purchasedSeats(seats));
                System.out.printf("Percentage:%.2f%%\n", (((float) (purchasedSeats(seats)) / totalSeats) * 100));
                System.out.printf("Current income: $%d\n", currentIncome);
                System.out.printf("Total income: $%d\n\n", totalIncome);
                break;
            case 0:
                return false;


        }
        return true;
    }

    public static void main(String[] args) {
        int i;
        int numberOfRows;
        int numberOfSeats;


        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        numberOfRows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row");
        numberOfSeats = scanner.nextInt();

        char[][] seats = new char[numberOfRows][numberOfSeats];
        for (i = 0; i < seats.length; i++) {
            Arrays.fill(seats[i], 'S');
            Arrays.fill(seats[i], 'S');
        }
        while (Menu(seats)) {


        }


    }

    public static int purchasedSeats(char[][] seats) {
        int counter = 0;
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[0].length; j++) {
                if (seats[i][j] == 'B') {
                    counter++;
                }
            }

        }
        return counter;

    }

    public static boolean inBounds(char[][] seats, int index1, int index2) {
        if (index1 > seats.length || index2 > seats[0].length) {
            System.out.println("Wrong input!");
            return false;
        } else if (index1 < 1 || index2 < 1) {
            System.out.println("Wrong input!");
            return false;
        }
        return true;
    }

}
