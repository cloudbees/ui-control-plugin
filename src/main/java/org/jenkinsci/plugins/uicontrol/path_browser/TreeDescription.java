package org.jenkinsci.plugins.uicontrol.path_browser;

import org.kohsuke.stapler.HttpResponse;
import org.kohsuke.stapler.StaplerRequest;
import org.kohsuke.stapler.StaplerResponse;
import org.kohsuke.stapler.export.Exported;
import org.kohsuke.stapler.export.ExportedBean;
import org.kohsuke.stapler.export.Flavor;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Kohsuke Kawaguchi
 */
@ExportedBean
public class TreeDescription implements HttpResponse {

    private final TreeNode self;

    public TreeDescription(TreeNode self) {
        this.self = self;
    }

    @Exported(inline=true)
    public List<TreeNode> getParents() {
        List<TreeNode> r = new ArrayList<TreeNode>();
        for (TreeNode n = self; n!=null ; n=n.getParent())
            r.add(n);
        return r;
    }

    /**
     * Inline properties of the self node.
     */
    public TreeNode getSelf() {
        return self;
    }

    @Exported(inline=true)
    public List<TreeNode> getChildren() {
        List<TreeNode> r = new ArrayList<TreeNode>();
        for (TreeNode n : self.children())
            r.add(n);
        return r;
    }

    // expose TreeNode properties.

    @Exported
    public String getPath() {
        return getSelf().getPath();
    }

    @Exported(name="name")
    public String getDisplayName() {
        return getSelf().getDisplayName();
    }

    @Exported
    public boolean hasChildren() {
        return getSelf().hasChildren();
    }

    @Exported
    public boolean isSelectable() {
        return getSelf().isSelectable();
    }

    @Exported
    public String getType() {
        return getSelf().getType();
    }

    @Override
    public void generateResponse(StaplerRequest req, StaplerResponse rsp, Object node) throws IOException, ServletException {
        rsp.serveExposedBean(req,this,Flavor.JSON);
    }
}
