package ast;
import java.util.*;

import environment.*;

/**
 * The Block class stores consecutive lines of statements.
 *
 * @author      Vienna Parnell
 * @version     3.9.2022
 */
public class Block extends Statement
{
    private List<Statement> stmts;

    /**
     * Constructor for Block object consisting of a list of statements.
     *
     * @param stmts     statements being stored in a list
     */
    public Block(List <Statement> stmts)
    {
        this.stmts = stmts;
    }

    /**
     * Executes block.
     *
     * @param env       given environment
     */
    public void exec(Environment env)
    {
        for (int i = 0; i < stmts.size(); i++)
        {
            stmts.get(i).exec(env);
        }
    }

    /**
     * Compiles Block.
     * @param e     given Emitter
     */
    public void compile(Emitter e)
    {
        for (int i = 0; i < stmts.size(); i++)
        {
            stmts.get(i).compile(e);
        }
    }
}
