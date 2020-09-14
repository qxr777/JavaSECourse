import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import java.io.*;

/**
 * A simple sound player. To start, create an instance of this class.
 * 
 * The sound player provides an interface to the MusicOrganizer class
 * from chapter 4.
 * 
 * @author Michael Kölling and David J. Barnes
 * @version 1.0
 */
public class MusicPlayerGUI extends JFrame
{
    private static final String VERSION = "Version 1.0";
    private static final String AUDIO_DIR = "../audio";
    
    private JList<String> fileList;
    private JSlider slider;
    private JLabel infoLabel;
    private MusicOrganizer organizer;
    // A player for the music tracks.
    private MusicPlayer player;
    // The current track list.
    private List<Track> trackList;

    /**
     * Main method for starting the player from a command line.
     */
    public static void main(String[] args)
    {
        MusicPlayerGUI gui = new MusicPlayerGUI();
    }
    
    /**
     * Create a SoundPlayer and display its GUI on screen.
     */
    public MusicPlayerGUI()
    {
        super("Music Player");
        organizer = new MusicOrganizer(AUDIO_DIR);
        player = new MusicPlayer();
        
        makeFrame();
    }
    
    /**
     * Play the sound file currently selected in the file list. If there is no
     * selection in the list, or if the selected file is not a sound file, 
     * do nothing.
     */
    private void play()
    {
        int index = fileList.getSelectedIndex();
        if(index >= 0 && index < trackList.size()) {
            slider.setValue(0);
            player.startPlaying(trackList.get(index).getFilename());
        }
    }

    /**
     * Stop the currently playing sound file (if there is one playing).
     */
    private void stop()
    {
        player.stop();
    }

    /**
     * Stop the currently playing sound file (if there is one playing).
     */
    private void pause()
    {
        player.pause();
    }

    /**
     * Resume a previously suspended sound file.
     */
    private void resume()
    {
        player.resume();
    }

    /**
     * Display information about a selected sound file (name and clip length).
     * @param message The message to display.
     */
    private void showInfo(String message)
    {
        infoLabel.setText(message);
    }
    
