package org.jenkinsci.plugins.uicontrol.demo;

import hudson.init.Initializer;
import hudson.model.RootAction;
import jenkins.model.Jenkins;

/**
 * Demo code to experiment with the path browser control.
 *
 * Activated only during "hpi:run" and is available at <tt>http://localhost:8080/jenkins/ui-control/</tt>
 *
 * @author Kohsuke Kawaguchi
 */
public class Demo  implements RootAction {
    @Override
    public String getIconFileName() {
        return null;
    }

    @Override
    public String getDisplayName() {
        return "Demo";
    }

    @Override
    public String getUrlName() {
        return "ui-control";
    }

    // navigation to various demos. TODO: use @Extension and discovery

    public PathBrowserDemo getPathBrowser() {
        return new PathBrowserDemo();
    }

    /**
     * Only activate this during "hpi:run"
     */
    @Initializer
    public static void install() {
        if (Boolean.getBoolean("hudson.hpi.run"))
            Jenkins.getInstance().getActions().add(new Demo());
    }
}
