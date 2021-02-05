//package org.springframework.samples.petclinic.web;
//
//import java.text.ParseException;
//import java.util.Collection;
//import java.util.Locale;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.format.Formatter;
//import org.springframework.samples.petclinic.model.Mensaje;
//import org.springframework.samples.petclinic.model.User;
//import org.springframework.samples.petclinic.service.MensajesService;
//import org.springframework.stereotype.Component;
//
//@Component
//public class MensajesFormatter implements Formatter<Mensaje>{
//	private final MensajesService mensajesService;
//	   
//    @Autowired
//    public MensajesFormatter(MensajesService mensajesService) {
//        this.mensajesService = mensajesService;
//    }
//    @Override
//    public String print(Mensaje mensaje, Locale locale) {
//        return mensaje.getCuerpo();
//    }
//    @Override
//    public User parse(String text, Locale locale) throws ParseException {
//        
//        throw new ParseException("client not found: " + text, 0);
//    }
//}