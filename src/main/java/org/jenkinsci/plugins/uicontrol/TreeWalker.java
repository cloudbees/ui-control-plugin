package org.jenkinsci.plugins.uicontrol;

import hudson.util.HttpResponses;
import org.apache.commons.lang.StringUtils;
import org.kohsuke.stapler.HttpResponse;
import org.kohsuke.stapler.QueryParameter;

/**
 * @author Kohsuke Kawaguchi
 */
public abstract class TreeWalker {
    public abstract TreeNode root();
    public abstract TreeNode get(String path);

    /**
     * Resolves a relative path component given in {@code from} against the absolute path component given in {@code path}.
     *
     * It renders the absolute path of the result of the resolution.
     */
    public TreeNode doResolve(@QueryParameter(required = true) String base, @QueryParameter(required = false) String relPath) {
        if (StringUtils.isBlank(base)) {
            return get(relPath);
        } else {
            TreeNode r = get(base).resolve(relPath);
            if (r==null)
                throw HttpResponses.notFound();
            return r;
        }
    }

    /**
     *
     */
    public HttpResponse doDescribe(@QueryParameter(required = true) String path,
                                   @QueryParameter(required = false) String type) {
        final TreeNode base = get(path);

        if (base==null)
            throw HttpResponses.notFound();

        return new TreeDescription(base);
    }
}
