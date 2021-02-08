package org.springframework.samples.petclinic.customvalidators;

import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DNIValidator implements ConstraintValidator<DNIConstraint, String> {
	
	public static final Map<Integer, String> letrasDNI = Map.ofEntries(Map.entry( 0,"T"),
			Map.entry( 1,"R" ), Map.entry( 2,"W" ), Map.entry( 3,"A" ),
			Map.entry( 4,"G" ), Map.entry( 5,"M" ), Map.entry( 6,"Y" ),
			Map.entry( 7,"F" ), Map.entry( 8,"P" ), Map.entry( 9,"D" ),
			Map.entry( 10,"X" ),Map.entry( 11,"B" ),Map.entry( 12,"N" ),
			Map.entry( 13,"J" ),Map.entry( 14,"Z" ),Map.entry( 15,"S" ),
			Map.entry( 16,"Q" ),Map.entry( 17,"V" ),Map.entry( 18,"H" ),
			Map.entry( 19,"L" ),Map.entry( 20,"C" ),Map.entry( 21,"K" ),Map.entry( 22,"E" )); 
	

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
