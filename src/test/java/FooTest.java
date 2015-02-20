import hudson.model.AbstractDescribableImpl;
import hudson.model.Action;
import hudson.model.Descriptor;
import org.jenkinsci.plugins.uicontrol.TreeNode;
import org.jenkinsci.plugins.uicontrol.TreeWalker;
import org.junit.Rule;
import org.junit.Test;
import org.jvnet.hudson.test.JenkinsRule;
import org.jvnet.hudson.test.TestExtension;

import java.io.File;

/**
 * @author Kohsuke Kawaguchi
 */
public class FooTest extends AbstractDescribableImpl<FooTest> implements Action {
    @Rule
    public JenkinsRule rule = new JenkinsRule();

    @Test
    public void testTreeComponent() throws Exception {
        // expose itself to the URL space
        rule.jenkins.getActions().add(this);

        rule.interactiveBreak();
    }

    @Override
    public String getIconFileName() {
        return null;
    }

    @Override
    public String getDisplayName() {
        return null;
    }

    @Override
    public String getUrlName() {
        return "test";
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

    @TestExtension
    public static class Descriptorimpl extends Descriptor<FooTest> {
        @Override
        public String getDisplayName() {
            return "null";
        }
    }
}
