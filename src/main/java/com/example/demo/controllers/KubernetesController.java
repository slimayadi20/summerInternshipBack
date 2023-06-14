package com.example.demo.controllers;

import com.example.demo.services.KubernetesService;
import io.fabric8.kubernetes.api.model.Namespace;
import io.fabric8.kubernetes.api.model.apps.DeploymentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class KubernetesController {
    @Autowired
    KubernetesService kubernetesService;


        @GetMapping("/namespaces")
    public List<Namespace> getNamespaces() {
        return kubernetesService.getNamespaces();
    }

    @GetMapping("/nodes")
    public void getNodes() {
        kubernetesService.getNodes();
    }

    @GetMapping("/releases")
    public List<String> getReleasesInNamespaces() {
        return kubernetesService.getReleasesInNamespaces();
    }

    @GetMapping("/infosrelease/{namespace}")
    public DeploymentList getReleasesInformation(@PathVariable String namespace) {
        return kubernetesService.getReleasesInformation(namespace);
    }
}