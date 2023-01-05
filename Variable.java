package ast;

import environment.*;

/**
 * The Variable class stores variable name.
 *
 * @author      Vienna Parnell
 * @version     3.9.2022
 */
public class Variable extends Expression
{
    private String name;

    /**
     * Constructs Variable object consisting of a name.
     *
     * @param name      given name
     */
    public Variable(String name)
    {
        this.name = name;
    }

    /**
     * Evaluates variable by retrieving its name.
     *
     * @param env       given environment
     * @return          name of variable
     */
    public int eval(Environment env)
    {
        return env.getVariable(name);
    }

    /**
     * Compiles variable.
     * @param e     given emitter
     */
    public void compile(Emitter e)
    {
        e.emit("la $t0 var" + name);
        e.emit("lw $v0 ($t0)");
    }
}
