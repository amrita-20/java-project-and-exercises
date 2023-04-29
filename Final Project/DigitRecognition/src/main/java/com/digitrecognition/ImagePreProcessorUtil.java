package com.digitrecognition;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImagePreProcessorUtil {

    //making the constructor private, so that the class cannot be instantiated from outside the class.
    private ImagePreProcessorUtil(){}


    /**
     Processes the image from the specified file path and returns a resized 2D pixel array of the image.

     @param filePath the file path of the image to be processed

     @param row the number of rows in the resized 2D pixel array

     @param col the number of columns in the resized 2D pixel array

     @return a resized 2D pixel array of the processed image
     */
    public static float[][] processImage(String filePath, int row, int col) {
        // Load the image from file
        BufferedImage inputImage = null;
        try {
            inputImage = ImageIO.read(new File(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int imgHeight = inputImage.getHeight();
        int imgWidth = inputImage.getWidth();

        // Convert the input image to a gray-scale canvas image
        BufferedImage canvasImage = new BufferedImage(imgWidth, imgHeight,
                BufferedImage.TYPE_BYTE_GRAY);
        Graphics graphics = canvasImage.getGraphics();
        graphics.drawImage(inputImage, 0, 0, null);
        graphics.dispose();

        //Created instance of preprocessor util class with canvas image
        ImagePreprocessor imagePreprocessorUtil = new ImagePreprocessor(canvasImage);

        BufferedImage scaledCenterImage = imagePreprocessorUtil.getScaledCenterImage(Constants.SCALED_LENGTH,0.01f);

        float[][] pixelArray = new float[imgHeight][imgWidth];
        // Transform the image to a 2D array
        byte[] pixels = ((DataBufferByte) scaledCenterImage.getRaster().getDataBuffer()).getData();
        for (int i = 0; i < imgHeight; i++) {
            for (int j = 0; j < imgWidth; j++) {
                pixelArray[i][j] = (float) (pixels[i * imgHeight + j] & 0xFF);
            }
        }

        float[][] resizedArr = new float[row][col];
        // Reduce image size by grouping into bins and taking average pixel values as new one
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                float mean = 0f;
                for (int v = 0; v < 10; v++) {
                    for (int h = 0; h < 10; h++) {
                        mean += pixelArray[i*10 + v][j*10 + h];
                    }
                }
                // Taking average pixel values in current bin as bin's value
                mean /= (float)(10*10);
                resizedArr[i][j] = mean;
            }
        }
        return resizedArr;
    }

    /**
     Converts a 2D float array representing an image to a 1D float array.
     Each pixel in the input array is normalized by dividing its value by 255 and subtracting it from 1.
     The resulting 1D array is of size PIXEL_ROW x PIXEL_COL
     @param inputArr The 2D float array representing the image.
     @return The 1D float array representing the normalized image data.
     */
    public static float[] covert2DTo1DArray(float [][] inputArr){
        float [] processedData = new float[Constants.PIXEL_ROW * Constants.PIXEL_COL];
        for (int i = 0; i < Constants.PIXEL_ROW; i++) {
            for (int j = 0; j < Constants.PIXEL_COL; j++) {
                processedData[i*Constants.PIXEL_ROW+j] = 1-(inputArr[i][j]/255);
            }
        }
        return processedData;
    }

    /**
     Converts a 2D float array representing an image to a 4D float array.
     Each pixel in the input array is normalized by dividing its value by 255 and subtracting it from 1.
     The resulting 4D array is of size 1 x PIXEL_ROW x PIXEL_COL x 1
     @param inputArr The 2D float array representing the image.
     @return The 4D float array representing the normalized image data.
     */

    public static float[][][][] convert2DTo4DArray(float [][] inputArr){
        float[][][][] processedData = new float[1][Constants.PIXEL_ROW][Constants.PIXEL_COL][1];
        for (int i = 0; i < Constants.PIXEL_ROW; i++) {
            for (int j = 0; j < Constants.PIXEL_COL; j++) {
                processedData[0][i][j][0] = 1-(inputArr[i][j]/255);
            }
        }
        return processedData;
    }

    /**
     * @method: create argmax method for finding maximum
     *          probability of drawn number
     * @param: array of output values
     * @return: index of value which has maximum probability
     */
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