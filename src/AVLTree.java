import java.util.ArrayList;
import java.util.List;

/**
 * Created by Muhammad Korra on 27-Apr-17.
 */
public class AVLTree extends AVLNode{

    private AVLNode root;

    public AVLTree (AVLNode root){
        this.root=root;
    }
    public  void loadTree()
    {
        insert("Ashraf");
        insert("Ahmed");
        insert("7amada");
        insert("Ashraf");
    }

    public boolean searchElement(String element){
        if (search (root,element)) {
//            System.out.println("Element found");
            return true;
        }
        else
        {
//            System.out.println("Element was not found");
            return false;
        }
    }

    private boolean search(AVLNode r, String val)
    {
        boolean found = false;
        while ((r != null) && !found)
        {
            String rval = r.element;
            if (val.compareTo(rval) < 0)
                r = r.leftChild;
            else if (val.compareTo(rval) > 0)
                r = r.rightChild;
            else
            {
                found = true;
                break;
            }
            found = search(r, val);
        }
        return found;
    }


    public boolean insert (String x){
        try {
            root = insert (x, root);
            return true;
        } catch(Exception e){
            return false;
        }
    }

    public boolean remove( String x ) {
        try {
            root = remove(x, root);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
}
