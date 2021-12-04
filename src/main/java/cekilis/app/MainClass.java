package cekilis.app;

import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MainClass {
	
	public static void main(String[] args) {
		String katilimciFilePath = System.getenv("reis_path");
		String reisMail = System.getenv("reis_mail");
		String reisPass = System.getenv("reis_pass");
		
		
		List<KatilimciPOJO> katilimcilar = Utilities.getKatilimci(katilimciFilePath);
		



		List<KatilimciPOJO> pairedKatilimcilar = Utilities.pairKatilimci(katilimcilar);
		

	    
	    for(KatilimciPOJO katilimci : pairedKatilimcilar) {
	    	String to =  katilimci.mail ; 
	 	    String subject = "Hangi reise hediye alacağını biliyon mu " + katilimci.name + "!";
	 	    String body = "Öncelikle selamın aleyküm " + katilimci.name + " reis,\nKime hediye alacağını biliyon mu? Ben biliyom!.\n\nBu şanslı kişi aşağıda belirtilmiştir:\n\n\n\n\n\n\n\n" + katilimci.receiverName + " reis!\n\nSize iyi günler dilerim ve ben kaçarım.\n\nSaygılarımla, Büyük Reis!\n\n\n\n";
	 	    body += "\n NOT: Kodumun linki: https://github.com/AdnanCigtekin/new-year-raffle";
	 	    Utilities.sendFromGMail(reisMail, reisPass, to, subject, body);
	 	    System.out.println("Sent mail to " + katilimci.name + " mail :" + katilimci.mail);
	    }
	   
	}

	


	
	
}
