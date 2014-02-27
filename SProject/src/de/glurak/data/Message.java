package de.glurak.data;
import de.glurak.data.User.User;

import java.io.Serializable;
import javax.persistence.*;
/**
 * Diese Klasse repraesentiert eine Nachricht, die sich User untereinander senden koennen.
 * @author Simon
 *
 */
@Entity
public class Message implements Serializable{

	private String message;
    @ManyToOne
	private User sender;
    @ManyToOne
	private User receiver;
    @Id
    @GeneratedValue
    @Column(name="ID")
	private long id;

    boolean isApplication;

    public boolean isAlreadyRead() {
        return isAlreadyRead;
    }

    public void setAlreadyRead(boolean isAlreadyRead) {
        this.isAlreadyRead = isAlreadyRead;
    }

    public boolean isApplication() {
        return isApplication;
    }

    public void setApplication(boolean isApplication) {
        this.isApplication = isApplication;
    }

    boolean isAlreadyRead;

	/**
	 * Konstruktor
	 */
	public Message(){
		
	}
	
	/**
	 * Konstruktor
	 * @param pMessage Die Nachricht, die gesendet werden soll.
	 * @param pSender Der Absender der Nachricht.
	 * @param pReceiver Der Empfaenger der Nachricht.
	 * @param pID Die ID der Nachricht.
	 */
	public Message(String pMessage, User pSender, User pReceiver, long pID){
		message = pMessage;
		sender = pSender;
		receiver = pReceiver;
		id = pID;
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
	
	public long getID(){
		return id;
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
