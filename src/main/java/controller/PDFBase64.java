package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.Objects;

public class PDFBase64 {
    /**
     * Converts a PDF file to a Base64 encoded string.
     *
     * @param pdfPath the File object representing the path to the PDF file
     * @return a Base64 encoded string representation of the PDF file
     * @throws RuntimeException if an I/O error occurs while reading the file
     */
    public static String PDFToBase64(File pdfPath){
        byte[] pdfBytes;
        try {
            pdfBytes = Files.readAllBytes(pdfPath.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return Base64.getEncoder().encodeToString(pdfBytes);
    }

    /**
     * Converts a Base64 encoded string to a PDF file and saves it to the specified path.
     *
     * <p>This method decodes the provided Base64 string into a byte array, deletes any existing files 
     * in the directory of the specified PDF path, and writes the byte array to a new PDF file.</p>
     *
     * @param base64String the Base64 encoded string representing the PDF content. If null, an empty string is used.
     * @param pdfPath the file path where the PDF will be saved. This should include the file name and extension.
     * @throws RuntimeException if an I/O error occurs while writing the PDF file.
     */
    public static void base64ToPDF(String base64String, String pdfPath){
        if (base64String == null) {
            base64String = "";
        }
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

    /**
     * Converts a Base64 encoded string to a PDF file and opens it using the default PDF viewer.
     *
     * <p>This method first decodes the provided Base64 string into a PDF file at the specified path.
     * After successfully creating the PDF file, it attempts to open the file using the Windows Explorer.</p>
     *
     * @param base64String the Base64 encoded string representing the PDF content
     * @param pdfPath the file system path where the PDF file will be saved
     * @throws RuntimeException if an I/O error occurs while trying to open the PDF file
     */
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
