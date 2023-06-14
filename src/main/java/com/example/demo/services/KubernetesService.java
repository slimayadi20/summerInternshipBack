package com.example.demo.services;

import com.example.demo.entities.DeploymentInfo;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.AppsV1Api;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Deployment;
import io.kubernetes.client.openapi.models.V1DeploymentList;
import io.kubernetes.client.openapi.models.V1Namespace;
import io.kubernetes.client.openapi.models.V1NamespaceList;
import io.kubernetes.client.util.ClientBuilder;
import io.kubernetes.client.util.KubeConfig;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class KubernetesService {
    public List<V1Namespace> getNamespaces() {
        List<V1Namespace> namespaces = new ArrayList<>();
        try {
            KubeConfig kubeConfig = KubeConfig.loadKubeConfig(new FileReader("src/main/resources/config"));
            ApiClient client = ClientBuilder.kubeconfig(kubeConfig).build();

            Configuration.setDefaultApiClient(client);
            CoreV1Api api = new CoreV1Api(client);
            V1NamespaceList namespaceList = api.listNamespace(null, null, null, null, null, null, null, null, null, null);
            namespaces.addAll(namespaceList.getItems());
        } catch (IOException | ApiException e) {
            e.printStackTrace();
        }
        return namespaces;
    }

    public List<String> getReleasesInNamespaces() {
        List<String> releases = new ArrayList<>();
        try {
            KubeConfig kubeConfig = KubeConfig.loadKubeConfig(new FileReader("src/main/resources/config"));
            ApiClient client = ClientBuilder.kubeconfig(kubeConfig).build();
            AppsV1Api api = new AppsV1Api();
            V1DeploymentList deploymentList = api.listDeploymentForAllNamespaces(null, null, null, null, null, null, null, null, null, null);
            deploymentList.getItems().forEach(deployment -> {
                String releaseName = deployment.getMetadata().getName();
                String namespace = deployment.getMetadata().getNamespace();
                String releaseInfo = "Release Name: " + releaseName + ", Namespace: " + namespace;
                releases.add(releaseInfo);
            });
        } catch (IOException | ApiException e) {
            e.printStackTrace();
        }
        return releases;
    }


    public List<DeploymentInfo> getReleasesInformation(String namespace) {
        List<DeploymentInfo> deployments = new ArrayList<>();
        try {
            KubeConfig kubeConfig = KubeConfig.loadKubeConfig(new FileReader("src/main/resources/config"));
            ApiClient client = ClientBuilder.kubeconfig(kubeConfig).build();
            Configuration.setDefaultApiClient(client);

            AppsV1Api api = new AppsV1Api(client);
            V1DeploymentList deploymentList = api.listNamespacedDeployment(namespace, null, null, null, null, null, null, null, null, null, null);

            for (V1Deployment deployment : deploymentList.getItems()) {
                DeploymentInfo deploymentInfo = new DeploymentInfo();
                System.out.println(deployment);
                deploymentInfo.setReleaseName(deployment.getMetadata().getName());
                deploymentInfo.setNamespace(deployment.getMetadata().getNamespace());
                deployments.add(deploymentInfo);
            }
        } catch (ApiException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return deployments;
    }

}
