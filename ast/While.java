package ast;

import environment.*;

/**
 * The While class executes statements as long as condition is fulfilled.
 *
 * @author      Vienna Parnell
 * @version     03.20.2022
 */
public class While extends Statement
{
    private Condition cond;
    private Statement stmt;

    /**
     * Constructs While object consisting of condition and statement.
     *
     * @param cond      given condition
     * @param stmt      given statement
     */
    public While(Condition cond, Statement stmt)
    {
        this.cond = cond;
        this.stmt = stmt;
    }

    /**
     * Executes statement.
     *
     * @param env       given environment
     */
    public void exec(Environment env)
    {
        while(cond.eval(env))
        {
            stmt.exec(env);
        }
    }

    /**
     * Compiles While statement.
     *
     * @param e     given Emitter
     */
    public void compile(Emitter e)
    {
        String l = "endwhile" + e.nextLabelID();
        e.emit(l + ":");
        int n = e.nextLabelID();
        cond.compile(e, "endwhile" + n);
        stmt.compile(e);
        e.emit("j "+ l);
        e.emit("endwhile" + n + ":");
    }
}
