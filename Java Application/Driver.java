import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class Driver 
{
	public static double rate;
	public static double principal;
	public static double time;
	public static double payment_amount;
	public static double simple_interest;
	public static double compound_interest;
	public static double total_interest = 0.00f;
	public static double principal_paid;
	public static NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US);
	public static DecimalFormat df = new DecimalFormat("#.##");
	
	public static double amortize(double principal, double rate, double time)
	{
		rate /= 1200;
		return principal * ((rate * Math.pow((1 + rate), time))/(Math.pow((1+rate),time)-1));
	}

	public static void main(String args[])
	{
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter principal");
		/*try { principal = scanner.nextDouble(); }
		catch (InputMismatchException e) 
		{ 
			System.out.println("Usage: Decimal or Integer input");
			scanner.nextLine(); 
		}
		
		System.out.println("Enter number of months");
		try { time = scanner.nextInt(); }
		catch (InputMismatchException e) 
		{ 
			System.out.println("Usage: Decimal or Integer input");
			scanner.nextLine(); 
		}

		System.out.println("Enter interest rate: (e.g. 5.8 for 5.8%)");
		try { rate = scanner.nextDouble(); }
		catch (InputMismatchException e) 
		{ 
			System.out.println("Usage: Decimal or Integer input");
			scanner.nextLine(); 
		}

		System.out.println("Enter payment amount: (e.g. 500.50");
		try { payment_amount = scanner.nextDouble(); }
		catch (InputMismatchException e)
		{
			System.out.println("Usage: Decimal or Integer input");
			scanner.nextLine();
		}
		scanner.close();
		*/
		principal = 100000;
		rate = 5.25;
		time = 360;
		
		System.out.println("Principal: " + n.format(principal));
		System.out.println("Interest Rate: " + df.format(rate) + "%");
		System.out.println("Time: " + df.format(time) + " months");
		
		simple_interest = principal * (rate / 100) * (time / 12);
		System.out.println("Simple interest: " + n.format(simple_interest));
		payment_amount = amortize(principal, rate, time);
		for (int i = 0; i < time; i++)
		{
			System.out.println("Month #: " + i);
			compound_interest = principal * (1+ (rate / 100) / 12) - principal;
			total_interest += compound_interest;
			System.out.println("Monthly payment amount is: " + n.format(payment_amount));
			System.out.println("Interest charged = " + n.format(compound_interest));
			principal_paid = payment_amount - compound_interest;
			principal -= principal_paid;
			System.out.println("Principal paid down = " + n.format(principal_paid) + "\n");
		}
		System.out.println("Total interest paid: " + n.format(total_interest));
	}
}