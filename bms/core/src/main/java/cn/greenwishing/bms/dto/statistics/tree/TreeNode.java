package cn.greenwishing.bms.dto.statistics.tree;

import cn.greenwishing.bms.dto.SelectItem;
import cn.greenwishing.bms.dto.Selectable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wufan
 * @date 2017/12/2
 */
public class TreeNode extends SelectItem {

    private List<TreeNode> children = new ArrayList<>();

    public TreeNode(Selectable selectable) {
        super(selectable);
    }

    public TreeNode(String value, String label) {
        super(value, label);
    }

    public void addChild(TreeNode child) {
        this.children.add(child);
    }

    public List<TreeNode> getChildren() {
        return children;
    }
}
