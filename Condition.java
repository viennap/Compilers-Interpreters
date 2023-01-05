package ast;
import environment.*;

/**
 * The Condition class provides instructions for comparing two expressions.
 *
 * @author      Vienna Parnell
 * @version     03.09.2022
 */
public class Condition
{
    private Expression e1;
    private String o;
    private Expression e2;

    /**
     * Constructor for Condition object consisting of two expressions and an operator.
     *
     * @param e1        first given expression
     * @param o         given operator
     * @param e2        second given expression
     */
    public Condition(Expression e1, String o, Expression e2)
    {
        this.e1 = e1;
        this.o = o;
        this.e2 = e2;
    }

    /**
     * Evaluates comparative expressions.
     *
     * @param env       given environment
     * @return          evaluated expression
     */
    public boolean eval(Environment env)
    {
        int n1 = e1.eval(env);
        int n2 = e2.eval(env);
        if(o.equals("="))
        {
            return n1==n2;
        }
        else if(o.equals("<"))
        {
            return n1<n2;
        }
        else if(o.equals(">"))
        {
            return n1>n2;
        }
        else if(o.equals("<="))
        {
            return n1<=n2;
        }
        else if(o.equals(">="))
        {
            return n1>=n2;
        }
        else if(o.equals("<>"))
        {
            return n1!=n2;
        }
        return false;
    }

    /**
     * Compiles condition.
     * @param e     given emitter
     * @param s     given string
     */
    public void compile(Emitter e, String s)
    {
        e1.compile(e);
        e.emitPush("$v0");
        e2.compile(e);
        e.emitPop("$t0");
        if(o.equals("="))
        {
            e.emit("bne $t0 $v0 " + s);
        }
        else if(o.equals("<"))
        {
            e.emit("bge $t0 $v0 " + s);
        }
        else if(o.equals(">"))
        {
            e.emit("ble $t0 $v0 " + s);
        }
        else if(o.equals("<="))
        {
            e.emit("bgt $t0 $v0 " + s);
        }
        else if(o.equals(">="))
        {
            e.emit("blt $t0 $v0 " + s);
        }
        else if(o.equals("<>"))
        {
            e.emit("beq $t0 $v0 " + s);
        }
    }
}