    /**
     * Quit function: quit the application.
     */
    private void quit()
    {
        System.exit(0);
    }
    
    
    /**
     * About function: show the 'about' box.
     */
    private void showAbout()
    {
        JOptionPane.showMessageDialog(this, 
                    "Music Player\n" + VERSION,
                    "About Music Player", 
                    JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Set the ordering of the track list.
     * @param ordering The ordering to use.
     */
    private void setListOrdering(String ordering)
    {
        trackList = organizer.sortByField(ordering);
        String[] tracks = getTracksDisplayList(trackList);
        fileList.setListData(tracks);
    }

    /**
     * Get a display version of the track list.
     * @param trackList The list of tracks to be displayed.
     * @return The tracks in display format.
     */
    private String[] getTracksDisplayList(List<Track> trackList)
    {
        int numTracks = trackList.size();
        String[] tracks = new String[numTracks];
        for(int i = 0; i < numTracks; i++) {
            String[] fields = trackList.get(i).getFields();
            StringBuilder listing = new StringBuilder();
            for(String field : fields) {
                listing.append(field);
                listing.append(" ");
            }
            tracks[i] = listing.toString().trim();
        }
        return tracks;
    }

    // ---- Swing stuff to build the frame and all its components and menus ----
    
    /**
     * Create the complete application GUI.
     */
    private void makeFrame()
    {
        // the following makes sure that our application exits when
        // the user closes its window
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        JPanel contentPane = (JPanel) getContentPane();
        contentPane.setBorder(new EmptyBorder(6, 10, 10, 10));

        makeMenuBar();
        
        // Specify the layout manager with nice spacing
        contentPane.setLayout(new BorderLayout(8, 8));

        // Create the left side with combobox and scroll list
        JPanel leftPane = new JPanel();
        {
            leftPane.setLayout(new BorderLayout(8, 8));

            // Set up components for ordering the list of tracks.
            JPanel orderingPanel = new JPanel();
            orderingPanel.setLayout(new BorderLayout());
            orderingPanel.add(new JLabel("Order by:"), BorderLayout.NORTH);
            
            // Get the list of field names, used for ordering.
            String[] ordering = Track.FIELDS;
            
            // Create the combo box.
            JComboBox<String> formatList = new JComboBox<>(ordering);
            formatList.addActionListener(e -> {
                int index = formatList.getSelectedIndex();
                if(index >= 0) {
                    String selectedOrder = formatList.getItemAt(index);
                    setListOrdering(selectedOrder);
                }
            });
            orderingPanel.add(formatList, BorderLayout.CENTER);
            
            leftPane.add(orderingPanel, BorderLayout.NORTH);
    
            // Create the scrolled list for track listing.
            fileList = new JList<>();
            fileList.setForeground(new Color(140,171,226));
            fileList.setBackground(new Color(0,0,0));
            fileList.setSelectionBackground(new Color(87,49,134));
            fileList.setSelectionForeground(new Color(140,171,226));
            JScrollPane scrollPane = new JScrollPane(fileList);
            scrollPane.setColumnHeaderView(new JLabel("Audio files"));
            leftPane.add(scrollPane, BorderLayout.CENTER);
            
            // Set up the initial listing.
            setListOrdering(ordering[0]);
        }
        contentPane.add(leftPane, BorderLayout.CENTER);

        // Create the center with image, text label, and slider
       JPanel centerPane = new JPanel();
        {
            centerPane.setLayout(new BorderLayout(8, 8));
    
            JLabel image = new JLabel(new ImageIcon("title.jpg"));
            centerPane.add(image, BorderLayout.NORTH);
            centerPane.setBackground(Color.BLACK);

            infoLabel = new JLabel("  ");
            infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
            infoLabel.setForeground(new Color(140,171,226));
            centerPane.add(infoLabel, BorderLayout.CENTER);

            slider = new JSlider(0, 100, 0);
            TitledBorder border = new TitledBorder("Seek");
            border.setTitleColor(Color.white);
            slider.setBorder(new CompoundBorder(new EmptyBorder(6, 10, 10, 10), border));
            // Provide a body for the change-listener lambda to react to changes
            // of the slider.
            slider.addChangeListener(e -> { });
            slider.setBackground(Color.BLACK);
            slider.setMajorTickSpacing(25);
            slider.setPaintTicks(true);
            centerPane.add(slider, BorderLayout.SOUTH);
        }
        contentPane.add(centerPane, BorderLayout.EAST);

        // Create the toolbar with the buttons
        JPanel toolbar = new JPanel();
        {
            toolbar.setLayout(new GridLayout(1, 0));
  
            JButton button = new JButton("Play");
            button.addActionListener(e -> play());
            toolbar.add(button);
            
            button = new JButton("Stop");
            button.addActionListener(e -> stop());
            toolbar.add(button);
    
            button = new JButton("Pause");
            button.addActionListener(e -> pause());
            toolbar.add(button);
            
            button = new JButton("Resume");
            button.addActionListener(e -> resume());
            toolbar.add(button);

        }
        
        contentPane.add(toolbar, BorderLayout.NORTH);

        // building is done - arrange the components      
        pack();
        
        // place this frame at the center of the screen and show
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(d.width/2 - getWidth()/2, d.height/2 - getHeight()/2);
        setVisible(true);
    }
    
    /**
     * Create the main frame's menu bar.
     */
    private void makeMenuBar()
    {
        final int SHORTCUT_MASK =
            Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();

        JMenuBar menubar = new JMenuBar();
        setJMenuBar(menubar);
        
        JMenu menu;
        JMenuItem item;
        
        // create the File menu
        menu = new JMenu("File");
        menubar.add(menu);
        
        item = new JMenuItem("Quit");
            item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, SHORTCUT_MASK));
            item.addActionListener(e -> quit());
        menu.add(item);

        // create the Help menu
        menu = new JMenu("Help");
        menubar.add(menu);
        
        item = new JMenuItem("About Music Player...");
            item.addActionListener(e -> showAbout());
        menu.add(item);
    }
}
