package parser;
import scanner.*;

import java.util.List;
import java.util.ArrayList;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.File;

import java.util.Map;
import java.util.HashMap;

import ast.*;
import ast.Number;

/**
 * The Parser class is a simple top-down recursive parser capable of parsing statements,
 * factors, expressions, and numbers. Variables are assigned values and stored in a HashMap
 * consisting of a key String and values of Integers.
 *
 * @author      Vienna Parnell
 * @version     03.11.2022
 */
public class Parser
{
    private String currentToken;
    private Map<String, Expression> map;

    private static FileInputStream fis;
    private static Scanner sc;

    /**
     * Constructs Parser object with specified scanner.
     *
     * @param scan      the scanner
     */
    public Parser(Scanner scan)
    {
        try
        {
            sc = scan;
            currentToken = sc.nextToken();
            map = new HashMap<String, Expression>();
        }
        catch (ScanErrorException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Consumes the next token after confirming that the current and expected tokens match.
     *
     * @param str                               expected token
     * @throws IllegalArgumentException         if current and expected tokens do not match
     */
    private void eat(String str)
    {
        if (str.equals(currentToken))
        {
            try
            {
                currentToken = sc.nextToken();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            throw new IllegalArgumentException("Current token " + currentToken
                    + " does not match expected token " + str + ".");
        }
    }

    /**
     * Identifies and scans number.
     *
     * @precondition        current token is an integer.
     * @postcondition       number token has been eaten.
     *
     * @return              the value of the parsed integer
     */
    private Expression parseNumber()
    {
        int num = Integer.parseInt(currentToken);
        eat(currentToken);
        return new Number(num);
    }


    /**
     * Parses statement contained within WRITELN expression.
     *
     * @precondition        current token is statement
     * @postcondition       statement is parsed and printed
     *
     */
    public Statement parseStatement()
    {
        if(currentToken.equals("WRITELN"))
        {
            eat("WRITELN");
            eat("(");
            Expression exp = parseExpression();
            eat(")");
            eat(";");
            return new Writeln(exp);
        }
        else if(currentToken.equals("BEGIN"))
        {
            eat("BEGIN");
            List<Statement>list = new ArrayList<>();
            while(!currentToken.equals("END"))
            {
                list.add(parseStatement());
            }
            Block block = new Block(list);
            eat("END");
            eat(";");
            return block;
        }
        else
        {
            String curr = currentToken;
            eat(curr);
            eat(":=");
            Assignment assignment = new Assignment(curr, parseExpression());
            eat(";");
            return assignment;
        }
    }


    /**
     * Parses factor, an expression that can be multiplied or divided.
     *
     * @precondition        current token is a factor
     * @postcondition       factor has been parsed
     *
     * @return              value of parsed factor
     */
    public Expression parseFactor()
    {
        if (currentToken.equals("-"))
        {
            eat("-");
            return new BinOp("-", new Number(0), parseFactor());
        }
        else if(currentToken.equals("("))
        {
            eat("(");
            Expression res = parseExpression();
            eat(")");
            return res;
        }
        else
        {
            try
            {
                return parseNumber();
            }
            catch(NumberFormatException e)
            {
                String curr = currentToken;
                eat(currentToken);
                return map.get(curr);
            }
        }
    }

    /**
     * Parses identified term, an expression that can be added or subtracted.
     *
     * @precondition        current token is a term
     * @postcondition       term has been parsed
     *
     * @return              value of parsed term
     */
    private Expression parseTerm()
    {
        Expression res = parseFactor();
        while(currentToken.equals("/") || currentToken.equals("*"))
        {
            if(currentToken.equals("/"))
            {
                eat("/");
                res = new BinOp("/", res, parseFactor());
            }
            else if(currentToken.equals("*"))
            {
                eat("*");
                res = new BinOp("*", res, parseFactor());
            }

        }
        return res;
    }

    /**
     * Parses expression consisting of operations of addition and subtraction.
     *
     * @precondition        current token is an expression
     * @postcondition       expression has been parsed
     *
     * @return              value of parsed expression
     */
    private Expression parseExpression()
    {
        Expression res = parseTerm();
        while(currentToken.equals("+") || currentToken.equals("-"))
        {
            if(currentToken.equals("+"))
            {
                eat("+");
                res = new BinOp("+", res, parseFactor());
            }
            else if(currentToken.equals("/"))
            {
                eat("+");
                res = new BinOp("-", res, parseFactor());
            }
        }
        return res;
    }

    /**
     * Implements Parser class methods and tests them on parserTest files.
     *
     * @param args      arguments from the command line
     */
    public static void main(String[] args)
    {
        try
        {
            fis = new FileInputStream(new File(
                    "/Users/vienna/Downloads/parserTests/parserTest3.txt"));
            sc = new Scanner(fis);
            Parser p = new Parser(sc);
            while(sc.hasNext())
            {
                p.parseStatement();
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}


