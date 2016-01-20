/* Created by Aleksandra Patrzalek 
 * Version date: 1/20/2016
 *
 * A simple calculator
 *
 * Usage: 
 * Enter a decimal, an operator, and another decimal then press "=".
 * Press Clear to start a new calculation.
 *
 * Some of the ideas / references came from others before me who shared their code.
 * I read through and understood each before using any part, you can view them here:
 * http://www.dreamincode.net/forums/topic/321933-creating-a-calculator-using-jframe/
 * http://www.thecrazyprogrammer.com/2014/06/program-to-create-calculator-using-java-swing.html
 * http://www.leepoint.net/examples/components/calculator/calc.html
 * 
 * Modifications that could be made:
 * - Error checking, such as the divide by zero exception. 
 * - Syntax errors, such as trying to add two decimal points or an operand before a number
 * - keyboard listener
 */
package calculator;

import java.awt.event.*; //handles events, esp. ActionListener
import javax.swing.*;    //visual components

/**
 * @author Aleksandra Patrzalek
 */
public class Calculator extends JFrame implements ActionListener {
    private String  _previousOp        = "=";  // previous operation
    private JTextArea _textfld = new JTextArea(1,20);    
    private static double _firstNum = 0.0, 
                         _lastNum  = 0.0, 
                         _result   = 0.0;    
    private JButton _bdiv,_bmul,_bsub,_badd,_bdec,_beq,_bclr;    
    private JButton[] _button = new JButton[10];
    private String[] _buttonString = { "0", "1", "2", "3", "4",
                              "5", "6", "7", "8", "9"};
    public JFrame frame;    
    
    Calculator (){                
        frame = new JFrame("Calculator");
        _textfld = new JTextArea();
              
        //Create the number buttons and add listeners
        for (int i = 0; i < 10; i++) {
            _button[i] = new JButton();
            _button[i].setText(_buttonString[i]);          
            _button[i].addActionListener(this);
        }
        
        //Create the operands
        _bdiv=new JButton("/");
        _bmul=new JButton("*");
        _bsub=new JButton("-");
        _badd=new JButton("+");
        _bdec=new JButton(".");
        _beq=new JButton("=");      
        _bclr=new JButton("Clear");
        
        //Add listeners to the operands
        _badd.addActionListener(this);
        _bdiv.addActionListener(this);
        _bmul.addActionListener(this);
        _bsub.addActionListener(this);
        _bdec.addActionListener(this);
        _beq.addActionListener(this);
        _bclr.addActionListener(this);        
        
        //Set the window boundaries
        _textfld.setBounds(30,40,280,30);
        _button[7].setBounds(40,100,50,40);
        _button[8].setBounds(110,100,50,40);
        _button[9].setBounds(180,100,50,40);
        _badd.setBounds(250,100,50,40);
        
        _button[4].setBounds(40,170,50,40);
        _button[5].setBounds(110,170,50,40);
        _button[6].setBounds(180,170,50,40);
        _bsub.setBounds(250,170,50,40);
        
        _button[1].setBounds(40,240,50,40);
        _button[2].setBounds(110,240,50,40);
        _button[3].setBounds(180,240,50,40);
        _bmul.setBounds(250,240,50,40);
        
        _bdec.setBounds(40,310,50,40);
        _button[0].setBounds(110,310,50,40);
        _beq.setBounds(180,310,50,40);
        _bdiv.setBounds(250,310,50,40);       
        _bclr.setBounds(60,380,200,40);
        
        //Add the text display field
        frame.add(_textfld);
        
        //Add the buttons to the window
        for( int j = 0; j < 10; j++){
            frame.add(_button[j]);
        }
        
        frame.add(_bdec);
        frame.add(_badd);
        frame.add(_bsub);
        frame.add(_bmul);
        frame.add(_bdiv);
        frame.add(_beq);        
        frame.add(_bclr);
        

        //Set up the window
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setSize(350,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
    } // end Calculator ctr
    
    public static void main(String[] args) {
        Calculator c = new Calculator();
    } //end main

    //Provide the necessary actions for the Clear button
    private void actionClear() {
        _textfld.setText("");
        _previousOp  = "=";
    } //end actionClear
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        //Set up the actions for all the numbers
        if(ae.getSource() == _button[1])
            _textfld.append("1");
        if(ae.getSource() == _button[2])
            _textfld.append("2");
        if(ae.getSource() == _button[3])
            _textfld.append("3");
        if(ae.getSource() == _button[4])
            _textfld.append("4");
        if(ae.getSource() == _button[5])
            _textfld.append("5");
        if(ae.getSource() == _button[6])
            _textfld.append("6");
        if(ae.getSource() == _button[7])
            _textfld.append("7");
        if(ae.getSource() == _button[8])
            _textfld.append("8");
        if(ae.getSource() == _button[9])
            _textfld.append("9");
        if(ae.getSource() == _button[0])
            _textfld.append("0");        
        if(ae.getSource() == _bdec)
            _textfld.append(".");
        
        //Set up the actions for all the operands
        if(ae.getSource() == _badd){
            _previousOp  = "+";
            _firstNum = Double.parseDouble(_textfld.getText());
            _textfld.setText("");
        } //end badd
            
        if(ae.getSource() == _bsub) {
            _previousOp  = "-";
            _firstNum = Double.parseDouble(_textfld.getText());
            _textfld.setText("");
        } // end bsub
            
        if(ae.getSource() == _bmul) {
            _previousOp  = "*";
            _firstNum = Double.parseDouble(_textfld.getText());
            _textfld.setText("");
        } //end bmul
        
        if(ae.getSource() == _bdiv) {
           _previousOp  = "/";
           _firstNum = Double.parseDouble(_textfld.getText());
           _textfld.setText("");
        } //end bdiv
        
        //Add functionality to the clear button
        if(ae.getSource() == _bclr)
            actionClear();
        
        //Once the equal button is pushed, the second number
        //should have been entered. Perform the stored operation
        //on the two numbers provided and return the result
        if(ae.getSource() == _beq){
            _lastNum = Double.parseDouble(_textfld.getText());
            switch(_previousOp){
                case "/":
                    _result = _firstNum / _lastNum;
                    break;
                case "*":
                    _result = _firstNum * _lastNum;
                    break;
                case "-":
                    _result = _firstNum - _lastNum;
                    break;
                case "+":
                    _result = _firstNum + _lastNum;
                    break;
                default:
                    _result = 0.0;
            }
            _textfld.setText("" + _result);
        }//end beq         
    } //end actionPerformed       
} //end class Calculator
