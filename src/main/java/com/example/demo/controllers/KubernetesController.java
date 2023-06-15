package com.example.demo.controllers;

import com.example.demo.entities.DeploymentInfo;
import com.example.demo.services.KubernetesService;
import io.kubernetes.client.openapi.models.V1Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class KubernetesController {
    private final KubernetesService kubernetesService;

    @Autowired
    public KubernetesController(KubernetesService kubernetesService) {
        this.kubernetesService = kubernetesService;
    }



    @GetMapping("/namespaces")
    public List<V1Namespace> getNamespaces() {
        return kubernetesService.getNamespaces();
    }


    @GetMapping("/releases")
    public List<String> getReleasesInNamespaces() {
        return kubernetesService.getReleasesInNamespaces();
    }

    @GetMapping("/infosrelease/{namespace}")
    public List<DeploymentInfo> getReleasesInformation(@PathVariable String namespace) {
        return kubernetesService.getReleasesInformation(namespace);
    }
}