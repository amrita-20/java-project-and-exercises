package com.digitrecognition;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImagePreprocessor {

    public static float[][][][] preprocessImage(String filePath) throws IOException {
        // Load the image from file
        BufferedImage inputImage = ImageIO.read(new File(filePath));

        // Resize the image to 28x28 pixels
        BufferedImage resizedImage = new BufferedImage(28, 28, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = resizedImage.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(inputImage, 0, 0, 28, 28, null);
        g.dispose();

        // Convert the resized image to grayscale
        BufferedImage grayscaleImage = new BufferedImage(28, 28, BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D g2 = grayscaleImage.createGraphics();
        g2.drawImage(resizedImage, 0, 0, null);
        g2.dispose();

        // Convert the grayscale image to a float array
        float[][][][] input = new float[1][28][28][1];
        for (int i = 0; i < 28; i++) {
            for (int j = 0; j < 28; j++) {
                int pixel = grayscaleImage.getRGB(j, i);
                float gray = ((pixel & 0xff) + ((pixel >> 8) & 0xff) + ((pixel >> 16) & 0xff)) / 3.0f;
                input[0][i][j][0] = gray / 255.0f;
            }
        }

        return input;
    }
    public static float[] loadImage(String imagePath) throws Exception {
        // load image file
        BufferedImage image = ImageIO.read(new File(imagePath));

        // resize image to 28x28
        BufferedImage resizedImage = new BufferedImage(28, 28, BufferedImage.TYPE_INT_ARGB);
        resizedImage.getGraphics().drawImage(image, 0, 0, 28, 28, null);

        // convert image to grayscale and normalize pixel values
        float[] input = new float[28 * 28];
        for (int i = 0; i < 28; i++) {
            for (int j = 0; j < 28; j++) {
                int rgb = resizedImage.getRGB(j, i);
                int gray = (int) (0.2989 * ((rgb >> 16) & 0xff) + 0.5870 * ((rgb >> 8) & 0xff) + 0.1140 * (rgb & 0xff));
                input[i * 28 + j] = 1.0f - (gray / 255.0f);
            }
        }

        return input;
    }
    public static int argmax(float[] array) {
        int maxIndex = 0;
        double maxValue = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > maxValue) {
                maxIndex = i;
                maxValue = array[i];
            }
        }
        return maxIndex;
    }
}