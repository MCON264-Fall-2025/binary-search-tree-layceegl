package bst;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinarySearchTree<T extends Comparable<T>> {

    private TreeNode<T> root;

    public void insert(T value) {
        root = insertRecursive(root, value);
    }

    private TreeNode<T> insertRecursive(TreeNode<T> node, T value) {
        if (node == null) {
            return new TreeNode<>(value);
        }
        int cmp = value.compareTo(node.value);
        if (cmp < 0) {
            node.left = insertRecursive(node.left, value);
        } else if (cmp > 0) {
            node.right = insertRecursive(node.right, value);
        }
        // ignore duplicates for this assignment
        return node;
    }

    public TreeNode<T> getRoot() {
        return root;
    }

    // --------- Recursive Traversals ----------

    public List<T> preorderRecursive() {
        List<T> result = new ArrayList<>();
        preorderRecursive(root, result);
        return result;
    }

    private void preorderRecursive(TreeNode<T> node, List<T> out) {
        // TODO: implement Preorder: Root -> Left -> Right
        // hint: check for null, then visit node, then recurse on left and right
        if (node == null) {
            return;
        }
        out.add(node.value);
        preorderRecursive(node.left, out);
        preorderRecursive(node.right, out);
    }

    public List<T> inorderRecursive() {
        List<T> result = new ArrayList<>();
        inorderRecursive(root, result);
        return result;
    }

    private void inorderRecursive(TreeNode<T> node, List<T> out) {
        // TODO: implement Inorder: Left -> Root -> Right
        if (node == null) {
            return;
        }
        inorderRecursive(node.left, out);
        out.add(node.value);
        inorderRecursive(node.right, out);
    }

    public List<T> postorderRecursive() {
        List<T> result = new ArrayList<>();
        postorderRecursive(root, result);
        return result;
    }

    private void postorderRecursive(TreeNode<T> node, List<T> out) {
        // TODO: implement Postorder: Left -> Right -> Root
        if (node == null) {
            return;
        }
        postorderRecursive(node.left, out);
        postorderRecursive(node.right, out);
        out.add(node.value);
    }

    // --------- Level-order (Breadth-First) ----------

    public List<T> levelOrder() {
        List<T> result = new ArrayList<>();
        // TODO: implement level-order using a Queue<TreeNode<T>>
        // 1. if root is null, return empty list
        Queue<TreeNode<T>> queue = new LinkedList<>();
        if (root == null) {
            return result;
        }
        // 2. enqueue root
        queue.add(root);
        // 3. while queue not empty:
        while(!queue.isEmpty()) {
            TreeNode<T> node = queue.poll();
            result.add(node.getValue());
            if(node.getLeft() != null) {
                queue.offer(node.getLeft());
            }
            if(node.getRight() != null) {
                queue.offer(node.getRight());
            }
        }
        //      - dequeue node
        //      - add node.value to result
        //      - enqueue children if not null (left then right)
        return result;
    }

    // --------- Unified API via TraversalType ----------

    public List<T> getByTraversal(TraversalType type) {
        // TODO: dispatch based on traversal type
        return switch (type) {
            case INORDER ->
                inorderRecursive();
            case POSTORDER ->
                postorderRecursive();
            case PREORDER ->
                preorderRecursive();
            case LEVEL_ORDER ->
                levelOrder();
            default ->
                throw new IllegalArgumentException("Not implemented yet");
        };

    }
}
