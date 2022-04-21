//CS 3361 Parser project2
//Dylan Sheehan, Dan Limbu, Edward Grady

public class Parser
{
	private String allTokens[] = new String[100];
	private String allValueTokens[] = new String[100];
	private int inCRA = 0;
	
	public Parser(String[] tokens, String[] values, int theAmount)
	{
		for(int x = 0; x < theAmount; x++)
		{
			allTokens[x] = tokens[x];
			allValueTokens[x] = values[x];
		}
			
		allTokens[theAmount] = "$$";
		allValueTokens[theAmount] = "$$";
		
		String tabs = "";
		program(tabs);
	}
	
	public void program(String tabs)
	{
		switch(allTokens[inCRA])
		{
		case "id":
		case "read":
		case "write":
			System.out.println(tabs + "<Program>");
			stmt_list(tabs + "  ");
		case "$$":
			match(allTokens[inCRA], tabs, inCRA); //tabs is dummy
			System.out.println(tabs + "</Program>");
		}
	}
	
	public void stmt_list(String tabs)
	{	
		switch(allTokens[inCRA])
		{
		case "id":
		case "read":
		case "write":
			System.out.println(tabs + "<Stmt_List>");
			stmt(tabs + "  ");
			stmt_list(tabs + "  ");
			System.out.println(tabs + "</Stmt_List>");
			break;
		case "$$":
			break;
		default:
            System.out.println("Error");
            System.exit(0);
		}
	}
	
	public void stmt(String tabs)
	{
		switch(allTokens[inCRA])
		{
		case "id":
			System.out.println(tabs + "<Stmt>");
			match(allTokens[inCRA], tabs + "  ", inCRA);
			if(!allTokens[inCRA].equals("assign"))
			{
				System.out.println("ERROR");
				System.exit(0);
			}
			match(allTokens[inCRA], tabs + "  ", inCRA);
			expr(tabs + "  ");
			System.out.println(tabs + "</Stmt>");
			break;
		case "read":
			System.out.println(tabs + "<Stmt>");
			match(allTokens[inCRA], tabs + "  ", inCRA);
			if(!allTokens[inCRA].equals("id"))
			{
				System.out.println("ERROR");
				System.exit(0);
			}
			match(allTokens[inCRA], tabs + "  ", inCRA);
			System.out.println(tabs + "</Stmt>");
			break;
		case "write":
			System.out.println(tabs + "<Stmt>");
			match(allTokens[inCRA], tabs + "  ", inCRA);
			expr(tabs + "  ");
			System.out.println(tabs + "</Stmt>");
			break;
		default:
            System.out.println("Error");
            System.exit(0);
		}
	}
	
	public void expr(String tabs)
	{
		switch(allTokens[inCRA])
		{
		case "id":
		case "number":
		case "lparen":
			System.out.println(tabs + "<Expr>");
			term(tabs + "  ");
			term_tail(tabs + "  ");
			System.out.println(tabs + "</Expr>");
			break;
		default:
            System.out.println("Error");
            System.exit(0);
		}
		
	}
	
	public void term(String tabs)
	{
		switch(allTokens[inCRA])
		{
		case "id":
		case "number":
		case "lparen":
			System.out.println(tabs + "<Term>");
			factor(tabs + "  ");
			fact_tail(tabs + "  ");
			System.out.println(tabs + "</Term>");
			break;
		default:
            System.out.println("Error");
            System.exit(0);
		}
	}
	
	public void factor(String tabs)
	{
		
		switch(allTokens[inCRA])
		{
		case "id":
			System.out.println(tabs + "<Factor>");
			match(allTokens[inCRA], tabs + "  ", inCRA);
			System.out.println(tabs + "</Factor>");
			break;
		case "number":
			System.out.println(tabs + "<Factor>");
			match(allTokens[inCRA], tabs + "  ", inCRA);
			System.out.println(tabs + "</Factor>");
			break;
		case "lparen":
			System.out.println(tabs + "<Factor>");
			match(allTokens[inCRA], tabs + "  ", inCRA);
			expr(tabs + "  ");
			match(allTokens[inCRA], tabs + "  ", inCRA);
			System.out.println(tabs + "</Factor>");
			break;
		default:
            System.out.println("Error");
            System.exit(0);
		}
	}
	
