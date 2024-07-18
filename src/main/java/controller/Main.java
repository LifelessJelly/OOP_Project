package controller;


import com.formdev.flatlaf.FlatLightLaf;


import javax.swing.*;


public class Main {



    public static void main(String[] args) {


        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);

        }
//        new InfobaseMainframe();
//        Image image;
//        try {
//            image = ImageIO.read(new File("E:\\IDEAProjects\\OOP\\WhatsApp Image 2024-05-31 at 23.27.42_a7d7c681.jpg"));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        System.out.println(ImageBase64.imageToBase64(image.getScaledInstance(400, 300, Image.SCALE_SMOOTH)));

        new LoginMainframe();

    }
}

