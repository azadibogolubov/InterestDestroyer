import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class Driver 
{
	public static double rate;
	public static double principal;
	public static int time;
	public static double simple_interest;
	public static double compound_interest;
	public static NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US);
	public static DecimalFormat df = new DecimalFormat("#.##");
	
	public static void main(String args[])
	{
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter principal");
		try { principal = scanner.nextDouble(); }
		catch (InputMismatchException e) 
		{ 
			System.out.println("Invalid input"); 
			scanner.nextLine(); 
		}
		
		System.out.println("Enter number of months");
		try { time = scanner.nextInt(); }
		catch (InputMismatchException e) 
		{ 
			System.out.println("Invalid input"); 
			scanner.nextLine(); 
		}

		System.out.println("Enter interest rate: (e.g. 5.8 for 5.8%)");
		try { rate = scanner.nextDouble(); }
		catch (InputMismatchException e) 
		{ 
			System.out.println("Invalid input"); 
			scanner.nextLine(); 
		}
		scanner.close();
		
		System.out.println("Principal: " + n.format(principal));
		System.out.println("Interest Rate: " + df.format(rate) + "%");
		System.out.println("Time: " + df.format(time) + " months");
		
		simple_interest = principal * (rate / 100) * (time / 12);
		System.out.println("Simple interest: " + n.format(simple_interest));

		compound_interest = principal * Math.pow((1+ (rate / 100)),(time / 12));
		System.out.println("Compound interest: " + n.format(compound_interest));
	}
}