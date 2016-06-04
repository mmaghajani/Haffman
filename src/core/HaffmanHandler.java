package core;

import core.tree.BinaryTree;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

/**
 * Created by mma on 6/4/2016.
 */
public class HaffmanHandler {
    private File decodedFile;
    private File encodedFile;
    private HashMap<Character, Integer> numOfChars;
    private HashMap<Character, String> codes;
    private BinaryTree treeOfFile;

    public HaffmanHandler(File decodedFile) {
        codes = new HashMap<>();
        this.decodedFile = decodedFile;
        numOfChars = getNumOfCharsFromContent(getContentFromFile(decodedFile)) ;
        treeOfFile = new BinaryTree() ;
        treeOfFile = treeOfFile.createHaffmanTree(numOfChars);
        generateCodesForChars(treeOfFile);
        System.out.println(codes) ;
    }

    public HaffmanHandler(File encodedFile, File tree) {
        codes = new HashMap<>();
        treeOfFile = readTreeFromFile(tree);
        generateCodesForChars(treeOfFile);
        this.encodedFile = encodedFile;
        numOfChars = new HashMap<>() ;
        setNumOfCharsFromTree();
    }

    public void setDecodedFile(File decodedFile){
        this.decodedFile = decodedFile;
        numOfChars = getNumOfCharsFromContent(getContentFromFile(decodedFile));
        treeOfFile = new BinaryTree() ;
        treeOfFile = treeOfFile.createHaffmanTree(numOfChars);
        generateCodesForChars(treeOfFile);
    }

    public void setEncodedFile(File encodedFile, File tree){
        treeOfFile = readTreeFromFile(tree);
        generateCodesForChars(treeOfFile);
        this.encodedFile = encodedFile;
        numOfChars = new HashMap<>() ;
        setNumOfCharsFromTree();
    }

    private BinaryTree readTreeFromFile(File tree) {
        try {
            InputStream is = new FileInputStream(tree);
            ObjectInputStream ois = new ObjectInputStream(is);
            try {
                return (BinaryTree) ois.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public File encode() throws FileNotFoundException {
        if (decodedFile == null) {
            throw new FileNotFoundException("Please select a file for encoding");
        } else {
            return convertToHaffmanCode();
        }
    }

    public File decode() throws FileNotFoundException {
        if (codes == null) {
            throw new FileNotFoundException("No tree file founded");
        } else {
            if (decodedFile != null) {
                return decodedFile;
            } else {
                String s = "HaffmanDecoded";
                s += encodedFile.getName();
                decodedFile = new File("decodedFile/" + s);
                String content = getContentFromFile(encodedFile);
                String temp = "";
                try {
                    FileWriter writer = new FileWriter(decodedFile);
                    for (int i = 0; i < content.length(); i++) {
                        temp += content.charAt(i);
                        if (codes.values().contains(temp)) {
                            for (char c : codes.keySet()) {
                                if (codes.get(c).equals(temp)) {
                                    writer.write(c);
                                    temp = "";
                                }
                            }
                        }
                    }

                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return decodedFile;
            }
        }
    }

    private File convertToHaffmanCode() {
        if (encodedFile == null) {
            String s = "HaffmanEncoded";
            s += decodedFile.getName();
            encodedFile = new File("encodedFile/" + s);
            if (encodedFile.exists()) {
                return encodedFile;
            } else {
                try {
                    encodedFile.createNewFile() ;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String content = getContentFromFile(decodedFile);
                String extension = "";
                for (int i = 0; i < content.length(); i++) {
                    extension += codes.get(content.charAt(i));
                }

                try {
                    FileWriter writer = new FileWriter(encodedFile);
                    writer.write(extension);
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                String t = "HaffmanTree";
                t += decodedFile.getName();
                File tree = new File("encodedFile/" + t);
                try {
                    OutputStream os = new FileOutputStream(tree);
                    ObjectOutputStream oos = new ObjectOutputStream(os);
                    oos.writeObject(this.treeOfFile);
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return encodedFile;
            }
        } else
            return encodedFile;
    }

    private void generateCodesForChars(BinaryTree treeOfFile) {
        dfs(treeOfFile.getRoot(), "");
    }

    private void dfs(BinaryTree.Node root, String code) {
        if (root.getLeft() == null) {
            char c = root.getLabel().charAt(0);
            codes.put(c, code);
        } else {
            dfs(root.getLeft(), code + "0");
            dfs(root.getRight(), code + "1");
        }
    }

    private void dfs(BinaryTree.Node root) {
        if (root.getLeft() == null) {
            char c = root.getLabel().charAt(0);
            numOfChars.put(c, root.getNum());
        } else {
            dfs(root.getLeft());
            dfs(root.getRight());
        }
    }

    private HashMap<Character, Integer> getNumOfCharsFromContent(String content) {
        HashMap<Character, Integer> numOfChars = new HashMap<>();
        for (int i = 0; i < content.length(); i++) {
            char c = content.charAt(i);
            if (numOfChars.keySet().contains(c)) {
                int x = numOfChars.get(c);
                x++;
                numOfChars.replace(c, x);
            } else {
                numOfChars.put(c, 1);
            }
        }

        return numOfChars;
    }

    private String getContentFromFile(File file) {
        String content = "";
        try {
            for (String string : Files.readAllLines(Paths.get(file.getPath()))) {
                content += string;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content;
    }

    public void setNumOfCharsFromTree() {
        dfs(treeOfFile.getRoot());
    }
}
