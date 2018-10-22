package com.github.brentworden.jmeter.protocol.pubsub;

import com.google.cloud.pubsub.v1.Publisher;
import com.google.pubsub.v1.ProjectTopicName;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.apache.jmeter.samplers.Entry;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.testelement.ThreadListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PubsubPublisherSampler extends AbstractPubsubSampler implements ThreadListener {

    private static final Logger LOG = LoggerFactory.getLogger(PubsubPublisherSampler.class);

    private static final String PROPERTY_PUBSUB_TOPIC_ID = "pubsub.topic.id";

    private static final long serialVersionUID = 1L;

    private Publisher publisher;

    private void destroyPublisher() {
        if (publisher != null) {
            Publisher localPublisher = publisher;
            publisher = null;

            try {
                localPublisher.shutdown();
            } catch (Exception ex) {
                LOG.error("failed to shutdown publisher", ex);
            }

            try {
                boolean terminated = localPublisher.awaitTermination(10L, TimeUnit.SECONDS);
                if (terminated) {
                    LOG.info("publisher successfully terminated");
                } else {
                    LOG.warn("publisher did not terminate quickly enough");
                }
            } catch (InterruptedException ex) {
                LOG.warn("publisher termination interrupted", ex);
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * @return the topicId
     */
    public String getTopicId() {
        return getPropertyAsString(PROPERTY_PUBSUB_TOPIC_ID, "");
    }

    private void initializePublisher() {
        if (publisher == null) {
            ProjectTopicName topicName = ProjectTopicName.of(getProjectId(), getTopicId());
            try {
                publisher = Publisher.newBuilder(topicName).build();
                LOG.info("publisher successfully built");
            } catch (IOException ex) {
                LOG.error("failed to build publisher", ex);
            }
        }
    }

    /**
     * @return
     */
    private boolean isPublisherReady() {
        initializePublisher();
        return publisher != null;
    }

    @Override
    public SampleResult sample(Entry e) {
        SampleResult result = new SampleResult();
        result.setSampleLabel(getName());
        result.setSuccessful(false);
        result.setResponseCode("500");

        if (isPublisherReady()) {
            result.setSuccessful(true);
            result.setResponseCode("200");
        }

        return result;
    }

    /**
     * @param topicId
     *            the topicId to set
     */
    public void setTopicId(String topicId) {
        setProperty(PROPERTY_PUBSUB_TOPIC_ID, topicId);
    }

    @Override
    public void threadFinished() {
        destroyPublisher();
    }

    @Override
    public void threadStarted() {
        initializePublisher();
    }

}
