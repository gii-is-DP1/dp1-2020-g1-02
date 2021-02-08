package org.springframework.samples.petclinic.customvalidators;

import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DNIValidator implements ConstraintValidator<DNIConstraint, String> {
	
	public static final Map<Integer, String> letrasDNI = new HashMap<Integer, String>() {{
		put( 0,"T");put( 1,"R" ); put( 2,"W" );
		put( 3,"A" );put( 4,"G" );put( 5,"M" ); 
		put( 6,"Y" );put( 7,"F" );put( 8,"P" ); 
		put( 9,"D" );put( 10,"X" );put( 11,"B" ); 
		put( 12,"N" );put( 13,"J" );put( 14,"Z" );
		put( 15,"S" );put( 16,"Q" );put( 17,"V" );
		put( 18,"H" );put( 19,"L" );put( 20,"C" ); 
		put( 21,"K" ); put( 22,"E" );}}; 
	

  @Override
  public void initialize(DNIConstraint dni) {
  }

  @Override
  public boolean isValid(String dni, ConstraintValidatorContext cxt) {
      return dni != null && dni.matches("[0-9]{8}[A-Z]") && dniIsCorrect(dni);
  }
  
  public boolean dniIsCorrect(String dni) {
	  String letra  = dni.substring(8);
	  Integer parteNumerica = Integer.valueOf(dni.substring(0, 8));
	  return letra.equals(letrasDNI.get(parteNumerica%23));
  }

}
