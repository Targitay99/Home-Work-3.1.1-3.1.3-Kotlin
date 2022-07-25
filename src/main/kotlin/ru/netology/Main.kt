package ru.netology

fun main() {

    val post1 = WallService.add(Post(1, 2, 3, 4, 5, "Раз", 7, 8, true, emptyArray(), Likes()))
    val post2 = WallService.add(Post(2, 2, 3, 4, 5, "Два", 7, 8, true, emptyArray(), Likes()))
    val post3 = WallService.add(Post(3, 2, 3, 4, 5, "Три", 7, 8, true, emptyArray(), Likes(10)))
    val post4 = Post(3, 2, 3, 4, 5, "пост не с этой страницы", 7, 8, true, emptyArray(), Likes())

    val comment1 = WallService.createComment(post1.id, Comment(text = "Я"))
    val comment2 = WallService.createComment(post1.id, Comment(text = "не привязан"))
    val comment3 = WallService.createComment(post1.id, Comment(text = "к посту!"))


    WallService.printComments()

    val reportComment1 = WallService.createReportComment(ReportComment(1, comment1.id, 1))
    // не существующий пост
    // val reportComment2 = WallService.createReportComment(ReportComment(1, 0, 0))

    // не существующий индекс жалобы
    // val reportComment3 = WallService.createReportComment(ReportComment(1, comment1.id, 9))

    WallService.printReportComment()

    WallService.createComment(post4.id, Comment(text = "Ошибка!"))
}

data class Post(
    val id: Int = 0,
    val ownerId: Int = 0,
    val fromId: Int = 0,
    val createdBy: Int = 0,
    val data: Int = 0,
    val text: String = " ",
    val replyOwnerId: Int = 0,
    val replyPostId: Int = 0,
    val friendsOnly: Boolean = false,
    val comment: Array<Comment> = emptyArray(),
    val likes: Likes,
    var attachment: Array<Attachment> = emptyArray()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Post

        if (!attachment.contentEquals(other.attachment)) return false

        return true
    }

    override fun hashCode(): Int {
        return attachment.contentHashCode()
    }
}

data class ReportComment(
    val ownerId: Int = 0,
    val commentId: Int = 0,
    val reason: Int = 0
)


data class Comment(
    val id: Int = 0,
    val fromId: Int = 0,
    val date: Int = 0,
    val text: String = "",
    val donut: Boolean = true,
    val replyToUser: Int = 0,
    val attachment: String = "",
    val parentsStack: Int = 0,
    val thread: String = ""
)

class PostNotFoundException(message: String) : RuntimeException(message)

class CommentNotFoundException(message: String) : RuntimeException(message)

data class Likes(
    val count: Int = 0,
    val userLikes: Boolean = false,
    val canLike: Boolean = true,
    val canPublish: Boolean = true
)

object WallService {
    private var posts = emptyArray<Post>()
    private var comments = emptyArray<Comment>()
    private var reportComments = emptyArray<ReportComment>()

    fun createComment(postId: Int, comment: Comment): Comment {
        for (post in posts)
            if (post.id == postId) {
                val commentId = comment.hashCode()
                comments += comment.copy(id = commentId)
                return comments.last()
            }
        throw PostNotFoundException("Post not found!")
    }

    fun createReportComment(reportComment: ReportComment): ReportComment {
        if (reportComment.reason in 0..8) {
            for (comment in comments)
                if (comment.id == reportComment.commentId) {
                    reportComments += reportComment
                    return reportComments.last()
                }
        }
        throw CommentNotFoundException("Error in the comment complaint")
    }

    fun add(post: Post): Post {
        val postId = posts.hashCode()
        posts += post.copy(id = postId)
        return posts.last()
    }

    fun update(post: Post): Boolean {
        var canUpdate = false
        for ((i, updatedPost) in posts.withIndex())
            if (post.id == updatedPost.id) {
                posts[i] = post.copy(ownerId = updatedPost.ownerId, data = updatedPost.data)
                canUpdate = true
            }
        return canUpdate
    }

    fun printPost() {
        for (i in 1..posts.size) {
            println("${posts[i - 1].id}   ${posts[i - 1].text}     ${posts[i - 1].comment} ")
        }
    }

    fun printAttachment(post: Post) {
        for (i in 1..post.attachment.size)
            println("${post.id} ${post.attachment[i - 1]} ")
    }

    fun printComments() {
        for (i in 1..(comments.size))
            println(comments[i - 1].text)
    }

    fun printReportComment() {
        for (i in 1..reportComments.size)
            println(reportComments[i - 1])
    }
}


