package com.pc_forge.backend.controller.api;

import com.pc_forge.backend.controller.service.ConfigurationService;
import com.pc_forge.backend.model.entity.user.User;
import com.pc_forge.backend.view.response.configuration.ConfigurationShortResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/configurations")
public class ConfigurationController {
    private final ConfigurationService configurationService;

    public ConfigurationController(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ConfigurationShortResponse>> getAllConfigurations(@AuthenticationPrincipal User user) {
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(configurationService.getAllConfigurations(user));
    }

}
