
public class Fib
{
	public static int fib(final int n)
	{
		if( n == 0 )
		{
			return 1;
		}
		else if( n == 1 )
		{
			return 1;
		}
		else
		{
			return fib_private(0, n, 0);
		}
	}
	
	private static int fib_private(int progress, final int n, final int sum)
	{
		if( progress == n )
		{
			return sum;
		}
		else
		{
			progress++;
			
			return fib_private(progress, n, sum + progress);
		}
	}
}
