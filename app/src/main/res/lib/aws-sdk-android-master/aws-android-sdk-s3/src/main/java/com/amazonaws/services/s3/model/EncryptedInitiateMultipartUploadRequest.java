/*
 * Copyright 2014-2015 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package com.amazonaws.services.s3.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is an extension of {@link InitiateMultipartUploadRequest} to allow
 * additional encryption material description to be specified on a per-request
 * basis. In particular, {@link EncryptedInitiateMultipartUploadRequest} is only
 * recognized by {@link AmazonS3EncryptionJavaClient}.
 * </p>
 * <p>
 * If {@link EncryptedInitiateMultipartUploadRequest} is used against the
 * non-encrypting {@link AmazonS3JavaClient}, these additional attributes will
 * be ignored.
 * </p>
 */

public class EncryptedInitiateMultipartUploadRequest extends InitiateMultipartUploadRequest
        implements MaterialsDescriptionProvider {

    /**
     * description of encryption materials to be used with this request.
     */
    private Map<String, String> materialsDescription;

    public EncryptedInitiateMultipartUploadRequest(String bucketName, String key) {
        super(bucketName, key);
    }

    public EncryptedInitiateMultipartUploadRequest(String bucketName, String key,
            ObjectMetadata objectMetadata) {
        super(bucketName, key, objectMetadata);
    }

    @Override
    public Map<String, String> getMaterialsDescription() {
        return materialsDescription;
    }

    /**
     * sets the materials description for the encryption materials to be used
     * with the current Multi Part Upload Request.
     *
     * @param materialsDescription the materialsDescription to set
     */
    public void setMaterialsDescription(Map<String, String> materialsDescription) {
        this.materialsDescription = materialsDescription == null
                ? null
                : Collections.unmodifiableMap(new HashMap<String, String>(materialsDescription));
    }

    /**
     * sets the materials description for the encryption materials to be used
     * with the current Multi Part Upload Request.
     *
     * @param materialsDescription the materialsDescription to set
     */
    public EncryptedInitiateMultipartUploadRequest withMaterialsDescription(
            Map<String, String> materialsDescription) {
        setMaterialsDescription(materialsDescription);
        return this;
    }
}
