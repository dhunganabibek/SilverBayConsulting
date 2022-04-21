//CS 3361 Parser project2
//Dylan Sheehan, Dan Limbu, Edward Grady

public class Main
{
	public static void main(String[] args)
	{
		Scan test = new Scan(args[0]);
		Parser theParse = new Parser(test.getTokens(), test.getValTokens(), test.getHowManyTokens());
	}
}
