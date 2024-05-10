float x = PI;
float y = TWO_PI;
float z = QUARTER_PI;

// The following parameters correspond
// to the the factors [k, l, m] { placed
// in order } of the differential equations,
// which produce different chaotic systems
// when mutated:
//
// --> [10, 28, 8/3]
// --> [12.3, 33.69, PI]
// --> [10, 33.3, 12.3]
// --> [0.3, 29.1, PI]
// --> [8.1, 36.9, sqrt(PI)]
// --> [11.1, 33.6, 13/5]
// --> [10.2, 33.3, 8/3]
float s = 10;
float r = 28;
float b = 8 / 3;
float deltaTime = 0.009;

ArrayList<PVector> points;
float angle = PI / 4;
float deltaAngle = PI / 312;

int pointLimit = 15 * 312;

void setup()
{
  surface.setTitle("Chaotic System");
  points = new ArrayList<PVector>();

  fullScreen(P3D);
  colorMode(HSB, 255, 255, 255);
}

void draw()
{
  background(0);
  translate(width / 2, height / 2);
  scale(5);

  rotateX(0.21 * angle);
  rotateY(angle);
  rotateZ(0.42 * angle);

  checkPointLimit();
  evaluateEquations();
  points.add(new PVector(x, y, z));
  renderChaoticSystem();

  angle += deltaAngle;
}

void evaluateEquations()
{
  var deltaX = s * (y - x) * deltaTime;
  var deltaY = (x * (r - z) - y) * deltaTime;
  var deltaZ = (x * y - b * z) * deltaTime;

  x += deltaX;
  y += deltaY;
  z += deltaZ;
}

void checkPointLimit()
{
  if (points != null && points.size() > pointLimit)
    points.clear();
}

void renderChaoticSystem()
{
  if (points != null || points.size() != 0)
  {
    beginShape();
    noFill();
    for (var systemPoint : points)
    {
      var hue = frameCount % 255;
      stroke(hue, 255, 255, 180);
      vertex(systemPoint.x, systemPoint.y, systemPoint.z);
    }
    endShape();
  }
}
