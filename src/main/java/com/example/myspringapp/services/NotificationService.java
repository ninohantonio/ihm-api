package com.example.myspringapp.services;

import com.example.myspringapp.dto.NotificationDto;
import com.example.myspringapp.entities.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class NotificationService {

    private final SimpMessagingTemplate template;

    public NotificationService(SimpMessagingTemplate template, UserService userService) {
        this.template = template;
    }

    @MessageMapping("/private") // /app/private
    public NotificationDto sendServeUpNotification(String email, @Payload NotificationDto notification){
        template.convertAndSendToUser(email, "/specific", notification);// /user/mail/specific
        System.out.println(notification);
        return notification;
    }
}
