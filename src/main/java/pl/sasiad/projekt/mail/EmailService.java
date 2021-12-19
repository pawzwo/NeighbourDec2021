package pl.sasiad.projekt.mail;

import org.springframework.stereotype.Service;


public interface EmailService {

    void sendSimpleMessage(String to, String subject, String text);
}
