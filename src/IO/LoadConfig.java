package IO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class LoadConfig
{
	private static String configPath;

	public LoadConfig()
	{
		configPath = System.getProperty("user.home") + "\\AppData\\Roaming\\FileOrganizer\\";
	}

	public static void loadParams()
	{
		if (!PrepareIO.checkFile(configPath, false))
		{
			generateConfig();
		}

	}

	private static void generateConfig()
	{
		File f = new File(configPath.substring(0, configPath.length() - 1));
		try
		{
			f.mkdir();
		} catch (Exception e1){}

		Properties prop = new Properties();
		OutputStream output = null;
		try
		{

			output = new FileOutputStream(configPath + "config.properties");

			// set the properties value
			//Destinations
			prop.setProperty("VideoLocation", "C:\\Users\\James\\Desktop\\Clean\\Video");
			prop.setProperty("MusicLocation", "C:\\Users\\James\\Desktop\\Clean\\Music");
			prop.setProperty("PicturesLocation", "C:\\Users\\James\\Desktop\\Clean\\Pictures");
			prop.setProperty("DocumentsLocation", "C:\\Users\\James\\Desktop\\Clean\\Documents");
			prop.setProperty("CompressedLocation", "C:\\Users\\James\\Desktop\\Clean\\Compressed");
			prop.setProperty("ArchiveLocation", "C:\\Users\\James\\Desktop\\Clean\\Archive");
			prop.setProperty("ExecutablesLocation", "C:\\Users\\James\\Desktop\\Clean\\Executables");
			//Sources
			prop.setProperty("DirsToCheck", "");
			// save properties to project root folder
			prop.store(output, "Configuration settings for file system organizer.");

		} catch (IOException io)
		{
			io.printStackTrace();
		} finally
		{
			if (output != null)
			{
				try
				{
					output.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}

		}
	}
}
