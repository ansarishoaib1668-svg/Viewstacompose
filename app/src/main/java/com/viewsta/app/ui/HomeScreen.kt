package com.viewsta.app.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

private val Purple = Color(0xFF7C3AED)
private val Pink = Color(0xFFFF3B81)

data class Story(
    val name: String,
    val image: String,
    val live: Boolean = false
)

data class FeedPost(
    val username: String,
    val location: String,
    val profileImage: String,
    val postImage: String,
    val caption: String,
    val likes: Int,
    val comments: Int
)

@Composable
fun HomeScreen() {

    var selectedTab by remember { mutableIntStateOf(0) }

    val stories = listOf(
        Story(
            "Your story",
            "https://images.unsplash.com/photo-1500648767791-00dcc994a43e?w=200"
        ),
        Story(
            "Aisha",
            "https://images.unsplash.com/photo-1494790108377-be9c29b29330?w=200"
        ),
        Story(
            "Arman",
            "https://images.unsplash.com/photo-1506794778202-cad84cf45f1d?w=200",
            true
        ),
        Story(
            "Sara",
            "https://images.unsplash.com/photo-1531123897727-8f129e1688ce?w=200"
        ),
        Story(
            "Zoya",
            "https://images.unsplash.com/photo-1517841905240-472988babdf9?w=200"
        ),
        Story(
            "Ali",
            "https://images.unsplash.com/photo-1500530855697-b586d89ba3ee?w=200"
        )
    )

    val posts = listOf(
        FeedPost(
            "aisha.view",
            "Mumbai, India",
            "https://images.unsplash.com/photo-1531123897727-8f129e1688ce?w=300",
            "https://images.unsplash.com/photo-1507525428034-b723cf961d3e?w=1200",
            "Beautiful moments are meant to be shared ✨",
            1248,
            126
        ),
        FeedPost(
            "arman.creates",
            "Delhi, India",
            "https://images.unsplash.com/photo-1506794778202-cad84cf45f1d?w=300",
            "https://images.unsplash.com/photo-1500534623283-312aade485b7?w=1200",
            "Creating my own view of the world.",
            876,
            84
        )
    )

    Scaffold(
        containerColor = Color.White,
        topBar = {
            TopBar()
        },
        bottomBar = {
            BottomBar(
                selected = selectedTab,
                onSelected = { selectedTab = it }
            )
        }
    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color.White),
            contentPadding = PaddingValues(bottom = 15.dp)
        ) {

            item {
                Stories(stories)
            }

            item {
                HorizontalDivider(
                    color = Color(0xFFEAEAEA),
                    thickness = 0.7.dp
                )
            }

            items(posts) { post ->
                PostCard(post)
            }
        }
    }
}

