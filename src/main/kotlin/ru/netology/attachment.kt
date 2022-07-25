package ru.netology



sealed class Attachment (val type: String)

data class AudioAttachment(val audio: Audio) : Attachment ("audio")

class Audio(
    private val id: Int = 0,
    private val ownerId: Int = 0,
    private val artist: String = "",
    private val title: String = "",
    private val duration: Int = 0,
    private val url: String = "",
    private val lyricsId: Int = 0,
    private val albumId: Int = 0,
    private val genreId: Int = 0,
    private val date: Int = 0,
    private val noSearch: Boolean = true,
    private val isHq: Boolean = true
) {
    override fun toString(): String {
        return "id = $id albumId = $albumId Название = $title Певец = $artist"
    }
}

data class PhotoAttachment(val photo: Photo) : Attachment ("photo")

class Photo(
    val id: Int = 0,
    val albumId: Int = 0,
    val ownerId: Int = 0,
    val userId: Int = 0,
    val text: String = "",
    val date: Int = 0,
    val sizes: Array<Sizes> = emptyArray(),
    val width: Int = 0,
    val height: Int = 0
)

data class Sizes(
    val type: String = "",
    val url: String = "",
    val width: Int = 0,
    val height: Int = 0
)

data class VideoAttachment(val video: Video) : Attachment ("video")

class Video(
    val id: Int = 0,
    val ownerId: Int = 0,
    val title: String = "",
    val description: String = "",
    val duration: Int = 0,
    val image: Array<Image> = emptyArray()
)

data class Image(
    val height: Int = 0,
    val url: String = "",
    val width: Int = 0,
    val withPadding: Int = 0
)

