package com.ve.cxe.commons;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by sathyasy on 8/2/15.
 */
public class ImageUtils {

    public static byte[] createThumbnail(byte[] content) throws IOException, NullPointerException {
        if (content == null)
            throw new NullPointerException();
        BufferedImage thumbnail = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        thumbnail.createGraphics()
                .drawImage(ImageIO
                        .read(new ByteArrayInputStream(content))
                        .getScaledInstance(100, 100, BufferedImage.SCALE_SMOOTH), 0, 0, null);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(thumbnail, "png", baos);
        baos.flush();
        return baos.toByteArray();
    }
}