	public void fact_tail(String tabs)
	{
		//System.out.println("<Fact_Tail>");
		switch(allTokens[inCRA])
		{
		case "plus":
		case "minus":
		case "rparen":
		case "id":
		case "read":
		case "write":
		case "$$":
			break;
		case "times":
		case "div":
			System.out.println(tabs + "<Fact_Tail>");
			mult_op(tabs + "  ");
			factor(tabs + "  ");
			fact_tail(tabs + "  ");
			System.out.println(tabs + "</Fact_Tail>");
			break;
		default:
            System.out.println("Error");
            System.exit(0);
		}
		//System.out.println("</Fact_Tail>");
	}
	
	public void term_tail(String tabs)
	{
		//System.out.println("<Term_Tail>");
		switch(allTokens[inCRA])
		{
		case "rparen":
		case "id":
		case "read":
		case "write":
		case "$$":
			break;
		case "plus":
		case "minus":
			System.out.println(tabs + "<Term_Tail>");
			add_op(tabs + "  ");
			term(tabs + "  ");
			term_tail(tabs + "  ");
			System.out.println(tabs +"</Term_Tail>");
			break;
		default:
            System.out.println("Error");
            System.exit(0);
		}
		//System.out.println("</Term_Tail>");
	}
	
	public void add_op(String tabs)
	{
		switch(allTokens[inCRA])
		{
		case "plus":
		case "minus":
			System.out.println(tabs + "<Add_Op>");
			match(allTokens[inCRA], tabs + "  ", inCRA);
			System.out.println(tabs + "</Add_Op>");
			break;
		default:
            System.out.println("Error");
            System.exit(0);
		}
	}
	
	public void mult_op(String tabs)
	{
		switch(allTokens[inCRA])
		{
		case "times":
		case "div":
			System.out.println(tabs + "<Mult_Op>");
			match(allTokens[inCRA], tabs + "  ", inCRA);
			System.out.println(tabs + "</Mult_Op>");
			break;
		default:
            System.out.println("Error");
            System.exit(0);
		}
	}
	
	
	
	public void match(String matcher, String tabs, int index)
	{
		switch (matcher) 
		{
			case "id":
				System.out.println(tabs + "<id>");
				System.out.println(tabs + "  " + allValueTokens[index]);
				System.out.println(tabs + "</id>");
				inCRA++;
				break;
			case "assign":
				System.out.println(tabs + "<assign>");
				System.out.println(tabs + "  " + allValueTokens[index]);
				System.out.println(tabs + "</assign>");
				inCRA++;
				break;
			case "read":
				System.out.println(tabs + "<read>");
				System.out.println(tabs + "  " + allValueTokens[index]);
				System.out.println(tabs + "</read>");
				inCRA++;
				break;
			case "write":
				System.out.println(tabs + "<write>");
				System.out.println(tabs + "  " + allValueTokens[index]);
				System.out.println(tabs + "</write>");
				inCRA++;
				break;
			case "lparen":
				System.out.println(tabs + "<lparen>");
				System.out.println(tabs + "  " + allValueTokens[index]);
				System.out.println(tabs + "</lparen>");
				inCRA++;
				break;
			case "rparen":
				System.out.println(tabs + "<rparen>");
				System.out.println(tabs + "  " + allValueTokens[index]);
				System.out.println(tabs + "</rparen>");
				inCRA++;
				break;
			case "number":
				System.out.println(tabs + "<number>");
				System.out.println(tabs + "  " + allValueTokens[index]);
				System.out.println(tabs + "</number>");
				inCRA++;
				break;
			case "plus":
				System.out.println(tabs + "<plus>");
				System.out.println(tabs + "  " + allValueTokens[index]);
				System.out.println(tabs + "</plus>");
				inCRA++;
				break;
			case "minus":
				System.out.println(tabs + "<minus>");
				System.out.println(tabs + "  " + allValueTokens[index]);
				System.out.println(tabs + "</minus>");
				inCRA++;
				break;
			case "times":
				System.out.println(tabs + "<times>");
				System.out.println(tabs + "  " + allValueTokens[index]);
				System.out.println(tabs + "</times>");
				inCRA++;
				break;
			case "div":
				System.out.println(tabs + "<div>");
				System.out.println(tabs + "  " + allValueTokens[index]);
				System.out.println(tabs + "</div>");
				inCRA++;
				break;
			case "$$":
				break;
		}
		
		
	}
}