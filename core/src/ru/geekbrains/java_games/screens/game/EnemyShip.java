package ru.geekbrains.java_games.screens.game;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.java_games.Ship;
import ru.geekbrains.java_games.pools.BulletPool;
import ru.geekuniversity.engine.math.Rect;
import ru.geekuniversity.engine.math.Rnd;
import ru.geekuniversity.engine.utils.Regions;


public class EnemyShip extends Ship {

    private static final float SHIP_HEIGHT = 0.15f;
    private static final float  BOTTOM_MARGIN = 0.08f;
    private static Rect worldboundsE;
    TextureAtlas atlas;


    public EnemyShip(TextureRegion region, int rows, int cols, int frames) {
        super(region, rows, cols, frames);
    }

    public EnemyShip(TextureAtlas atlas, BulletPool bulletPool) {
        this.atlas = atlas;
        this.bulletPool = bulletPool;
        bulletRegion = atlas.findRegion("bulletEnemy");
        bulletHeight = 0.01f;
        reloadInterval = 0.5f;
    }

    public void set(Rect worldBounds,
                    float sizeShip,
                    Vector2 speedShip,
                    Vector2 speedBullet,
                    int bulletDamage,
                    int stage)
    {
        frame = 0;
        String en= "enemy" + stage;
        regions = Regions.split(atlas.findRegion(en), 1, 2, 2);

        this.worldBounds = worldBounds;
        pos.x = Rnd.nextFloat(worldBounds.getLeft(), worldBounds.getRight());
        if(getRight() >= worldBounds.getRight()) {
            setRight(worldBounds.getRight());
        }
        if(getLeft() <= worldBounds.getLeft()) {
            setLeft(worldBounds.getLeft());
        }
        setBottom(worldBounds.getTop());

        setHeightProportion(sizeShip);
        v.set(speedShip);
        bulletV.set(speedBullet);
        this.bulletDamage = bulletDamage;

    }

    public void set(Rect worldbounds){
        this.worldBounds = worldbounds;
        pos.x = Rnd.nextFloat(worldBounds.getLeft(), worldBounds.getRight());
        if(getRight() > worldBounds.getRight()) {
            setRight(worldBounds.getRight());
        }
        if(getLeft() < worldBounds.getLeft()) {
            setLeft(worldBounds.getLeft());
        }
        setBottom(worldBounds.getTop());
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        //setBottom(worldBounds.getTop() + BOTTOM_MARGIN);
    }

    @Override
    public void update(float deltaTime) {
        pos.mulAdd(v, deltaTime);
        reloadTimer += deltaTime;
        if(reloadTimer >= reloadInterval) {
            reloadTimer = 0f;
            shoot();
        }
        if (getTop() < worldBounds.getBottom()) destroy();

    }

    Vector2 getV() {
        return v;
    }


}
