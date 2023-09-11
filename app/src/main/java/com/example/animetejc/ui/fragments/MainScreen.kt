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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.Button
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

@Preview(showBackground = true, widthDp = 360, heightDp = 600)
@Composable
fun MainScreen() {
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