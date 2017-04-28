/**
 * Created by Muhammad Korra on 27-Apr-17.
 */
public class AVLNode{

    String element;
    AVLNode leftChild, rightChild;
    int height;
    private static final int ALLOWED_IMBALANCE = 1;


    public AVLNode() {
    }

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

    public AVLNode insert (String element, AVLNode node){
        if (node == null)
            return new AVLNode(element);

        int compareResult = element.compareTo(node.element);

        if (compareResult < 0)
            node.leftChild = insert(element, node.leftChild);
        else if (compareResult > 0)
            node.rightChild = insert(element, node.rightChild);

        return balance(node);
    }

    private AVLNode balance (AVLNode node){
        if (node == null)
            return node;

        if (height(node.leftChild) - height(node.rightChild) > ALLOWED_IMBALANCE)
            if (height(node.leftChild.leftChild) >= height(node.leftChild.rightChild))
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

//    public AVLNode remove( String element, AVLNode node )
//    {
//        if( node == null )
//            return node; // Item not found; do nothing
//
//        int compareResult = element.compareTo(node.element);
//
//        if( compareResult < 0 )
//            node.leftChild = remove(element, node.leftChild);
//
//        else if( compareResult > 0 )
//            node.rightChild = remove(element, node.rightChild);
//
//        else if(node.leftChild != null && node.rightChild != null) // Two children
//        {
//            node.element = findMin(node.rightChild).element;
//            node.rightChild = remove(node.element, node.rightChild);
//        }
//        else
//            node = (node.leftChild != null) ? node.leftChild : node.rightChild;
//
//        return balance(node);
//    }

    public AVLNode remove(String x, AVLNode t)throws NullPointerException {
        if (t==null)    {
//            System.out.println("Sorry but you're mistaken, " + t + " doesn't exist in this tree :)\n");
            throw new NullPointerException();
        }
//        System.out.println("Remove starts... " + t.element + " and " + x);

        if (x.compareTo(t.element) < 0 ) {
            t.leftChild = remove(x,t.leftChild);
            int l = t.leftChild != null ? t.leftChild.height : 0;

            if((t.rightChild != null) && (t.rightChild.height - l >= 2)) {
                int rightHeight = t.rightChild.rightChild != null ? t.rightChild.rightChild.height : 0;
                int leftHeight = t.rightChild.leftChild != null ? t.rightChild.leftChild.height : 0;

                if(rightHeight >= leftHeight)
                    t = rotateWithLeftChild(t);
                else
                    t = doubleWithRightChild(t);
            }
        }
        else if (x.compareTo(t.element) > 0) {
            t.rightChild = remove(x,t.rightChild);
            int r = t.rightChild != null ? t.rightChild.height : 0;
            if((t.leftChild != null) && (t.leftChild.height - r >= 2)) {
                int leftHeight = t.leftChild.leftChild != null ? t.leftChild.leftChild.height : 0;
                int rightHeight = t.leftChild.rightChild != null ? t.leftChild.rightChild.height : 0;
                if(leftHeight >= rightHeight)
                    t = rotateWithRightChild(t);
                else
                    t = doubleWithLeftChild(t);
            }
        }
      /*
         Here, we have ended up when we are node which shall be removed.
         Check if there is a left-hand node, if so pick out the largest element out, and move down to the root.
       */
        else if(t.leftChild != null) {
            t.element = findMax(t.leftChild).element;
            remove(t.element, t.leftChild);

            if((t.rightChild != null) && (t.rightChild.height - t.leftChild.height >= 2)) {
                int rightHeight = t.rightChild.rightChild != null ? t.rightChild.rightChild.height : 0;
                int leftHeight = t.rightChild.leftChild != null ? t.rightChild.leftChild.height : 0;

                if(rightHeight >= leftHeight)
                    t = rotateWithLeftChild(t);
                else
                    t = doubleWithRightChild(t);
            }
        }

        else
            t = (t.leftChild != null) ? t.leftChild : t.rightChild;

        if(t != null) {
            int leftHeight = t.leftChild != null ? t.leftChild.height : 0;
            int rightHeight = t.rightChild!= null ? t.rightChild.height : 0;
            t.height = Math.max(leftHeight,rightHeight) + 1;
        }
        return t;
    } //End of remove...

    private AVLNode findMax( AVLNode t )
    {
        if( t == null )
            return t;

        while( t.rightChild!= null )
            t = t.rightChild;
        return t;
    }

}
