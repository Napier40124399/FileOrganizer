package GUI;

import java.awt.AWTException;
import java.awt.CheckboxMenuItem;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import FileOrganizer.Manager;
import IO.ConfigData;

public class TrayGUI
{
	private Manager manager;
	private Timer timer;
	private TrayIcon trayIcon;
	
	public TrayGUI()
	{
		manager = new Manager();
	}

	public void createAndShowGUI()
	{
		// Check the SystemTray support
		if (!SystemTray.isSupported())
		{
			return;
		}
		final PopupMenu popup = new PopupMenu();
		trayIcon = new TrayIcon(createImage("trayIcon-Idle.png","Idle"));
		final SystemTray tray = SystemTray.getSystemTray();

		// Create a popup menu components
		MenuItem aboutItem = new MenuItem("About");
		CheckboxMenuItem checkBoxAuto = new CheckboxMenuItem("Auto manage");
		Menu displayMenu = new Menu("Force organize");
		MenuItem allDirs = new MenuItem("All Directories");
		MenuItem exitItem = new MenuItem("Exit");

		// Add components to popup menu
		popup.add(aboutItem);
		popup.addSeparator();
		popup.add(checkBoxAuto);
		popup.addSeparator();
		popup.add(displayMenu);
		displayMenu.add(allDirs);
		popup.add(exitItem);

		trayIcon.setPopupMenu(popup);

		try
		{
			tray.add(trayIcon);
		} catch (AWTException e)
		{
			System.out.println("TrayIcon could not be added.");
			return;
		}

		trayIcon.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				JOptionPane.showMessageDialog(null, "TODO: Window pop-up and GUI."); // TODO
			}
		});

		aboutItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				JOptionPane.showMessageDialog(null, "Written by James F. Taylor\nContact: JamesFTaylorWork@gmail.com");
			}
		});

		checkBoxAuto.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent e)
			{
				int cb1Id = e.getStateChange();
				if (cb1Id == ItemEvent.SELECTED)
				{
					start();
					timer.start();
				} else
				{
					timer.stop();
				}
			}
		});

		allDirs.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				start();
			}
		});

		exitItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				tray.remove(trayIcon);
				System.exit(0);
			}
		});
		
		timer = new Timer(3600000, new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				start();
			}
		});
		
		trayIcon.setImageAutoSize(true);
	}

	private static Image createImage(String imgName, String description)
	{
		try
		{
			return (new ImageIcon(ConfigData.getConfigLoc()+imgName, description)).getImage();
		} catch (Exception e)
		{
			return null;
		}
	}
	
	private void start()
	{
		trayIcon.setImage(createImage("trayIcon-Busy.png","Working..."));
		manager.begin();
		trayIcon.setImage(createImage("trayIcon-Idle.png","Idle"));
	}
}
