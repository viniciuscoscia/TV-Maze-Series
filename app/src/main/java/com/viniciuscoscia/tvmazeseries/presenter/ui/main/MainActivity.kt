package com.viniciuscoscia.tvmazeseries.presenter.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.compose.rememberNavController
import com.viniciuscoscia.tvmazeseries.R
import com.viniciuscoscia.tvmazeseries.presenter.navigation.NavigationComponent
import com.viniciuscoscia.tvmazeseries.presenter.ui.theme.Shapes
import com.viniciuscoscia.tvmazeseries.presenter.ui.theme.TVMazeSeriesTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TVMazeSeriesTheme {
                val navController = rememberNavController()
                NavigationComponent(navController)
            }
        }
    }
}

@Preview
@Composable
private fun TestBody() {
    val constraints = ConstraintSet {
        val greenBox = createRefFor("greenbox")
        val redBox = createRefFor("redbox")
        val guideline = createGuidelineFromTop(0.5f)

        constrain(greenBox) {
            top.linkTo(guideline)
            start.linkTo(parent.start)
            width = Dimension.value(100.dp)
            height = Dimension.value(100.dp)
        }

        constrain(redBox) {
            top.linkTo(parent.top)
            start.linkTo(greenBox.end)
            end.linkTo(parent.end)
            width = Dimension.value(100.dp)
            height = Dimension.value(100.dp)
        }

        createHorizontalChain(greenBox, redBox, chainStyle = ChainStyle.Packed)
    }

    ConstraintLayout(
        constraintSet = constraints,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .background(Color.Green)
                .layoutId("greenbox")
        )

        Box(
            modifier = Modifier
                .background(Color.Red)
                .layoutId("redbox")
        )
    }
}

@Composable
private fun ShowSnackbar(scaffoldState: ScaffoldState) {
    val scope = rememberCoroutineScope()

    var textState by remember {
        mutableStateOf("Batata")
    }
    //prefirir usar o contexto da aplicação para evitar memory leaks

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp)
    ) {
        TextField(
            value = textState,
            onValueChange = {
                textState = it
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(16.dp))
        Snackbar {
            Text(text = "Hello, World!")
        }
        Button(onClick = {
            scope.launch {
                scaffoldState.snackbarHostState.showSnackbar("Hello, $textState")
            }
        }) {
            Text(text = "Pls greet me")
        }
    }
}

@Composable
private fun TextStyle() {
    val fontFamily = FontFamily(
        Font(R.font.lexend_thin, FontWeight.Thin),
        Font(R.font.lexend_light, FontWeight.Light),
        Font(R.font.lexend_regular, FontWeight.Normal),
        Font(R.font.lexend_medium, FontWeight.Medium),
        Font(R.font.lexend_semi_bold, FontWeight.SemiBold),
        Font(R.font.lexend_bold, FontWeight.Bold),
        Font(R.font.lexend_extra_bold, FontWeight.ExtraBold),
    )

    val fontSize = 30.sp

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF101010))
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Color.Green,
                        fontSize = fontSize
                    )
                ) {
                    append("J")
                }
                append("etpack ")
                withStyle(
                    style = SpanStyle(
                        color = Color.Green,
                        fontSize = fontSize
                    )
                ) {
                    append("C")
                }
                append("ompose")
            },
            color = Color.White,
            fontSize = fontSize,
            fontFamily = fontFamily,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.Center,
            textDecoration = TextDecoration.Underline
        )
    }
}

@Composable
fun ImageCard(
    painter: Painter,
    contentDescription: String,
    title: String,
    modifier: Modifier = Modifier
) {
//    Box(modifier = Modifier.fillMaxSize(0.5f)) {
//        ImageCard(
//            painter = painterResource(R.drawable.no_poster),
//            contentDescription = "Test",
//            title = "Test"
//        )
//    }

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = CutCornerShape(15.dp),
        elevation = 5.dp
    ) {
        Box(modifier = Modifier.height(200.dp)) {
            Image(
                painter = painter,
                contentDescription = contentDescription,
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            ),
                            startY = 400f
                        )
                    )
            ) {

            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Text(
                    text = title,
                    style = TextStyle(color = Color.White, fontSize = 16.sp)
                )
            }
        }
    }
}

@Composable
private fun ColorfulHelloWorld() {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()
            .clip(Shapes.medium)
            .border(10.dp, Color.Magenta)
            .padding(10.dp)
            .border(10.dp, Color.Yellow)
            .padding(10.dp)
            .border(10.dp, Color.Blue)
            .padding(10.dp)
            .border(10.dp, Color.Green)
            .padding(10.dp)
            .background(Color.Red)
    ) {
        Text(
            text = "Hello, World!",
            modifier = Modifier
                .offset(20.dp, 40.dp)
                .background(Color.Blue)
        )
    }
}

@Composable
private fun FourColors() {
    Column(
        modifier = Modifier
            .background(Color.Blue)
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
    }
    Column(
        modifier = Modifier
            .background(Color.Yellow)
            .fillMaxHeight(0.5f)
            .fillMaxWidth()
    ) {
    }
    Column(
        modifier = Modifier
            .background(Color.Red)
            .fillMaxHeight()
            .fillMaxWidth(0.5f)
    ) {
    }
    Column(
        modifier = Modifier
            .background(Color.Green)
            .fillMaxHeight(0.5f)
            .fillMaxWidth(0.5f)
    ) {
    }
}