package ast;

import environment.*;

/**
 * The Assignment class matches variables with their corresponding expressions.
 *
 * @author      Vienna Parnell
 * @version     3.9.2022
 */
public class Assignment extends Statement
{
    private String var;
    private Expression exp;

    /**
     * Constructs Assignment object containing variable and expressions.
     *
     * @param var       variable being assigned
     * @param exp       expression being assigned
     */
    public Assignment(String var, Expression exp)
    {
        this.var = var;
        this.exp = exp;
    }

    /**
     * Executes assignment.
     *
     * @param env       given environment
     */
    public void exec(Environment env)
    {
        env.setVariable(var, exp.eval(env));
    }

    /**
     * Compiles assignment.
     * @param e     given Emitter
     */
    public void compile(Emitter e)
    {
        exp.compile(e);
        e.emit("sw $v0 var"+ var);
    }
}
