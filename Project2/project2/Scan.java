//CS 3361 Parser project2
//Dylan Sheehan, Dan Limbu, Edward Grady

import java.util.*;
import java.io.*;

public class Scan
{
    private String[] tokens = new String[100]; //Direct access to tokens outside class
    private String[] valOFTOKENS = new String[100]; //Direct access to value of tokens outside class
    private int amountTokens = 0; //Direct access to amount of tokens not nulls outside class

    public Scan(String fileName)
    {
        readFile("test1.txt");
    }

    public void readFile(String file)
    {
        try
        {
            BufferedReader inputStream = new BufferedReader(new FileReader(file));
            int charNum = 0;
            charNum = inputStream.read();

            while(charNum != -1) //-1 indicates end of file
            {
                if (charNum == 32 || charNum == 10 || charNum == 9){ //if 'space', 'tab', or 'newline'
                    while (charNum == 32 || charNum == 10 || charNum == 9){
                        charNum = inputStream.read();
                    }
                }
                checkChar(charNum, inputStream);
                charNum = inputStream.read();
            }
            inputStream.close();
        }
        catch(FileNotFoundException e)
        {
            e.getMessage();
        }
        catch(IOException e)
        {
            e.getMessage();
        }
    }

    public void checkChar(int charNum, BufferedReader stream) throws IOException
    {
        char currentChar = (char)charNum;

        switch(currentChar)
        {
            case '(':
            	tokens[amountTokens]= "lparen";
            	valOFTOKENS[amountTokens]= "(";
            	amountTokens++;
            	break;
            case ')':
            	tokens[amountTokens]= "rparen";
            	valOFTOKENS[amountTokens]= ")";
            	amountTokens++;
            	break;
            case '+':
            	tokens[amountTokens]= "plus";
            	valOFTOKENS[amountTokens]= "+";
            	amountTokens++;
            	break;
            case '-':
            	tokens[amountTokens]= "minus";
            	valOFTOKENS[amountTokens]= "-";
            	amountTokens++;
            	break;
            case '*':
            	tokens[amountTokens]= "times";
            	valOFTOKENS[amountTokens]= "*";
            	amountTokens++;
            	break;
            case ':':
                currentChar = (char)stream.read();
                if(currentChar == '=')
                {
                	tokens[amountTokens] = "assign";
                	valOFTOKENS[amountTokens]= ":=";
                	amountTokens++;
                	break;
                }
                	
                else
                {
                	System.out.println("error in :");
                    System.exit(0);
                }
                    
            case '/':
                currentChar = (char)stream.read();
                if(currentChar == '/')
                {
                    stream.readLine();
                    checkChar(stream.read(), stream);
                    break;
                }
                else if(currentChar == '*')
                {
                    char previous = currentChar;
                    currentChar = (char)stream.read();

                    String prevChar = new String(Character.toString(previous));
                    String curChar = new String(Character.toString(currentChar));

                    while (!prevChar.concat(curChar).equals("*/")){
                        previous = currentChar;
                        currentChar = (char)stream.read();

                        prevChar = new String(Character.toString(previous));
                        curChar = new String(Character.toString(currentChar));
                    }
                    checkChar(stream.read(), stream);
                    break;
                }
                else
                {
                    tokens[amountTokens]= "div";
                    valOFTOKENS[amountTokens]= "/";
                    amountTokens++;
                    break;
                }
            case '.':
                char quickRead = (char)stream.read();
                if(Character.isDigit(quickRead))
                {
                	String s = ".";
                    while(Character.isDigit(quickRead)){
                        s = s + Character.toString(quickRead);
                        quickRead = (char)stream.read();
                    }
                    tokens[amountTokens]= "number";
                    valOFTOKENS[amountTokens] = s;
                    
                    amountTokens++;
                    checkChar(quickRead, stream);
                    break;
                }
                else
                    System.out.println("error in . digit");
                    System.exit(0);
            default:
                String temp = new String ();
                String f;
                if (Character.isDigit(currentChar)){
                	f = Character.toString(currentChar);
                    char next = (char)stream.read();
                    while(Character.isDigit(next) || next == '.') {
                    	f = f + Character.toString(next);
                        next = (char)stream.read();
                    }
                    tokens[amountTokens]= "number";
                    valOFTOKENS[amountTokens] = f;
                    amountTokens++;
                    checkChar(next, stream);
                    break;
                }
                else if(Character.isAlphabetic(currentChar)){
                    temp = temp.concat(Character.toString(currentChar));
                    currentChar = (char)stream.read();
                    while (Character.isAlphabetic(currentChar) || Character.isDigit(currentChar)){
                        temp = temp.concat(Character.toString(currentChar));
                        currentChar = (char)stream.read();
                    }
                    if (temp.equals("write"))
                    {
                    	tokens[amountTokens]= "write";
                    	valOFTOKENS[amountTokens] = temp;
                    	amountTokens++;
                    	checkChar(currentChar, stream);
                    	break;
                    }
                        
                    else if(temp.equals("read"))
                    {
                    	tokens[amountTokens] = "read";
                    	valOFTOKENS[amountTokens] = temp;
                    	amountTokens++;
                    	checkChar(currentChar, stream);
                    	break;
                    }
                        
                    else
                    {
                    	tokens[amountTokens] = "id";
                    	valOFTOKENS[amountTokens] = temp;
                    	amountTokens++;
                    	checkChar(currentChar, stream);
                    	break;
                    }                   
                }
        }
    }

    public String[] getTokens()
    {
    	String[] aryTokens = new String[100];
    	
    	for(int x = 0; x < tokens.length; x++)
    		aryTokens[x] = tokens[x];
    	
    	
    	return aryTokens;
    }
    
    public String[] getValTokens()
    {
    	String[] aryTokens2 = new String[100];
    	
    	for(int x = 0; x < valOFTOKENS.length; x++)
    		aryTokens2[x] = valOFTOKENS[x];
    	
    	
    	return aryTokens2;	
    }
    
    public int getHowManyTokens()
    {
    	return amountTokens;	
    }
    
    public String toString()
    {
        String finalTokens = "(";
        for(int i = 0; i < amountTokens; i++) 
        {
        	if(i == amountTokens - 1)
        	{
        		finalTokens = finalTokens + tokens[i] + ")";
        	}
        	else
        		finalTokens = finalTokens + tokens[i] + ", ";
        }
        return finalTokens;
    }
}
