//L: BSTs
// Noelani Mishina Hinh and George Morales
//Professor Cwir
//CS 2013
//2 May 2024

import java.util.TreeMap;
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
    this.val = val;
    this.left = left;
    this.right = right;
    }
}

class Solution {
    public TreeNode searchBST(TreeNode root, int val) {
        TreeNode curNode = root;
        //Implementation of TreeMap class using Red-Black Tree search
        while(curNode != null) { //Current node has no value, doesn't run if null
            if(val == curNode.val) {
                return curNode; //Self-sorting and self-balancing
            }
            else if(val < curNode.val) {
                curNode = curNode.left;
            }
            else {
                curNode = curNode.right ;
            }
        }

        return null;

        }
    }
}