import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import acm.gui.TableLayout;
import acm.program.Program;

public class acm_calc extends Program implements ChangeListener, ActionListener {

	JTextField infix= new JTextField("");			//Text fields, slider and String variables for later use.
	JTextField output = new JTextField("");
	JTextField prec_Text;
	JSlider prec_Slider;
	String base = "";
	String original = "";


	public void init() 
	{	
		setSize (400,600);										//setting the opening window size
		setLayout (new TableLayout (8,4));						//dividing the window into 8 rows and 4 columns

		infix.setEditable(true);								//infix textfield is editable and output is not.
		output.setEditable(false);
		infix.setBackground(Color.WHITE);						//setting color of the textfield

		add (infix, "Gridwidth=4 height=50");					//adding infix and output textfields and setting their dimensions.
		add (output, "Gridwidth=4 height=50");

		String labels[] = {"C","DEL",".","/","7","8","9","x","4","5","6","+","1","2","3","-","0","(",")","="}; //Array of button names that will be iterated through

		String Button_dimension ="Gridwidth=1 height=70";		//setting button dimensions to be referred to later.

		for(int i=0; i < labels.length; i++){					//loop that iterates through array of button names created a new button with each loop.
			JButton current = new JButton(labels[i]);
			current.addActionListener(this);
			add(current, Button_dimension);
		}

		JLabel precL = new JLabel("Precision");					//creating and adding the precision label.
		add(precL,"Gridwidth=1 Gridheight=1");

		int Sval = 6;											
		String Sval_text = Sval + "";

		prec_Slider = new JSlider(0,10, Sval);					//creating new precision slider with a 0-10 range, starting at 6
		prec_Slider.addChangeListener(this);					//adding a change listener in the slider to listen to its motion.
		add(prec_Slider, "Gridwidth=2 Gridheight=1");			

		prec_Text = new JTextField(Sval_text);					//adding the precision text field that will hold the vale of the slider.
		prec_Text.setEditable(false);
		add(prec_Text,"Gridwidth=1 Gridheight=1");

	}

	@Override
	public void stateChanged (ChangeEvent b){					

		String answer;											
		int prec_Slider_value = prec_Slider.getValue();			//linking movement of slider to value in the precision text field.
		prec_Text.setText(prec_Slider_value + "");

		if(original != ""){										//if output answer is cleared slider does nothing.

			String[] decimal_split = original.split("\\.");								//splitting answer to calculation at the decimal.
			String decimal1 = decimal_split[1] + "000000000000000000";					//adding 0's to the end of the decimal part of the answer to fill end of number.
			String decimal = String.format ("%."+ prec_Slider_value +"s", decimal1);	//choosing number of decimal places based on Slider value.

			if(prec_Slider_value == 0){								//if slider value is 0, only include number before "." and not decimal.
				answer = decimal_split[0];
			}
			else{
				answer = decimal_split[0] + "." + decimal;			//if slider value is anything else, include appriate number of decimal places and ".".
			}

			output.setText(answer);									//set output text field to value with correct number of decimal places.
		}

	}

	@Override
	public void actionPerformed (ActionEvent A){
		try{
			if (A.getActionCommand() == "="){					

				prec_Slider.setValue(6);		
				prec_Text.setText(6 + "");
				original = JCalc.main(base);					//defining original as value of calculation to be used in precision operations.

				String[] decimal_split = original.split("\\.");		//after every = button press revert back to original precision.
				String decimal1 = "." + decimal_split[1] + "00000000000000";
				String decimal = String.format ("%.7s", decimal1);
				String output_formatted = decimal_split[0] + decimal;

				output.setText(output_formatted);

			}

			else if (A.getActionCommand() == "DEL"){		//DEL button removes last value in input text field.
				if(base != ""){
					StringBuilder removing = new StringBuilder(base);
					removing.deleteCharAt(base.length()-1);
					base = removing.toString();
					infix.setText(base);
				}
			}

			else if(A.getActionCommand() == "C"){		//Clearing input and output textfields  and answer value when pressing C button.
				base = "";
				infix.setText(base);
				output.setText(base);
				original = "";

			}
			else {
				base += A.getActionCommand();			//if the button pressed is anything other than above, add it to infix for calculation.
				infix.setText(base);
			}

		}
		catch(Exception f){								//catching errors for illegal input.
			output.setText("Error");
		}

	}

}
