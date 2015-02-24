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
