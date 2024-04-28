package com.example.sherbek791.screens.insideCategory.module


import com.google.gson.annotations.SerializedName

data class TopicModule(

	@field:SerializedName("only_submissions_after")
	val onlySubmissionsAfter: Any? = null,

	@field:SerializedName("starts_at")
	val startsAt: String? = null,

	@field:SerializedName("featured")
	val featured: Boolean? = null,

	@field:SerializedName("cover_photo")
	val coverPhoto: CoverPhoto? = null,

	@field:SerializedName("visibility")
	val visibility: String? = null,

	@field:SerializedName("top_contributors")
	val topContributors: List<TopContributorsItem?>? = null,

	@field:SerializedName("total_photos")
	val totalPhotos: Int? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("owners")
	val owners: List<OwnersItem?>? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("preview_photos")
	val previewPhotos: List<PreviewPhotosItem?>? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("total_current_user_submissions")
	val totalCurrentUserSubmissions: Any? = null,

	@field:SerializedName("current_user_contributions")
	val currentUserContributions: List<Any?>? = null,

	@field:SerializedName("links")
	val links: Links? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("ends_at")
	val endsAt: Any? = null,

	@field:SerializedName("published_at")
	val publishedAt: String? = null,

	@field:SerializedName("slug")
	val slug: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class ProfileImage(

	@field:SerializedName("small")
	val small: String? = null,

	@field:SerializedName("large")
	val large: String? = null,

	@field:SerializedName("medium")
	val medium: String? = null
)

data class Wallpapers(

	@field:SerializedName("status")
	val status: String? = null
)

data class PreviewPhotosItem(

	@field:SerializedName("urls")
	val urls: Urls? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("blur_hash")
	val blurHash: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("slug")
	val slug: String? = null
)

data class TopicSubmissions(

	@field:SerializedName("nature")
	val nature: Nature? = null,

	@field:SerializedName("wallpapers")
	val wallpapers: Wallpapers? = null,

	@field:SerializedName("animals")
	val animals: Animals? = null,

	@field:SerializedName("travel")
	val travel: Travel? = null
)

data class Links(

	@field:SerializedName("self")
	val self: String? = null,

	@field:SerializedName("html")
	val html: String? = null,

	@field:SerializedName("photos")
	val photos: String? = null,

	@field:SerializedName("followers")
	val followers: String? = null,

	@field:SerializedName("portfolio")
	val portfolio: String? = null,

	@field:SerializedName("following")
	val following: String? = null,

	@field:SerializedName("likes")
	val likes: String? = null,

	@field:SerializedName("download")
	val download: String? = null,

	@field:SerializedName("download_location")
	val downloadLocation: String? = null
)

data class User(

	@field:SerializedName("total_photos")
	val totalPhotos: Int? = null,

	@field:SerializedName("accepted_tos")
	val acceptedTos: Boolean? = null,

	@field:SerializedName("social")
	val social: Social? = null,

	@field:SerializedName("twitter_username")
	val twitterUsername: Any? = null,

	@field:SerializedName("last_name")
	val lastName: String? = null,

	@field:SerializedName("bio")
	val bio: String? = null,

	@field:SerializedName("total_likes")
	val totalLikes: Int? = null,

	@field:SerializedName("portfolio_url")
	val portfolioUrl: Any? = null,

	@field:SerializedName("profile_image")
	val profileImage: ProfileImage? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("for_hire")
	val forHire: Boolean? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("location")
	val location: String? = null,

	@field:SerializedName("links")
	val links: Links? = null,

	@field:SerializedName("total_collections")
	val totalCollections: Int? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("first_name")
	val firstName: String? = null,

	@field:SerializedName("instagram_username")
	val instagramUsername: String? = null,

	@field:SerializedName("username")
	val username: String? = null
)

data class Social(

	@field:SerializedName("twitter_username")
	val twitterUsername: Any? = null,

	@field:SerializedName("paypal_email")
	val paypalEmail: Any? = null,

	@field:SerializedName("instagram_username")
	val instagramUsername: String? = null,

	@field:SerializedName("portfolio_url")
	val portfolioUrl: Any? = null
)

data class TopContributorsItem(

	@field:SerializedName("total_photos")
	val totalPhotos: Int? = null,

	@field:SerializedName("accepted_tos")
	val acceptedTos: Boolean? = null,

	@field:SerializedName("social")
	val social: Social? = null,

	@field:SerializedName("twitter_username")
	val twitterUsername: Any? = null,

	@field:SerializedName("last_name")
	val lastName: String? = null,

	@field:SerializedName("bio")
	val bio: String? = null,

	@field:SerializedName("total_likes")
	val totalLikes: Int? = null,

	@field:SerializedName("portfolio_url")
	val portfolioUrl: String? = null,

	@field:SerializedName("profile_image")
	val profileImage: ProfileImage? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("for_hire")
	val forHire: Boolean? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("location")
	val location: String? = null,

	@field:SerializedName("links")
	val links: Links? = null,

	@field:SerializedName("total_collections")
	val totalCollections: Int? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("first_name")
	val firstName: String? = null,

	@field:SerializedName("instagram_username")
	val instagramUsername: String? = null,

	@field:SerializedName("username")
	val username: String? = null
)

data class OwnersItem(

	@field:SerializedName("total_photos")
	val totalPhotos: Int? = null,

	@field:SerializedName("accepted_tos")
	val acceptedTos: Boolean? = null,

	@field:SerializedName("social")
	val social: Social? = null,

	@field:SerializedName("twitter_username")
	val twitterUsername: String? = null,

	@field:SerializedName("last_name")
	val lastName: Any? = null,

	@field:SerializedName("bio")
	val bio: String? = null,

	@field:SerializedName("total_likes")
	val totalLikes: Int? = null,

	@field:SerializedName("portfolio_url")
	val portfolioUrl: String? = null,

	@field:SerializedName("profile_image")
	val profileImage: ProfileImage? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("for_hire")
	val forHire: Boolean? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("location")
	val location: String? = null,

	@field:SerializedName("links")
	val links: Links? = null,

	@field:SerializedName("total_collections")
	val totalCollections: Int? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("first_name")
	val firstName: String? = null,

	@field:SerializedName("instagram_username")
	val instagramUsername: String? = null,

	@field:SerializedName("username")
	val username: String? = null
)

data class Nature(

	@field:SerializedName("approved_on")
	val approvedOn: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class Urls(

	@field:SerializedName("small")
	val small: String? = null,

	@field:SerializedName("small_s3")
	val smallS3: String? = null,

	@field:SerializedName("thumb")
	val thumb: String? = null,

	@field:SerializedName("raw")
	val raw: String? = null,

	@field:SerializedName("regular")
	val regular: String? = null,

	@field:SerializedName("full")
	val full: String? = null
)

data class CoverPhoto(

	@field:SerializedName("topic_submissions")
	val topicSubmissions: TopicSubmissions? = null,

	@field:SerializedName("current_user_collections")
	val currentUserCollections: List<Any?>? = null,

	@field:SerializedName("color")
	val color: String? = null,

	@field:SerializedName("sponsorship")
	val sponsorship: Any? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("description")
	val description: Any? = null,

	@field:SerializedName("liked_by_user")
	val likedByUser: Boolean? = null,

	@field:SerializedName("urls")
	val urls: Urls? = null,

	@field:SerializedName("alt_description")
	val altDescription: Any? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("width")
	val width: Int? = null,

	@field:SerializedName("blur_hash")
	val blurHash: String? = null,

	@field:SerializedName("links")
	val links: Links? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("promoted_at")
	val promotedAt: Any? = null,

	@field:SerializedName("user")
	val user: User? = null,

	@field:SerializedName("slug")
	val slug: String? = null,

	@field:SerializedName("breadcrumbs")
	val breadcrumbs: List<Any?>? = null,

	@field:SerializedName("height")
	val height: Int? = null,

	@field:SerializedName("likes")
	val likes: Int? = null
)

data class Travel(

	@field:SerializedName("status")
	val status: String? = null
)

data class Animals(

	@field:SerializedName("status")
	val status: String? = null
)
