package mannberg.se.halftone;

import android.graphics.PointF;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.CropTransformation;
import jp.wasabeef.picasso.transformations.gpu.VignetteFilterTransformation;
import mannberg.se.halftone.transformations.ColorHalftoneTransformation;

public class MainActivity extends AppCompatActivity {

    private ImageView   image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        image = (ImageView) findViewById(R.id.image);
        loadImage(4, 6);
    }

    private void loadImage(int halftone, int vignette) {
        float v = 1f - (vignette/100f);
        Picasso.with(getApplicationContext())
                .load(R.drawable.hero)
                //.rotate(21f)
                .transform(new CropTransformation(1, CropTransformation.GravityHorizontal.CENTER,
                        CropTransformation.GravityVertical.CENTER))
                .transform(new ColorHalftoneTransformation(halftone))
                .transform(new VignetteFilterTransformation(getApplicationContext(), new PointF(0.5f, 0.5f),
                        new float[] { 0.0f, 0.0f, 0.0f }, 0f, v)) //0.75f*/
                .placeholder(R.color.placeholder)
                .error(R.color.error)
                .into(image);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
