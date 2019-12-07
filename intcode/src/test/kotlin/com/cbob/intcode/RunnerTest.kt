import org.junit.Assert
import org.junit.Test

class RunnerTest {
    @Test
    fun whenAdding1and3_thenAnswerIs4() {
       val runner = Runner()
        Assert.assertEquals(5, runner.test())
    }
}