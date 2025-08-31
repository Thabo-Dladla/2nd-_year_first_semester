// Hussein's AVL Tree
// 2 April 2017
// Hussein Suleman
// reference: kukuruku.co/post/avl-trees/
/**Modified by Thabo Dladla
 *27  March 2025
 */

public class AVLTree<dataType extends Comparable<? super dataType>> extends BinaryTree<dataType> {

    private int searchCount = 0;
    private int insertCount = 0;
    private int opCount = 0;

    public int getSearchCount() {
        return searchCount;
    }

    public int getInsertCount() {
        return insertCount;
    }

    public void incrementer() {
        opCount = opCount + 1;
    }

    public int getOpCount() {
        return opCount;
    }

    public int height(BinaryTreeNode<dataType> node) {
        if (node != null)
            return node.height;
        return -1;
    }

    public int balanceFactor(BinaryTreeNode<dataType> node) {
        return height(node.right) - height(node.left);
    }

    public void fixHeight(BinaryTreeNode<dataType> node) {
        node.height = Math.max(height(node.left), height(node.right)) + 1;
    }

    public BinaryTreeNode<dataType> rotateRight(BinaryTreeNode<dataType> p) {
        BinaryTreeNode<dataType> q = p.left;
        p.left = q.right;
        q.right = p;
        fixHeight(p);
        fixHeight(q);
        return q;
    }

    public BinaryTreeNode<dataType> rotateLeft(BinaryTreeNode<dataType> q) {
        BinaryTreeNode<dataType> p = q.right;
        q.right = p.left;
        p.left = q;
        fixHeight(q);
        fixHeight(p);
        return p;
    }

    public BinaryTreeNode<dataType> balance(BinaryTreeNode<dataType> p) {
        fixHeight(p);
        if (balanceFactor(p) == 2) {
            if (balanceFactor(p.right) < 0)
                p.right = rotateRight(p.right);
            return rotateLeft(p);
        }
        if (balanceFactor(p) == -2) {
            if (balanceFactor(p.left) > 0)
                p.left = rotateLeft(p.left);
            return rotateRight(p);
        }
        return p;
    }

    public void insert(dataType d) {
        root = insert(d, root);
    }

    /**
     * A method used to insert new data into the AVL tree and balance the tree after each insertion if needed
     * @param d the new data to be inserted
     * @param node the current node where the new data might be inserted
     */
    public BinaryTreeNode<dataType> insert(dataType d, BinaryTreeNode<dataType> node) {
        if (node == null) {
            insertCount++;
            incrementer();
            return new BinaryTreeNode<dataType>(d, null, null);
        }

        incrementer();
        if (d.compareTo(node.data) < 0)
            node.left = insert(d, node.left);
        else
            node.right = insert(d, node.right);
        return balance(node);
    }

    public void delete(dataType d) {
        root = delete(d, root);
    }

    public BinaryTreeNode<dataType> delete(dataType d, BinaryTreeNode<dataType> node) {
        if (node == null) return null;
        if (d.compareTo(node.data) < 0)
            node.left = delete(d, node.left);
        else if (d.compareTo(node.data) > 0)
            node.right = delete(d, node.right);
        else {
            BinaryTreeNode<dataType> q = node.left;
            BinaryTreeNode<dataType> r = node.right;
            if (r == null)
                return q;
            BinaryTreeNode<dataType> min = findMin(r);
            min.right = removeMin(r);
            min.left = q;
            return balance(min);
        }
        return balance(node);
    }

    public BinaryTreeNode<dataType> findMin(BinaryTreeNode<dataType> node) {
        if (node.left != null)
            return findMin(node.left);
        else
            return node;
    }

    public BinaryTreeNode<dataType> removeMin(BinaryTreeNode<dataType> node) {
        if (node.left == null)
            return node.right;
        node.left = removeMin(node.left);
        return balance(node);
    }

    public BinaryTreeNode<dataType> find(dataType d) {
        if (root == null) {
            incrementer();
            searchCount++;
            return null;
        } else
            return find(d, root);
    }

    /**
     * A searching method used when looking to find data in the AVL tree
     * @param node the node to be found in tree
     * @param d the data in the node we are searching for
     * @return the node we are looking for, or {@code null} if it is not found
     */
    public BinaryTreeNode<dataType> find(dataType d, BinaryTreeNode<dataType> node) {
        searchCount++;
        incrementer();
        if (d.compareTo(node.data) == 0) {
            return node;
        }

        searchCount++;
        incrementer();
        if (d.compareTo(node.data) < 0) {
            return (node.left == null) ? null : find(d, node.left);
        }

        return (node.right == null) ? null : find(d, node.right);
    }

    public void treeOrder() {
        treeOrder(root, 0);
    }

    public void treeOrder(BinaryTreeNode<dataType> node, int level) {
        if (node != null) {
            for (int i = 0; i < level; i++)
                System.out.print(" ");
            System.out.println(node.data);
            treeOrder(node.left, level + 1);
            treeOrder(node.right, level + 1);
        }
    }
}
