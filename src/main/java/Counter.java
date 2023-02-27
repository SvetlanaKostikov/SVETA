


import org.apache.pdfbox.pdmodel.PDDocument;

import org.apache.poi.xwpf.usermodel.XWPFDocument;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class Counter {

    public static Integer count=0;
    public static List<String> fyleType = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        fyleType.add(".docx");
        fyleType.add(".pdf");

        Scanner in = new Scanner(System.in);
        System.out.print("Input file path: ");
        Path PATH = Paths.get(in.nextLine());
        in.close();


        List<Path> files = Files.list(PATH).collect(Collectors.toList());
        List<Path> correctFiles = new ArrayList<>();
        for (Path p : files) {
            for (String type : fyleType) {
                if (p.getFileName().toString().endsWith(type)) {
                    correctFiles.add(p);
                }
            }
        }

        for(Path p : correctFiles) {
            if(p.getFileName().toString().endsWith(".pdf")){
                count = count + ConvertToPDF(p.toString());
            }
        }

        System.out.println("Documents: "+correctFiles.size());
        System.out.println("Pages: "+count);

    }

    public static int ConvertToPDF(String path) throws IOException {
        PDDocument doc = PDDocument.load(new File(path));
        int page = doc.getNumberOfPages();
        doc.close();
        return page;
    }

    //выдает ошибку No valid entries or contents found, this is not a valid OOXML (Office Open XML) file
    public static int ConvertToWord(String filePath) throws IOException {
       return new XWPFDocument(new FileInputStream(filePath))
                .getProperties().getExtendedProperties().getUnderlyingProperties().getPages();
    }
}


