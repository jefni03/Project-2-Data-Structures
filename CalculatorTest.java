import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class CalculatorTest{

	@Test
	public void shouldGivePostfix1(){
		String expectedPostfix = "ab^c*d-";
		String infix = "a^b*c-d";
		String actualPostfix = Calculator.convertToPostfix(infix);
		assertEquals(expectedPostfix,actualPostfix);
	}

	@Test
	public void shouldGivePostfix2(){
		String expectedPostfix = "ab^cd^*";
		String infix = "a^b*c^d";
		String actualPostfix = Calculator.convertToPostfix(infix);
		assertEquals(expectedPostfix,actualPostfix);
	}

	@Test
	public void shouldGivePostfix3(){
		String expectedPostfix = "abcdc*^*+e-";
		String infix = "a+(b*c^(d*c))-e";
		String actualPostfix = Calculator.convertToPostfix(infix);
		assertEquals(expectedPostfix,actualPostfix);
	}

	@Test
	public void shouldEvalPostfix1(){
		double expectedResult = 27;
		String postfix = "ab^c*d-";
		double actualResult = Calculator.evaluatePostfix(postfix);
		assertEquals(expectedResult, actualResult, 0);
	}

	@Test
	public void shouldEvalPostfix2(){
		double expectedResult = 8192;
		String postfix = "ab^cd^*";
		double actualResult = Calculator.evaluatePostfix(postfix);
		assertEquals(expectedResult, actualResult, 0);
	}

	@Test
	public void shouldEvalPostfix3(){
		double expectedResult = 2.0+(3.0*Math.pow(4.0,5.0*4.0))-6.0;
		String postfix = "abcdc*^*+e-";
		double actualResult = Calculator.evaluatePostfix(postfix);
		assertEquals(expectedResult, actualResult, 0);
	}
}
