package subsystems;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import javax.imageio.ImageIO;

public class ImageBase64 {
    /**
     * Converts an image file to a Base64 encoded string.
     *
     * @param pathToImage the path to the image file to be converted
     * @return a Base64 encoded string representing the image
     * @throws RuntimeException if there are any issues during the conversion process
     */
    public static String imageToBase64(String pathToImage) {
        Image image;
        ImageIO.setUseCache(false);
        try {
            image = ImageIO.read(new File(pathToImage));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        Pattern pngPattern = Pattern.compile("png$", Pattern.CASE_INSENSITIVE);
//        Pattern jpgPattern = Pattern.compile("jpg$", Pattern.CASE_INSENSITIVE);
//        Pattern jpegPattern = Pattern.compile("jpeg$", Pattern.CASE_INSENSITIVE);
//        String fileType = "";
//        if (pngPattern.matcher(pathToImage).find()) {
//            fileType = "png";
//        }
//        if (jpgPattern.matcher(pathToImage).find()) {
//            fileType = "jpg";
//        }
//        if (jpegPattern.matcher(pathToImage).find()) {
//            fileType = "jpeg";
//        }
        return imageToBase64(image);

    }

    public static String imageToBase64(Image image){
        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        bufferedImage.getGraphics().drawImage(image, 0, 0, null);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage, "png", baos); //smaller file size to load faster
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        byte[] bytes = baos.toByteArray();                          //extract data as bytearray
        return Base64.getEncoder().encodeToString(bytes);

    }

    /**
     * Converts a Base64 encoded string back to an image.
     *
     * @param base64 the Base64 encoded string representing the image
     * @return the BufferedImage decoded from the Base64 string
     * @throws RuntimeException if there are any issues during the conversion process
     */
    public static BufferedImage base64ToImage(String base64) {
        byte[] bytes = Base64.getDecoder().decode(base64);          //decodes image for usage
        try {
            return ImageIO.read(new ByteArrayInputStream(bytes));   //reads the decoded image
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
