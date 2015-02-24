package org.jenkinsci.plugins.uicontrol.demo;

import hudson.Extension;
import hudson.init.Initializer;
import hudson.model.AbstractDescribableImpl;
import hudson.model.Descriptor;
import hudson.model.RootAction;
import jenkins.model.Jenkins;
import org.jenkinsci.plugins.uicontrol.TreeNode;
import org.jenkinsci.plugins.uicontrol.TreeWalker;

import java.io.File;

/**
 * Demo code to experiment with the path browser control.
 *
 * Activated only during "hpi:run" and is available at <tt>http://localhost:8080/jenkins/ui-control/</tt>
 *
 * @author Kohsuke Kawaguchi
 */
public class Demo extends AbstractDescribableImpl<Demo> implements RootAction {
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

    /**
     * Initial value of the control.
     */
    public String getControlValue() {
        return new File(".").getAbsolutePath();
    }

    /**
     * Bind the endpoint for the control.
     */
    public TreeWalker getTreeData() {
        return new TreeWalker() {
            @Override
            public TreeNode root() {
                File windows = new File("c:\\");
                if (windows.exists())
                    return new FileTreeNode(windows);
                else
                    return new FileTreeNode(new File("/"));
            }

            @Override
            public TreeNode get(String path) {
                return new FileTreeNode(new File(path));
            }
        };
    }

    @Extension
    public static class DescriptorImpl extends Descriptor<Demo> {
        @Override
        public String getDisplayName() {
            return "null";
        }
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
