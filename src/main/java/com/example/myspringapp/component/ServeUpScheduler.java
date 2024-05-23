package com.example.myspringapp.component;

import com.example.myspringapp.dao.UserRepository;
import com.example.myspringapp.dto.NotificationDto;
import com.example.myspringapp.dto.UserInfo;
import com.example.myspringapp.entities.User;
import com.example.myspringapp.services.NotificationService;
import com.example.myspringapp.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


import java.time.LocalTime;

@Component
public class ServeUpScheduler {

    private final NotificationService notificationService;
    private final UserRepository userRepository;
    @Autowired
    private UserService userService;


    public ServeUpScheduler(NotificationService notificationService, UserRepository userRepository) {
        this.notificationService = notificationService;
        this.userRepository = userRepository;
    }

    public void scheduleServeUpNotification(){
        UserInfo userInfo = userService.getSessionData("connectedUser");
        if(userInfo == null){
            System.out.println("Aucun user connecter");
        }else{
        LocalTime currentTime = LocalTime.now();
        String email = userInfo.getEmail();
        NotificationDto notification = new NotificationDto();
        notification.setMessage("Vou devez servire les animaux a : " + currentTime);
        notificationService.sendServeUpNotification(email,notification);
        System.out.println("Debug " + userInfo + " for " + currentTime);
        }
    }

    public boolean isCurrentTimeBeforeWakeUpTime(LocalTime serveTime){
        LocalTime cureentTime = LocalTime.now();
        return cureentTime.isBefore(serveTime);
    }





}
