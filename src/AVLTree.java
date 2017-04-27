import java.util.ArrayList;
import java.util.List;

/**
 * Created by Muhammad Korra on 27-Apr-17.
 */
public class AVLTree extends AVLNode{

    AVLNode root;

    public AVLTree (AVLNode root){
        this.root=root;
    }
    public  void loadTree()
    {
        insert("Ashraf");
        insert("Ahmed");
        insert("7amada");
    }
    public boolean insert (String x){
        try {
            root = insert (x, root);

            return true;
        } catch(Exception e){
            return false;
        }
    }

    public void remove( String x ) {
        root = remove(x, root);
    }
}
