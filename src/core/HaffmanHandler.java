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
    private File file;
    private HashMap<Character, Integer> numOfChars;
    private HashMap<Character, String> codes;

    public HaffmanHandler(File file) {
        codes = new HashMap<>() ;
        this.file = file;
        numOfChars = getNumOfCharsFromContent(getContentFromFile());
        BinaryTree treeOfFile = BinaryTree.createHaffmanTree(numOfChars);
        generateCodesForChars(treeOfFile);
    }

    public File encode() {
        if (file == null) {
            try {
                throw new FileNotFoundException("Please select a file for encoding");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            return null;
        } else {
            return convertToHaffmanCode();
        }
    }

    private File convertToHaffmanCode() {
        String s = "HaffmanEncoded";
        s += file.getName();
        s += ".txt";
        File encodedFile = new File("../encodedFile/" + s);
        if (encodedFile.exists()) {
            return encodedFile;
        } else {
            String content = getContentFromFile();
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

            return encodedFile;
        }
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

    private String getContentFromFile() {
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
}
