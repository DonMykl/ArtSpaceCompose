package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {

                    ArtWork()
                }
            }
        }

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtWork( modifier: Modifier = Modifier
) {
    var currentImage by remember { mutableStateOf(1) }
    var totalImages = 7


    val imageResource = when (currentImage) {
        1 -> R.drawable.timekeeper
        2 -> R.drawable.state
        3 -> R.drawable.aritual
        4 -> R.drawable.uzumaki
        5 -> R.drawable.monkpunk
        6 -> R.drawable.blackhole
        else -> R.drawable.renaissance

    }
    val imageTitle = when (currentImage) {
        1 -> R.string.timekeeper
        2 -> R.string.state
        3 -> R.string.ritual
        4 -> R.string.uzumaki
        5 -> R.string.monk_punk
        6 -> R.string.black_hole
        else -> R.string.re_naissance

    }


    Scaffold (
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "ArtSpace",
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { innerPadding ->

        Surface (
            modifier = modifier
                .fillMaxSize()
                .padding(
                    innerPadding
//                    top = 8.dp,
//                    bottom = 8.dp,
//                    start = 16.dp,
//                    end = 16.dp
                )
                .background(MaterialTheme.colorScheme.tertiaryContainer),
            color = MaterialTheme.colorScheme.primaryContainer
        ) {
            Column(
                modifier = Modifier
                    .padding(
                        start = 16.dp,
                        end = 16.dp,
                        bottom = 8.dp
                    )
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card (
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                    modifier = Modifier
                        .padding(top = 16.dp)
                ) {
                    Row(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        Image(
                            painter = painterResource(id = imageResource),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(400.dp)
                                .padding(
                                    top = 16.dp,
                                    bottom = 16.dp
                                )

                        )
                    }
                }

                Spacer(modifier = modifier.height(48.dp))

                Row(
                    modifier = modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.inverseOnSurface),

                    ) {
                    Column(
                        modifier = modifier
                            .padding(24.dp),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = stringResource(imageTitle),
                            fontSize = 24.sp
                        )
                    }

                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.Bottom
                ) {
                    Button(
                        modifier = modifier
                            .weight(1f),
                        shape = RoundedCornerShape(48.dp),
                        colors = ButtonDefaults.buttonColors(contentColor = MaterialTheme.colorScheme.tertiaryContainer),
                        onClick = {
                            currentImage = if (currentImage > 1) {
                                currentImage - 1 //Go to previous image
                            } else {
                                totalImages //Wrap to last image
                            }
                        }
                    ) {
                        Text(
                            text = "Previous"
                        )
                    }

                    Spacer(modifier = Modifier.weight(0.5f))
                    Button(
                        modifier = modifier
                            .weight(1f),
                        shape = RoundedCornerShape(48.dp),
                        colors = ButtonDefaults.buttonColors(contentColor = MaterialTheme.colorScheme.tertiaryContainer),
                        onClick = {
                            currentImage = if (currentImage < totalImages) {
                                currentImage + 1 //Go to next image
                            } else {
                                1 //Wrap to first image
                            }
                        }
                    ) {
                        Text(
                            text = "Next"
                        )
                    }

                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun ArtPreview() {
    ArtSpaceTheme {
        ArtWork()
    }
}