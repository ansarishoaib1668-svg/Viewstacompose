package com.viewsta.app.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource

private val ViewstaPurple = Color(0xFF7C3AED)
private val ViewstaPink = Color(0xFFFF3B81)

data class Story(
    val name: String,
    val initials: String,
    val live: Boolean = false
)

data class FeedPost(
    val username: String,
    val location: String,
    val initials: String,
    val caption: String,
    val likes: Int
)

@Composable
fun HomeScreen() {

    val stories = listOf(
        Story("Your story", "Y"),
        Story("Aisha", "A"),
        Story("Arman", "AR", true),
        Story("Sara", "S"),
        Story("Zoya", "Z"),
        Story("Ali", "AL")
    )

    val posts = listOf(
        FeedPost(
            "aisha.view",
            "Mumbai, India",
            "A",
            "Beautiful moments are meant to be shared ✨",
            1248
        ),
        FeedPost(
            "arman.creates",
            "Delhi, India",
            "AR",
            "Creating my own view of the world.",
            876
        )
    )

    var selectedTab by remember { mutableIntStateOf(0) }

    Scaffold(
        containerColor = Color.White,
        topBar = {
            ViewstaTopBar()
        },
        bottomBar = {
            ViewstaBottomBar(
                selectedTab = selectedTab,
                onSelected = { selectedTab = it }
            )
        }
    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(padding),
            contentPadding = PaddingValues(bottom = 12.dp)
        ) {

            item {
                StoriesRow(stories)
            }

            item {
                HorizontalDivider(
                    color = Color(0xFFEFEFEF),
                    thickness = 0.7.dp
                )
            }

            items(posts) { post ->
                FeedPostCard(post)
            }
        }
    }
}

@Composable
private fun ViewstaTopBar() {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(62.dp)
            .background(Color.White)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = "Viewsta",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.weight(1f))

        IconButton(onClick = {}) {
            Icon(
                Icons.Outlined.AddBox,
                contentDescription = "Create",
                tint = Color.Black
            )
        }

        IconButton(onClick = {}) {
            Icon(
                Icons.Outlined.FavoriteBorder,
                contentDescription = "Activity",
                tint = Color.Black
            )
        }

        Box {

            IconButton(onClick = {}) {
                Icon(
                    Icons.Outlined.Send,
                    contentDescription = "Chat",
                    tint = Color.Black
                )
            }

            Box(
                modifier = Modifier
                    .size(17.dp)
                    .clip(CircleShape)
                    .background(ViewstaPink)
                    .align(Alignment.TopEnd),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "3",
                    color = Color.White,
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
private fun StoriesRow(stories: List<Story>) {

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(
            start = 12.dp,
            end = 12.dp,
            top = 10.dp,
            bottom = 12.dp
        ),
        horizontalArrangement = Arrangement.spacedBy(14.dp)
    ) {

        items(stories) { story ->

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.width(66.dp)
            ) {

                Box(
                    modifier = Modifier
                        .size(62.dp)
                        .clip(CircleShape)
                        .background(ViewstaPurple)
                        .padding(3.dp)
                ) {

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(CircleShape)
                            .background(Color.White)
                            .padding(3.dp)
                    ) {

                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(CircleShape)
                                .background(Color(0xFFF1F1F1)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                story.initials,
                                fontWeight = FontWeight.Bold,
                                fontSize = 17.sp,
                                color = Color.DarkGray
                            )
                        }
                    }

                    if (story.live) {
                        Surface(
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .offset(y = 5.dp),
                            shape = RoundedCornerShape(5.dp),
                            color = Color.Black
                        ) {
                            Text(
                                "LIVE",
                                color = Color.White,
                                fontSize = 8.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(
                                    horizontal = 5.dp,
                                    vertical = 2.dp
                                )
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = story.name,
                    fontSize = 11.sp,
                    maxLines = 1
                )
            }
        }
    }
}

@Composable
private fun FeedPostCard(post: FeedPost) {

    var liked by remember { mutableStateOf(false) }
    var saved by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Avatar(post.initials, 40)

            Spacer(modifier = Modifier.width(10.dp))

            Column(modifier = Modifier.weight(1f)) {

                Row(verticalAlignment = Alignment.CenterVertically) {

                    Text(
                        post.username,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Icon(
                        Icons.Outlined.Verified,
                        contentDescription = "Verified",
                        tint = ViewstaPurple,
                        modifier = Modifier.size(15.dp)
                    )
                }

                Text(
                    post.location,
                    fontSize = 11.sp,
                    color = Color.Gray
                )
            }

            IconButton(onClick = {}) {
                Icon(
                    Icons.Outlined.MoreVert,
                    contentDescription = "More",
                    tint = Color.Black
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(390.dp)
                .background(Color(0xFFEFEFEF)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                "VIEW",
                fontSize = 42.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFCCCCCC)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 12.dp,
                    end = 12.dp,
                    top = 8.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {

            IconButton(onClick = { liked = !liked }) {
                Icon(
                    if (liked)
                        Icons.Filled.Favorite
                    else
                        Icons.Outlined.FavoriteBorder,
                    contentDescription = "Like",
                    tint = if (liked) ViewstaPink else Color.Black
                )
            }

            IconButton(onClick = {}) {
                Icon(
                    Icons.Outlined.ChatBubbleOutline,
                    contentDescription = "Comment",
                    tint = Color.Black
                )
            }

            IconButton(onClick = {}) {
                Icon(
                    Icons.Outlined.Send,
                    contentDescription = "Share",
                    tint = Color.Black
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            IconButton(onClick = { saved = !saved }) {
                Icon(
                    if (saved)
                        Icons.Filled.Bookmark
                    else
                        Icons.Outlined.BookmarkBorder,
                    contentDescription = "Save",
                    tint = Color.Black
                )
            }
        }

        Text(
            text = "${if (liked) post.likes + 1 else post.likes} likes",
            fontWeight = FontWeight.Bold,
            fontSize = 13.sp,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Text(
            text = "${post.username} ${post.caption}",
            fontSize = 13.sp,
            modifier = Modifier.padding(
                horizontal = 16.dp,
                vertical = 5.dp
            )
        )

        Text(
            text = "View all comments",
            color = Color.Gray,
            fontSize = 12.sp,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(5.dp))

        Text(
            text = "2 HOURS AGO",
            color = Color.Gray,
            fontSize = 9.sp,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))
    }
}

@Composable
private fun Avatar(initials: String, size: Int) {

    Box(
        modifier = Modifier
            .size(size.dp)
            .clip(CircleShape)
            .background(ViewstaPurple),
        contentAlignment = Alignment.Center
    ) {
        Text(
            initials,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = (size / 2.5).sp
        )
    }
}

@Composable
private fun ViewstaBottomBar(
    selectedTab: Int,
    onSelected: (Int) -> Unit
) {

    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 0.dp
    ) {

        val items = listOf(
            Icons.Outlined.Home to "Home",
            Icons.Outlined.Search to "Search",
            Icons.Outlined.VideoLibrary to "Reels",
            Icons.Outlined.AutoAwesome to "Discover",
            Icons.Outlined.PersonOutline to "Profile"
        )

        items.forEachIndexed { index, item ->

            NavigationBarItem(
                selected = selectedTab == index,
                onClick = { onSelected(index) },
                icon = {
                    Icon(
                        item.first,
                        contentDescription = item.second
                    )
                },
                label = null
            )
        }
    }
}
