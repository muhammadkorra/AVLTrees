public class Main {

    public static void main(String[] args) {
        AVLTree avlTree = new AVLTree(new AVLNode("Zeyad"));
        avlTree.loadTree();
        avlTree.searchElement("Zeyad");
        avlTree.searchElement("Mohamed");
        avlTree.searchElement("Ashraf");

    }
}
