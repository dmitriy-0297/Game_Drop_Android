package com.gdk1743.game;
import …

public class GameScreenEasily implements Screen, InputProcessor {

    Drop game;
    OrthographicCamera camera;
    SpriteBatch batch;
    Texture dropImage;
    Texture bucketImage;
    Sound dropSound;
    Music rainMusic;
    Rectangle bucket;
    Vector3 touchPos;
    Array<Rectangle> raindrops;
    long lastDropTime;
    int dropsGathered;
    int tmp1 = 5;
    int tmp2 = 200;
    int level = 1;

    public GameScreenEasily(final Drop gam) {
        this.game = gam;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        batch = new SpriteBatch();

        touchPos = new Vector3();

        dropImage = new Texture("droplet.png");
        bucketImage = new Texture("bucket.png");

        dropSound = Gdx.audio.newSound(Gdx.files.internal("waterdrop.wav"));
        rainMusic = Gdx.audio.newMusic(Gdx.files.internal("undertreeinrain.mp3"));

        rainMusic.setLooping(true);
        rainMusic.play();

        bucket = new Rectangle();
        bucket.x = 800 / 2 - 64 / 2;
        bucket.y = 20;
        bucket.width = 64;
        bucket.height = 64;

        raindrops = new Array<Rectangle>();
        spawnRaindrop();
    }

    private void spawnRaindrop(){
        Rectangle raindrop = new Rectangle();
        raindrop.x = MathUtils.random(0, 800-64);
        raindrop.y = 480;
        raindrop.width = 64;
        raindrop.height = 64;
        raindrops.add(raindrop);
        lastDropTime = TimeUtils.nanoTime();
    }

    @Override
    public void render (float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.font.draw(game.batch, "Drops Collected: " + dropsGathered, 0, 480);
        game.font.draw(game.batch, "Level: " + level, 0, 460);
        if(game.stope > 3) {
            dropImage.dispose();
            bucketImage.dispose();
            dropSound.dispose();
            rainMusic.dispose();
            game.stope = 0;
            game.stoped();
        }
        game.batch.draw(bucketImage, bucket.x, bucket.y);
        for (Rectangle raindrop: raindrops){
            game.batch.draw(dropImage, raindrop.x, raindrop.y);
        }
        game.batch.end();

        if(Gdx.input.isTouched()){
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            bucket.x = (int) (touchPos.x -64 / 2);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) bucket.x -= tmp2 * Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) bucket.x += tmp2 * Gdx.graphics.getDeltaTime();

        if (bucket.x < 0) bucket.x = 0;
        if (bucket.x > 800 - 64) bucket.x = 800 - 64;
        if (TimeUtils.nanoTime() - lastDropTime > 1000000000) spawnRaindrop();
        Iterator<Rectangle> iter = raindrops.iterator();
        while (iter.hasNext()){
            Rectangle raindrop = iter.next();
            raindrop.y -= tmp2 * Gdx.graphics.getDeltaTime();
            if (raindrop.y + 64 < 0){
                iter.remove();
                game.stope++;
            }

            if (raindrop.overlaps(bucket)){
                if(dropsGathered == tmp1) {
                    tmp2 = tmp2 + 100;
                    tmp1 = tmp1 + 10;
                    level++;
                }
                dropsGathered++;
                dropSound.play();
                iter.remove();
            }
        }
    }

    @Override
    public void dispose() {
        dropImage.dispose();
        bucketImage.dispose();
        dropSound.dispose();
        rainMusic.dispose();
    }

    @Override
    public void show() {
        rainMusic.play();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }
}

