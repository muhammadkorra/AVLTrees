import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by zeyadzanaty on 4/28/17.
 */
public class Dictionary {
    AVLTree tree;
    int wordCount=0;
    public Dictionary(AVLTree tree) {
        this.tree = tree;
    }
    public void loadDictionary(String fileName)
    {
        BufferedReader br = null;
        FileReader fr = null;

        try {

            fr = new FileReader(fileName);
            br = new BufferedReader(fr);

            String sCurrentLine;

            br = new BufferedReader(new FileReader(fileName));

            while ((sCurrentLine = br.readLine()) != null) {
                if(tree.insert(sCurrentLine.trim()))
                wordCount++;
            }
        }
        catch (Exception e) { e.printStackTrace();}
        finally {
            try {
                if (br != null)
                    br.close();

                if (fr != null)
                    fr.close();
                System.out.println(wordCount+" words added successfully!");
            }
            catch (IOException ex) {ex.printStackTrace();}
        }
    }
    public void searchWord(String word)
    {
        if(tree.searchElement(word))
        {
            System.out.println("'"+word+"' found!");
        }
        else
        {
            System.out.println("'"+word+"' not found!");
        }
    }
    public void removeWord(String word)
    {
        if(tree.remove(word)) {
            wordCount--;
            System.out.println("Word '"+word+"' removed!" );
        }
        else
        {
            System.out.println("Remove failed // Word not found in dictionary");
        }
    }
    public void insertWord(String word)
    {
        if(!tree.searchElement(word))
        {
            tree.insert(word);
            wordCount++;
            System.out.println("Word '"+word+"' added!" );

        }
        else
        {
            System.out.println("Error: '"+word+"' already added" );

        }

    }
    public int getSize()
    {
        return wordCount;
    }
    public void batchLookUp(String fileName)
    {
        BufferedReader br = null;
        FileReader fr = null;
        int foundWords=0;
        try {

            fr = new FileReader(fileName);
            br = new BufferedReader(fr);

            String sCurrentLine;

            br = new BufferedReader(new FileReader(fileName));

            while ((sCurrentLine = br.readLine()) != null) {
                if(tree.searchElement(sCurrentLine.trim())) {
                    System.out.println(sCurrentLine+"- Found!");
                    foundWords++;
                }
                else
                {
                    System.out.println(sCurrentLine+"- Not Found!");
                }
            }
        }
        catch (Exception e) { e.printStackTrace();}
        finally {
            try {
                if (br != null)
                    br.close();
                if (fr != null)
                    fr.close();
                System.out.println(foundWords+" words found!");
            }
            catch (IOException ex) {ex.printStackTrace();}
        }
    }
    public void batchDelete(String fileName)
    {
        BufferedReader br = null;
        FileReader fr = null;
        int foundWords=0;
        try {

            fr = new FileReader(fileName);
            br = new BufferedReader(fr);

            String sCurrentLine;

            br = new BufferedReader(new FileReader(fileName));

            while ((sCurrentLine = br.readLine()) != null) {
                if(tree.remove(sCurrentLine.trim())) {
                    System.out.println(sCurrentLine+"- deleted!");
                    foundWords++;
                }
                else
                {
                    System.out.println(sCurrentLine+"- Not Found!");
                }
            }
        }
        catch (Exception e) { e.printStackTrace();}
        finally {
            try {
                if (br != null)
                    br.close();
                if (fr != null)
                    fr.close();
                System.out.println(foundWords+" words deleted!");
            }
            catch (IOException ex) {ex.printStackTrace();}
        }
    }

}
