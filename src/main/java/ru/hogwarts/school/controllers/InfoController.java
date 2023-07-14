package ru.hogwarts.school.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.dto.InfoDTO;
import ru.hogwarts.school.services.InfoService;

@RestController
@RequestMapping("info")
public class InfoController {

    private InfoService infoService;

    public InfoController(InfoService infoService) {
        this.infoService = infoService;
    }

    @GetMapping
    public ResponseEntity<InfoDTO> appInfo() {
        return ResponseEntity.ok(infoService.getInfo());
    }
}
