package com.example.animetejc.ui.fragments

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.animetejc.data.CustomSharedValues
import com.example.animetejc.ui.paging.CharacterPagingSource
import com.google.gson.GsonBuilder

//@Preview(showBackground = true, widthDp = 360, heightDp = 600)
@Composable
fun MainScreen() {
    ShowPagedList()
}

@Composable
fun AnimationScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(state = ScrollState(0))
    ) {
        val animator = remember { mutableStateOf(true) }
        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = { animator.value = !animator.value },
            content = {
                Text(text = "animate button")
            }
        )
        AnimatedVisibility(
            visible = animator.value,
            enter = slideInVertically { -1200 },
            exit = slideOutVertically { -1200 },
            content = {
                OutlinedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .height(400.dp),
                    content = {
                        Text(
                            text = "sample",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                    }
                )
            }
        )

        val expanded = remember { mutableStateOf(true) }
        ExtendedFloatingActionButton(
            modifier = Modifier.align(Alignment.End),
            onClick = { expanded.value = expanded.value.not() },
            text = { Text(text = "sample") },
            icon = { Icon(imageVector = Icons.Default.Create, contentDescription = null) },
            expanded = expanded.value
        )
    }
}

@Preview
@Composable
@Preview(showBackground = true, widthDp = 300, heightDp = 500)
fun ShowPagedList() {
    val pager = Pager(
        config = PagingConfig(pageSize = CustomSharedValues.pageSize),
        pagingSourceFactory = { CharacterPagingSource() }
    )

    val lazyListItems = pager.flow.collectAsLazyPagingItems()
    LazyColumn {
        if (lazyListItems.loadState.refresh == LoadState.Loading) {
            item {
                Text(
                    text = "Waiting for items to load from the backend",
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally)
                )
            }
        }

        items(count = lazyListItems.itemCount) {
            val item = lazyListItems[it]
            Card (modifier = Modifier.padding(all = 16.dp)){
                Text(
                    modifier = Modifier.fillMaxWidth().padding(all = 16.dp),
                    text = GsonBuilder().setPrettyPrinting().create().toJson(item),
                )
            }
        }
    }

//    LazyColumn {
//        items(,) { movieItem: Movie? ->
//            ResultItem()
//        }
//    }
}

//@Composable
//fun ResultItem() {
//
//}