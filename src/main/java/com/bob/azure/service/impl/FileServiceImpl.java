package com.bob.azure.service.impl;


import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

import org.springframework.stereotype.Service;

import com.azure.identity.DefaultAzureCredential;
import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.bob.azure.service.FileService;

@Service
public class FileServiceImpl implements FileService {
    
    private static final String BLOB_CONTAINER = "bobsstoragecontainer";
    private static final String CONNECT_STR = System.getenv("AZURE_STORAGE_CONNECTION_STRING");
    
    private static final BlobServiceClient BLOB_SERVICE_CLIENT = new BlobServiceClientBuilder()
            .connectionString(CONNECT_STR)
            .buildClient();

    @Override
    public void uploadFile(String fileName, String content) {
        final var blobContainerClient = BLOB_SERVICE_CLIENT.getBlobContainerClient(BLOB_CONTAINER);
        final var blobClient = blobContainerClient.getBlobClient(fileName);
        blobClient.upload(new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8), 0, content.length()));
    }

}
