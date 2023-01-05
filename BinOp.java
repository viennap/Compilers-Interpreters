package ast;

import environment.*;

/**
 * The BinOp class stores and calculates binary expressions.
 *
 * @author      Vienna Parnell
 * @version     3.9.2022
 */
public class BinOp extends Expression
{
    private String op;
    private Expression e1;
    private Expression e2;

    /**
     * Constructs BinOp object containing operator and two expressions.
     *
     * @param op        given operator
     * @param e1        first given expression
     * @param e2        second given expression
     */
    public BinOp(String op, Expression e1, Expression e2)
    {
        this.op = op;
        this.e1 = e1;
        this.e2 = e2;
    }

    /**
     * Given two integer values, this method evaluates the binary operation.
     *
     * @param env       given environment
     * @return          evaluated binary operation
     */
    public int eval(Environment env)
    {
        int res = 0;
        int v1 = e1.eval(env);
        int v2 = e2.eval(env);
        if (op.equals("+"))
        {
            res = v1+v2;
        }
        else if (op.equals("-"))
        {
            res = v1-v2;
        }
        else if (op.equals("*"))
        {
            res = v1*v2;
        }
        else if (op.equals("/"))
        {
            res = v1/v2;
        }
        else if (op.equals("%"))
        {
            res = v1%v2;
        }
        return res;
    }

    /**
     * Compiles binary operation.
     * @param e     given emitter
     */
    public void compile(Emitter e)
    {
        e1.compile(e);
        e.emitPush("$v0");

        e2.compile(e);
        e.emitPop("$t0");

        if(op.equals("+"))
        {
            e.emit("addu $v0 $t0 $v0");
        }
        else if(op.equals("-"))
        {
            e.emit("subu $v0 $t0 $v0");
        }
        else if(op.equals("*"))
        {
            e.emit("mult $t0 $v0");
            e.emit("mflo $v0");
        }
        else if(op.equals("/"))
        {
            e.emit("div $t0 $v0");
            e.emit("mflo $v0");
        }
        else if(op.equals("%"))
        {
            e.emit("div $t0 $v0");
            e.emit("mfhi $v0");
        }
    }
}
