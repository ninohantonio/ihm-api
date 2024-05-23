package com.example.myspringapp.config;

import com.example.myspringapp.component.ServeUpScheduler;
import com.example.myspringapp.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.IntervalTask;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Controller;

import java.time.Duration;

@Controller
@Configuration
public class ScheduledTasksConfig implements SchedulingConfigurer {

    @Autowired
    private ServeUpScheduler serveUpScheduler;



    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        // Définir l'intervalle de temps pour la tâche planifiée
        long interval = 2000; // 2000 millisecondes = 2 secondes

        // Créer un Runnable qui appelle la méthode avec le paramètre nécessaire
        Runnable task = () -> serveUpScheduler.scheduleServeUpNotification();

        // Enregistrer la tâche planifiée avec l'intervalle spécifié
        taskRegistrar.addFixedRateTask(task, interval);
    }

}
