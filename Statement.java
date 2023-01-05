package ast;

import environment.*;

/**
 * The Statement class stores WRITELN and block statements.
 *
 * @author      Vienna Parnell
 * @version     3.9.2022
 */
public abstract class Statement
{
    /**
     * Executes statement.
     *
     * @param env       given statement
     */
    public abstract void exec(Environment env);

    /**
     * Compiles statement.
     *
     * @param e     given Emitter
     */
    public abstract void compile(Emitter e);

}
