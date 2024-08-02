package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.Objects;

public class PDFBase64 {
    public static String PDFToBase64(File pdfPath){
        byte[] pdfBytes = null;
        try {
            pdfBytes = Files.readAllBytes(pdfPath.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return Base64.getEncoder().encodeToString(pdfBytes);
    }
    public static void base64ToPDF(String base64String, String pdfPath){
        byte[] pdfBytes = Base64.getDecoder().decode(base64String);
        File pdfFile = new File(pdfPath);
        File[] files = pdfFile.getParentFile().listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.delete()){
                    System.out.println("file in pdf temp deleted");
                }
            }
        }
        try (FileOutputStream fos = new FileOutputStream(pdfFile)) {
                fos.write(pdfBytes);
                fos.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void base64ToPdfAndOpen(String base64String, String pdfPath){
        base64ToPDF(base64String, pdfPath);
        Process openPdfFile;
        try {
            //will only work on windows
            openPdfFile = Runtime.getRuntime().exec("explorer.exe \"" + pdfPath + '\"');
            openPdfFile.getOutputStream().close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
