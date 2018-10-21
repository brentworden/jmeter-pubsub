package com.github.brentworden.jmeter.protocol.pubsub.gui;

import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import org.apache.jmeter.gui.util.VerticalPanel;
import org.apache.jmeter.samplers.gui.AbstractSamplerGui;
import org.apache.jorphan.gui.JLabeledTextField;

public abstract class AbstractPubsubSamplerGui extends AbstractSamplerGui {

    private static final long serialVersionUID = 1L;

    protected final JLabeledTextField projectId = new JLabeledTextField("Project ID");

    protected final JPanel createMainPanel() {
        return new VerticalPanel();
    }

    protected final JPanel createProjectPanel() {
        JPanel projectPanel = new JPanel(new BorderLayout());
        projectPanel.setBorder(
                BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Google Cloud Project"));
        projectId.setToolTipText("Google Cloud Project ID");
        projectPanel.add(projectId);
        return projectPanel;
    }
}
