/**
V, Kara
COP-3252
Assignment 6
3/27/17

Driver that interacts with the user
Template provided by Professor Richard Hurst (FSU, Spring 2017), details filled in by me labeled below

*/


// ArrayAccess.java
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ArrayAccess extends JFrame
{
    private JTextField inputField;
    private JTextField retrieveField1;
    private JTextField retrieveField2;
    private JTextField outputField;
    private JPanel inputArea;
    private JPanel retrieveArea;
    private JPanel outputArea;

    private int num;
    private int index = 0;
    private int array[] = new int[ 10 ];
    private String result;

    // set up GUI
    public ArrayAccess()
    {
        super( "Accessing Array values" );
        setLayout( new FlowLayout() );

        // set up input Panel
        inputArea = new JPanel();
        inputArea.add( new JLabel( "Enter array elements here" ) );
        inputField = new JTextField( 10 );
        inputArea.add( inputField );
        inputField.addActionListener(
                
                //ActionListener() created by Kara V
                
                new ActionListener()
                {   
                    public void actionPerformed( ActionEvent e ) {
                        try{

                            num =  Integer.parseInt(inputField.getText());
                            array[index] = num;
                            ++index;
                        }

                        catch (ArrayIndexOutOfBoundsException outOfBounds) {
                        JOptionPane.showMessageDialog(null, "Cannot assign this number to an index. Array is full.", "Array Full", JOptionPane.ERROR_MESSAGE);
                        }

                        catch (NumberFormatException inputMismatch) {
                        JOptionPane.showMessageDialog(null, "You must enter an integer value. Please re-enter your decision.", "Invalid Entry", JOptionPane.ERROR_MESSAGE);
                        }

                        inputField.setText( "" );

                    } // end method actionPerformed
                } // end anonymous inner class
        ); // end call to addActionListener

        // set up retrieve Panel
        retrieveArea = new JPanel( new GridLayout( 2, 2 ) );
        retrieveArea.add( new JLabel( "Enter number to retrieve" ) );
        retrieveField1 = new JTextField( 10 );
        retrieveArea.add( retrieveField1 );
        retrieveField1.addActionListener(
               
               //ActionListener() created by Kara V
               
               new ActionListener()
                {
                    public void actionPerformed( ActionEvent event ) {
                        try {

                            num = Integer.parseInt(retrieveField1.getText());
                            Boolean exists = false;   //will determine if any matches at all in the array and if exception should be thrown
                            result = "";

                            //searches array for matches...when a match exists, adds it on to "result" to print once loop completes iterating
                            for (int i = 0; i < index; ++i) {
                                if (num == array[i]) {
                                    result += "[" + Integer.toString(i) + "] ";
                                    exists = true;
                                }
                            }

                            //true if no matches occurred
                            if (!exists)
                                throw new NumberNotFoundException("That number was not found in this array.");  //calls 1-argument constructor in class NumberNotFoundException

                            else
                                outputField.setText("Number " + retrieveField1.getText() + " located at indices: " + result);
                        }

                        catch (NumberNotFoundException exception) {
                            JOptionPane.showMessageDialog(null, exception.getMessage(), "Number Not Found", JOptionPane.ERROR_MESSAGE);
                            outputField.setText(""); //clears output field on screen
                        }

                        catch (NumberFormatException inputMismatch) {
                            JOptionPane.showMessageDialog(null, "You must enter an integer value. Please re-enter your decision.", "Invalid Entry", JOptionPane.ERROR_MESSAGE);
                            outputField.setText(""); //clears output field on screen
                        }

                        retrieveField1.setText("");

                    } // end method actionPerformed
                } // end anonymous inner class
        ); // end call to addActionListener

        retrieveArea.add( new JLabel( "Enter index to retrieve" ) );
        retrieveField2 = new JTextField( 10 );
        retrieveArea.add( retrieveField2 );
        retrieveField2.addActionListener(
               
            
                //ActionListener() created by Kara V
                
                new ActionListener()
                {
                    public void actionPerformed( ActionEvent event ) {
                        try {
                            num =  Integer.parseInt(retrieveField2.getText());
                            if (num > index)
                                throw new ArrayIndexOutOfBoundsException();  //throws exception if index has not been initialized

                            String result = Integer.toString(array[num]);

                            outputField.setText("Index [" + retrieveField2.getText() + "] contains number " + result);
                        }

                        catch (ArrayIndexOutOfBoundsException outOfBounds) {
                            JOptionPane.showMessageDialog(null, "This array has no set index of that value.", "Index Out Of Bounds", JOptionPane.ERROR_MESSAGE);
                            outputField.setText( "" );  //clears output field on screen
                        }

                        catch (NumberFormatException inputMismatch) {
                            JOptionPane.showMessageDialog(null, "You must enter an integer value. Please re-enter your decision.", "Invalid Entry", JOptionPane.ERROR_MESSAGE);
                            outputField.setText( "" );  //clears output field on screen
                        }

                        retrieveField2.setText( "" );

                    } // end anonymous inner class
                } // end new ActionListener
        ); // end call to addActionListener

        // set up output Panel
        outputArea = new JPanel();
        outputArea.add( new JLabel( "Result" ) );
        outputField = new JTextField( 30 );
        outputField.setEditable( false );
        outputArea.add( outputField );

        add( inputArea );
        add( retrieveArea );
        add( outputArea );
    }  // end constructor
} // end class ArrayAccess
