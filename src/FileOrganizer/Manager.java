package FileOrganizer;

import IO.LoadConfig;
import IO.PrepareIO;

public class Manager
{
	//Instances
	private PrepareIO prepIO;
	private LoadConfig loadConfig;
	
	public Manager()
	{
		loadConfig = new LoadConfig();
		prepIO = new PrepareIO();
	}
	
	public void begin()
	{
		loadConfig.loadParams();
		prepIO.iterateDirs();
	}
}
