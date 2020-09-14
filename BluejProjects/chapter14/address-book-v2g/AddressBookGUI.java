import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

/**
 * Provide a GUI view of an AddressBook.
 * Different panes provide access to the data in the address book.
 *
 *      One to search the address book.
 *
 *      One to allow a set of contact details to be entered.
 *      The add button adds the data to the address book.
 *
 *      One to show all the entries in the book.
 *
 * @author David J. Barnes and Michael Kölling.
 * @version 2016.02.29
 */
public class AddressBookGUI extends JFrame
{
    // Size preferences for this frame.
    private static final int PREFERRED_WIDTH = 500;
    private static final int PREFERRED_HEIGHT = 500;
    private static final Dimension PREFERRED_SIZE =
                            new Dimension(PREFERRED_WIDTH,PREFERRED_HEIGHT);
    // The address book to be viewed and manipulated.
    private AddressBook book;
    
    /**
     * Create the frame with its panels.
     * @param book The address book to be manipulated.
     */
    public AddressBookGUI(AddressBook book)
    {
        this.book = book;
        setTitle("Address Book");
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev)
            {
                setVisible(false);
            }
        });

        final Container contentPane = getContentPane();
        JTabbedPane tabbedArea = new JTabbedPane();
        tabbedArea.add("Search the Entries", setupSearchArea());
        tabbedArea.add("Enter New Details",  setupDataEntry());
        tabbedArea.add("List the Entries",   setupListArea());
        contentPane.add(tabbedArea);

        setSize(PREFERRED_SIZE);
    }
    
    /**
     * Show the window if it has been closed.
     */
    public void showWindow()
    {
        setVisible(true);
    }

    /**
     * @return The preferred size of this window.
     */
    public Dimension getPreferredSize()
    {
        return PREFERRED_SIZE;
    }

    /**
     * Set up the panel for data entry.
     * @return The completed panel.
     */
    private Container setupDataEntry()
    {
        // Set up the name field.
        Box nameLabelArea = Box.createHorizontalBox();
        nameLabelArea.add(new JLabel("Name", JLabel.LEFT));
        nameLabelArea.add(Box.createGlue());
        final JTextField nameField = new JTextField(50);
        Box nameArea = Box.createVerticalBox();
        nameArea.add(nameLabelArea);
        nameArea.add(nameField);

        // Set up the phone number area.
        Box phoneLabelArea = Box.createHorizontalBox();
        phoneLabelArea.add(new JLabel("Phone", JLabel.LEFT));
        phoneLabelArea.add(Box.createGlue());
        final JTextField phoneField = new JTextField(50);
        Box phoneArea = Box.createVerticalBox();
        phoneArea.add(phoneLabelArea);
        phoneArea.add(phoneField);
        
        // Set up the address area.
        Box addressLabelArea = Box.createHorizontalBox();
        addressLabelArea.add(new JLabel("Address", JLabel.LEFT));
        addressLabelArea.add(Box.createGlue());
        Box addressArea = Box.createVerticalBox();
        final JTextArea address = new JTextArea(10, 50);
        addressArea.add(addressLabelArea);
        addressArea.add(address);

        // Layout the entry-details fields in a panel.
        Box singleLineFields = Box.createVerticalBox();
        singleLineFields.add(nameArea);
        singleLineFields.add(phoneArea);
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BorderLayout());
        detailsPanel.add(singleLineFields, BorderLayout.NORTH);
        detailsPanel.add(addressArea, BorderLayout.CENTER);

        // Set up the buttons.
        JPanel buttonArea = new JPanel();
        JButton add = new JButton("Add");
        JButton clear = new JButton("Clear");

        // Take the necessary action to add the new details.
        add.addActionListener(e -> {
                book.addDetails(
                    new ContactDetails(nameField.getText(),
                                       phoneField.getText(),
                                       address.getText()));
            }
        );

        // Clear the data-entry areas.
        clear.addActionListener(e -> {
                nameField.setText("");
                phoneField.setText("");
                address.setText("");
            }
        );
        buttonArea.add(add);
        buttonArea.add(clear);

        // Layout the details area above the button area.
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(detailsPanel, BorderLayout.CENTER);
        panel.add(buttonArea, BorderLayout.SOUTH);
        return panel;
    }

    /**
     * Set up the panel for searching the entries.
     * @return The completed panel.
     */
    private Container setupSearchArea()
    {
        // Set up the area for entering the search string.
        Box searchLabelArea = Box.createHorizontalBox();
        searchLabelArea.add(new JLabel("Search", JLabel.LEFT));
        searchLabelArea.add(Box.createGlue());
        final JTextField searchField = new JTextField(50);
        Box searchArea = Box.createHorizontalBox();
        searchArea.add(searchLabelArea);
        searchArea.add(searchField);
        
        // Set up the area where the resuts will be displayed.
        final JTextArea resultList = new JTextArea(10,50);
        resultList.setEditable(false);
        JScrollPane scrollArea =
                new JScrollPane(resultList,
                                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        // Any change to the name field causes a new search of
        // the address book to be made.
        searchField.getDocument().addDocumentListener(new DocumentListener(){
            public void changedUpdate(DocumentEvent ev)
            {
                research();
            }
            
            public void insertUpdate(DocumentEvent ev)
            {
                research();
            }
            
            public void removeUpdate(DocumentEvent ev)
            {
                research();
            }
            
            /**
             * Search the address book and present the results unless
             * the search string is empty, in which case the results
             * area is cleared.
             */
            private void research()
            {
                String searchString = searchField.getText();
                StringBuilder buffer = new StringBuilder();
                if(searchString.length() > 0) {
                    ContactDetails[] results = book.search(searchString);
                    for(int i = 0; i < results.length; i++) {
                        buffer.append(results[i].toString()).append("\n\n");
                    }
                }
                resultList.setText(buffer.toString());
            }
        });

        JPanel listArea = new JPanel();
        listArea.setLayout(new BorderLayout());
        listArea.add(searchArea, BorderLayout.NORTH);
        listArea.add(scrollArea, BorderLayout.CENTER);

        return listArea;
    }

    /**
     * Set up the panel for listing the entries.
     * @return The completed panel.
     */
    private Container setupListArea()
    {
        // Set up the area where the details will be displayed.
        final JTextArea details = new JTextArea(10, 50);
        details.setEditable(false);
        JScrollPane scrollArea =
                new JScrollPane(details,
                                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        // Set up the buttons.
        JPanel buttonArea = new JPanel();
        JButton list = new JButton("List");
        JButton clear = new JButton("Clear");
        
        // List all of the entries.
        list.addActionListener(e -> details.setText(book.listDetails()));

        // Clear the details area.
        clear.addActionListener(e -> details.setText(""));
        buttonArea.add(list);
        buttonArea.add(clear);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(scrollArea, BorderLayout.CENTER);
        panel.add(buttonArea, BorderLayout.SOUTH);

        return panel;
    }
}
