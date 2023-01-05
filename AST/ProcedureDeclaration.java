package ast;
import java.util.*;
import environment.*;

/**
 * The ProcedureDeclaration class describes instructions to declare
 * and execute a procedure.
 *
 * @author      Vienna Parnell
 * @version     03.29.2022
 */
public class ProcedureDeclaration extends Statement
{
    private String name;
    private Statement stmts;
    private List<String> params;

    /**
     * Constructs ProcedureDeclaration object that uses procedure name, a list of statements, and
     * a list of parameters.
     *
     * @param name          name of procedure
     * @param stmts         statements being used
     * @param params        parameters being used
     */
    public ProcedureDeclaration(String name, Statement stmts, List<String>params)
    {
        this.name = name;
        this.stmts = stmts;
        this.params = params;
    }

    /**
     * Executes Procedure Declaration.
     *
     * @param env       given statement
     */
    public void exec(Environment env)
    {
        env.setProcedure(name, this);
    }

    /**
     * Retrieves parameters from Procedure Declaration.
     *
     * @return      parameters from Procedure Declaration
     */
    public List<String> getParams()
    {
        return params;
    }

    /**
     * Retrieves statement from Procedure Declaration.
     *
     * @return      statement from Procedure Declaration
     */
    public Statement getStatement()
    {
        return stmts;
    }

    /**
     * Compiles ProcedureDeclaration.
     * @param e     given Emitter
     */
    public void compile(Emitter e)
    {

    }
}
