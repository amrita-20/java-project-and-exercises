package com.digitrecognition;

import java.awt.Graphics;
import java.awt.image.DataBufferByte;
import java.awt.image.BufferedImage;


public class ImagePreprocessor {
    /**
     * Created class for preprocessing drawn numbers
     */
    private final BufferedImage bufferedImage;
    private final float[][] pixelArray;
    private final int row;
    private final int col;
    /**
     * Image boundaries along X-axis and Y-axis
     */
    private int minX;
    private int minY;
    private int maxX;
    private int maxY;

    ImagePreprocessor(BufferedImage bufferedImage){
        this.bufferedImage = bufferedImage;
        col = bufferedImage.getWidth();
        row = bufferedImage.getHeight();
        pixelArray = get2DArray(bufferedImage);
    }

    public float[][] get2DArray(BufferedImage img) {
        /**
         * Transform the image to a 2D array
         */
        float[][] pixelArray = new float[row][col];
        byte[] pixels = ((DataBufferByte) img.getRaster().getDataBuffer()).getData();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                pixelArray[i][j] = (float) (pixels[i * row + j] & 0xFF);
            }
        }
        return pixelArray;
    }

    /**
     * Created method to get image boundaries along X-axis and Y-axis
     * (ignoring noise points by a pixel magnitude of threshold (0.01f))
     */
    public void calculateBoundary(float threshold) {
        minX = col;
        minY = row;
        maxX = -1;
        maxY = -1;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (pixelArray[i][j] < threshold * 255f) {
                    minX = minX > j ? j : minX;
                    maxX = maxX < j ? j : maxX;
                    minY = minY > i ? i : minY;
                    maxY = maxY < i ? i : maxY;
                }
            }
        }
        if (maxX < 0) {
            // when a whole white image
            minX = maxX = col/2;
            minY = maxY = row/2;
        }
    }

    /**
     * @method Created method to resize and center the drawn number
     * @param scaledLength
     * @param threshold
     * @return resized centred image
     */
    public BufferedImage getScaledCenterImage(int scaledLength, float threshold) {
        calculateBoundary(threshold);
        // Get length of bounding box
        int bndX = maxX - minX + 1;
        int bndY = maxY - minY + 1;
        int bndLength = bndX > bndY ? bndX : bndY;

        // Calculate scale factor and corresponding new width and height
        float scaleFactor = scaledLength / (float) bndLength;
        int scaledWidth = (int)(bndX * scaleFactor);
        int scaledHeight = (int)(bndY * scaleFactor);

        // Crop image by estimated boundary box
        BufferedImage croppedImage = bufferedImage.getSubimage(minX, minY, bndX, bndY);
        BufferedImage scaledCenterImage = new BufferedImage(col, row, BufferedImage.TYPE_BYTE_GRAY);
        Graphics graphics = scaledCenterImage.getGraphics();
        graphics.setColor(java.awt.Color.WHITE);
        graphics.fillRect(0,0, col, row);

        // Re-draw cropped image with re-sizing
        graphics.drawImage(croppedImage, (col-scaledWidth)/2, (row-scaledHeight)/2, scaledWidth, scaledHeight,null);
        graphics.dispose();

        return scaledCenterImage;
    }
}
