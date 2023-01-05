package ast;

import environment.*;

/**
 * The Whiteln class outputs and prints statements.
 *
 * @author      Vienna Parnell
 * @version     3.9.2022
 */
public class Writeln extends Statement
{
    private Expression exp;

    /**
     * Constructor for Whiteln object consisting of expression.
     *
     * @param exp       given expression
     */
    public Writeln(Expression exp)
    {
        this.exp = exp;
    }

    /**
     * Executes Writeln.
     *
     * @param env       given environment
     */
    public void exec(Environment env)
    {
        System.out.println(exp.eval(env));
    }

    /**
     * Compiles WriteLn.
     * @param e     given Emitter
     */
    public void compile(Emitter e)
    {
        exp.compile(e);

        e.emit("move $a0 $v0");
        e.emit("li $v0 1");
        e.emit("syscall");

        e.emit("li $v0 4");
        e.emit("la $a0 newLine");
        e.emit("syscall");
    }
}
