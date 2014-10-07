/*This program takes in a number N, then determine N 
longest lines in a given input.
Input Sample:
2
Hello World
CodeEval
Quick Fox
A
San Francisco

Output Sample:
San Francisco
Hello World
*/


import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException
	{
		File f = null;
		boolean LOCAL_TEST = false;
		if (LOCAL_TEST)
			f = new File("test.txt");
		else
			f = new File(args[0]);

		BufferedReader in = new BufferedReader(new FileReader(f));
		String line;
		int Size;
		//Need to get the first line, it's the size
		if( (line = in.readLine()) != null)
			Size = Integer.parseInt(line);
		else
		{
			System.out.println("Longest line requested is invalid.");
			return;
		}
		//Process the lines.
		String[] Longest;
		Longest = new String[Size];
		for(int i=0; i<Size; i++)
			Longest[i] = "";
		//System.out.println(Longest[0].length());
		while ((line = in.readLine()) != null) {
			//System.out.println(Longest[0].length());
			CompareLengthAndReplace(Longest, line, Size);
		}
		PrintLongest(Longest, Size);
		
	}
	
	public static void CompareLengthAndReplace(String[] Longest, String inputline, int Size)
	{
		for(int i = 0; i<Size; i++)
		{
			if(inputline.length() > Longest[i].length() )
			{
				String replacedLine = Longest[i];
				Longest[i] = inputline;
				
				if(i == Size-1)   //Don't have to worry about it if it's the last element of the Longest gets replaced.
					break;
				else
					PushDown(i, Longest, replacedLine, Size);          //If inputline is greater than Longest[0], then
											   			   			   //push the original Longest[0] to Longest[1]
											   			   			   //Longest[1] to Longest[2], and so on.
				break;
			}
		}
		return;
	}
	
	public static void PushDown(int index, String[] Longest, String replacedLine, int Size) 
	{
		String temp;
		String temp2;
		
		int r = index+1;
		temp = Longest[r];
		Longest[r] = replacedLine;
		
		for(int x = r+1; x<Size; x++)
		{
			temp2 = Longest[x];
			Longest[x] = temp;
			temp = temp2;
		}
		return;
	}
	
	public static void PrintLongest(String[] Longest, int Size)
	{
		for(int i=0; i<Size; i++)
		{
			System.out.println(Longest[i]);
		}
		return;
		
	}
	
}
