package com.pochoF1.exceptions;

public class ParsingRaceTimeTableException extends Exception
{
	private static final long serialVersionUID = 1L;
    public ParsingRaceTimeTableException() {}

    public ParsingRaceTimeTableException(String message)
    {
       super(message);
    }
}