package Subsystems;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;

public class ImageBase64 {
    public static String imageToBase64(String pathToImage) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(pathToImage));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Pattern pngPattern = Pattern.compile("png$", Pattern.CASE_INSENSITIVE);
        Pattern jpgPattern = Pattern.compile("jpg$", Pattern.CASE_INSENSITIVE);
        Pattern jpegPattern = Pattern.compile("jpeg$", Pattern.CASE_INSENSITIVE);
        String fileType = "";
        if (pngPattern.matcher(pathToImage).find()) {
            fileType = "png";
        }
        if (jpgPattern.matcher(pathToImage).find()) {
            fileType = "jpg";
        }
        if (jpegPattern.matcher(pathToImage).find()) {
            fileType = "jpeg";
        }
        try {
            ImageIO.write(image, fileType, baos);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        byte[] bytes = baos.toByteArray();
        return Base64.getEncoder().encodeToString(bytes);
    }

    public static BufferedImage base64ToImage(String base64) {
        byte[] bytes = Base64.getDecoder().decode(base64);
        try {
            return ImageIO.read(new ByteArrayInputStream(bytes));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
