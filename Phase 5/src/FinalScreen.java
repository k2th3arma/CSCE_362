import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class FinalScreen extends JFrame implements ActionListener{

    
    private final JSplitPane splitPane;  
    private final JPanel topPanel;       
    private final JPanel bottomPanel;    
    private final JButton button; 
    private  JTextArea textField;
    public FinalScreen(){

    	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	
        splitPane = new JSplitPane();

        topPanel = new JPanel();        
        bottomPanel = new JPanel();      
        textField = new JTextArea();
        button = new JButton("Exit");
        textField.setEditable(false);     
        
        setPreferredSize(new Dimension(600, 600));     
        
        getContentPane().setLayout(new GridLayout());  
       
        getContentPane().add(splitPane);               

        splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);  
        splitPane.setDividerLocation(500);                    
        splitPane.setTopComponent(topPanel);                  
        splitPane.setBottomComponent(bottomPanel);       
        
        textField.setText("Rank	Score			Name" + System.lineSeparator());
        
        for(int i = 0; i < Data.b.size(); ++i){
        	textField.append(Data.b.get(i).toString() + System.lineSeparator());   	
        }
        
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));               
        
        topPanel.add(textField);
        bottomPanel.add(button);
        button.addActionListener(this);

        pack();
        setVisible(true);
    }

	@Override
	public void actionPerformed(ActionEvent event) {
		Object source = event.getSource();
		
		if( source == button){
			System.exit(DISPOSE_ON_CLOSE);		
		}
	}
}

