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
 * <p>
 * Describes the realtime endpoint information for a <i>MLModel</i> .
 * </p>
 * <p>
 * Provides results to the DescribeMLModel operation in <b>Normal</b>
 * format, and the ListMLModels operation.
 * </p>
 */
public class RealtimeEndpointInfo implements Serializable {

    /**
     * Maximum processing rate for the realtime endpoint for <i>MLModel</i>,
     * measured in incoming requests per second.
     */
    private Integer peakRequestsPerSecond;

    /**
     * The time that the request to create the realtime endpoint for the
     * <i>MLModel</i> was received. The time is expressed in epoch time.
     */
    private java.util.Date createdAt;

    /**
     * URI that specifies where to send realtime prediction requests for the
     * <code>MLModel</code>. <note> <p>The application must wait until the
     * realtime endpoint is ready before using this URI. </note>
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>0 - 2048<br/>
     * <b>Pattern: </b>https://[a-zA-Z0-9-.]*\.amazon(aws)?\.com[/]?<br/>
     */
    private String endpointUrl;

    /**
     * The current status of realtime endpoint for <i>MLModel</i>. This
     * element can have one of the following values: <ul> <li>NONE --
     * Endpoint does not exist or previously deleted </li> <li>READY --
     * Endpoint ready to be used for realtime predictions</li> <li>UPDATING
     * -- updating/creating the endpoint for requested rate of incoming
     * requests per second </li> </ul>
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Allowed Values: </b>NONE, READY, UPDATING, FAILED
     */
    private String endpointStatus;

    /**
     * Maximum processing rate for the realtime endpoint for <i>MLModel</i>,
     * measured in incoming requests per second.
     *
     * @return Maximum processing rate for the realtime endpoint for <i>MLModel</i>,
     *         measured in incoming requests per second.
     */
    public Integer getPeakRequestsPerSecond() {
        return peakRequestsPerSecond;
    }
    
    /**
     * Maximum processing rate for the realtime endpoint for <i>MLModel</i>,
     * measured in incoming requests per second.
     *
     * @param peakRequestsPerSecond Maximum processing rate for the realtime endpoint for <i>MLModel</i>,
     *         measured in incoming requests per second.
     */
    public void setPeakRequestsPerSecond(Integer peakRequestsPerSecond) {
        this.peakRequestsPerSecond = peakRequestsPerSecond;
    }
    
    /**
     * Maximum processing rate for the realtime endpoint for <i>MLModel</i>,
     * measured in incoming requests per second.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param peakRequestsPerSecond Maximum processing rate for the realtime endpoint for <i>MLModel</i>,
     *         measured in incoming requests per second.
     *
     * @return A reference to this updated object so that method calls can be chained
     *         together.
     */
    public RealtimeEndpointInfo withPeakRequestsPerSecond(Integer peakRequestsPerSecond) {
        this.peakRequestsPerSecond = peakRequestsPerSecond;
        return this;
    }

    /**
     * The time that the request to create the realtime endpoint for the
     * <i>MLModel</i> was received. The time is expressed in epoch time.
     *
     * @return The time that the request to create the realtime endpoint for the
     *         <i>MLModel</i> was received. The time is expressed in epoch time.
     */
    public java.util.Date getCreatedAt() {
        return createdAt;
    }
    
    /**
     * The time that the request to create the realtime endpoint for the
     * <i>MLModel</i> was received. The time is expressed in epoch time.
     *
     * @param createdAt The time that the request to create the realtime endpoint for the
     *         <i>MLModel</i> was received. The time is expressed in epoch time.
     */
    public void setCreatedAt(java.util.Date createdAt) {
        this.createdAt = createdAt;
    }
    