@Composable
private fun TopBar() {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(65.dp)
            .padding(horizontal = 15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = "Viewsta",
            fontSize = 29.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(Modifier.weight(1f))

        Icon(
            Icons.Outlined.AddBox,
            contentDescription = "Create",
            modifier = Modifier
                .size(29.dp)
                .clickable { },
            tint = Color.Black
        )

        Spacer(Modifier.width(20.dp))

        Box {

            Icon(
                Icons.Outlined.FavoriteBorder,
                contentDescription = "Activity",
                modifier = Modifier.size(29.dp),
                tint = Color.Black
            )

            Box(
                modifier = Modifier
                    .size(9.dp)
                    .background(Pink, CircleShape)
                    .align(Alignment.TopEnd)
            )
        }

        Spacer(Modifier.width(20.dp))

        Box {

            Icon(
                Icons.Outlined.Send,
                contentDescription = "Chat",
                modifier = Modifier.size(29.dp),
                tint = Color.Black
            )

            Box(
                modifier = Modifier
                    .size(19.dp)
                    .background(Pink, CircleShape)
                    .align(Alignment.TopEnd),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "3",
                    color = Color.White,
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
private fun Stories(stories: List<Story>) {

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .height(126.dp),
        contentPadding = PaddingValues(
            start = 13.dp,
            end = 13.dp,
            top = 9.dp,
            bottom = 8.dp
        ),
        horizontalArrangement = Arrangement.spacedBy(14.dp)
    ) {

        items(stories) { story ->

            Column(
                modifier = Modifier.width(65.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Box {

                    Box(
                        modifier = Modifier
                            .size(63.dp)
                            .background(
                                Brush.linearGradient(
                                    listOf(
                                        Color(0xFFFFB300),
                                        Color(0xFFFF1978),
                                        Color(0xFF7C3AED)
                                    )
                                ),
                                CircleShape
                            )
                            .padding(3.dp)
                    ) {

                        AsyncImage(
                            model = story.image,
                            contentDescription = story.name,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(CircleShape)
                                .border(
                                    3.dp,
                                    Color.White,
                                    CircleShape
                                )
                        )
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

                Spacer(Modifier.height(6.dp))

                Text(
                    story.name,
                    fontSize = 11.sp,
                    maxLines = 1
                )
            }
        }
    }
}

@Composable
private fun PostCard(post: FeedPost) {

    var liked by remember { mutableStateOf(false) }
    var saved by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 12.dp,
                    vertical = 10.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {

            AsyncImage(
                model = post.profileImage,
                contentDescription = post.username,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(43.dp)
                    .clip(CircleShape)
            )

            Spacer(Modifier.width(10.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        post.username,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(Modifier.width(4.dp))

                    Icon(
                        Icons.Outlined.Verified,
                        contentDescription = "Verified",
                        tint = Color(0xFF2196F3),
                        modifier = Modifier.size(15.dp)
                    )
                }

                Text(
                    post.location,
                    fontSize = 11.sp,
                    color = Color.Gray
                )
            }

            Icon(
                Icons.Outlined.MoreVert,
                contentDescription = "More",
                tint = Color.Black
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .background(Color(0xFFEFEFEF))
        ) {

            AsyncImage(
                model = post.postImage,
                contentDescription = "Post",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(12.dp)
                    .background(
                        Color.Black.copy(alpha = .6f),
                        RoundedCornerShape(20.dp)
                    )
                    .padding(
                        horizontal = 11.dp,
                        vertical = 6.dp
                    )
            ) {

                Text(
                    "1/5",
                    color = Color.White,
                    fontSize = 12.sp
                )
            }

            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 10.dp)
            ) {

                repeat(5) { index ->

                    Box(
                        modifier = Modifier
                            .padding(horizontal = 2.dp)
                            .size(
                                if (index == 0) 8.dp else 6.dp
                            )
                            .background(
                                if (index == 0)
                                    Color.White
                                else
                                    Color.White.copy(.5f),
                                CircleShape
                            )
                    )
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 10.dp,
                    vertical = 7.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                if (liked)
                    Icons.Filled.Favorite
                else
                    Icons.Outlined.FavoriteBorder,
                contentDescription = "Like",
                tint = if (liked) Pink else Color.Black,
                modifier = Modifier
                    .size(30.dp)
                    .clickable {
                        liked = !liked
                    }
            )

            Spacer(Modifier.width(18.dp))

            Icon(
                Icons.Outlined.ChatBubbleOutline,
                contentDescription = "Comment",
                modifier = Modifier.size(29.dp)
            )

            Spacer(Modifier.width(18.dp))

            Icon(
                Icons.Outlined.Send,
                contentDescription = "Share",
                modifier = Modifier.size(29.dp)
            )

            Spacer(Modifier.weight(1f))

            Icon(
                if (saved)
                    Icons.Outlined.Bookmark
                else
                    Icons.Outlined.BookmarkBorder,
                contentDescription = "Save",
                modifier = Modifier
                    .size(29.dp)
                    .clickable {
                        saved = !saved
                    }
            )
        }

        Text(
            text = "${if (liked) post.likes + 1 else post.likes} likes",
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 13.dp)
        )

        Text(
            text = "${post.username} ${post.caption}",
            fontSize = 13.sp,
            modifier = Modifier.padding(
                horizontal = 13.dp,
                vertical = 5.dp
            )
        )

        Text(
            text = "View all ${post.comments} comments",
            fontSize = 12.sp,
            color = Color.Gray,
            modifier = Modifier.padding(horizontal = 13.dp)
        )

        Text(
            text = "2 HOURS AGO",
            fontSize = 9.sp,
            color = Color.Gray,
            modifier = Modifier.padding(
                horizontal = 13.dp,
                vertical = 6.dp
            )
        )

        Spacer(Modifier.height(10.dp))
    }
}

@Composable
private fun BottomBar(
    selected: Int,
    onSelected: (Int) -> Unit
) {

    val items = listOf(
        Icons.Filled.Home,
        Icons.Outlined.Search,
        Icons.Outlined.VideoLibrary,
        Icons.Outlined.AutoAwesome,
        Icons.Filled.Person
    )

    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 0.dp
    ) {

        items.forEachIndexed { index, icon ->

            NavigationBarItem(
                selected = selected == index,
                onClick = {
                    onSelected(index)
                },
                icon = {
                    Icon(
                        icon,
                        contentDescription = null,
                        modifier = Modifier.size(27.dp),
                        tint = Color.Black
                    )
                },
                label = null,
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}
