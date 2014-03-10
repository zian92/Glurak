package de.glurak.data;
import de.glurak.data.User.Reachable;
import de.glurak.data.User.Rights;
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
	private Reachable receiver;
    @Id
    @GeneratedValue
	private long id;

    boolean isApplication;

    /**
     * Gibt den Gelesen-Zustand aus
     * @return true falls gelesen, false sonst
     */
    public boolean isAlreadyRead() {
        return isAlreadyRead;
    }

    /**
     * Setzt den Gelese-Zustand
     * @param isAlreadyRead true falls gelesen, false sonst
     */
    public void setAlreadyRead(boolean isAlreadyRead) {
        this.isAlreadyRead = isAlreadyRead;
    }

    /**
     * Gibt zurück ob die Naricht eine Bewerbung ist
     * @return true falls Bewerbung, false sonst
     */
    public boolean isApplication() {
        return isApplication;
    }

    /**
     * Setzt ob die Naricht eine Bewerbung ist
     * @param isApplication true falls Bewerbung, false sonst
     */
    public void setApplication(boolean isApplication) {
        this.isApplication = isApplication;
    }

    private boolean isAlreadyRead;

	/**
	 * Konstruktor
	 */
	public Message(){
		
	}
	public void setMessage(String pMessage){
		message = pMessage;
	}
	
	public void setSender(User pSender){
        NotEnoughRightException.throwIfNot(pSender, Rights.DO_MESSAGE);
		sender = pSender;
	}
	
	public void setReceiver(Reachable pReceiver){
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
	
	public Reachable  getReceiver(){
		return receiver;
	}
	
}
