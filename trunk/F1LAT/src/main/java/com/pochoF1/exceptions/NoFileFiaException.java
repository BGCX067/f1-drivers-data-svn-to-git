package com.pochoF1.exceptions;

public class NoFileFiaException extends Exception
{
	private static final long serialVersionUID = 1L;
    public NoFileFiaException() {}

    public NoFileFiaException(String message)
    {
       super(message);
    }

}
