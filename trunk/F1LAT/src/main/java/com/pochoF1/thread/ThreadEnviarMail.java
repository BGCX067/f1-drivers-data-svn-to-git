package com.pochoF1.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pochoF1.utils.EmailUtil;

public class ThreadEnviarMail implements Runnable {
	
	private static Logger log = LoggerFactory.getLogger(ThreadEnviarMail.class);
	
	private String emailContacto = null;
	private String mensaje = null;
	private String nombreContacto = null;
	
	 public ThreadEnviarMail(String emailContacto,String mensaje,String nombreContacto) {
		 this.emailContacto = emailContacto;
		 this.mensaje = mensaje;
		 this.nombreContacto = nombreContacto;
	   }
	
	@Override
	public void run() {
		log.info("Comenzo el proceso de envio de mail");
		EmailUtil.enviarMail(emailContacto,mensaje,nombreContacto);
		log.info("Finalizo el proceso de envio de mail");
	}

}
