package mandelbrotSet;
import javax.swing.JFrame;//needed for graphics
import java.awt.*;//needed for graphics
import static javax.swing.JFrame.EXIT_ON_CLOSE;//needed for graphics
import java.util.*;//needed for reading file
import java.io.*;//needed for reading file

public class mandelbrotSet extends JFrame{
	int height=800;
	int width=800;
	double xMin;
	double xMax;
	double yMin;
	double yMax;
	double xCartesian;
	double yCartesian;
	int maxIterations;

	public void initializeWindow() {
		setTitle("Mandelbrot Set");
		setSize(width, height);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBackground(Color.BLACK);
		setVisible(true);
	}

	public void paint(Graphics g) {
		double xScale=(xMax - xMin) / width;
		double yScale=(yMax - yMin) / height;
		for (int xScreen = 0; xScreen < width; xScreen++) {
			//determines the real part of a complex number based on which pixel on the screen it is checking
			xCartesian = xScale*(xScreen) + xMin;
			for (int yScreen = 0; yScreen < height; yScreen++) {
				//determines the imaginary part of a complex number based on which pixel on the screen it is checking
				yCartesian = yMax - yScale* (yScreen);   

				//basicPattern(g, xCartesian, yCartesian); //simple booean algorithim
				coolPattern(g, xCartesian, yCartesian); //integer based algorithim

				g.drawLine(xScreen, yScreen, xScreen + 1, yScreen + 1);// draws a pixel
			}
		}
	}
	
	public void basicPattern(Graphics g, double x,double y){
		if(isInMan(new Complex(x,y))) { //check if it's in Mandelbrot Set
			g.setColor(Color.ORANGE);                 
		}
		else {
			g.setColor(Color.BLACK);
		}
	}
	
	//determines colour based on escapeVal
	public void coolPattern(Graphics g, double x,double y){
		int escapeVal = determineEscapeVal(new Complex(x,y));//the value at which the point escapes the set
		//Note: As you increase the exponent the shape of the Mandelbrot set gets less defined
		int mix = (int) 15 * escapeVal % 255;//creates wave pattern
		Color color = new Color(mix, mix, mix);	//black&white
		//Color color = new Color(0, mix, 0);
		g.setColor(color);
	}

	//determines whether it is in the Mandelbrot Set
	public boolean isInMan(Complex C) {
		int count = 0;
		Complex Z = C;
		while (Z.absoluteSquared() < 4) {//more efficient 
			count++;
			Z = (Z.exponent(2)).add(C); 	//Z(n+1) = Z(n)^2 + C where c = Z(0)
			if (count >= maxIterations) {
				return true;
			}
		}
		return false;
	}
	
	//determines how close a certain complex number was to escaping
	public int determineEscapeVal(Complex C) {
		Complex Z = C;
		for (int i = 0; i <= maxIterations; i++) {
			Z = (Z.exponent(2)).add(C);
			if (Z.absoluteSquared() > 4) {//determines at which iteration it escapes the set
				return i;
			}
		}
		return maxIterations;
	}
	
	//reads input from file and initialize the variables
	public void readFile() throws IOException{
		Scanner in = new Scanner(new FileReader("input-value.txt"));
		xMin=in.nextDouble();
		xMax=in.nextDouble();
		yMin=in.nextDouble();
		yMax=in.nextDouble();
		maxIterations=in.nextInt();
	}

	public static void main(String[] args) throws IOException {
		mandelbrotSet set = new mandelbrotSet();
		set.readFile();
		set.initializeWindow();       
	}
}


