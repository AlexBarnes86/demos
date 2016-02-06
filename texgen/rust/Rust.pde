PImage metalImg;
PImage rustImg;
PImage circleMask;

/*
Rust growth CA
age increment = (1.0/number of frames)*5.0
probability A = probability map value
probability B = (probability A - ((1.0-probability A)/2.0))
if (current frame is first frame)
current cell state = distribution map value
current age = 0
if (previous cell state = on)
current cell state = previous cell state
current age = previous age + age increment
else if (previous cell state = off AND 3 neighbors are on AND a random number >= probability A)
current cell state = on
else if (previous cell state = off AND 4 neighbors are on AND a random number >= probability B)
current cell state = on
if(current age > 1)
current age = 1
*/

PImage buildCircleMask(int w, int h) {
  PGraphics pg = createGraphics(w, h);
  pg.beginDraw();
  pg.ellipseMode(CORNERS);
  pg.background(0);
  pg.fill(255);
  pg.ellipse(0, 0, w, h);
  pg.endDraw();
  return pg.get();
}

PImage buildRustImage(int w, int h) {
  PGraphics pg = createGraphics(w, h);
  pg.beginDraw();
  pg.stroke(0);
  pg.strokeWeight(0);
  //pg.loadPixels();
  ellipseMode(CENTER);
  for(int r = 0; r < h; ++r) {
    for(int c = 0; c < w; ++c) {
      float rad = random(10.0);
      pg.fill(200, 50, 0, random(10));
      pg.ellipse(r, c, rad, rad);
    }
  }
  //pg.updatePixels();
  pg.endDraw();
  return pg.get();
}

void setup() {
  size(500, 500);
  metalImg = loadImage("metal.jpg");
  circleMask = buildCircleMask(width, height);
  imageMode(CORNER);
  rustImg = buildRustImage(width, height);
  rustImg.mask(circleMask);
}

void draw() {
  background(255);
  image(metalImg, 0, 0);
  image(rustImg, 0, 0);
  //blend(rustImg, 0, 0, width, height, 0, 0, width, height, );
}

void keyPressed() {
  if(key == ENTER) {
    rustImg = buildRustImage(width, height);
  }
}