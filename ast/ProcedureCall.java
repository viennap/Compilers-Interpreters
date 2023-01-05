package ast;
import environment.*;
import java.util.*;

/**
 * The ProcedureCall class describes the instructions to
 * call and evaluate a procedure.
 *
 *
 * @author      Vienna Parnell
 * @version     04.11.2022
 */
public class ProcedureCall extends Expression
{
    private String procedureName;
    private List<Expression> params;

    /**
     * Constructs ProcedureCall consisting of procedure name and parameter input list.
     *
     * @param name          name of ProcedureCall
     * @param params        list of parameter expressions
     */
    public ProcedureCall(String name, List<Expression> params)
    {
        procedureName = name;
        this.params = params;
    }

    /**
     * Evaluates ProcedureCall.
     *
     * @param env       given environment
     * @return          retrieved map
     */
    public int eval(Environment env)
    {
        Environment e = new Environment(env);
        e.setVariable(procedureName, 0);

        ProcedureDeclaration pd = e.getParent().getProcedure(procedureName);
        List<String> p = pd.getParams();

        for(int i = 0; i < params.size(); i++)
        {
            e.declareVariable(p.get(i), params.get(i).eval(env));
        }
        Statement exp = pd.getStatement();

        exp.exec(e);
        return e.getMap().get(procedureName);
    }

    /**
     * Compiles ProcedureCall.
     * @param e     given emitter
     */
    public void compile(Emitter e)
    {

    }
}
