package d2c.appchalen.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import d2c.appchalen.R;

public class ImageData {
	public static Bitmap Hand;
	public static Bitmap Ruler;
	
	public static void loadData(Context context)
	{
		Hand = BitmapFactory.decodeResource(context.getResources(), R.drawable.hand1);
		Ruler = BitmapFactory.decodeResource(context.getResources(), R.drawable.ruler);
	}
	
	public static void resizeImage()
	{
		Hand = ResizeImage.getResizedBitmap(Hand, Hand.getWidth()*0.5f, Hand.getWidth()*0.5f);
		Ruler = ResizeImage.getResizedBitmap(Ruler, Ruler.getHeight()*0.5f, Ruler.getWidth()*0.5f);
	}
}
