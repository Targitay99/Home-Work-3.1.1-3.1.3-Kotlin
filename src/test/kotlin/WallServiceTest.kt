import org.junit.Test
import org.junit.Assert.*
import ru.netology.*


class WallServiceTest {

    @Test(expected = PostNotFoundException::class)
    fun shouldThrow() {
        WallService.createComment(1, Comment(text = "Ошибка!")) ?: throw PostNotFoundException("Пост не найден!")
    }

    @Test
    fun addReportCommentTrue() {
        val post = WallService.add(Post(id = 0, comment = emptyArray(), likes = Likes()))
        var test = false
        val testComment = WallService.createComment(post.id, Comment(text = "test"))

        if (testComment != null) {
            val testReportComment = WallService.createReportComment(ReportComment(1, testComment.id, 1))
            if (testReportComment != null) {
                if (testReportComment.reason == 1)
                    test = true
            }
        }
        assertTrue(test)
    }

    @Test
    fun addReportCommentFalse() {
        var test = false
        val testReportComment = WallService.createReportComment(ReportComment(0, 0, 1))

        if (testReportComment != null) {
            if (testReportComment.reason == 1)
                test = true
        }

        assertFalse(test)
    }

    @Test
    fun addCommentTrue() {
        val post = WallService.add(Post(id = 0, comment = emptyArray(), likes = Likes()))
        var test = false
        val testComment = WallService.createComment(post.id, Comment(text = "test"))

        if (testComment != null) {
            if (testComment.text == "test") test = true
        }
        assertTrue(test)
    }

    @Test
    fun addCommentFalse() {
        val post = Post(id = 0, comment = emptyArray(), likes = Likes())
        var test = false
        val testComment = WallService.createComment(post.id, Comment(text = "test"))

        if (testComment != null) {
            if (testComment.text == "test") test = true
        }
        assertFalse(test)
    }

    @Test
    fun addPost() {
        val post = WallService.add(Post(id = 0, comment = emptyArray(), likes = Likes()))
        var test = true

        if (post.id == 0) test = false

        assertTrue(test)
    }

    @Test
    fun updatePostTrue() {
        val post = WallService.add(Post(text = "being", comment = emptyArray(), likes = Likes()))
        var test = true
        val updatePost = post.copy(text = "end")

        test = WallService.update(updatePost)

        assertTrue(test)
    }

    @Test
    fun updatePostFalse() {
        val post = Post(text = "being", comment = emptyArray(), likes = Likes())
        var test = true
        val updatePost = post.copy(text = "end")

        test = WallService.update(updatePost)

        assertFalse(test)
    }
}