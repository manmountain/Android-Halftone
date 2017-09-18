package mannberg.se.halftone.transformations;

import android.graphics.Bitmap;
import android.graphics.Rect;

import com.squareup.picasso.Transformation;

/**
 * A filter which acts as a superclass for filters which need to have the whole
 * image in memory to do their stuff.
 */
public abstract class WholeImageTransformation implements Transformation {

    /**
     * The output image bounds.
     */
    protected Rect transformedSpace;

    /**
     * The input image bounds.
     */
    protected Rect originalSpace;

    /**
     * Construct a WholeImageFilter.
     */
    public WholeImageTransformation() {
    }

    @Override
    public Bitmap transform(Bitmap source) {
        int width = source.getWidth();
        int height = source.getHeight();
        Bitmap bitmap = source.copy(source.getConfig(), true);
        source.recycle();

        originalSpace = new Rect(0, 0, width, height);
        transformedSpace = new Rect(0, 0, width, height);
        transformSpace(transformedSpace);

        int[] inPixels = new int[width * height];
        bitmap.getPixels(inPixels, 0, width, 0, 0, width, height);
        inPixels = filterPixels(width, height, inPixels, transformedSpace);

        bitmap.setPixels(inPixels, 0, transformedSpace.width(), 0, 0, transformedSpace.width(),
                transformedSpace.height());
        source.recycle();
        /*if (bitmap != source) {
            source.recycle();
        }*/
        return bitmap;
    }

    /**
     * Calculate output bounds for given input bounds.
     * 
     * @param rect
     *            input and output rectangle
     */
    protected void transformSpace(Rect rect) {
    }

    /**
     * Actually filter the pixels.
     * 
     * @param width
     *            the image width
     * @param height
     *            the image height
     * @param inPixels
     *            the image pixels
     * @param transformedSpace
     *            the output bounds
     * @return the output pixels
     */
    protected abstract int[] filterPixels(int width, int height, int[] inPixels, Rect transformedSpace);
}
