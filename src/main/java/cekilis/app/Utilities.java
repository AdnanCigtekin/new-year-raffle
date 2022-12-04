package cekilis.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Utilities {


	public static void sendFromOutlook(String from, String pass, String to, String subject, String body) {
		Properties props = System.getProperties();
		String host = "smtp-mail.outlook.com";
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.user", from);
		props.put("mail.smtp.password", pass);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");

		Session session = Session.getDefaultInstance(props);
		MimeMessage message = new MimeMessage(session);

		try {
			message.setFrom(new InternetAddress(from));

			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			message.setSubject(subject);
			message.setText(body);
			Transport transport = session.getTransport("smtp");
			transport.connect(host, from, pass);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		}
		catch (AddressException ae) {
			ae.printStackTrace();
		}
		catch (MessagingException me) {
			me.printStackTrace();
		}
	}

	
    public static void sendFromGMail(String from, String pass, String to, String subject, String body) {
        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(from));

            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            message.setSubject(subject);
            message.setText(body);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }
        catch (AddressException ae) {
            ae.printStackTrace();
        }
        catch (MessagingException me) {
            me.printStackTrace();
        }
    }
    
    public static List<KatilimciPOJO> getKatilimci(String path) {
    	File myFile = new File(path);
    	try {
			Scanner myReader = new Scanner(myFile);
			List result = new ArrayList<KatilimciPOJO>();
			while (myReader.hasNextLine()) {
		        String data = myReader.nextLine();
		        String[] splittedData = data.split("\t"); 
		        KatilimciPOJO katilimci = new KatilimciPOJO();
		        katilimci.mail = splittedData[0];
		        katilimci.name = splittedData[1];
		        result.add(katilimci);
		      }
		      myReader.close();
		      return result;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
    	
    }

    public static List<KatilimciPOJO> pairKatilimci(List<KatilimciPOJO> input){


    	Random rand = new Random();
    	Boolean acceptablePair = false;
    	while(!acceptablePair) {
    		//reset pairs
        	for(int i = 0; i < input.size(); i++) {
        		input.get(i).receiverName = null;
        		input.get(i).senderName = null;
        	}
        	
        	//Pair up people
        	for(int i = 0; i < input.size(); i++) {
    			System.out.println("Currently looking for " + input.get(i).name);
        		while(input.get(i).receiverName == null) {
        	    	int curRand = rand.nextInt(input.size());

        			
        			if(!input.get(curRand).name.equals(input.get(i).name) && input.get(curRand).senderName == null) {
        				input.get(curRand).senderName = input.get(i).name;
        				input.get(i).receiverName = input.get(curRand).name;
        				
        				System.out.println(input.get(i).name + " will buy present to " + input.get(curRand).name);

        			}
        			
        		}
        	}
        	
        	//Check pairs if they are acceptable
        	for(int i = 0; i < input.size(); i++) {
        		if(input.get(i).receiverName.equals(input.get(i).senderName)) {
        			acceptablePair = false;
        			break;
        		}
        		acceptablePair = true;
        	}
        	
    	}

    	
    	for(KatilimciPOJO katilimci : input) {
    		System.out.println(katilimci.name + " " + katilimci.receiverName + " " + katilimci.senderName);
    	}
    	
    	return input;
    }



	public static String getColor(Boolean willSelectColor){
		if(willSelectColor){
			Random rand = new Random();
			int selectedColorIndex = rand.nextInt(Constants.COLORS.size());
			String selectedColor = Constants.COLORS.get(selectedColorIndex);
			System.out.println("Selected " + selectedColor + " for color");
			Constants.COLORS.remove(selectedColorIndex);
			return selectedColor;
		}
		return null;

	}
}
