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
	//TODO: Before sending real mails set reis_path environment variable to real file path
	
	public static void main(String[] args) {
		String katilimciFilePath = System.getenv("reis_path");
		String reisMail = System.getenv("reis_mail");
		String reisPass = System.getenv("reis_pass");
		
		
		List<KatilimciPOJO> katilimcilar = Utilities.getKatilimci(katilimciFilePath);
		
		//TODO: remove these when sending the new emails
		katilimcilar.get(0).senderName = "test Sender";
		katilimcilar.get(0).receiverName = "test receiver";

		//TODO: Use the code in the comment when sending the real mails
		List<KatilimciPOJO> pairedKatilimcilar = katilimcilar;//Utilities.pairKatilimci(katilimcilar);
		

	    
	    for(KatilimciPOJO katilimci : pairedKatilimcilar) {
	    	String to =  katilimci.mail ; 
	 	    String subject = "Hangi reise hediye alacağını biliyon mu " + katilimci.name + "!";
	 	    String body = "Selamın aleyküm " + katilimci.name + " reis,\nKime hediye alacağını biliyon mu? Ben biliyom bu sefer ehehehehe.\n\nBu şanslı kişi aşağıda belirtilmiştir:\n\n" + katilimci.receiverName + " reis! UUUİİİİYYYYYYYYY!\n\nSize iyi günler dilerim ve ben kaçarım.\n\nSaygılarımla,\nAdnanın bilgisayarı\n\n\n";
	 	    body += "\n NOT:Kodumun linkine şuradan ulaşabilirsiniz: https://github.com/AdnanCigtekin/new-year-raffle";
	 	    Utilities.sendFromGMail(reisMail, reisPass, to, subject, body);
	 	    System.out.println("Sent mail to " + katilimci.name + " mail :" + katilimci.mail);
	    }
	   
	}

	


	
	
}
