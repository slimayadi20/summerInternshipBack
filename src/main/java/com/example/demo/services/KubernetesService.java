package com.example.demo.services;

import io.fabric8.kubernetes.api.model.Namespace;
import io.fabric8.kubernetes.api.model.Node;
import io.fabric8.kubernetes.api.model.NodeList;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.api.model.apps.DeploymentList;
import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.ConfigBuilder;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KubernetesService {
    public List<Namespace> getNamespaces() {
        Config config = new ConfigBuilder().build();


        try (KubernetesClient client = new DefaultKubernetesClient(config)) {
            return client.namespaces().list().getItems();
        }
    }

    public void getNodes() {
        try (KubernetesClient client = new DefaultKubernetesClient()) {
            NodeList nodeList = client.nodes().list();
            for (Node node : nodeList.getItems()) {
                // Process each node as needed
                String nodeName = node.getMetadata().getName();
                System.out.println("Node Name: " + nodeName);
            }
        } catch (Exception e) {
            // Handle any exceptions
        }
    }

    public List<String> getReleasesInNamespaces() {
        List<String> releases = new ArrayList<>();

        try (KubernetesClient kubeClient = new DefaultKubernetesClient()) {
            DeploymentList deploymentList = kubeClient.apps().deployments().inAnyNamespace().list();
            deploymentList.getItems().forEach(deployment -> {
                ObjectMeta metadata = deployment.getMetadata();
                String releaseName = metadata.getName();
                String namespace = metadata.getNamespace();
                String releaseInfo = "Release Name: " + releaseName + ", Namespace: " + namespace;
                releases.add(releaseInfo);
            });
        } catch (Exception e) {
            // Handle any exceptions
        }

        return releases;
    }

    public DeploymentList getReleasesInformation(String namespace) {
        try (KubernetesClient kubeClient = new DefaultKubernetesClient()) {
            return kubeClient.apps().deployments().inNamespace(namespace).list();
        } catch (Exception e) {
            // Handle the exception
            e.printStackTrace();
            return null;
        }
    }


}
