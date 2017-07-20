package IO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class LoadConfig
{
	private static String configPath;
	private static String configName = "config.properties";

	public LoadConfig()
	{
		configPath = System.getProperty("user.home") + "\\AppData\\Roaming\\1_FileOrganizer\\";
	}

	public static void loadParams()
	{
		if (!PrepareIO.checkFile(configPath, false))
		{
			generateConfig();
		}
		readConfig();
		ConfigData.getDirsToCheckStr();
	}

	private static void readConfig()
	{
		Properties prop = new Properties();
		InputStream input = null;
		try
		{
			input = new FileInputStream(configPath + configName);
			prop.load(input);

			ConfigData.setLocVideo(prop.getProperty("VideoLocation"));
			ConfigData.setLocMusic(prop.getProperty("MusicLocation"));
			ConfigData.setLocPics(prop.getProperty("PicturesLocation"));
			ConfigData.setLocDocs(prop.getProperty("DocumentsLocation"));
			ConfigData.setLocZips(prop.getProperty("CompressedLocation"));
			ConfigData.setLocArch(prop.getProperty("ArchiveLocation"));
			ConfigData.setLocExec(prop.getProperty("ExecutablesLocation"));
			ConfigData.setLocDirs(prop.getProperty("DirectoryLocation"));
			ConfigData.setLocMisc(prop.getProperty("MiscLocation"));

			ConfigData.setDirsToCheck(prop.getProperty("DirsToCheck"));

			ConfigData.addToExtHashMap(prop.getProperty("VideoExtensions"));
			ConfigData.addToExtHashMap(prop.getProperty("MusicExtensions"));
			ConfigData.addToExtHashMap(prop.getProperty("PictureExtensions"));
			ConfigData.addToExtHashMap(prop.getProperty("DocumentExtensions"));
			ConfigData.addToExtHashMap(prop.getProperty("CompressedExtensions"));
			ConfigData.addToExtHashMap(prop.getProperty("ExecutableExtensions"));
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private static void generateConfig()
	{
		File f = new File(configPath.substring(0, configPath.length() - 1));
		try
		{
			Files.createDirectories(Paths.get(configPath));
		} catch (Exception e1)
		{
		}

		Properties prop = new Properties();
		OutputStream output = null;
		try
		{

			output = new FileOutputStream(configPath + configName);

			// set the properties value
			// Destinations
			prop.setProperty("VideoLocation", "C:\\Users\\James\\Desktop\\Clean\\Video");
			prop.setProperty("MusicLocation", "C:\\Users\\James\\Desktop\\Clean\\Music");
			prop.setProperty("PicturesLocation", "C:\\Users\\James\\Desktop\\Clean\\Pictures");
			prop.setProperty("DocumentsLocation", "C:\\Users\\James\\Desktop\\Clean\\Documents");
			prop.setProperty("CompressedLocation", "C:\\Users\\James\\Desktop\\Clean\\Compressed");
			prop.setProperty("ArchiveLocation", "C:\\Users\\James\\Desktop\\Clean\\Archive");
			prop.setProperty("ExecutablesLocation", "C:\\Users\\James\\Desktop\\Clean\\Executables");
			prop.setProperty("DirectoryLocation", "C:\\Users\\James\\Desktop\\Clean\\Old_Directories");
			prop.setProperty("MiscLocation", "C:\\Users\\James\\Desktop\\Clean\\Miscellaneous");
			// Sources
			prop.setProperty("DirsToCheck", "C:\\Users\\James\\Downloads");//#C:\\Users\\James\\Desktop");
			// Extensions
			prop.setProperty("VideoExtensions", "10@mp4#mkv#wmv#avi#flv#mov");
			prop.setProperty("MusicExtensions", "11@mp3#flac#aac#ogg#wav");
			prop.setProperty("PictureExtensions", "12@jpg#bmp#jpeg#png");
			prop.setProperty("DocumentExtensions", "13@ppt#docx#xlt#xls#doc#docm#dotm#xlsm#xltm#xlam#pptm#potm#txt");
			prop.setProperty("CompressedExtensions", "14@zip#7z");
			prop.setProperty("ExecutableExtensions", "15@exe#msi");
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
