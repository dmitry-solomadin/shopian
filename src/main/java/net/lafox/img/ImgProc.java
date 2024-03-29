package net.lafox.img;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ImgProc {
    public static BufferedImage crop(BufferedImage src, double width, double height) throws IOException {
        double wOrig = (double) src.getWidth();
        double hOrig = (double) src.getHeight();

        double wCrop, hCrop;
        double kOrig = wOrig / hOrig;
        double kQuery = width / height;
        if (kOrig > kQuery) {
            hCrop = hOrig;
            wCrop = hCrop * kQuery;
        } else {
            wCrop = wOrig;
            hCrop = wCrop / kQuery;
        }
        return Thumbnails.of(src).sourceRegion(Positions.CENTER, i(wCrop), i(hCrop)).forceSize(i(width), i(height)).asBufferedImage();
    }

    public static BufferedImage width(BufferedImage src, double width) throws IOException {
        return Thumbnails.of(src).width(i(width)).asBufferedImage();
    }

    public static int i(double x) {
        return (int) Math.round(x);
    }
/*
    public static BufferedImage deepClone(BufferedImage bi) {
        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = bi.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }

*/

    public static Dimension imgDimension(final byte[] imageInByte) {
        Dimension dim = new Dimension(0, 0);
        try {
            InputStream in = new ByteArrayInputStream(imageInByte);
            BufferedImage img = ImageIO.read(in);
            dim.setSize(img.getWidth(), img.getHeight());
        } catch (IOException e) {
        }
        return dim;
    }

}
