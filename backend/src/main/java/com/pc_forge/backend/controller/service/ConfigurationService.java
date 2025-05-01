package com.pc_forge.backend.controller.service;

import com.pc_forge.backend.model.entity.configuration.Configuration;
import com.pc_forge.backend.model.entity.user.User;
import com.pc_forge.backend.model.repository.configuration.ConfigurationRepository;
import com.pc_forge.backend.view.response.configuration.ConfigurationShortResponse;
import com.pc_forge.backend.view.response.configuration.ProductConfigurationResponse;
import com.pc_forge.backend.view.util.ResponseBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ConfigurationService {
    private final ConfigurationRepository configurationRepository;

    public ConfigurationService(ConfigurationRepository configurationRepository) {
        this.configurationRepository = configurationRepository;
    }

    public List<ConfigurationShortResponse> getAllConfigurations(User user) {
        List<ConfigurationShortResponse> configListResponse = new ArrayList<>();
        List<Configuration> configurations = configurationRepository.findByUser_Id(user.getId());
        for (Configuration configuration : configurations) {
            configListResponse.add(getConfigurationShortResponse(configuration));
        }
        return configListResponse;
    }

    private ConfigurationShortResponse getConfigurationShortResponse(Configuration configuration) {
        ConfigurationShortResponse response = new ConfigurationShortResponse();
        response.setName(configuration.getConfigName());
        response.setPrice(configuration.getTotalPrice());
        List<ProductConfigurationResponse> products = new ArrayList<>();
        products.add(ResponseBuilder.getProductConfigurationResponse(configuration.getProcessor()));
        products.add(ResponseBuilder.getProductConfigurationResponse(configuration.getMotherboard()));
        products.add(ResponseBuilder.getProductConfigurationResponse(configuration.getMemory()));
        products.add(ResponseBuilder.getProductConfigurationResponse(configuration.getGraphicsCard()));
        products.add(ResponseBuilder.getProductConfigurationResponse(configuration.getPowerSupply()));
        products.add(ResponseBuilder.getProductConfigurationResponse(configuration.getCooler()));
        products.add(ResponseBuilder.getProductConfigurationResponse(configuration.getPcCase()));
        products.add(ResponseBuilder.getProductConfigurationResponse(configuration.getHardDiskDrive()));
        products.add(ResponseBuilder.getProductConfigurationResponse(configuration.getSolidStateDrive()));
        response.setProducts(products);
        return response;
    }
}
