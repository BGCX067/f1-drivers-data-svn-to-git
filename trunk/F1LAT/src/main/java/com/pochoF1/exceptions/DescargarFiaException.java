package com.pochoF1.exceptions;

public class DescargarFiaException extends Exception
{
	private static final long serialVersionUID = 1L;
    public DescargarFiaException() {}

    public DescargarFiaException(String message)
    {
       super(message);
    }
}
