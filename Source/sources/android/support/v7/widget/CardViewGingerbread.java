package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v7.widget.RoundRectDrawableWithShadow;
import anywheresoftware.b4a.keywords.Common;

class CardViewGingerbread implements CardViewImpl {
    final RectF sCornerRect = new RectF();

    CardViewGingerbread() {
    }

    @Override // android.support.v7.widget.CardViewImpl
    public void initStatic() {
        RoundRectDrawableWithShadow.sRoundRectHelper = new RoundRectDrawableWithShadow.RoundRectHelper() {
            /* class android.support.v7.widget.CardViewGingerbread.AnonymousClass1 */

            @Override // android.support.v7.widget.RoundRectDrawableWithShadow.RoundRectHelper
            public void drawRoundRect(Canvas canvas, RectF bounds, float cornerRadius, Paint paint) {
                float twoRadius = cornerRadius * 2.0f;
                float innerWidth = (bounds.width() - twoRadius) - 1.0f;
                float innerHeight = (bounds.height() - twoRadius) - 1.0f;
                if (cornerRadius >= 1.0f) {
                    float roundedCornerRadius = cornerRadius + 0.5f;
                    CardViewGingerbread.this.sCornerRect.set(-roundedCornerRadius, -roundedCornerRadius, roundedCornerRadius, roundedCornerRadius);
                    int saved = canvas.save();
                    canvas.translate(bounds.left + roundedCornerRadius, bounds.top + roundedCornerRadius);
                    canvas.drawArc(CardViewGingerbread.this.sCornerRect, 180.0f, 90.0f, true, paint);
                    canvas.translate(innerWidth, Common.Density);
                    canvas.rotate(90.0f);
                    canvas.drawArc(CardViewGingerbread.this.sCornerRect, 180.0f, 90.0f, true, paint);
                    canvas.translate(innerHeight, Common.Density);
                    canvas.rotate(90.0f);
                    canvas.drawArc(CardViewGingerbread.this.sCornerRect, 180.0f, 90.0f, true, paint);
                    canvas.translate(innerWidth, Common.Density);
                    canvas.rotate(90.0f);
                    canvas.drawArc(CardViewGingerbread.this.sCornerRect, 180.0f, 90.0f, true, paint);
                    canvas.restoreToCount(saved);
                    canvas.drawRect((bounds.left + roundedCornerRadius) - 1.0f, bounds.top, 1.0f + (bounds.right - roundedCornerRadius), bounds.top + roundedCornerRadius, paint);
                    canvas.drawRect((bounds.left + roundedCornerRadius) - 1.0f, bounds.bottom - roundedCornerRadius, 1.0f + (bounds.right - roundedCornerRadius), bounds.bottom, paint);
                }
                canvas.drawRect(bounds.left, bounds.top + cornerRadius, bounds.right, bounds.bottom - cornerRadius, paint);
            }
        };
    }

    @Override // android.support.v7.widget.CardViewImpl
    public void initialize(CardViewDelegate cardView, Context context, ColorStateList backgroundColor, float radius, float elevation, float maxElevation) {
        RoundRectDrawableWithShadow background = createBackground(context, backgroundColor, radius, elevation, maxElevation);
        background.setAddPaddingForCorners(cardView.getPreventCornerOverlap());
        cardView.setCardBackground(background);
        updatePadding(cardView);
    }

    private RoundRectDrawableWithShadow createBackground(Context context, ColorStateList backgroundColor, float radius, float elevation, float maxElevation) {
        return new RoundRectDrawableWithShadow(context.getResources(), backgroundColor, radius, elevation, maxElevation);
    }

    @Override // android.support.v7.widget.CardViewImpl
    public void updatePadding(CardViewDelegate cardView) {
        Rect shadowPadding = new Rect();
        getShadowBackground(cardView).getMaxShadowAndCornerPadding(shadowPadding);
        cardView.setMinWidthHeightInternal((int) Math.ceil((double) getMinWidth(cardView)), (int) Math.ceil((double) getMinHeight(cardView)));
        cardView.setShadowPadding(shadowPadding.left, shadowPadding.top, shadowPadding.right, shadowPadding.bottom);
    }

    @Override // android.support.v7.widget.CardViewImpl
    public void onCompatPaddingChanged(CardViewDelegate cardView) {
    }

    @Override // android.support.v7.widget.CardViewImpl
    public void onPreventCornerOverlapChanged(CardViewDelegate cardView) {
        getShadowBackground(cardView).setAddPaddingForCorners(cardView.getPreventCornerOverlap());
        updatePadding(cardView);
    }

    @Override // android.support.v7.widget.CardViewImpl
    public void setBackgroundColor(CardViewDelegate cardView, @Nullable ColorStateList color) {
        getShadowBackground(cardView).setColor(color);
    }

    @Override // android.support.v7.widget.CardViewImpl
    public ColorStateList getBackgroundColor(CardViewDelegate cardView) {
        return getShadowBackground(cardView).getColor();
    }

    @Override // android.support.v7.widget.CardViewImpl
    public void setRadius(CardViewDelegate cardView, float radius) {
        getShadowBackground(cardView).setCornerRadius(radius);
        updatePadding(cardView);
    }

    @Override // android.support.v7.widget.CardViewImpl
    public float getRadius(CardViewDelegate cardView) {
        return getShadowBackground(cardView).getCornerRadius();
    }

    @Override // android.support.v7.widget.CardViewImpl
    public void setElevation(CardViewDelegate cardView, float elevation) {
        getShadowBackground(cardView).setShadowSize(elevation);
    }

    @Override // android.support.v7.widget.CardViewImpl
    public float getElevation(CardViewDelegate cardView) {
        return getShadowBackground(cardView).getShadowSize();
    }

    @Override // android.support.v7.widget.CardViewImpl
    public void setMaxElevation(CardViewDelegate cardView, float maxElevation) {
        getShadowBackground(cardView).setMaxShadowSize(maxElevation);
        updatePadding(cardView);
    }

    @Override // android.support.v7.widget.CardViewImpl
    public float getMaxElevation(CardViewDelegate cardView) {
        return getShadowBackground(cardView).getMaxShadowSize();
    }

    @Override // android.support.v7.widget.CardViewImpl
    public float getMinWidth(CardViewDelegate cardView) {
        return getShadowBackground(cardView).getMinWidth();
    }

    @Override // android.support.v7.widget.CardViewImpl
    public float getMinHeight(CardViewDelegate cardView) {
        return getShadowBackground(cardView).getMinHeight();
    }

    private RoundRectDrawableWithShadow getShadowBackground(CardViewDelegate cardView) {
        return (RoundRectDrawableWithShadow) cardView.getCardBackground();
    }
}
