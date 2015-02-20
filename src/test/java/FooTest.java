import hudson.model.AbstractDescribableImpl;
import hudson.model.Action;
import hudson.model.Descriptor;
import org.junit.Rule;
import org.junit.Test;
import org.jvnet.hudson.test.JenkinsRule;
import org.jvnet.hudson.test.TestExtension;

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

    @TestExtension
    public static class Descriptorimpl extends Descriptor<FooTest> {
        @Override
        public String getDisplayName() {
            return "null";
        }
    }
}
