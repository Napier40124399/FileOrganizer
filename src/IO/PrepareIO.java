package IO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PrepareIO
{
	public PrepareIO()
	{
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

	public static void iterateDirs()
	{
		for (String dir : ConfigData.getDirsToCheck())
		{
			checkDir(dir);
		}
	}

	private static void checkDir(String dir)
	{
		File directory = new File(dir);
		for (File f : directory.listFiles())
		{
			// System.out.println(f.getName());
			move(f, ConfigData.getLocation(f.getName()));
		}
	}

	public static void move(File file, String destination)
	{
		File dest = new File(destination+"\\"+file.getName());
		if (!dest.exists())
		{
			try
			{
				Files.createDirectories(Paths.get(destination));
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		file.renameTo(dest);
	}
}
