package cn.greenwishing.bms.dto.statistics.tree;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Wufan
 * @date 2017/12/2
 */
public class TreeNode {

    private Serializable value;
    private String label;
    private List<TreeNode> children = new ArrayList<>();

    public TreeNode(Serializable value, String label) {
        this.value = value;
        this.label = label;
    }

    public Serializable getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }

    public void addChild(TreeNode child) {
        this.children.add(child);
    }

    public List<TreeNode> getChildren() {
        return children;
    }
}
