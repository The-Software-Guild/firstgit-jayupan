import java.util.Scanner;

public class WindowMaster 
{
	public static void main (String[] args)
	{
		float height, width;
		String strHeight, strWidth;
		float areaOfWindow, cost, perOfTheWindow;

		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter window height:");
		strHeight = scanner.nextLine();
		System.out.println("Please enter window width:");
		strWidth = scanner.nextLine();

		height = Float.parseFloat(strHeight);
		width = Float.parseFloat(strWidth);

		areaOfWindow = height * width;
		perOfTheWindow = 2 * (height + width);

		cost = ((3.50f * areaOfWindow) + (2.25f * perOfTheWindow));

		System.out.println("Window height = : " + height);
		System.out.println("Window width = : " + width);
		System.out.println("Window area = : " + areaOfWindow);
		System.out.println("Window perimeter = : " + perOfTheWindow);
		System.out.println("Total Cost = : " + cost);
	}
}