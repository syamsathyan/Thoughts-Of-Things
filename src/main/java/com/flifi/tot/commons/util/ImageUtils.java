package com.flifi.tot.commons.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by SSG on 8/2/15.
 */
public class ImageUtils {

    public static byte[] createThumbnail(byte[] content, int width, int height) throws IOException, NullPointerException {
        if (content == null)
            throw new NullPointerException();
        BufferedImage thumbnail = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        thumbnail.createGraphics()
                .drawImage(ImageIO
                        .read(new ByteArrayInputStream(content))
                        .getScaledInstance(width, height, BufferedImage.SCALE_SMOOTH), 0, 0, null);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(thumbnail, "png", baos);
        baos.flush();
        return baos.toByteArray();
    }
}
