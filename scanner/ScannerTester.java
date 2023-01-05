package scanner;

import java.io.*;

/**
 * ScannerTester evaluates the Scanner class according to the specified text files.
 *
 * @author     Vienna Parnell
 * @version     2.1.2022
 *
 */
public class ScannerTester
{
    private static FileInputStream fis;
    private static Scanner scan;

    /**
     * Creates a new Tester for the Scanner class.
     *
     * @param args  information from the command line
     */
    public static void main(String[] args)
    {
        try
        {
//            fis = new FileInputStream(new File(
//                    "/Users/Vienna/Downloads/ScannerTest.txt"));
            fis = new FileInputStream(new File(
              "/Users/Vienna/Downloads/scannerTestAdvanced.txt"));
            scan = new Scanner(fis);
            readFile();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Reads the text file and ouputs tokens following Scanner's rules.
     */
    private static void readFile()
    {

        System.out.println(scan.isOperand('$'));

        try
        {
            String currentToken = "";
            while (scan.hasNext())
            {
                currentToken = scan.nextToken();
                if(!currentToken.equals(""))
                    System.out.println(currentToken);
            }
            System.out.println("END");
        }
        catch (ScanErrorException e)
        {
            e.printStackTrace();
        }
    }
}
