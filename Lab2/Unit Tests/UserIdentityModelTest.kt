import org.junit.Assert.*
import org.junit.Test

class UserIdentityModelTest {
    private val userIdentityModel = UserIdentityModel()

    @Test
    fun testSetUserId() {
        userIdentityModel.setUserId("TestUser")
        assertEquals("TestUser", userIdentityModel.getUserId())
    }

    @Test
    fun testGetUserId() {
        assertNull(userIdentityModel.getUserId())
        userIdentityModel.setUserId("TestUser")
        assertEquals("TestUser", userIdentityModel.getUserId())
    }
}