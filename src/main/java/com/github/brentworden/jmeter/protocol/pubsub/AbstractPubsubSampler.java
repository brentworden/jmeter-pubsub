package com.github.brentworden.jmeter.protocol.pubsub;

import org.apache.jmeter.samplers.AbstractSampler;

public abstract class AbstractPubsubSampler extends AbstractSampler {

    private static final long serialVersionUID = 1L;

    private String projectId;

    /**
     * @return the projectId
     */
    public String getProjectId() {
        return projectId;
    }

    /**
     * @param projectId
     *            the projectId to set
     */
    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

}
