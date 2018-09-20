/*
** Program  : BufferedReaderDemo1.java
**
** Purpose  : To demonstrate using a BufferedReader to read a file.
**
** Developer: F DAngelo
**
*/


import java.io.IOException;
import java.util.regex.PatternSyntaxException;
import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader ;
import java.io.FileWriter;

public class BufferedReaderWriterDemo 
{
	final static String IN_FILE_NAME = "c:/data/StudentRawData.txt";
	
	final static String OUT_FILE_NAME = "c:/data/FormattedStudentOutput.txt";
	
	final static double MAX_REGULAR_HOURS = 40.0; 
	
	final static double OVERTIME_FACTOR = 1.5;
	
	public static void main(String[] args)
	{
		// The program uses these constants to keep track of data item it is reading from the file.

		File inFileObject         = null;

		FileReader readerObject     = null;

		BufferedReader bufferedReaderObject = null;
		
		File outFileObject         = null;

		FileWriter writerObject     = null;

		BufferedWriter bufferedWriterObject = null;

		try
		{
			inFileObject = new File( IN_FILE_NAME );

			readerObject   = new FileReader( inFileObject );

			bufferedReaderObject = new BufferedReader( readerObject );
		}
			
		catch (Exception ex)
		{
			System.out.println("\nAn Exception occurred while creating a BufferedReader for " + IN_FILE_NAME + " : " + ex.getMessage() + ".\n");

			System.exit(-1);
		}
		
		try
		{			
			outFileObject = new File( OUT_FILE_NAME );

			writerObject   = new FileWriter( outFileObject );

			bufferedWriterObject = new BufferedWriter( writerObject );
		}

		catch (Exception ex)
		{
			System.out.println("\nAn Exception occurred while creating a BufferedWriter for " + OUT_FILE_NAME + " : " + ex.getMessage() + ".\n");

			System.exit(-2);
		}

		int recordsProcessed = 0;
		
		try
		{
		
			recordsProcessed = processInputFile(bufferedReaderObject, bufferedWriterObject);
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
			System.out.println("\nrecordsProcessed: " + recordsProcessed );
			
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
	
	private static int processInputFile(BufferedReader br, BufferedWriter bw) throws PatternSyntaxException, IOException, Exception
	{	
		final int ID_INDEX  	= 0;
		final int LNAME_INDEX   = 1;
		final int FNAME_INDEX  	= 2;
		final int MAJOR_INDEX	= 3;
		final int GPA_INDEX		= 4;
		
		int recordsProcessed = 0;
		
		String inputLine = "";
		
		
		
		while ( (inputLine = br.readLine()) != null)
		{							
			String[] inputArray = inputLine.split(":");
			
			// not using in calculations so String is OK.
			
			// Produce formatted, easy-to-read output:
			
			// The minus sign in the format specifier left-justifies the value.
			String formattedOutput = 
					String.format("Student ID: %-6s Last name: %-16s First name: %-16s Major %-4s GPA: %4s %n", 
							inputArray[ID_INDEX], inputArray[LNAME_INDEX], inputArray[FNAME_INDEX], 
							inputArray[MAJOR_INDEX], inputArray[GPA_INDEX]);
			
			
			bw.write(formattedOutput);
			
			bw.write(System.lineSeparator());	
			
			recordsProcessed++;
		}
		
		return recordsProcessed;
	}
	
	private static double[] calcPay(double hours, double rate)
	{
		double[] payArray = new double[3]; // Position 0 = regular pay, Position 1 = Overtime pay, Position 3 = Total
		
		if (hours <= MAX_REGULAR_HOURS )
		{
			payArray[0] = rate * hours;
			
			payArray[1] = 0.0;
		}
		
		else
		{
			double regHours = MAX_REGULAR_HOURS;
			
			double otHours = hours - MAX_REGULAR_HOURS;
			
			payArray[0] = rate * regHours;
			
			payArray[1] = rate * otHours * OVERTIME_FACTOR;
		}
		
		payArray[2] = payArray[0] + payArray[1];
		
		return payArray;
	}
}
