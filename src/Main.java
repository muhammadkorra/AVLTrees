public class Main {

    public static void main(String[] args) {
        AVLTree avlTree = new AVLTree(new AVLNode("Zeyad"));
//        avlTree.loadTree();
//        avlTree.searchElement("Zeyad");
//        avlTree.searchElement("Ahmed");
//        avlTree.searchElement("Ashraf");
//        avlTree.remove("Zeyad");
//        avlTree.searchElement("Zeyad");

        Dictionary dictionary = new Dictionary(avlTree);
        dictionary.loadDictionary("Dictionary.txt");
        dictionary.batchLookUp("Queries.txt");
        dictionary.batchDelete("Deletions.txt");
        dictionary.searchWord("Backseat");
    }
}
