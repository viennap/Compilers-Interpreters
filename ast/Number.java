package ast;

import environment.*;

/**
 * The Number class stores integer values.
 *
 * @author      Vienna Parnell
 * @version     3.9.2022
 */
public class Number extends Expression
{
    private int value;

    /**
     * Constructor for Number object containing a value.
     *
     * @param value     given value
     */
    public Number(int value)
    {
        this.value = value;
    }

    /**
     * Evaluates number by retrieving value.
     *
     * @param env       given environment
     * @return          evaluated numerical expression
     */
    public int eval(Environment env)
    {
        return value;
    }

    /**
     * Compiles number.
     * @param e     given Emitter
     */
    public void compile(Emitter e)
    {
        e.emit("li $v0 " + value);
    }
}
