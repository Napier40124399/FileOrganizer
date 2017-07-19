package IO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class PrepareIO
{
	private static String configPath;

	public PrepareIO()
	{
		configPath = System.getProperty("user.home") + "\\AppData\\Roaming\\FileOrganizer\\";
		// System.out.println(System.getProperty("user.home")+" --
		// "+System.getenv("APPDATA"));
		System.out.println(configPath);
	}

	public static boolean checkFile(String filePath, boolean buildIfAbsent)
	{
		File f = new File(filePath);
		try
		{
			if (!f.exists() && buildIfAbsent)
			{
				f.createNewFile();
			}
		} catch (Exception e)
		{
		}
		return f.exists();
	}

	public static void makeThing(String filePath)
	{
		BufferedReader br = null;
		FileReader fr = null;
		try
		{
			String sCurrentLine;
			br = new BufferedReader(new FileReader(filePath));

			while ((sCurrentLine = br.readLine()) != null)
			{
				System.out.println(sCurrentLine);
			}

		}
		catch (IOException e){}
		finally
		{
			try
			{
				if (br != null)
					br.close();
				if (fr != null)
					fr.close();
			} catch (IOException ex)
			{
				ex.printStackTrace();
			}

		}
	}
}
