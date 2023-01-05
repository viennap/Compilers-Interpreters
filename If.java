package ast;

import environment.*;

/**
 * The If class stores and executes if statements.
 *
 * @author      Vienna Parnell
 * @version     03.20.2022
 */
public class If extends Statement
{
    private Condition cond;
    private Statement stmt;

    /**
     * Constructor for If class consisting of a condition and a statement.
     *
     * @param cond     given condition
     * @param stmt     given statement
     */
    public If(Condition cond, Statement stmt)
    {
        this.cond = cond;
        this.stmt = stmt;
    }

    /**
     * Executes if statement.
     *
     * @param env       given environment
     */
    public void exec(Environment env)
    {
        if(cond.eval(env))
        {
            stmt.exec(env);
        }
    }

    /**
     * Compiles If statement.
     * @param e     given Emitter
     */
    public void compile(Emitter e)
    {
        String label = "endif" + e.nextLabelID();
        cond.compile(e, label);
        stmt.compile(e);
        e.emit(label + ":");
    }
}
