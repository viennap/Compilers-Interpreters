package scanner;

import java.io.*;

/**
 * Scanner is a simple scanner for Compilers and Interpreters (2014-2015) lab exercise 1
 * @author Vienna Parnell
 *
 *
 * @assistance      April Sun for recognizing >=, <=, and := in nextToken() method
 * @version         1.28.2022
 *
 * Usage:
 * Scanner object reads input from files and identifies tokens.
 *
 */
public class Scanner
{
    private BufferedReader in;
    private char currentChar;
    private boolean eof;

    /**
     * Scanner constructor for construction of a scanner that
     * uses an InputStream object for input.
     *
     * Usage:
     * FileInputStream inStream = new FileInputStream(new File(<file name>);
     * Scanner lex = new Scanner(inStream);
     * @param inStream the input stream to use
     */
    public Scanner(InputStream inStream)
    {
        in = new BufferedReader(new InputStreamReader(inStream));
        eof = false;
        getNextChar();
    }

    /**
     * Scanner constructor for constructing a scanner that
     * scans a given input string.  It sets the end-of-file flag an then reads
     * the first character of the input string into the instance field currentChar.
     *
     * Usage: Scanner lex = new Scanner(input_string);
     * @param inString the string to scan
     */
    public Scanner(String inString)
    {
        in = new BufferedReader(new StringReader(inString));
        eof = false;
        getNextChar();
    }

    /**
     * Scans to next character that is going to be read.
     */
    private void getNextChar()
    {
        try
        {
            int input = in.read();
            if (input == -1)
                eof = true;
            else
            {
                currentChar = (char) input;
                if (currentChar == '.')
                {
                    eof = true;
                }
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    /**
     * Takes in a char value representing the expected value of the current character.
     * Advances the input stream one character if read input matches expected value.
     *
     * @param expected              expected character that is compared to the current character
     * @throws ScanErrorException   if the character that is taken in
     *                              does not match the current character
     *
     */
    private void eat(char expected) throws ScanErrorException
    {
        try
        {
            if(currentChar == expected)
            {
                getNextChar();
            }
            else
            {
                throw new ScanErrorException("Illegal character — expected " + expected +
                        " and found " + currentChar);
            }
        }
        catch(Exception e)
        {
            System.exit(1);
        }
    }

    /**
     * Determines if there is another character to be read next.
     *
     * @return      true if another character exists next; otherwise
     *              false
     */
    public boolean hasNext()
    {
        return !eof;
    }

    /**
     * Examines value of current character and scans next token in the input stream.
     *
     * @return                      string representing lexeme found in input stream
     * @throws ScanErrorException   if no lexeme is recognized
     */
    public String nextToken() throws ScanErrorException
    {
        try
        {
            String result = "";

            // Skipping ahead to first token after initial white spaces
            if (isWhiteSpace(currentChar))
            {
                while(hasNext() && isWhiteSpace(currentChar))
                {
                    eat(currentChar);
                }
            }

            // Accounting for special cases of operands
            if (isOperand(currentChar))
            {
                // >=, <=, and :=
                if(scanOperand().equals(">") || scanOperand().equals("<") ||
                        scanOperand().equals(":") )
                {
                    result += scanOperand();
                    eat(currentChar);
                    if (hasNext() && scanOperand().equals("="))
                    {
                        result += scanOperand();
                        eat(currentChar);
                    }
                    else if (hasNext() && result.equals("<") && scanOperand().equals(">"))
                    {
                        result += scanOperand();
                        eat(currentChar);
                    }
                    return result;
                }
                // Single line comments beginning with //
                else if (scanOperand().equals("/"))
                {
                    result += scanOperand();
                    eat(currentChar);
                    if (hasNext() && scanOperand().equals("/"))
                    {
                        while(hasNext() && currentChar!= '\n')
                        {
                            eat(currentChar);
                        }
                        eat(currentChar);
                        return "";
                    }
                    return result;
                }
                else
                {
                    result += scanOperand();
                    eat(currentChar);
                    return result;
                }
            }
            else if(isDigit(currentChar))
            {
                return scanNumber();
            }
            else if (isLetter(currentChar))
            {
                return scanIdentifier();
            }
            else if (!hasNext())
            {
                return "eof";
            }
            else
            {
                throw new ScanErrorException(
                        "Unrecognized character: " + currentChar);
            }
        }
        catch(ScanErrorException e)
        {
            //eat(currentChar);
            throw new ScanErrorException(
                    "Unrecognized character" + currentChar);
        }
    }

    /**
     * Determines whether a character is a digit.
     *
     * @param character     character that is being checked
     * @return              true if the character is a digit; otherwise,
     *                      false
     */
    public static boolean isDigit(char character)
    {
        return character >= '0' && character <= '9';
    }

    /**
     * Determines whether a character is a letter.
     *
     * @param character     character that is being checked
     * @return              true if the character is a letter; otherwise,
     *                      false
     */
    public static boolean isLetter(char character)
    {
        return ((character >= 'a' && character <= 'z') || (character >= 'A' && character <= 'Z'));
    }

    /**
     * Determines whether a character is whitespace.
     *
     * @param character     character that is being checked
     * @return              true if the character is whitespace; otherwise,
     *                      false
     */
    public static boolean isWhiteSpace(char character)
    {
        return character == ' ' || character == '\t' || character == '\r' || character == '\n';
    }

    /**
     * Scans number, represented by digit(digit)*
     *
     * @return                      string containing scanned number
     * @throws ScanErrorException   if scanned token is not recognized as number lexeme
     */
    private String scanNumber() throws ScanErrorException
    {
        String result = "";
//        eat(currentChar);
        while (hasNext() && isDigit(currentChar))
        {
            result += currentChar;
            eat(currentChar);
        }
        return result;
    }

    /**
     * Scans identifier, represented by letter(letter|digit)*
     *
     * @return                      string containing scanned identifier
     * @throws ScanErrorException   if scanned token is not recognized as identifier lexem
     */
    private String scanIdentifier() throws ScanErrorException
    {
        String result = "";
//        eat(currentChar);
        while(hasNext() && (isLetter(currentChar) || isDigit(currentChar)))
        {
            result += currentChar;
            eat(currentChar);
        }
        return result;
    }

    /**
     * Scans the input for an operand.
     *
     * @return a String that represents the lexeme in the input stream
     */
    private String scanOperand()
    {
        if((currentChar >= '!' && currentChar<= '/') || (currentChar >= ':' &&
                currentChar<= '@') || (currentChar >= '{' && currentChar<= '}') ||
                (currentChar >= '[' && currentChar<= '`'))
        {
            return "" + currentChar;
        }
        return "";
        //        if(isOperand(currentChar))
//        {
//            return "" + currentChar;
//        }
//        return "";
    }

    /**
     * Scans the input for an operand.
     *
     * @param character a character passed into the method
     * @return a String that represents the lexeme in the input stream
     * @throws ScanErrorException if a lexeme is not recognized
     */
    public static boolean isOperand(char character)
    {
        return ((character >= '!' && character<= '/') || (character >= ':' &&
                character<= '@') || (character >= '{' && character<= '}') ||
                (character >= '[' && character<= '`'));
        //        return (character == '+' || character == '/' || character == '*' ||
        //                character == '(' || character == ')' || character == '%' ||
        //                character == '-' || character == '>' || character == '<' ||
        //                character == ':' || character == '=');

    }
}