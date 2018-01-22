package by.htp.controller;

import java.util.Scanner;

public class ConsoleHandler {
	
	@SuppressWarnings("resource")
	public static String readStringFromConsole() {
		Scanner scanner = new Scanner(System.in);
		String res ="";
		try {
			res = scanner.nextLine();
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return res;
	}
	
	public static boolean readYesOrNoAnswerFromCOnsole() {
		Scanner scanner = new Scanner(System.in);
		String answer ="";
		do {
			System.out.println("Enter answer(y/n): ");
			answer = scanner.nextLine();
        }
		while(!answer.equals("y") && !answer.equals("n") );
		if(answer.equals("y")) {
			return true;
		}
		return false;
	}
	
	public static int enterPositiveIntegerValue() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		int value = -1;
		do {
            if ( scanner.hasNextInt() )
            {
                if ( (value = scanner.nextInt())  <= 0)
                {
                    System.out.println("Enter Number above zero!");
                }
                else
                {
                    System.out.println("You have entered " + value);
                }
            }
            else
            {
                System.out.println("Please, enter integer number: ");
                scanner.next();
            }
        }
        while( value <= 0 );
		return value;
	}
}
