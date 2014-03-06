package de.glurak.frontend.mainFrame.content.message;

import de.glurak.data.Message;
import de.glurak.data.User.Reachable;
import de.glurak.data.User.User;

import javax.swing.*;
import java.awt.*;

/**
 * @author Entscheider
 */
public class MessageViewList extends JList<Message> {
    private class MessageRenderer implements ListCellRenderer<Message> {

        @Override
        public Component getListCellRendererComponent(JList<? extends Message> list, Message value, int index, boolean isSelected, boolean cellHasFocus) {
            JPanel res = new JPanel();
            if (!value.isAlreadyRead())
                res.setBackground(Color.GREEN);
            if (isSelected)
                res.setBackground(Color.GRAY);
            res.setLayout(new BorderLayout());
            JLabel message = new JLabel(value.getMessage());
            res.add(message,BorderLayout.CENTER) ;
            JLabel from = new JLabel();
            from.setText("<html><b>From</b>:<i>"+value.getSender().getUsername()+"</i></html>");
            res.add(from,BorderLayout.NORTH);
            JLabel to = new JLabel();
            Reachable r = value.getReceiver();
            if (r instanceof User){
                User u = (User) r;
                to.setText("<html><b>To</b>: <i>"+u.getUsername()+"</i></html>");
            }
            res.add(to,BorderLayout.SOUTH);
            return res;
        }
    }

    public MessageViewList(){
        this.setCellRenderer(new MessageRenderer());
    }
}
