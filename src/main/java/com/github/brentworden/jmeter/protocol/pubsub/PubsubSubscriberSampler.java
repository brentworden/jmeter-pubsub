package com.github.brentworden.jmeter.protocol.pubsub;

import org.apache.jmeter.samplers.Entry;
import org.apache.jmeter.samplers.SampleResult;

public class PubsubSubscriberSampler extends AbstractPubsubSampler {

    private static final long serialVersionUID = 1L;

    @Override
    public SampleResult sample(Entry e) {
        SampleResult result = new SampleResult();
        result.setSampleLabel(getName());
        result.setSuccessful(true);
        result.setResponseCode("200");

        return result;
    }

}
