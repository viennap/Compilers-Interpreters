package environment;

import java.util.Map;
import java.util.HashMap;

import ast.ProcedureDeclaration;

/**
 * The Environment class stores and retrieves the values of variables.
 *
 * @author Vienna Parnell
 * @version 3.9.21
 */
public class Environment
{
    private Map<String, Integer> map;
    private Map<String, ProcedureDeclaration> procedures;
    private Environment parent;

    /**
     * The Environment object initiates HashMap for matching variables and values.
     *
     * @param parent        given parent of environment
     */
    public Environment(Environment parent)
    {
        this.parent = parent;
        map = new HashMap<>();
        procedures = new HashMap<>();
    }

    /**
     * Retrieves variable from map given a variable.
     *
     * @param variable      given variable
     * @return              corresponding value in map
     */
    public int getVariable(String variable)
    {
        if(map.get(variable)==null)
        {
            return parent.getMap().get(variable);
        }
        return map.get(variable);
    }


    /**
     * Retrieves ProcedureDeclaration corresponding with given variable name.
     *
     * @param var   name of String variable
     * @return      corresponding ProcedureDeclaration
     */
    public ProcedureDeclaration getProcedure(String var)
    {
        return procedures.get(var);
    }

    /**
     * Assigned ProcedureDeclaration object to variable name.
     *
     * @param var       given variable
     * @param pd        given procedure declaration
     *
     */
    public void setProcedure(String var, ProcedureDeclaration pd)
    {
        procedures.put(var, pd);
    }

    /**
     * Returns the stored parent environment.
     *
     * @return      parent environment
     */
    public Environment getParent()
    {
        return parent;
    }

    /**
     * Returns the map of environment.
     *
     * @return      map of environment
     */
    public Map <String, Integer> getMap()
    {
        return map;
    }

    /**
     * Sets variable with matching integer value in map.
     *
     * @param var      given variable
     * @param val         given value
     */
    public void setVariable(String var, int val)
    {
        if(map.get(var)==null && parent != null
                && parent.getMap().get(var)!= null)
        {
            parent.setVariable(var, val);
        }
        else
        {
            declareVariable(var, val);
        }
    }

    /**
     * Declares a new variable with given variable name and given value.
     *
     * @param variable the given variable name
     * @param value the given value
     */
    public void declareVariable(String variable, int value)
    {
        map.put(variable,value);
    }

}