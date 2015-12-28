package com.pochoF1.exceptions;

public class NoFileFormulaOneException extends Exception
{
	private static final long serialVersionUID = 1L;
    public NoFileFormulaOneException() {}

    public NoFileFormulaOneException(String message)
    {
       super(message);
    }
}