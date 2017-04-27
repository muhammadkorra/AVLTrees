/**
 * Created by Muhammad Korra on 27-Apr-17.
 */
public class AVLNode{

    String element;
    AVLNode leftChild, rightChild;
    int height;
    private static final int ALLOWED_IMBALANCE = 1;


    public AVLNode (String element){
        this.element = element;
    }

    public AVLNode (String element, AVLNode leftChild, AVLNode rightChild){
        this.element = element;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
        this.height = 0;
    }

    private int height (AVLNode node){
        return node == null? -1 : node.height;
    }

    private AVLNode insert (String element, AVLNode node){
        if (node == null)
            return new AVLNode(element);

        int compareResult = element.compareTo(node.element);

        if (compareResult < 0)
            node.leftChild = insert(element, node);
        else if (compareResult > 0)
            node.rightChild = insert(element, node);

        return balance(node);
    }

    private AVLNode balance (AVLNode node){
        if (node == null)
            return node;

        if (height(node.leftChild) - height(node.rightChild) > ALLOWED_IMBALANCE)
            if (height(node.leftChild.leftChild) >= height(node.rightChild.leftChild))
                node = rotateWithLeftChild(node);
            else
                node = doubleWithLeftChild(node);
        else if (height(node.rightChild) - height (node.leftChild) > ALLOWED_IMBALANCE)
            if( height(node.rightChild.rightChild) >= height(node.rightChild.leftChild))
                node = rotateWithRightChild(node);
            else
                node = doubleWithRightChild(node);

            node.height = Math.max(height(node.leftChild),height(node.rightChild)) + 1;
            return node;
    }

    private AVLNode rotateWithLeftChild( AVLNode k2) {

        AVLNode k1 = k2.leftChild;
        k2.leftChild = k1.rightChild;
        k1.rightChild = k2;
        k2.height = Math.max (height(k2.leftChild), height(k2.rightChild)) + 1;
        k1.height = Math.max (height(k1.leftChild), k2.height) + 1;
        return k1;

    }

    private AVLNode rotateWithRightChild (AVLNode k1){
        AVLNode k2 = k1.rightChild;
        k1.rightChild = k2.leftChild;
        k2.leftChild = k1;
        k1.height = Math.max (height (k1.leftChild), height (k1.rightChild)) + 1;
        k2.height = Math.max (height (k2.rightChild), k1.height) + 1;
        return k2;
    }

    private AVLNode doubleWithLeftChild( AVLNode k3 ) {

        k3.leftChild = rotateWithRightChild(k3.leftChild);
        return rotateWithLeftChild(k3);
    }

    private AVLNode doubleWithRightChild( AVLNode k3 ) {

        k3.rightChild = rotateWithLeftChild(k3.rightChild);
        return rotateWithRightChild(k3);
    }

    private AVLNode findMin(AVLNode node)     {
        if( node == null )
            return node;

        while( node.leftChild != null )
            node = node.leftChild;

        return node;
    }

    private AVLNode remove( String element, AVLNode node )
    {
        if( node == null )
            return node; // Item not found; do nothing

        int compareResult = element.compareTo(node.element);

        if( compareResult < 0 )
            node.leftChild = remove(element, node.leftChild);

        else if( compareResult > 0 )
            node.rightChild = remove(element, node.rightChild);

        else if(node.leftChild != null && node.rightChild != null) // Two children
        {
            node.element = findMin(node.rightChild).element;
            node.rightChild = remove(node.element, node.rightChild);
        }
        else
            node = (node.leftChild != null) ? node.leftChild : node.rightChild;

        return balance(node);
    }





}
