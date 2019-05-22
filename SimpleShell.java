import java.io.*;
import java.util.ArrayList;
import java.util.List; 
import java.lang.System;

public class SimpleShell {
	public static void main(String [] agrs) throws java.io.IOException{

		String commandLine;
		int index = 0;
		boolean exit = true;
		BufferedReader cons = new BufferedReader(new InputStreamReader(System.in));
		ProcessBuilder PB = new ProcessBuilder();
		List<String> history = new ArrayList<String>();
		while(exit == true) {
			
			System.out.println("jsh> ");
			commandLine = cons.readLine();
			String[] commands = commandLine.split(" ");
			List<String> list = new ArrayList<String>();
			
			for(int i = 0; i < commands.length; i++) {
				list.add(commands[i]);
				
			}
			
			history.addAll(list);
			try
			{
				if(list.get(list.size()-1).equals("history"))
				{
					for (String s: history)
						System.out.println((index++) + " " + s);
					continue;
				}
				
				if(list.get(list.size()-1).equals("<control><c>"))
				{
					System.out.println("System Exit");
					System.exit(1);
					
				}
				if(list.contains("cat")) 
				{
					if (list.get(list.size()-1).equals("cat")) 
					{
						File home = new File(System.getProperty("user.home"));
						System.out.println("The home directory is " + home);
						PB.directory(home);
						continue;
					}
				
					else
					{
						String dir = list.get(1);
						File path = new File(dir);
						boolean e = path.exists();
						if(e)
						{
							System.out.println("/" + dir);
							PB.directory(path);
							continue; 
						}
						else
						{
							System.out.println("Path ");
						}
					}
				}
				if(list.contains("type")) 
				{
					if (list.get(list.size()-1).equals("type")) 
					{
						File home = new File(System.getProperty("user.home"));
						System.out.println("The home directory is " + home);
						PB.directory(home);
						continue;
					}
				
					else
					{
						String dir = list.get(1);
						File path = new File(dir);
						boolean e = path.exists();
						if(e)
						{
							System.out.println("/" + dir);
							PB.directory(path);
							continue; 
						}
						else
						{
							System.out.println("Path ");
						}
					}
				}
			
				
				
				if(list.contains("cd")) 
				{
					if (list.get(list.size()-1).equals("cd")) 
					{
						File home = new File(System.getProperty("user.home"));
						System.out.println("The home directory is " + home);
				
						
						PB.directory(home);
						continue;
					}
				
					else
					{
						String dir = list.get(1);
						File path = new File(dir);
						boolean e = path.exists();
						if(e)
						{
							System.out.println("/" + dir);
							PB.directory(path);
							continue;
						}
						else
						{
							System.out.println("Path ");
						}
					}
				}
				
				if(list.get(list.size()-1).equals("!!")) {
					PB.command(history.get(history.size()-2));
				}
				else if(list.get(list.size()-1).charAt(0) == '!') {
					int A = Character.getNumericValue(list.get(list.size()-1).charAt(1));
					if(A <= history.size())
						PB.command(history.get(A));
				}
				else
				{
					PB.command(list);
				}
			Process process = PB.start();
			InputStream is = process.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);

		
			String line;
			while((line = br.readLine()) != null)
				System.out.println(line);
			br.close();
			
			if(commandLine.equals(" "))
				continue;
			}
			
			catch (IOException e ) {
				System.out.println("Error ");
			}
			}
	}

}
