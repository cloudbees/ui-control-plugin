package org.jenkinsci.plugins.uicontrol.demo;

import hudson.Extension;
import hudson.model.AbstractDescribableImpl;
import hudson.model.Descriptor;
import org.jenkinsci.plugins.uicontrol.path_browser.TreeNode;
import org.jenkinsci.plugins.uicontrol.path_browser.TreeWalker;
import org.kohsuke.stapler.StaplerRequest;
import org.kohsuke.stapler.StaplerResponse;

import javax.servlet.ServletException;
import java.io.File;
import java.io.IOException;

/**
 * @author Kohsuke Kawaguchi
 */
public class PathBrowserDemo extends AbstractDescribableImpl<PathBrowserDemo> {
    /**
     * Initial value of the control.
     */
    public String getControlValue() throws IOException {
        return new File(".").getCanonicalPath();
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

    // serve images, etc
    public void doDynamic(StaplerRequest req, StaplerResponse rsp) throws ServletException, IOException {
        rsp.serveFile(req,getClass().getResource("PathBrowserDemo"+req.getRestOfPath()));
    }

    @Extension
    public static class DescriptorImpl extends Descriptor<PathBrowserDemo> {
        @Override
        public String getDisplayName() {
            return "null";
        }
    }
}
