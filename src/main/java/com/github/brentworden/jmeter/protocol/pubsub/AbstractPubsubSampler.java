package com.github.brentworden.jmeter.protocol.pubsub;

import org.apache.jmeter.samplers.AbstractSampler;

public abstract class AbstractPubsubSampler extends AbstractSampler {

    private static final String PROPERTY_PUBSUB_PROJECT_ID = "pubsub.project.id";

    private static final long serialVersionUID = 1L;

    /**
     * @return the projectId
     */
    public String getProjectId() {
        return getPropertyAsString(PROPERTY_PUBSUB_PROJECT_ID, "");
    }

    /**
     * @param projectId
     *            the projectId to set
     */
    public void setProjectId(String projectId) {
        setProperty(PROPERTY_PUBSUB_PROJECT_ID, projectId);
    }

}
