package DataStore;

import java.util.ArrayList;
import java.util.HashMap;

public class ConfigData
{
	//FUGLY CLASS
	private static String configLoc;
	
	private static String locVideo; // Videos
	private static String locMusic; // Music
	private static String locPics; // Pictures
	private static String locDocs; // Focuments (Word, PowerPoint, NotePad,
									// etc...)
	private static String locZips; // Compressed folders and files
	private static String locArch; // Archives containing all these directories
									// (on monthly or weekly basis)
	private static String locExec; // Executables (installers <.msi> and similar
									// runnables
	private static String locDirs; // Directories which shouldn't be changed
	private static String locMisc; // Anything which doesn't fit into these
									// categories

	private static ArrayList<String> dirsToCheck; // Which directories should be
													// cleaned up?

	private static HashMap<String, String> extHashMap;

	public static void setConfigLoc(String _configLoc)
	{
		configLoc = _configLoc;
	}
	
	public static String getConfigLoc()
	{
		return configLoc;
	}
	
	public static void addDirToCheck(String dirToCheck)
	{
		dirsToCheck.add(dirToCheck);
	}

	public static void removeDirToCheck(String dirToCheck)
	{
		dirsToCheck.remove(dirToCheck);
	}

	public static String getLocVideo()
	{
		return locVideo;
	}

	public static void setLocVideo(String locVideo)
	{
		ConfigData.locVideo = locVideo;
	}

	public static String getLocMusic()
	{
		return locMusic;
	}

	public static void setLocMusic(String locMusic)
	{
		ConfigData.locMusic = locMusic;
	}

	public static String getLocPics()
	{
		return locPics;
	}

	public static void setLocPics(String locPics)
	{
		ConfigData.locPics = locPics;
	}

	public static String getLocDocs()
	{
		return locDocs;
	}

	public static void setLocDocs(String locDocs)
	{
		ConfigData.locDocs = locDocs;
	}

	public static String getLocZips()
	{
		return locZips;
	}

	public static void setLocZips(String locZips)
	{
		ConfigData.locZips = locZips;
	}

	public static String getLocArch()
	{
		return locArch;
	}

	public static void setLocArch(String locArch)
	{
		ConfigData.locArch = locArch;
	}

	public static String getLocExec()
	{
		return locExec;
	}

	public static void setLocExec(String locExec)
	{
		ConfigData.locExec = locExec;
	}

	public static String getLocDirs()
	{
		return locDirs;
	}

	public static void setLocDirs(String locDirs)
	{
		ConfigData.locDirs = locDirs;
	}

	public static String getLocMisc()
	{
		return locMisc;
	}

	public static void setLocMisc(String locMisc)
	{
		ConfigData.locMisc = locMisc;
	}

	public static ArrayList<String> getDirsToCheck()
	{
		return dirsToCheck;
	}

	public static String getDirsToCheckStr()
	{
		String output = "";
		for (String s : dirsToCheck)
		{
			output += s + "#";
		}
		return output.substring(0, output.length() - 1);
	}

	public static void setDirsToCheck(ArrayList<String> dirsToCheck)
	{
		dirsToCheck = new ArrayList<String>();
		ConfigData.dirsToCheck = dirsToCheck;
	}

	public static void setDirsToCheck(String _dirsToCheck)
	{
		dirsToCheck = new ArrayList<String>();
		String[] split = _dirsToCheck.split("#");
		for (String s : split)
		{
			ConfigData.dirsToCheck.add(s);
		}
	}

	public static void setExtHashMap()
	{
		extHashMap = new HashMap<>();
	}

	public static void addToExtHashMap(String extensions)
	{
		try
		{
			extHashMap.containsKey(" "); // ugly
		} catch (Exception e)
		{
			setExtHashMap();
		}
		String[] split = extensions.split("@");
		int key = Integer.parseInt(split[0]);
		String destination;
		switch (key)
		{
		case 10:
			destination = locVideo;
			break;
		case 11:
			destination = locMusic;
			break;
		case 12:
			destination = locPics;
			break;
		case 13:
			destination = locDocs;
			break;
		case 14:
			destination = locZips;
			break;
		case 15:
			destination = locExec;
			break;
		default:
			destination = locMisc;
		}
		for (String s : split[1].split("#"))
		{
			extHashMap.put(s, destination);
		}
	}

	public static String getLocation(String name)
	{
		String[] split = name.split("\\.");
		if(split.length < 2)return locDirs;
		String output;
		try
		{
			output = extHashMap.get(split[split.length-1]);
			if(output == null)
			{
				return locMisc;
			}
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			output = locDirs;
		}
		return output;
	}
}
