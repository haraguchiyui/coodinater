package cloth.apple.yui06.coordistcloset;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

public class SquareImageViewBottoms extends AppCompatImageView {
    public SquareImageViewBottoms(Context context){
        super(context);
        setScaleType(ScaleType.CENTER_CROP);
    }


    public SquareImageViewBottoms(Context context, AttributeSet attrs) {
        super(context, attrs);
        setScaleType(ScaleType.CENTER_CROP);
    }

    public SquareImageViewBottoms(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setScaleType(ScaleType.CENTER_CROP);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 常に横幅と同じ縦幅を持った矩形のサイズを要求する
        setMeasuredDimension(widthMeasureSpec, widthMeasureSpec);

//        Log.d("widthMeasureSpec", String.valueOf(widthMeasureSpec));
    }
}
