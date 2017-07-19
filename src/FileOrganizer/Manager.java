package FileOrganizer;

import IO.LoadConfig;
import IO.PrepareIO;

public class Manager
{
	//Instances
	private PrepareIO prepIO;
	
	public Manager()
	{
		prepIO = new PrepareIO();
	}
	
	public void begin()
	{
		LoadConfig loadConfig = new LoadConfig();
		loadConfig.loadParams();
	}
}
