package actforothers;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;

public class ServerIPDialog extends ONCConnectDialog
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String serverAddress;
	
	ServerIPDialog(JFrame pf, String failedaddress)
	{
		super(pf);
		this.setTitle("Enter ONC Server IP Address");
		
		lblMssg1.setText("<html><b><i><font color=red>Unable to establish a connection with the " +								
						 "<br><center>ONC Server at IP Address:</center></font></i></b></html>");
		lblMssg2.setText("<html><center>" + failedaddress + "</center></html>");
		lblTF1.setText("Enter New IP Address:");
		btnAction.setText("Connect");		
	}
	
	String getNewAddress() { return serverAddress; }
	
	/**********************************************************************************
	 * This Action Listener processes events generated by the Action JButton. It reads
	 * the user id and password text fields and compares for matches in the users array 
	 * list class member. If a match is found, it sets the user index to the correct 
	 * position in the array and disposes the dialog.
	 * If a match is not found, it displays an error message encouraging the user to
	 * try again. Each failed login event is counted and the Login JButton text is
	 * updated with the attempt number
	 **********************************************************************************/
	@Override
	public void actionPerformed(ActionEvent e)
	{	
		if(e.getSource() == btnAction)
		{
			serverAddress = tf1.getText().trim();
			this.dispose();	
		}
	}
}