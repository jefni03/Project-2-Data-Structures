import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class CalculatorTest{

	//tests for convertToPostfix()
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
	public void shouldGivePostfix4(){
		String expectedPostfix = "abc^^";
		String infix = "a^b^c";
		String actualPostfix = Calculator.convertToPostfix(infix);
		assertEquals(expectedPostfix,actualPostfix);
	}

	//tests for evaluatePostfix()
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
	@Test
	public void shouldEvalPostfix4(){
		double expectedResult = Math.pow(2,Math.pow(3,4));
		String postfix = "abc^^";
		double actualResult = Calculator.evaluatePostfix(postfix);
		assertEquals(expectedResult, actualResult, 0);
	}

	//tests for getPriority()
	@Test
	public void shouldGivePriority1(){
		int expectPriority = 1;
		int actualPriority = Calculator.getPriority('+');
		assertEquals(expectPriority, actualPriority, 0);
	}
	@Test
	public void shouldGivePriority2(){
		int expectPriority = 3;
		int actualPriority = Calculator.getPriority('^');
		assertEquals(expectPriority, actualPriority, 0);
	}

	//tests for convertCharToValue()
	@Test
	public void shouldGiveValueOfChar1(){
		double expectValue = 2;
		double actualValue = Calculator.convertCharToValue('a');
		assertEquals(expectValue, actualValue, 0);
	}
	@Test
	public void shouldGiveValueOfChar2(){
		double expectValue = 5;
		double actualValue = Calculator.convertCharToValue('d');
		assertEquals(expectValue, actualValue, 0);
	}

}
