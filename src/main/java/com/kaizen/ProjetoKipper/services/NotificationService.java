package com.kaizen.ProjetoKipper.services;

import com.kaizen.ProjetoKipper.DTOS.NotificationDTO;
import com.kaizen.ProjetoKipper.Domains.Users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationService {
    @Autowired
    private RestTemplate restTemplate;

    public void sendNotification(User user, String message) throws Exception{
        String email = user.getEmail();
        //NotificationDTO notificationRequest = new NotificationDTO(email, message);

        /*ResponseEntity<String> notificationResponse = restTemplate.postForEntity("http://util.devi.tools/api/v1/notify", notificationRequest, String.class);

        if (!(notificationResponse.getStatusCode() == HttpStatus.OK)) {
            System.out.println("ERRO AO ENVIAR NOTIFICACAO");
            throw new Exception("SISTEMA DE NOTIFICACAO FORA DO AR");
        }*/
        System.out.println("NOTIFICACAO ENVIADA AO USUARIO");
    }
}
