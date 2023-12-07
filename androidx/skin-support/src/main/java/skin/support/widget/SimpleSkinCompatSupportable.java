package skin.support.widget;

import static skin.support.widget.SkinCompatHelper.INVALID_ID;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;

import androidx.core.view.ViewCompat;

import skin.support.R;
import skin.support.SkinCompatManager;
import skin.support.content.res.SkinCompatResources;

/**
 * @author Hai
 * @date 2023/12/6 10:26
 * @desc  非SkinCompatSupportable view 默认的背景适配 不支持不启动切换 且如果代码和xml中都设置过颜色 切换后会重置为xml中初始值
 */
public class SimpleSkinCompatSupportable implements SkinCompatSupportable {
    public static final int TAG_VIEW = 0xF0000000;
    public SimpleSkinCompatSupportable(View holdView, AttributeSet attrs) {
        this.holdView = holdView;
        this.attrs = attrs;
        applySkin();
    }

    View holdView;
    AttributeSet attrs;
    int bgResId = INVALID_ID;
    @Override
    public void applySkin() {
        Context context = holdView.getContext();
        if(bgResId == INVALID_ID){
            context = context == null ? SkinCompatManager.getInstance().getContext() : context;
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SkinBackgroundHelper);
            try {
                if (a.hasValue(R.styleable.SkinBackgroundHelper_android_background)) {
                    bgResId = a.getResourceId(R.styleable.SkinBackgroundHelper_android_background, INVALID_ID);
                }
            } finally {
                a.recycle();
            }
        }

        if(bgResId!=INVALID_ID){
            ViewCompat.setBackground(holdView, SkinCompatResources.getDrawable(context,bgResId));
        }
    }
}
