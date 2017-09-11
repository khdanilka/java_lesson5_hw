package ru.geekbrains.java_games.screens.stars;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

//import static com.sun.tools.internal.xjc.reader.Ring.add;

public class TrackingStar extends Star {

    private final Vector2 trackingV;

    public TrackingStar(TextureRegion textureRegion, float vx, float vy, float height, Vector2 trackingV) {
        super(textureRegion, vx, vy, height);
        this.trackingV = trackingV;
    }

    private final Vector2 sumV = new Vector2();

    @Override
    public void update(float deltaTime) {
        sumV.setZero().mulAdd(trackingV, 0.2f).rotate(180).add(v);
        //sumV.setZero().mulAdd(trackingV, 0.2f).add(v);
        pos.mulAdd(sumV, deltaTime);
        checkAndHandleBounds();
    }
}
