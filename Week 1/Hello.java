public class Hello
{
	public static void main (String[] args) 
	{
		if(args.length == 0)
			System.out.println("Hello, World");
		else if (args[0] == null) //will never run, args[0] != null, it is not there
			System.out.println("Hello, World");
		else
			System.out.println("Hello, World! My name is " + args[0]);
	}
}