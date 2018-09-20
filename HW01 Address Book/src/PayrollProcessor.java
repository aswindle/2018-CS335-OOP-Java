/*
** Program  : PayrollProcessor.java
**
** Purpose  : To calculate payroll and write it to output file using input data from a file.
**
** Developer: Alex Swindle, aswindle@email.arizona.edu
*/


import java.io.IOException;
import java.util.Arrays;
import java.util.regex.PatternSyntaxException;
import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader ;
import java.io.FileWriter;

public class PayrollProcessor 
{
	final static String IN_FILE_NAME = "EmployeePayData.txt"; // file path may need to be different on your system
	
	final static String OUT_FILE_NAME = "EmployeePayroll.txt"; // file path may need to be different on your system
	
	final static double MAX_REGULAR_HOURS = 40.0; 
	
	final static double OVERTIME_FACTOR = 1.5;
	
	public static void main(String[] args)
	{
		// Declare File, FileReader and BufferedReader objects
		File	inFile = null;
		FileReader	readerObject = null;
		BufferedReader	bufferedReaderObject = null;
		
		// Declare File, FileWriter and BufferedWriter objects
		File	outFile = null;
		FileWriter	writerObject = null;
		BufferedWriter	bufferedWriterObject = null;

		try
		{
			// Instantiate File, FileReader and BufferedReader objects
			inFile = new File(IN_FILE_NAME);
			readerObject = new FileReader(inFile);
			bufferedReaderObject = new BufferedReader(readerObject);
		}
			
		catch (Exception ex)
		{
			System.out.println("\nAn Exception occurred while creating a BufferedReader for " + IN_FILE_NAME + " : " + ex.getMessage() + ".\n");

			System.exit(-1);
		}
		
		try
		{			
			// Instantiate File, FileWriter and BufferedWriter objects
			outFile = new File(OUT_FILE_NAME);
			writerObject = new FileWriter(outFile);
			bufferedWriterObject = new BufferedWriter(writerObject);
		}

		catch (Exception ex)
		{
			System.out.println("\nAn Exception occurred while creating a BufferedWriter for " + OUT_FILE_NAME + " : " + ex.getMessage() + ".\n");

			System.exit(-2);
		}

		int recordsProcessed = 0;
		
		try
		{
		
			recordsProcessed = processPayrollFile(bufferedReaderObject, bufferedWriterObject);
		}
		
		catch (PatternSyntaxException ex)
		{
			System.out.println("\nA PatternSyntaxException occurred while reading " + IN_FILE_NAME + " : " + ex.getMessage() + ".\n");
		}

		catch (IOException ex)
		{
			System.out.println("\nAn IOException occurred while reading " + IN_FILE_NAME + " : " + ex.getMessage() + ".\n");
		}

		catch (Exception ex)
		{
			System.out.println("\nAn Exception occurred while reading " + IN_FILE_NAME + " : " + ex.getMessage() + ".\n");
		}

		finally // actions to perform regardless of whether exceptions previously occurred.
		{
			System.out.println("\nrecordsProcessed: " + recordsProcessed);
			
			try
			{
				bufferedReaderObject.close();
			}
			
			catch( Exception ex)
			{
				System.out.println("\nAn Exception occurred while closing " + IN_FILE_NAME + " : " + ex.getMessage() + ".\n");
			}
			
			try
			{
				bufferedWriterObject.close();
			}
			
			catch( Exception ex)
			{
				System.out.println("\nAn Exception occurred while closing " + OUT_FILE_NAME + " : " + ex.getMessage() + ".\n");
			}
	
		}
	}
	
	// Why is this method static?
	private static int processPayrollFile(BufferedReader br, BufferedWriter bw) throws PatternSyntaxException, IOException, Exception
	{
		String employeeID   = "";
		String lastName     = "";
		String firstName    = "";
		String dept			= "";
		double payrate 		= 0.0;
		double hoursWorked	= 0.0;
		
		double[] weeklyPayArray = new double[3]; // Position 0 = regular pay, Position 1 = Overtime pay, Position 3 = Total
		
		int recordsProcessed = 0;
		
		String inputLine = "";
		
		while ((inputLine = br.readLine()) != null) // complete this with code that both reads the BufferedReader and controls the loop.
		{
			// add code to split the input line.
			String[] lineItems = inputLine.split(":");			
		
			// add code to assign data to variables
			// lineItems is 6 elements:
			// ID, last, first, dept, rate, hours			
			employeeID	= lineItems[0];
			lastName	= lineItems[1];
			firstName	= lineItems[2];
			dept		= lineItems[3];

			//payrate and hoursWorked need to be doubles, so must be converted from String
			payrate		= Double.parseDouble(lineItems[4]);
			hoursWorked	= Double.parseDouble(lineItems[5]);

			// Calculate normal, overtime, and total pay
			weeklyPayArray = calcPay( hoursWorked, payrate);
			
			// add code to write data to the BufferedReader. Remember to convert doubles to Strings. Writing Strings is a bit awkward. The relevant method needs the starting position and the length.
			// add 4 text fields followed by the 3 elements from the returned calcPay() array
			String formattedOutput = 
					String.format("%s:%s:%s:%s:%.1f:%.1f:%.1f:", employeeID, lastName, firstName, dept, 
							weeklyPayArray[0], weeklyPayArray[1], weeklyPayArray[2]);
			bw.write(formattedOutput);
			
			// Write a new line character after each employee. Use the Java system line separator so you don't have to hard code "\n" or "\r\n".
			bw.write(System.lineSeparator());

			recordsProcessed++;
		}
		
		return recordsProcessed;
	}
	
	// Why is this method static?
	private static double[] calcPay(double hours, double rate) // Why is this method static?
	{
		// Create a 3 element array of doubles for pay data -- Position 0 = regular pay, Position 1 = Overtime pay, Position 3 = Total
		double[] payArray = new double[3];

		// Employees who work <= 40hrs will just get a value for regular pay, and overtime will be 0
		if(hours <= 40)
		{
			payArray[0] = hours * rate;
			payArray[1] = 0;
		}
		
		//Employees who work > 40hrs will get 40 hours of normal pay and the remaining as overtime
		else
		{
			payArray[0] = 40 * rate;
			hours -= 40;
			payArray[1] = hours * rate * OVERTIME_FACTOR;
		}
		
		// Calculate total pay
		// Simply normal + overtime
		payArray[2] = payArray[0] + payArray[1];
		
		// return the array
		return payArray;
	}
}
