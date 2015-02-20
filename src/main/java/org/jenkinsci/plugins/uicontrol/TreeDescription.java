package org.jenkinsci.plugins.uicontrol;

import hudson.model.Api;
import org.kohsuke.stapler.HttpResponse;
import org.kohsuke.stapler.StaplerRequest;
import org.kohsuke.stapler.StaplerResponse;
import org.kohsuke.stapler.export.Exported;
import org.kohsuke.stapler.export.ExportedBean;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Kohsuke Kawaguchi
 */
@ExportedBean
class TreeDescription implements HttpResponse {

    private final TreeNode self;

    public TreeDescription(TreeNode self) {
        this.self = self;
    }

    @Exported
    public List<TreeNode> getParents() {
        List<TreeNode> r = new ArrayList<TreeNode>();
        for (TreeNode n = self; n!=null ; n=n.getParent())
            r.add(n);
        return r;
    }

    /**
     * Inline properties of the self node.
     */
    @Exported(inline=true)
    public TreeNode getSelf() {
        return self;
    }

    @Exported
    public List<TreeNode> getChildren() {
        List<TreeNode> r = new ArrayList<TreeNode>();
        for (TreeNode n : self.children())
            r.add(n);
        return r;
    }

    @Override
    public void generateResponse(StaplerRequest req, StaplerResponse rsp, Object node) throws IOException, ServletException {
        new Api(this).doJson(req,rsp);
    }
}
