package controller;


import com.formdev.flatlaf.FlatLightLaf;
import data.Staff;
import subsystems.ImageBase64;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;


public class Main {



    public static void main(String[] args) {


        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);

        }
        new InfobaseMainframe(new Staff("I touch kids", Staff.MANAGER));

//        Image image1;
//        Image image2;
//        Image image3;
//        Image image4;
//        Image image5;
//        Image image6;
//        Image image7;
//        Image image8;
//        try {
//            image1 = ImageIO.read(new File("E:\\IDEAProjects\\OOP\\CompanyLogoThemes\\CompanyLogoBorealis.png"));
//            image2 = ImageIO.read(new File("E:\\IDEAProjects\\OOP\\CompanyLogoThemes\\CompanyLogoPornhub.png"));
//            image3 = ImageIO.read(new File("E:\\IDEAProjects\\OOP\\CompanyLogoThemes\\CompanyLogoNostalgia.png"));
//            image4 = ImageIO.read(new File("E:\\IDEAProjects\\OOP\\CompanyLogoThemes\\CompanyLogoNight.png"));
//            image5 = ImageIO.read(new File("E:\\IDEAProjects\\OOP\\CompanyLogoThemes\\CompanyLogoMint.png"));
//            image6 = ImageIO.read(new File("E:\\IDEAProjects\\OOP\\CompanyLogoThemes\\CompanyLogoTide.png"));
//            image7 = ImageIO.read(new File("E:\\IDEAProjects\\OOP\\CompanyLogoThemes\\CompanyLogoUnicorn.png"));
//            image8 = ImageIO.read(new File("E:\\IDEAProjects\\OOP\\AddToShortlistIcon.png"));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        System.out.println(ImageBase64.imageToBase64(image1));
//        System.out.println(ImageBase64.imageToBase64(image2));
//        System.out.println(ImageBase64.imageToBase64(image3));
//        System.out.println(ImageBase64.imageToBase64(image4));
//        System.out.println(ImageBase64.imageToBase64(image5));
//        System.out.println(ImageBase64.imageToBase64(image6));
//        System.out.println(ImageBase64.imageToBase64(image7));
//        System.out.println(ImageBase64.imageToBase64(image8));
    }
}

