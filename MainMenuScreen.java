package com.gdk1743.game;
import …

class MainMenuScreen implements Screen, InputProcessor {
    Drop game;
    private int width, height;
    private Texture bgTexture;
    private SpriteBatch spriteBatch;
    public OrthographicCamera cam;
    public float ppuX, ppuX1, ppuX2;
    public float ppuY, ppuY1, ppuY2;
    float CAMERA_WIDTH = 800F;
    float CAMERA_HEIGHT = 480F;
    public  Map<String, Texture> textures;
    boolean downBtn, downBtn1, downBtn2;
    @Override
    public void show() {
        textures = new HashMap<String, Texture>();
        ppuX = (float)width / CAMERA_WIDTH;
        ppuY = (float)height / CAMERA_HEIGHT;
        downBtn = false;
        downBtn1 = false;
        downBtn2 = false;
        spriteBatch = new SpriteBatch();
        this.cam = new OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT);
        loadTextures1();
        loadTextures2();
        loadTextures3();
        Gdx.input.setInputProcessor(this);
    }
    public void SetCamera(float x, float y){
        this.cam.position.set(x, y,0);
        this.cam.update();
    }
    private void loadTextures1(){
        bgTexture = new Texture(Gdx.files.internal("images/bg.png"));
        textures.put("cover_button_start_up(1)", new Texture(Gdx.files.internal("images/cover_button_start_up(1).png")));
        textures.put("cover_button_start_down", new Texture(Gdx.files.internal("images/cover_button_start_down.png")));
    }
    private void loadTextures2(){
        textures.put("cover_button_start_up(2)", new Texture(Gdx.files.internal("images/cover_button_start_up(2).png")));
        textures.put("cover_button_start_down", new Texture(Gdx.files.internal("images/cover_button_start_down.png")));
    }
    private void loadTextures3(){
        textures.put("cover_button_start_up(3)", new Texture(Gdx.files.internal("images/cover_button_start_up(3).png")));
        textures.put("cover_button_start_down", new Texture(Gdx.files.internal("images/cover_button_start_down.png")));
    }

    public void showMenu() {
        if (downBtn) {
            spriteBatch.draw(textures.get("cover_button_start_down"), 653, 183, 256, 128);
        }else {
            spriteBatch.draw(textures.get("cover_button_start_up(1)"), 653, 183, 256, 128);
        }
        if(downBtn1) {
            spriteBatch.draw(textures.get("cover_button_start_down"), 653, 100, 256, 128);
        }else {
            spriteBatch.draw(textures.get("cover_button_start_up(2)"), 653, 100, 256, 128);
        }
        if(downBtn2) {
            spriteBatch.draw(textures.get("cover_button_start_down"), 653, 17, 256, 128);
        }else {
            spriteBatch.draw(textures.get("cover_button_start_up(3)"), 653, 17, 256, 128);
        }
    }

    public void showBG(){
        spriteBatch.draw(bgTexture,0, -32, 1024 , 512);
    }

    public MainMenuScreen(Drop game){
        this.game = game;
    }
    @Override
    public boolean keyUp(int keycode) {
        return true;
    }
    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    public void setSize (int w, int h) {
        this.width = w;
        this.height = h;
        ppuX = (float)width / CAMERA_WIDTH;
        ppuY = (float)height / CAMERA_HEIGHT;
        ppuX1 = (float)width / CAMERA_WIDTH;
        ppuY1 = (float)height / CAMERA_HEIGHT;
        ppuX2 = (float)width / CAMERA_WIDTH;
        ppuY2 = (float)height / CAMERA_HEIGHT;
    }

    @Override
    public void resize(int width, int height) {
        setSize(width, height);
        this.width = width;
        this.height = height;
    }


    @Override
    public void render(float delta) {
        SetCamera(CAMERA_WIDTH/2, CAMERA_HEIGHT / 2f);
        spriteBatch.setProjectionMatrix(this.cam.combined);
        Gdx.gl.glClearColor(0,0,0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        spriteBatch.begin();
        showBG();
        showMenu();
        spriteBatch.end();
    }

    @Override
    public boolean keyTyped(char character) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void dispose() {
        Gdx.input.setInputProcessor(null);
        try{
            spriteBatch.dispose();
            bgTexture.dispose();
            textures.clear();
        }
        catch(Exception e){
        }
    }

    @Override
    public boolean touchDragged(int x, int y, int pointer) {

        // TODO Auto-generated method stub
        return false;
    }


    @Override
    public boolean mouseMoved(int x, int y) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {

        if((height-y)/ppuY >= 213+20 && (height-y)/ppuY <= 283+20 && x/ppuX>=660+40 && x/ppuX<=780+40)
            downBtn = true;

        if((height-y)/ppuY >= 213+20 && (height-y)/ppuY <= 283+20 && x/ppuX>=660 && x/ppuX<=780)
            downBtn = true;

        if((height-y)/ppuY1 >= 213-83 && (height-y)/ppuY1 <= 283 && x/ppuX1>=660 && x/ppuX1<=780)
            downBtn1 = true;

        if((height-y)/ppuY1 >= 213-83 && (height-y)/ppuY1 <= 283 && x/ppuX1>=660 && x/ppuX1<=780)
            downBtn1 = true;

        if((height-y)/ppuY2 >= 213-83*2 && (height-y)/ppuY2 <= 283 && x/ppuX2>=660 && x/ppuX2<=780)
            downBtn2 = true;

        if((height-y)/ppuY2 >= 213-83*83 && (height-y)/ppuY2 <= 283 && x/ppuX2>=660 && x/ppuX2<=780)
            downBtn2 = true;

        return true;
    }

    @Override
    public boolean keyDown(int keycode) {
        return true;
    }

    @Override
    public boolean touchUp(int x, int y, int pointer, int button) {
        if (!Gdx.app.getType().equals(ApplicationType.Android))
            return false;
        if(downBtn){
            dispose();
            game.setScreen(new GameScreenEasily(game));
        }
        else if(downBtn1){
            dispose();
            game.setScreen(new GameScreenAverage(game));
        }
        else if(downBtn2){
            dispose();
            game.setScreen(new GameScreenComplex(game));
        }
        downBtn = false;
        downBtn1 = false;
        downBtn2 = false;
        return true;
    }
}
