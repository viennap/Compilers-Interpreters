package ast;

import environment.*;
import java.util.*;

import java.io.*;

/**
 * The program class stores the list of procedure declarations and statements for a program.
 *
 * @author      Vienna Parnell
 * @version     03.29.2022
 */
public class Program
{
    private List<ProcedureDeclaration> procedures;
    private Statement statement;
    private List<String>vars;

    /**
     * Constructs Program object consisting of list of procedure declarations and statements.
     *
     * @param procedures        list of procedure declarations
     * @param statement         statements
     */
    public Program(List<ProcedureDeclaration> procedures, Statement statement, List<String>vars)
    {
        this.procedures = procedures;
        this.statement = statement;
        this.vars = vars;
    }

    /**
     * Executes program.
     *
     * @param env       given environment
     */
    public void exec(Environment env)
    {
        for (int i = 0; i < procedures.size(); i++)
        {
            procedures.get(i).exec(env);
        }
        statement.exec(env);
    }

    /**
     * Takes in output file name and writes code to file.
     * @param outputFile        name of output file
     */
    public void compile(String outputFile)
    {
        Emitter e = new Emitter(outputFile);
        e.emit(".data");
        e.emit("newLine:\t.asciiz \"\\n\"");
        for(int i = 0; i < vars.size(); i++)
        {
            e.emit("var"+ vars.get(i) + ": .word 0");
        }
        e.emit(".text");
        e.emit(".globl main");
        e.emit("main: ");
        statement.compile(e);
        e.emit("\n li $v0 10");
        e.emit("syscall");
    }
}

