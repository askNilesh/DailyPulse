package com.asknilesh.dailypulse.android.screens

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.List
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImagePainter
import coil3.compose.AsyncImagePainter.State.Error
import coil3.compose.SubcomposeAsyncImage
import coil3.compose.SubcomposeAsyncImageContent
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.asknilesh.dailypulse.articles.domain.Article
import com.asknilesh.dailypulse.articles.domain.Source
import com.asknilesh.dailypulse.articles.presentation.ArticlesViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import org.koin.androidx.compose.getViewModel

@Composable
fun ArticlesScreen(
    articlesViewModel: ArticlesViewModel = getViewModel<ArticlesViewModel>(),
    onAboutButtonClick: () -> Unit,
) {

    val articlesState = articlesViewModel.articlesState.collectAsState().value
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(topBar = {
        ArticlesTopBar(onAboutButtonClick) {
            showDialog = true
        }
    }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (showDialog) {
                SingleSelectionDialog(
                    options = articlesState.sources.sortedBy { it.country },
                    onDismiss = { showDialog = false }
                )
            }
            if (articlesState.error != null) ErrorMessage(articlesState.error!!)
            SwipeRefresh(
                onRefresh = { articlesViewModel.refreshArticles() },
                state = SwipeRefreshState(articlesState.loading)
            ) {
                if (articlesState.articles.isEmpty()) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(
                            "No Articles Found",
                            style = TextStyle(fontSize = 28.sp, textAlign = TextAlign.Center)
                        )
                    }
                } else {
                    ArticlesListView(articlesState.articles)
                }

            }


        }
    }
}

@Composable
fun SingleSelectionDialog(
    options: List<Source>,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Sources", textAlign = TextAlign.Center) },
        text = {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(options) { option ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {

                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(
                                option.name,
                                style = TextStyle(
                                    fontSize = 18.sp,
                                    fontStyle = FontStyle.Normal,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                            Text(
                                option.description,
                                style = TextStyle(fontSize = 16.sp, fontStyle = FontStyle.Normal)
                            )
                            Text(
                                "${option.country} - ${option.language}",
                                style = TextStyle(fontSize = 16.sp, fontStyle = FontStyle.Normal),
                                textAlign = TextAlign.Right,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("OK")
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticlesTopBar(
    onAboutButtonClick: () -> Unit,
    showDialog: () -> Unit
) {
    TopAppBar(
        title = {
            Text("Articles")
        },
        actions = {
            IconButton(onClick = showDialog) {
                Icon(
                    imageVector = Icons.Outlined.List,
                    contentDescription = "Choose Source",
                )
            }
            IconButton(onClick = onAboutButtonClick) {
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = "About Device Button",
                )
            }


        })
}

@Composable
fun ArticlesListView(articles: List<Article>) {

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(articles) { article ->
            ArticleItemView(article = article)
        }
    }
}

@Composable
fun ArticleItemView(article: Article) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            ArticleImage(article)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = article.title,
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 22.sp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = article.desc)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = article.date,
                style = TextStyle(color = Color.Gray),
                modifier = Modifier.align(Alignment.End)
            )
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}

@Composable
fun ArticleImage(article: Article) {
    val context = LocalContext.current

    val imageRequest = ImageRequest.Builder(context).data(article.imageUrl).crossfade(true).build()

    SubcomposeAsyncImage(
        model = imageRequest,
        contentDescription = "Image description",
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    ) {
        val state by painter.state.collectAsState()

        when (state) {
            is AsyncImagePainter.State.Success -> {
                SubcomposeAsyncImageContent()
            }

            is Error -> {
                val error = (state as Error).result.throwable
                Log.e("SubcomposeAsyncImage", "Error loading image: ${error.message}", error)
                Text("Error avi nilya")
            }

            else -> {
                Box(contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(150.dp),
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        trackColor = MaterialTheme.colorScheme.secondary,
                    )
                }
            }
        }
    }
}

@Composable
fun Loader() {
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.width(64.dp),
            color = MaterialTheme.colorScheme.surfaceVariant,
            trackColor = MaterialTheme.colorScheme.secondary,
        )
    }
}

@Composable
fun ErrorMessage(message: String) {
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Text(
            text = message, style = TextStyle(fontSize = 28.sp, textAlign = TextAlign.Center)
        )
    }
}