package com.yildirim.customfabbuttonapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yildirim.customfabbuttonapp.ui.theme.CustomFabButtonAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CustomFabButtonAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CustomFabButton()
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomFabButton() {
    val context = LocalContext.current
    val listState = rememberLazyGridState()
    val fabVisibility by derivedStateOf { listState.firstVisibleItemIndex == 0 }
    val list = (0..9).flatMap { listOf(it, it) }.shuffled()
    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Hidden Floating Action Button")
                },
            )
        },

        floatingActionButton = {
            CustomFabButton(modifier = Modifier
                .padding(all = 30.dp),
                isVisibleBecauseOfScrolling = fabVisibility,
                onClick = {
                    Toast.makeText(context,"Click Fab",Toast.LENGTH_SHORT).show()
                },
            )
        }

    ) {
        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center){
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                state = listState,
                // content padding
                contentPadding = PaddingValues(
                    start = 12.dp,
                    top = 70.dp,
                    end = 12.dp,
                    bottom = 16.dp
                ),
                content = {
                    items(list.size) { index ->
                        Card(
                            elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
                            modifier = Modifier
                                .padding(all = 5.dp)
                                .fillMaxWidth(),
                            colors = CardDefaults.cardColors(
                                containerColor = colorResource(id = R.color.white),
                            )
                        ) {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Spacer(modifier = Modifier.size(15.dp))
                                Text(
                                    text = list[index].toString(),
                                    fontSize = 72.sp,
                                    fontWeight = FontWeight.W300,
                                    color = colorResource(id = R.color.gray)
                                )
                                Spacer(modifier = Modifier.size(15.dp))
                            }
                        }
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CustomFabButtonAppTheme {
        CustomFabButton()
    }
}