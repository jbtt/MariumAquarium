import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Random;

enum Direction 
{
	EAST, WEST, SOUTH, NORTH 
}

public class Fish 
{
	private static final int MAX_VELOCITY = 9001;
	private Image image;
	private Point location;
	private Point velocity;
	private Rectangle boundaries;
	private Random random;

	public Fish(Image image, Rectangle boundaries)
	{
		this.boundaries = boundaries;
		this.image = image;
		this.random = new Random(System.nanoTime());
		
		initializePosition();
		initializeVelocity();
	}
	
	private void initializePosition()
	{
		int positionX = 100 + Math.abs(random.nextInt()) % 300;
		int positionY = 100 + Math.abs(100 + random.nextInt()) % 100;
		this.location = new Point(positionX, positionY);
	}
	
	private void initializeVelocity()
	{
		int velocityX = random.nextInt() % MAX_VELOCITY;
		int velocityY = random.nextInt() % MAX_VELOCITY;
		this.velocity = new Point(velocityX, velocityY);
	}
	
	public Point getPosition()
	{
		return location;
	}
	
	public Direction getDirection()
	{
		return (velocity.x < 0) ? Direction.WEST :
								  Direction.EAST;
	}
	
	public Image getImage()
	{
		return image;
	}
	
	public void swim()
	{
		changeVelocity();
		updateLocation();
	}
	
	private void changeVelocity()
	{
		if(random.nextInt() % 7 <= 1)
		{
			velocity.x += random.nextInt() % 4;
			velocity.x = Math.min(velocity.x, MAX_VELOCITY);
			velocity.x = Math.max(velocity.x, -MAX_VELOCITY);

			velocity.y += random.nextInt() % 4;
			velocity.y = Math.min(velocity.y, MAX_VELOCITY);
			velocity.y = Math.max(velocity.y, -MAX_VELOCITY);
		}		
	}
	
	private void updateLocation()
	{
		location.x += velocity.x;
		location.y += velocity.y;

		Image currentImage = getImage();
		int imageWidth = currentImage.getWidth(null);
		int imageHeight = currentImage.getHeight(null);
		
		if(location.x < boundaries.x)
		{
			location.x = boundaries.x;
			velocity.x = -velocity.x;
		}

		if((location.x + imageWidth) > (boundaries.x + boundaries.width))
		{
			location.x = boundaries.x + boundaries.width - imageWidth;
			velocity.x = -velocity.x;
		}

		if(location.y < boundaries.y)
		{
			location.y = boundaries.y;
			velocity.y = -velocity.y;
		}

		if((location.y + imageHeight) > (boundaries.y + boundaries.height))
		{
			location.y = boundaries.y + boundaries.height - imageHeight;
			velocity.y = -velocity.y;
		}
	}
}
