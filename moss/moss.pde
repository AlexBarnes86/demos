float noiseScale = 0.02;

boolean isWhite(color c) {
    return c == color(255);
}

void setup() {
  size(768,768);
  background(255);

  seed();
  // PImage img = loadImage("https://lh3.googleusercontent.com/-lclqavFtus4/ThcFbeYdyOI/AAAAAAAAAAY/rPtqb0naoSE/w426-h283/baller.jpg", "jpg");
  // image(img, 0,0);

  //frameRate(1);
}

void draw() {
  loadPixels();
  for (int x = 0; x < width; x++) {
   for (int y = 0; y < height; y++) {
     colorCells3(x, y);
   }
  }
  updatePixels();

  //if (frameCount > frameRate * 60)
  // seed();
}

// single dot top center
// TODO paramaterize
void seed3() {
  loadPixels();
  pixels[floor(width/2)] = color(0);
  updatePixels();
}

// random dots
void seed2() {
  loadPixels();
  for (int x = 0; x < width; x++) {
    for (int y = 0; y < height; y++) {
      int rx = floor(random(width))+1, ry = floor(random(height))+1;
      if (x % rx == 0 && y % ry == 0) {
        pixels[y * width + x] = color(0);
      }
    }
  }
  updatePixels();
}

// random blotches
void seed() {
  loadPixels();
  for (int x = 0; x < width; x++) {
    for (int y = 0; y < height; y++) {
      float noiseVal = noise(x * noiseScale, y * noiseScale);
      if (noiseVal > 0.75) {
        pixels[y * width + x] = color(0);
      }
    }
  }
  updatePixels();
}

// returns true if the neighborhood matches the pattern
// pattern is array of "flags", 0 means white cell, any other number means colored cell
boolean patternMatch(PixelValue[] neighborhood, int[] pattern) {
  boolean match = true;
  if (neighborhood.length != pattern.length) {
    match = false;
  } else {
    for (int i = 0; i < neighborhood.length; i++) {
      int nc = isWhite(neighborhood[i].c) ? 0 : 1;
      if (nc != pattern[i]) {
        match = false;
        break;
      }
    }
  }
  return match;
}

// color if tl or tr is colored
void colorCells6(int x, int y) {
  PixelValue[] n = getNeighborhood(x, y);
  if (patternMatch(n, new int[]{1,0,0,0,0,0,0,0}) ||
      patternMatch(n, new int[]{0,0,0,0,0,0,0,1}) ||
      patternMatch(n, new int[]{0,0,1,0,0,0,0,0})) {
    pixels[y * width + x] = color(0);
  }
}

void colorCells5(int x, int y) {
  PixelValue[] neighborhood = getNeighborhood(x, y);

  int totalBlack = 0;
  for(int i=0; i < neighborhood.length; i++) {
    if (!isWhite(neighborhood[i].c))
      totalBlack++;
  }

  if (totalBlack == 1) {
    pixels[y * width + x] = color(0);
  }
}

void colorCells4(int x, int y) {
  PixelValue[] neighborhood = getNeighborhood(x, y);

  int avg = 0;
  int r=0, g=0, b=0;
  for(int i=0; i < neighborhood.length; i++) {
    color c = neighborhood[i].c;
    avg += c;
    r += red(c);
    g += green(c);
    b += blue(c);
  }
  avg /= neighborhood.length;
  r /= neighborhood.length;
  g /= neighborhood.length;
  b /= neighborhood.length;

  int rx = floor(random(width))+1, ry = floor(random(height))+1;
  //if (x % 2 == 0 && y % 2 == 0)
  //  pixels[y * width + x] = avg;
  //else
    pixels[y * width + x] = color(r,g,b);
}


// color center the "average" color of neighborhood
void colorCells3(int x, int y) {
  PixelValue[] neighborhood = getNeighborhood(x, y);

  int avg = 0;
  for(int i=0; i < neighborhood.length; i++) {
    color c = neighborhood[i].c;
    avg += c;
  }
  avg /= neighborhood.length;

  pixels[y * width + x] = avg;
}

void colorCells2(int x, int y) {
  PixelValue[] neighborhood = getNeighborhood(x, y);

  int numBlack = 0;
  for(int i=0; i < neighborhood.length; i++) {
    if (neighborhood[i].c != color(255)) {
      numBlack++;
    }
  }

  Integer c = null;
  switch(numBlack) {
    case 1:
      c = color(255, 0, 0);
      break;
    case 2:
      c = color(0, 255, 0);
      break;
    case 3:
      c = color(0, 0, 255);
      break;
    case 4:
      c = color(0,255,255);
      break;
  }

  if (c != null)
    pixels[y * width + x] = c;
}

void colorCells(int x, int y) {
  int val = pixels[y * width + x];
  if (brightness(val) != 255) {
    PixelValue[] neighborhood = getNeighborhood(x, y);
    PixelValue[] shiftedNeighborhood = shiftNeighborhood(neighborhood);
    PixelValue[] growthPixels = getGrowthPixels(shiftedNeighborhood);

    for (int i = 0; i < growthPixels.length; i++) {
      PixelValue p = growthPixels[i];
      pixels[p.i] = color(0);
      println("Updating pixel "+p.i+" to black");
    }
  }
}

PixelValue[] getGrowthPixels(PixelValue[] neighborhood) {
  ArrayList<Tuple<Integer>> groups = new ArrayList<Tuple<Integer>>(); // max of 4 groups
  int groupStart = 0, whiteLength = 0;
  for (int i = 0; i < neighborhood.length; i++) {
    if (brightness(neighborhood[i].c) == 255) {
      whiteLength++;
    } else {
      if (whiteLength >= 3) {
        groups.add(new Tuple<Integer>(groupStart, whiteLength));
      }
      groupStart = i+1;
      whiteLength = 0;
    }
  }

  int size = groups.size();
  PixelValue[] growthPixels = new PixelValue[size];
  for (int i = 0; i < size; i++) {
    int start = groups.get(i).v[0];
    int groupLength = groups.get(i).v[1];
    growthPixels[i] = neighborhood[start + ceil(groupLength / 2)];
  }
  return growthPixels;
}

// shift the neighborhood so first value is black
PixelValue[] shiftNeighborhood(PixelValue[] neighborhood) {
  PixelValue[] shift = new PixelValue[neighborhood.length];

  int firstBlack = 0;
  for (int i = 0; i < neighborhood.length; i++) {
    if (brightness(neighborhood[i].c) == 0) {
      firstBlack = i;
      break;
    }
  }

  for (int i = 0; i < neighborhood.length; i++) {
    shift[i] = neighborhood[(firstBlack + i) % neighborhood.length];
  }

  return shift;
}

// square neighborhood
PixelValue[] getNeighborhood(int x, int y) {
  PixelValue[] neighborhood = new PixelValue[8];
  int up = Math.max(0, y-1);
  int down = Math.min(height-1, y+1);
  int left = Math.max(0, x-1);
  int right = Math.min(width-1, x+1);
  int[] ni = {
    up * width + left,   // up left
    up * width + x,      // up
    up * width + right,  // up right
    y * width + left,    // left
    y * width + right,   // right
    down * width + left, // down left
    down * width + x,    // down
    down * width + right // down right
  };

  for (int i = 0; i < ni.length; i++) {
    neighborhood[i] = new PixelValue(ni[i], pixels[ni[i]]);
  }
  return neighborhood;
}
