import org.jenkinsci.plugins.uicontrol.TreeNode;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Kohsuke Kawaguchi
 */
public class FileTreeNode extends TreeNode {
    private final File file;

    public FileTreeNode(File file) {
        this.file = file;
    }

    @Override
    public String getPath() {
        return file.getAbsolutePath();
    }

    @Override
    public String getDisplayName() {
        return file.getName();
    }

    @Override
    public boolean hasChildren() {
        return file.isDirectory();
    }

    @Override
    public boolean isSelectable() {
        return true;
    }

    @Override
    public String getType() {
        return file.isDirectory() ? "dir" : "file";
    }

    @Override
    public Iterable<TreeNode> children() {
        File[] children = file.listFiles();
        List<TreeNode> r = new ArrayList<TreeNode>();
        for (File c : children) {
            r.add(new FileTreeNode(c));
        }
        return r;
    }

    @Override
    public TreeNode resolve(String relPath) {
        return new FileTreeNode(new File(file,relPath));
    }

    @Override
    public TreeNode getParent() {
        return new FileTreeNode(file.getParentFile());
    }
}
