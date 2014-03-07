package de.glurak.frontend.mainFrame.content.message;

import de.glurak.data.Message;
import de.glurak.data.User.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Panel das die Oberfläche zum Narichtenschreiben zeigt.
 * @author Simon
 */
public class MessageViewWriter extends JPanel {

    protected JTextField t_receiver = new JTextField();
    protected JTextArea t_message = new JTextArea(4,50);
    // Scrolltabelle fuer das Nachrichtenfeld
    private JScrollPane s_message = new JScrollPane(t_message);
    // Buttons
    protected JButton b_send;
    protected JButton b_cancel;

    /**
     * Konstruktor
     * @param eventCallback der Aktionlistener für die Knöpfe, null für keinen
     */
    public MessageViewWriter(ActionListener eventCallback){
        setLayout(new BorderLayout());

        // Initialisierung der Buttons
        b_send = new JButton("Senden");
        b_send.setActionCommand("send");
        if (eventCallback!=null)
            b_send.addActionListener(eventCallback);
        b_cancel = new JButton("Abbrechen");
        b_cancel.setActionCommand("cancel");
        if (eventCallback!=null)
            b_cancel.addActionListener(eventCallback);

        // Initialisierung der Labels
        JLabel l_receiver = new JLabel("Empfänger: ");
        JLabel l_message = new JLabel("Nachricht: ");

        // Initialisierung der Panels
        JPanel pan_receiver = new JPanel();
        JPanel pan_message = new JPanel();
        JPanel pan_buttons = new JPanel();

        // Layout der Panels festlegen
        pan_receiver.setLayout(new GridLayout(1, 0));
        pan_message.setLayout(new BorderLayout());
        pan_buttons.setLayout(new FlowLayout());

        // Buttons in das Buttonpanel hinzufuegen
        pan_buttons.add(b_send);
        pan_buttons.add(b_cancel);

        //Schriftart des Nachrichtenfeldes aendern
        t_message.setFont(new Font("Arial", Font.BOLD, 12));
        
        // Label und Textfeld in das Empfaengerpanel hinzufuegen
        pan_receiver.add(l_receiver);
        pan_receiver.add(t_receiver);

        // Textfeld anpassen
        t_message.setLineWrap(true);
        t_message.setWrapStyleWord(true);

        // Label und Textfeld in das Nachrichtenpanel hinzufuegen
        pan_message.add(l_message, BorderLayout.NORTH);
        pan_message.add(s_message, BorderLayout.CENTER);

        // Panels in das Frame einfuegen
        add(pan_receiver, BorderLayout.NORTH);
        add(pan_message, BorderLayout.CENTER);
        add(pan_buttons, BorderLayout.SOUTH);
    }

    /**
     * Gibt die Naricht zurück. Diese Message ist temporär und
     * nicht in der Datenbank. Sie ist dafür da sofort wieder gelöscht zu werden
     * @return die Naricht
     */
    public Message createMessage(){
        Message res = new Message();
        User u = new User();
        u.setUsername(t_receiver.getText());
        res.setMessage(t_message.getText());
        res.setReceiver(u);
        return res;
    }


    /**
     * Setzt die eingeben Naricht
     * @param m die Naricht, null für ein leeres Feld
     */
    public void setMessage(Message m){
         if (m==null){
             t_receiver.setText("");
             t_message.setText("");
             return;
         }
        User u = (User) m.getReceiver();
        t_receiver.setText(u.getUsername());
        t_message.setText(t_message.getText());
    }

    public void setMessage(String receiver, String message){
        t_receiver.setText(receiver);
        t_message.setText(message);
    }
}
