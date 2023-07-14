package ru.hogwarts.school.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.dto.InfoDTO;

@Service
public class InfoService {

    @Value("${app.env}")
    private String appEnv;
    public InfoDTO getInfo() {
        InfoDTO infoDTO = new InfoDTO("hogwarts-school", "0.0.1", appEnv);
        return infoDTO;
    }

}
