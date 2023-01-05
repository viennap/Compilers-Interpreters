package ast;

import java.io.*;

/**
 * Emitter class outputs MIP instructions to a file.
 * @author      Vienna Parnell
 * @version     05.11.2022
 */
public class Emitter
{
    private PrintWriter out;
    private int count;

    /**
     * Constructs an Emitter object.
     * @param outputFileName        name of output file
     */
    public Emitter(String outputFileName)
    {
        try
        {
            out = new PrintWriter(new FileWriter(outputFileName), true);
            count = 0;
        }
        catch(IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * Prints one line of code to file (with non-labels indented).
     * @param code      given code
     */
    public void emit(String code)
    {
        if (!code.endsWith(":"))
            code = "\t" + code;
        out.println(code);
    }

    /**
     * Closes the file. Should be called after all calls to emit.
     */
    public void close()
    {
        out.close();
    }

    /**
     * Pushes value onto top of stack.
     * @param reg       register that will store pushed value
     */
    public void emitPush(String reg)
    {
        emit("subu $sp, $sp, 4 \t #push " + reg);
        emit("sw " + reg + ", ($sp) ");
    }

    /**
     * Pops value from top of stack.
     * @param reg       register that will store popped value
     */
    public void emitPop(String reg)
    {
        emit("lw " + reg + ", ($sp) \t #pop " + reg);
        emit("addu $sp, $sp, 4");
    }

    /**
     * Returns ID of the next label.
     * @return      1 the first time its called, then 2, then 3, and so on.
     */
    public int nextLabelID()
    {
        count++;
        return count; 
    }
}