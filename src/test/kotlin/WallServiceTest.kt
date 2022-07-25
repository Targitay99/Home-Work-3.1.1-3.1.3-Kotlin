import org.junit.Test
import org.junit.Assert.*
import ru.netology.*


class WallServiceTest {

    @Test(expected = PostNotFoundException::class)
    fun shouldThrow() {
        val postTest =
            Post(id = 0, comment = emptyArray(), likes = Likes()) // Объект Post создан, но в массив не добавлен.
        WallService.createComment(postTest.id, Comment(text = "Ошибка!"))
    }

    @Test(expected = CommentNotFoundException::class)
    fun shouldThrowCommentNotFound() {
        val commentTest = Comment(0, 0, 0) // Объект Comment создан, но в массив не добавлен.
        WallService.createReportComment(ReportComment(1, commentTest.id, 1))
    }

    @Test(expected = CommentNotFoundException::class)
    fun shouldThrowReasonNotFound() {
        val post = WallService.add(Post(id = 0, comment = emptyArray(), likes = Likes()))
        val commentTest = WallService.createComment(post.id, Comment(text = "test"))
        WallService.createReportComment(ReportComment(1, commentTest.id, 9)) // Ошибка т.к. видов жалоб всего 8
    }

    @Test
    fun addReportCommentTrue() {
        val post = WallService.add(Post(id = 0, comment = emptyArray(), likes = Likes()))
        var test = false
        val testComment = WallService.createComment(post.id, Comment(text = "test"))


        val testReportComment = WallService.createReportComment(ReportComment(1, testComment.id, 1))
        if (testReportComment.reason == 1)
            test = true

        assertTrue(test)
    }

    @Test
    fun addReportCommentFalse() {
        val test: Boolean
        val testComment = Comment(id = 0, text = "test")

        test = try {
            WallService.createReportComment(ReportComment(0, testComment.id, 1))
            true
        } catch (e: CommentNotFoundException) {
            false
        }
        assertFalse(test)
    }

    @Test
    fun addCommentTrue() {
        val post = WallService.add(Post(id = 0, comment = emptyArray(), likes = Likes()))
        var test = false
        val testComment = WallService.createComment(post.id, Comment(text = "test"))

        if (testComment.text == "test") test = true
        assertTrue(test)
    }

    @Test
    fun addCommentFalse() {
        val test: Boolean
        val post = Post(id = 0, comment = emptyArray(), likes = Likes())
        test = try {
            WallService.createComment(post.id, Comment(text = "test"))
            true
        } catch (e: PostNotFoundException) {
            false
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