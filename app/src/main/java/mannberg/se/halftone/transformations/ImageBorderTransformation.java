package mannberg.se.halftone.transformations;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import com.squareup.picasso.Transformation;

/**
 * Created by goma on 2016-12-14.
 */

public class ImageBorderTransformation implements Transformation {
    @Override
    public Bitmap transform(Bitmap source) {
        int w = source.getWidth();
        int h = source.getHeight();

        Bitmap bitmap = Bitmap.createBitmap(source, 0, 0, w, h);
        if (bitmap != source) {
            source.recycle();
        }
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        BitmapShader shader = new BitmapShader(bitmap, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
        paint.setShader(shader);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);
        paint.setColor(Color.parseColor("#212121"));

        canvas.drawRect(new RectF(0+2, 0+2, source.getWidth()-2, source.getHeight()-2), paint);
        return bitmap;
    }

    @Override
    public String key() {
        return "ImageBorderTransformation";
    }
}
