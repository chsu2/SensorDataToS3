/*
 * Copyright 2010-2015 Amazon.com, Inc. or its affiliates. All Rights Reserved.
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
package com.amazonaws.services.machinelearning.model;

import java.io.Serializable;

/**
 * Predict Result
 */
public class PredictResult implements Serializable {

    /**
     * Output from a predict operation.
     */
    private Prediction prediction;

    /**
     * Output from a predict operation.
     *
     * @return Output from a predict operation.
     */
    public Prediction getPrediction() {
        return prediction;
    }
    
    /**
     * Output from a predict operation.
     *
     * @param prediction Output from a predict operation.
     */
    public void setPrediction(Prediction prediction) {
        this.prediction = prediction;
    }
    
    /**
     * Output from a predict operation.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param prediction Output from a predict operation.
     *
     * @return A reference to this updated object so that method calls can be chained
     *         together.
     */
    public PredictResult withPrediction(Prediction prediction) {
        this.prediction = prediction;
        return this;
    }

    /**
     * Returns a string representation of this object; useful for testing and
     * debugging.
     *
     * @return A string representation of this object.
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getPrediction() != null) sb.append("Prediction: " + getPrediction() );
        sb.append("}");
        return sb.toString();
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int hashCode = 1;
        
        hashCode = prime * hashCode + ((getPrediction() == null) ? 0 : getPrediction().hashCode()); 
        return hashCode;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;

        if (obj instanceof PredictResult == false) return false;
        PredictResult other = (PredictResult)obj;
        
        if (other.getPrediction() == null ^ this.getPrediction() == null) return false;
        if (other.getPrediction() != null && other.getPrediction().equals(this.getPrediction()) == false) return false; 
        return true;
    }
    
}
    