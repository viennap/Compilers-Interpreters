package ast;

import environment.*;



/**
 * The Expression class stores binary and numerical expressions.
 *
 * @author      Vienna Parnell
 * @version     3.9.2022
 */
public abstract class Expression
{
    /**
     * Evaluates expression.
     *
     * @param env given environment
     * @return evaluated expression
     */
    public abstract int eval(Environment env);

    /**
     * Compiles expression.
     * @param e     given emitter
     */
    public abstract void compile(Emitter e);

}
