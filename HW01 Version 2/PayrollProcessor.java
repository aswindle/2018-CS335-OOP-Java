/*
** Program  : PayrollProcessor.java
**
** Purpose  : To calculate payroll and write it to output file using input data from a file.
**
** Developer: 
**
** TODO: Complete this program.
*/

package payrollProcessor;

import java.io.IOException;
import java.util.regex.PatternSyntaxException;
import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader ;
import java.io.FileWriter;

public class StringParser 
{
	final static String IN_FILE_NAME = "c:/data/EmployeePayData.txt"; // file path may need to be different on your system
	
	final static String OUT_FILE_NAME = "c:/data/EmployeePayroll.txt"; // file path may need to be different on your system
	
	final static double MAX_REGULAR_HOURS = 40.0; 
	
	final static double OVERTIME_FACTOR = 1.5;
	
	public static void main(String[] args)
	{
		// Declare File, FileReader and BufferedReader objects
		
		// Declare File, FileWriter and BufferedWriter objects

		try
		{
			// Instantiate File, FileReader and BufferedReader objects
		}
			
		catch (Exception ex)
		{
			System.out.println("\nAn Exception occurred while creating a BufferedReader for " + IN_FILE_NAME + " : " + ex.getMessage() + ".\n");

			System.exit(-1);
		}
		
		try
		{			
			// Instantiate File, FileWriter and BufferedWriter objects
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
		
		while ( ...) // complete this with code that both reads the BufferedReader and controls the loop.
		{							
			// add code to split the input line.
			
			// add code to assign data to variables

			weeklyPayArray = calcPay( hoursWorked, payrate);
			
			// add code to write data to the BufferedReader. Remember to convert doubles to Strings. Writing Strings is a bit awkward. The relevant method needs the starting position and the length.
			
			// Write a new line character after each employee. Use the Java system line separator so you don't ave to hard code "\n" or "\r\n".
			
			recordsProcessed++;
		}
		
		return recordsProcessed;
	}
	
	// Why is this method static?
	private static double[] calcPay(double hours, double rate) // Why is this method static?
	{
		// Create a 3 element array of doubles for pay data -- Position 0 = regular pay, Position 1 = Overtime pay, Position 3 = Total
		
		// Calculate regular pay for hours less than MAX_REGULAR_HOURS
		
		// Calculate overtime pay for hours over MAX_REGULAR_HOURS. Avoid double counting hours as both regular and OT.
		
		// Calculate total pay
		
		// return the array
	}
}
