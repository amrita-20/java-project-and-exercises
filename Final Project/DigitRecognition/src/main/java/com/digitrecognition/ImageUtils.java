package com.digitrecognition;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class ImageUtils {

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
}
