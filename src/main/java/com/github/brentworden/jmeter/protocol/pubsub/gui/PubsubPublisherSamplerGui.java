package com.github.brentworden.jmeter.protocol.pubsub.gui;

import com.github.brentworden.jmeter.protocol.pubsub.PubsubPublisherSampler;
import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import org.apache.jmeter.testelement.TestElement;
import org.apache.jorphan.gui.JLabeledTextField;

/**
 *
 */
public class PubsubPublisherSamplerGui extends AbstractPubsubSamplerGui {

    private static final long serialVersionUID = 1L;

    private final JLabeledTextField topicId = new JLabeledTextField("Topic ID");

    public PubsubPublisherSamplerGui() {
        super();
        init();
    }

    @Override
    public void clearGui() {
        super.clearGui();
        projectId.setText("");
        topicId.setText("");
    }

    /**
     * the implementation loads the URL and the soap action for the request.
     */
    @Override
    public void configure(TestElement el) {
        super.configure(el);
        PubsubPublisherSampler sampler = (PubsubPublisherSampler) el;
        projectId.setText(sampler.getProjectId());
        topicId.setText(sampler.getTopicId());
    }

    /**
     * @see org.apache.jmeter.gui.JMeterGUIComponent#createTestElement()
     */
    @Override
    public TestElement createTestElement() {
        PubsubPublisherSampler sampler = new PubsubPublisherSampler();
        modifyTestElement(sampler);
        return sampler;
    }

    private final JPanel createTopicPanel() {
        JPanel topicPanel = new JPanel(new BorderLayout());
        topicPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Pub/Sub Topic"));
        projectId.setToolTipText("Pub/Sub Topic ID");
        topicPanel.add(topicId);
        return topicPanel;
    }

    @Override
    public String getLabelResource() {
        return "pubsub_publisher_title";
    }

    @Override
    public String getStaticLabel() {
        return "Pub/Sub Publisher";
    }

    private void init() {
        // WARNING: called from ctor so must not be overridden
        setLayout(new BorderLayout());
        setBorder(makeBorder());
        add(makeTitlePanel(), BorderLayout.NORTH);

        JPanel mainPanel = createMainPanel();
        JPanel projectPanel = createProjectPanel();
        JPanel topicPanel = createTopicPanel();

        mainPanel.add(projectPanel);
        mainPanel.add(topicPanel);
        add(mainPanel, BorderLayout.CENTER);
    }

    /**
     * Modifies a given TestElement to mirror the data in the gui components.
     *
     * @see org.apache.jmeter.gui.JMeterGUIComponent#modifyTestElement(TestElement)
     */
    @Override
    public void modifyTestElement(TestElement s) {
        PubsubPublisherSampler sampler = (PubsubPublisherSampler) s;
        super.configureTestElement(sampler);

        sampler.setProjectId(projectId.getText());
        sampler.setTopicId(topicId.getText());
    }
}
