package ru.geekbrains.java_games.pools;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.geekbrains.java_games.Bullet;
import ru.geekbrains.java_games.screens.game.EnemyShip;
import ru.geekuniversity.engine.pool.SpritesPool;



public class EnemyPool extends SpritesPool<EnemyShip> {

    protected BulletPool bulletPool;
    protected TextureAtlas atlas;

    public EnemyPool(TextureAtlas atlas, BulletPool bulletPool) {
        this.bulletPool = bulletPool;
        this.atlas = atlas;
    }

    @Override
    protected EnemyShip newObject() {
        return new EnemyShip(atlas,bulletPool);
    }

    @Override
    protected void debugLog() {
        System.out.println("EnemyPool change active/free: " + activeObjects.size() + "/" + freeObjects.size());
    }
}
