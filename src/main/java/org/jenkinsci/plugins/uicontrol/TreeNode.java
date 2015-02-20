package org.jenkinsci.plugins.uicontrol;

import hudson.util.HttpResponses;
import org.kohsuke.stapler.HttpResponse;
import org.kohsuke.stapler.StaplerRequest;
import org.kohsuke.stapler.StaplerResponse;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * @author Kohsuke Kawaguchi
 */
public abstract class TreeNode implements HttpResponse {

    /**
     * Opaque ID that client-side JavaScript doesn't try to interpret.
     */
    public abstract String getPath();

    /**
     * Human readable name of this particular node.
     * This name shouldn't include the names of the ancestors.
     */
    public abstract String getDisplayName();

    /**
     * Can this node possibly have children?
     *
     * Used as UI cue.
     */
    public abstract boolean hasChildren();

    /**
     * Can this node be selected as the value of the control?
     */
    public abstract boolean isSelectable();

    /**
     * TODO: needs better documentation
     *
     * Used to draw icons in the left of the item. This value will be directly appended to the CSS class of
     * the LI element. The caller is expected to insert separate CSS stylesheet that binds this to the appropriate
     * icons.
     *
     * This is also used to sort children by similar kinds (such as jobs vs folders)
     */
    public abstract String getType();

    public abstract Iterable<TreeNode> children();

    public abstract TreeNode resolve(String relPath);

    public abstract TreeNode getParent();

    /**
     * {@link TreeNode} should produce a path.
     */
    @Override
    public void generateResponse(StaplerRequest req, StaplerResponse rsp, Object node) throws IOException, ServletException {
        HttpResponses.plainText(getPath()).generateResponse(req,rsp,node);
    }
}
