package com.github.brentworden.jmeter.protocol.pubsub.gui;

import com.github.brentworden.jmeter.protocol.pubsub.PubsubSubscriberSampler;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import org.apache.jmeter.gui.util.VerticalPanel;
import org.apache.jmeter.testelement.TestElement;
import org.apache.jorphan.gui.JLabeledTextField;

/**
 *
 */
public class PubsubSubscriberSamplerGui extends AbstractPubsubSamplerGui {

    private static final long serialVersionUID = 1L;

    private final JLabeledTextField projectId = new JLabeledTextField("Project ID");

    public PubsubSubscriberSamplerGui() {
        super();
        init();
    }

    @Override
    public void clearGui() {
        super.clearGui();
        projectId.setText("");
    }

    /**
     * the implementation loads the URL and the soap action for the request.
     */
    @Override
    public void configure(TestElement el) {
        super.configure(el);
        PubsubSubscriberSampler sampler = (PubsubSubscriberSampler) el;
        projectId.setText(sampler.getProjectId());
    }

    /**
     * @see org.apache.jmeter.gui.JMeterGUIComponent#createTestElement()
     */
    @Override
    public TestElement createTestElement() {
        PubsubSubscriberSampler sampler = new PubsubSubscriberSampler();
        modifyTestElement(sampler);
        return sampler;
    }

    @Override
    public String getLabelResource() {
        return "pubsub_subscriber_title";
    }

    private void init() {
        // WARNING: called from ctor so must not be overridden
        setLayout(new BorderLayout());
        setBorder(makeBorder());
        add(makeTitlePanel(), BorderLayout.NORTH);

        JPanel mainPanel = new VerticalPanel();
        add(mainPanel, BorderLayout.CENTER);

        projectId.setToolTipText("Google Cloud Project ID");
        mainPanel.add(projectId);
    }

    /**
     * Modifies a given TestElement to mirror the data in the gui components.
     *
     * @see org.apache.jmeter.gui.JMeterGUIComponent#modifyTestElement(TestElement)
     */
    @Override
    public void modifyTestElement(TestElement s) {
        PubsubSubscriberSampler sampler = (PubsubSubscriberSampler) s;
        super.configureTestElement(sampler);

        sampler.setProjectId(projectId.getText());
    }
}
