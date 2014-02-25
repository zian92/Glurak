package de.glurak.data;

/**
 * Diese Klasse repraesentiert eine Nachricht, die sich User untereinander senden koennen.
 * @author Simon
 *
 */
public class Message {

	private String message;
	private User sender;
	private User receiver;
	private String ID;
	
	public Message(){
		
	}
	
	public Message(String pMessage, User pSender, User pReceiver, String pID){
		message = pMessage;
		sender = pSender;
		receiver = pReceiver;
		ID = pID;
	}
	
	public void setMessage(String pMessage){
		message = pMessage;
	}
	
	public void setSender(User pSender){
		sender = pSender;
	}
	
	public void setReceiver(User pReceiver){
		receiver = pReceiver;
	}
	
	public String getID(){
		return ID;
	}
	
	public String getMessage(){
		return message;
	}
	
	public User getSender(){
		return sender;
	}
	
	public User getReceiver(){
		return receiver;
	}
	
}