    /**
     * The time that the request to create the realtime endpoint for the
     * <i>MLModel</i> was received. The time is expressed in epoch time.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param createdAt The time that the request to create the realtime endpoint for the
     *         <i>MLModel</i> was received. The time is expressed in epoch time.
     *
     * @return A reference to this updated object so that method calls can be chained
     *         together.
     */
    public RealtimeEndpointInfo withCreatedAt(java.util.Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    /**
     * URI that specifies where to send realtime prediction requests for the
     * <code>MLModel</code>. <note> <p>The application must wait until the
     * realtime endpoint is ready before using this URI. </note>
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>0 - 2048<br/>
     * <b>Pattern: </b>https://[a-zA-Z0-9-.]*\.amazon(aws)?\.com[/]?<br/>
     *
     * @return URI that specifies where to send realtime prediction requests for the
     *         <code>MLModel</code>. <note> <p>The application must wait until the
     *         realtime endpoint is ready before using this URI. </note>
     */
    public String getEndpointUrl() {
        return endpointUrl;
    }
    
    /**
     * URI that specifies where to send realtime prediction requests for the
     * <code>MLModel</code>. <note> <p>The application must wait until the
     * realtime endpoint is ready before using this URI. </note>
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>0 - 2048<br/>
     * <b>Pattern: </b>https://[a-zA-Z0-9-.]*\.amazon(aws)?\.com[/]?<br/>
     *
     * @param endpointUrl URI that specifies where to send realtime prediction requests for the
     *         <code>MLModel</code>. <note> <p>The application must wait until the
     *         realtime endpoint is ready before using this URI. </note>
     */
    public void setEndpointUrl(String endpointUrl) {
        this.endpointUrl = endpointUrl;
    }
    
    /**
     * URI that specifies where to send realtime prediction requests for the
     * <code>MLModel</code>. <note> <p>The application must wait until the
     * realtime endpoint is ready before using this URI. </note>
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>0 - 2048<br/>
     * <b>Pattern: </b>https://[a-zA-Z0-9-.]*\.amazon(aws)?\.com[/]?<br/>
     *
     * @param endpointUrl URI that specifies where to send realtime prediction requests for the
     *         <code>MLModel</code>. <note> <p>The application must wait until the
     *         realtime endpoint is ready before using this URI. </note>
     *
     * @return A reference to this updated object so that method calls can be chained
     *         together.
     */
    public RealtimeEndpointInfo withEndpointUrl(String endpointUrl) {
        this.endpointUrl = endpointUrl;
        return this;
    }

    /**
     * The current status of realtime endpoint for <i>MLModel</i>. This
     * element can have one of the following values: <ul> <li>NONE --
     * Endpoint does not exist or previously deleted </li> <li>READY --
     * Endpoint ready to be used for realtime predictions</li> <li>UPDATING
     * -- updating/creating the endpoint for requested rate of incoming
     * requests per second </li> </ul>
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Allowed Values: </b>NONE, READY, UPDATING, FAILED
     *
     * @return The current status of realtime endpoint for <i>MLModel</i>. This
     *         element can have one of the following values: <ul> <li>NONE --
     *         Endpoint does not exist or previously deleted </li> <li>READY --
     *         Endpoint ready to be used for realtime predictions</li> <li>UPDATING
     *         -- updating/creating the endpoint for requested rate of incoming
     *         requests per second </li> </ul>
     *
     * @see RealtimeEndpointStatus
     */
    public String getEndpointStatus() {
        return endpointStatus;
    }
    
    /**
     * The current status of realtime endpoint for <i>MLModel</i>. This
     * element can have one of the following values: <ul> <li>NONE --
     * Endpoint does not exist or previously deleted </li> <li>READY --
     * Endpoint ready to be used for realtime predictions</li> <li>UPDATING
     * -- updating/creating the endpoint for requested rate of incoming
     * requests per second </li> </ul>
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Allowed Values: </b>NONE, READY, UPDATING, FAILED
     *
     * @param endpointStatus The current status of realtime endpoint for <i>MLModel</i>. This
     *         element can have one of the following values: <ul> <li>NONE --
     *         Endpoint does not exist or previously deleted </li> <li>READY --
     *         Endpoint ready to be used for realtime predictions</li> <li>UPDATING
     *         -- updating/creating the endpoint for requested rate of incoming
     *         requests per second </li> </ul>
     *
     * @see RealtimeEndpointStatus
     */
    public void setEndpointStatus(String endpointStatus) {
        this.endpointStatus = endpointStatus;
    }
    
    /**
     * The current status of realtime endpoint for <i>MLModel</i>. This
     * element can have one of the following values: <ul> <li>NONE --
     * Endpoint does not exist or previously deleted </li> <li>READY --
     * Endpoint ready to be used for realtime predictions</li> <li>UPDATING
     * -- updating/creating the endpoint for requested rate of incoming
     * requests per second </li> </ul>
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Allowed Values: </b>NONE, READY, UPDATING, FAILED
     *
     * @param endpointStatus The current status of realtime endpoint for <i>MLModel</i>. This
     *         element can have one of the following values: <ul> <li>NONE --
     *         Endpoint does not exist or previously deleted </li> <li>READY --
     *         Endpoint ready to be used for realtime predictions</li> <li>UPDATING
     *         -- updating/creating the endpoint for requested rate of incoming
     *         requests per second </li> </ul>
     *
     * @return A reference to this updated object so that method calls can be chained
     *         together.
     *
     * @see RealtimeEndpointStatus
     */
    public RealtimeEndpointInfo withEndpointStatus(String endpointStatus) {
        this.endpointStatus = endpointStatus;
        return this;
    }

    /**
     * The current status of realtime endpoint for <i>MLModel</i>. This
     * element can have one of the following values: <ul> <li>NONE --
     * Endpoint does not exist or previously deleted </li> <li>READY --
     * Endpoint ready to be used for realtime predictions</li> <li>UPDATING
     * -- updating/creating the endpoint for requested rate of incoming
     * requests per second </li> </ul>
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Allowed Values: </b>NONE, READY, UPDATING, FAILED
     *
     * @param endpointStatus The current status of realtime endpoint for <i>MLModel</i>. This
     *         element can have one of the following values: <ul> <li>NONE --
     *         Endpoint does not exist or previously deleted </li> <li>READY --
     *         Endpoint ready to be used for realtime predictions</li> <li>UPDATING
     *         -- updating/creating the endpoint for requested rate of incoming
     *         requests per second </li> </ul>
     *
     * @see RealtimeEndpointStatus
     */
    public void setEndpointStatus(RealtimeEndpointStatus endpointStatus) {
        this.endpointStatus = endpointStatus.toString();
    }
    
    /**
     * The current status of realtime endpoint for <i>MLModel</i>. This
     * element can have one of the following values: <ul> <li>NONE --
     * Endpoint does not exist or previously deleted </li> <li>READY --
     * Endpoint ready to be used for realtime predictions</li> <li>UPDATING
     * -- updating/creating the endpoint for requested rate of incoming
     * requests per second </li> </ul>
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Allowed Values: </b>NONE, READY, UPDATING, FAILED
     *
     * @param endpointStatus The current status of realtime endpoint for <i>MLModel</i>. This
     *         element can have one of the following values: <ul> <li>NONE --
     *         Endpoint does not exist or previously deleted </li> <li>READY --
     *         Endpoint ready to be used for realtime predictions</li> <li>UPDATING
     *         -- updating/creating the endpoint for requested rate of incoming
     *         requests per second </li> </ul>
     *
     * @return A reference to this updated object so that method calls can be chained
     *         together.
     *
     * @see RealtimeEndpointStatus
     */
    public RealtimeEndpointInfo withEndpointStatus(RealtimeEndpointStatus endpointStatus) {
        this.endpointStatus = endpointStatus.toString();
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
        if (getPeakRequestsPerSecond() != null) sb.append("PeakRequestsPerSecond: " + getPeakRequestsPerSecond() + ",");
        if (getCreatedAt() != null) sb.append("CreatedAt: " + getCreatedAt() + ",");
        if (getEndpointUrl() != null) sb.append("EndpointUrl: " + getEndpointUrl() + ",");
        if (getEndpointStatus() != null) sb.append("EndpointStatus: " + getEndpointStatus() );
        sb.append("}");
        return sb.toString();
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int hashCode = 1;
        
        hashCode = prime * hashCode + ((getPeakRequestsPerSecond() == null) ? 0 : getPeakRequestsPerSecond().hashCode()); 
        hashCode = prime * hashCode + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode()); 
        hashCode = prime * hashCode + ((getEndpointUrl() == null) ? 0 : getEndpointUrl().hashCode()); 
        hashCode = prime * hashCode + ((getEndpointStatus() == null) ? 0 : getEndpointStatus().hashCode()); 
        return hashCode;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;

        if (obj instanceof RealtimeEndpointInfo == false) return false;
        RealtimeEndpointInfo other = (RealtimeEndpointInfo)obj;
        
        if (other.getPeakRequestsPerSecond() == null ^ this.getPeakRequestsPerSecond() == null) return false;
        if (other.getPeakRequestsPerSecond() != null && other.getPeakRequestsPerSecond().equals(this.getPeakRequestsPerSecond()) == false) return false; 
        if (other.getCreatedAt() == null ^ this.getCreatedAt() == null) return false;
        if (other.getCreatedAt() != null && other.getCreatedAt().equals(this.getCreatedAt()) == false) return false; 
        if (other.getEndpointUrl() == null ^ this.getEndpointUrl() == null) return false;
        if (other.getEndpointUrl() != null && other.getEndpointUrl().equals(this.getEndpointUrl()) == false) return false; 
        if (other.getEndpointStatus() == null ^ this.getEndpointStatus() == null) return false;
        if (other.getEndpointStatus() != null && other.getEndpointStatus().equals(this.getEndpointStatus()) == false) return false; 
        return true;
    }
    
}
    