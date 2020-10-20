/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import agents.agenteComprador;
import java.awt.Font;
import javax.swing.JTextArea;

/**
 *
 * @author usuario
 */
public class BookBuyGui extends JFrame
{
    private agenteComprador myAgent;
	public String title;
	private JTextField titleField, priceField;
	
	public BookBuyGui(agenteComprador a) {
		super(a.getLocalName());
		
		myAgent = a;
		
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(2, 2));
		p.add(new JLabel("Titulo del libro:"));
		titleField = new JTextField(15);
		p.add(titleField);
		//p.add(new JLabel("Precio:"));
		//priceField = new JTextField(15);
		//p.add(priceField);
		getContentPane().add(p, BorderLayout.CENTER);
		
		JButton addButton = new JButton("Comprar");
               // JTextArea textArea = new JTextArea("");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				try {
					 title = titleField.getText().trim();
					titleField.setText("");
                                       
				
				}catch(Exception e) {
					JOptionPane.showMessageDialog(BookBuyGui.this, "valores","Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
                JTextArea textArea = new JTextArea("Bucando el libro");
                
                    textArea.setFont(new Font("Serif", Font.ITALIC, 16));
                    textArea.setLineWrap(true);
                    textArea.setWrapStyleWord(true);
		
                 
		p = new JPanel();
		p.add(addButton);
                p.add(textArea);
                
		getContentPane().add(p, BorderLayout.SOUTH);
		
		addWindowListener(new WindowAdapter() {
		  public void windowClosing(WindowEvent e) {
		    myAgent.doDelete();
		  }
		});
		
		setResizable(false);
	}
	
	public void showGui() {
        
	  pack();
	  Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	 // int centerX = (int)screenSize.getWidth() / 2;
	//  int centerY = (int)screenSize.getHeight() / 2;
          setSize(300,200);
	  setLocationRelativeTo(null);
	 // setLocation(centerX - getWidth() / 2, centerY - getHeight() / 2);
	  super.setVisible(true); 
        }
}
