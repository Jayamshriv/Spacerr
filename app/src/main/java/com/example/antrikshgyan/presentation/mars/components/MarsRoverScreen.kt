package com.example.antrikshgyan.presentation.mars.components

import android.util.Log
import android.widget.ImageButton
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.antrikshgyan.R
import com.example.antrikshgyan.presentation.common.CenteredCircularProgress
import com.example.antrikshgyan.presentation.common.TopAppCustomBar
import com.example.antrikshgyan.presentation.mars.state.MarsRoverImageDataState
import com.example.antrikshgyan.presentation.mars.viewmodel.MarsRoverImageViewModel
import com.example.antrikshgyan.presentation.navgraph.Routes
import com.example.antrikshgyan.ui.theme.Blue
import com.example.antrikshgyan.ui.theme.FailureRed
import com.example.antrikshgyan.ui.theme.fonts
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlin.coroutines.CoroutineContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarsRoverScreen(
    navController: NavController
) {
    val context = LocalContext.current
    val viewModel: MarsRoverImageViewModel = hiltViewModel()

    var solState by remember { mutableStateOf(2500) }
    var pageState by remember { mutableStateOf(1) }
    var boxColor by remember { mutableStateOf(Color.Blue) }

    LaunchedEffect(Unit) {
        viewModel.getMarRoverImage(solState, pageState)
    }
    val marsRoverImageState = viewModel.marsRoverImageViewModel.value
    Box(
        Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.marrs_night_bg),
            contentDescription = "bg",
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.45f),
            contentScale = ContentScale.FillBounds
        )

        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
        Scaffold(
            containerColor = Color.Transparent,
            contentColor = Color(0xFF1F1F1F),
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(scrollBehavior.nestedScrollConnection),

            topBar =
            {
                TopAppCustomBar(
                    heading = "Mars Rover Images",
                    scrollBehavior = scrollBehavior
                ) {
                    navController.navigate(Routes.HomeScreen.routes)
                }
            }

        ) { innerPadding ->
            Log.e("padding", innerPadding.toString())
            Box(
                modifier = Modifier.padding(
                    bottom = innerPadding.calculateBottomPadding(),
                    top = innerPadding.calculateTopPadding()
                )
            ) {
                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {

                    if (marsRoverImageState.isLoading or marsRoverImageState.error.isNotEmpty()) {
                            CenteredCircularProgress()
                            Log.e("MarsRoverScreen", marsRoverImageState.toString())
                    } else {
                        val marsRoverImageList = marsRoverImageState.marsRoverImage.photos
//                        val state = rememberLazyListState()

                        LazyColumn(
                            verticalArrangement = Arrangement.Center,
//                            state = state
                        ) {
                            item{
                                Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceEvenly,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "Sol : ",
                                        fontFamily = fonts,
                                        color = Color.White,
                                        fontSize = 16.sp,
                                        lineHeight = 25.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        style = MaterialTheme.typography.bodyLarge

                                    )
                                    Row(
                                        modifier = Modifier
                                            .wrapContentWidth()
                                            .height(44.dp)
                                            .border(
                                                BorderStroke(
                                                    width = 0.5.dp,
                                                    color = boxColor,
                                                ),
                                                shape = RoundedCornerShape(12.dp)
                                            )
                                            .background(
                                                color = Color(0x79696969),
                                                shape = RoundedCornerShape(12.dp)
                                            )
//                        .alpha(0.8f)
                                            .shadow(
                                                elevation = 0.dp,
                                                ambientColor = Blue,
                                                spotColor = Blue
                                            ),
                                        horizontalArrangement = Arrangement.Center,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        IconButton(
                                            modifier = Modifier
                                                .size(20.dp),
                                            onClick = {
                                                if (solState > 0) {
                                                    solState -= 1
                                                } else {
                                                    Toast.makeText(
                                                        context,
                                                        "ab kya time travel karega",
                                                        Toast.LENGTH_SHORT
                                                    ).show()
                                                }
                                            }
                                        ) {
                                            Image(
                                                imageVector = Icons.Filled.KeyboardArrowLeft,
                                                contentDescription = "previous",
                                                colorFilter = ColorFilter.tint(Color.White)
                                            )
                                        }

                                        TextField(
                                            colors = TextFieldDefaults.colors(
                                                focusedIndicatorColor = Color.Transparent,
                                                unfocusedIndicatorColor = Color.Transparent,
                                                disabledIndicatorColor = Color.Transparent,
                                                focusedContainerColor = Color.Transparent,
                                                unfocusedContainerColor = Color.Transparent,
                                                disabledContainerColor = Color.Transparent,
                                                focusedTextColor = Color.White
                                            ),
                                            maxLines = 1,
                                            minLines = 1,
                                            textStyle = TextStyle(
                                                color = Color.White,
                                                fontFamily = fonts,
                                                fontSize = 12.sp,
                                                textAlign = TextAlign.Center
                                            ),
                                            placeholder = { Text(text = "Sol") },
                                            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                                            modifier = Modifier
                                                .width(72.dp)
                                                .height(52.dp),
                                            value = solState.toString(),
                                            onValueChange = { newValue ->

                                                Log.e("SolCount", newValue)
                                                val newSolState = newValue.trim().toIntOrNull()
                                                if (newSolState != null && 0 < newSolState && newSolState <= 4102) {
                                                    solState = newSolState
                                                    boxColor = Blue
                                                } else {
                                                    boxColor = FailureRed
                                                    Log.e("SolCount", "Invalid input: $newValue")
                                                }
                                            }
                                        )

                                        IconButton(
                                            modifier = Modifier
                                                .size(20.dp),
                                            onClick = {
                                                if (solState <= 4102) {
                                                    solState += 1
                                                } else {
                                                    Toast.makeText(
                                                        context,
                                                        "ab kya time travel karega",
                                                        Toast.LENGTH_SHORT
                                                    ).show()
                                                }
                                            }
                                        ) {
                                            Image(
                                                imageVector = Icons.Filled.KeyboardArrowRight,
                                                contentDescription = "previous",
                                                colorFilter = ColorFilter.tint(Color.White)
                                            )
                                        }
                                    }

                                }

                                Row(
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {

                                    Text(
                                        text = "Page : ",
                                        fontFamily = fonts,
                                        color = Color.White,
                                        fontSize = 16.sp,
                                        lineHeight = 25.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        style = MaterialTheme.typography.bodyLarge

                                    )
                                    Row(
                                        modifier = Modifier
                                            .wrapContentWidth()
                                            .height(44.dp)
                                            .border(
                                                BorderStroke(
                                                    width = 0.5.dp,
                                                    color = boxColor,
                                                ),
                                                shape = RoundedCornerShape(12.dp)
                                            )
                                            .background(
                                                color = Color(0x79696969),
                                                shape = RoundedCornerShape(12.dp)
                                            )
//                        .alpha(0.8f)
                                            .shadow(
                                                elevation = 0.dp,
                                                ambientColor = Blue,
                                                spotColor = Blue
                                            ),
                                        horizontalArrangement = Arrangement.Center,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        IconButton(
                                            modifier = Modifier
                                                .size(20.dp),
                                            onClick = {
                                                if (pageState > 0) {
                                                    pageState -= 1
                                                } else {
                                                    Toast.makeText(
                                                        context,
                                                        "ab kya time travel karega",
                                                        Toast.LENGTH_SHORT
                                                    ).show()
                                                }
                                            }
                                        ) {
                                            Image(
                                                imageVector = Icons.Filled.KeyboardArrowLeft,
                                                contentDescription = "previous",
                                                colorFilter = ColorFilter.tint(Color.White)
                                            )
                                        }

                                        TextField(
                                            colors = TextFieldDefaults.colors(
                                                focusedIndicatorColor = Color.Transparent,
                                                unfocusedIndicatorColor = Color.Transparent,
                                                disabledIndicatorColor = Color.Transparent,
                                                focusedContainerColor = Color.Transparent,
                                                unfocusedContainerColor = Color.Transparent,
                                                disabledContainerColor = Color.Transparent,
                                                focusedTextColor = Color.White
                                            ),
                                            maxLines = 1,
                                            minLines = 1,
                                            textStyle = TextStyle(
                                                color = Color.White,
                                                fontFamily = fonts,
                                                fontSize = 12.sp,
                                                textAlign = TextAlign.Center
                                            ),
                                            placeholder = { Text(text = "Sol") },
                                            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                                            modifier = Modifier
                                                .width(44.dp)
                                                .height(52.dp),
                                            value = pageState.toString(),
                                            onValueChange = { newValue ->

                                                Log.e("pageState", newValue)
                                                val newpageState = newValue.trim().toIntOrNull()
                                                if (newpageState != null && 0 < newpageState && newpageState <= 4102) {
                                                    pageState = newpageState
                                                    boxColor = Blue
                                                } else {
                                                    boxColor = FailureRed
                                                    Log.e("pageState", "Invalid input: $newValue")
                                                }
                                            }
                                        )

                                        IconButton(
                                            modifier = Modifier
                                                .size(20.dp),
                                            onClick = {
                                                if (pageState <= 12) {
                                                    pageState += 1
                                                } else {
                                                    Toast.makeText(
                                                        context,
                                                        "ab kya time travel karega",
                                                        Toast.LENGTH_SHORT
                                                    ).show()
                                                }
                                            }
                                        ) {
                                            Image(
                                                imageVector = Icons.Filled.KeyboardArrowRight,
                                                contentDescription = "previous",
                                                colorFilter = ColorFilter.tint(Color.White)
                                            )
                                        }
                                    }
                                }

                                Image(
                                    imageVector = Icons.Filled.Send,
                                    contentDescription = "fghj",
                                    colorFilter = ColorFilter.tint(Color.White),
                                    modifier = Modifier.size(32.dp).clickable {
                                        viewModel.getMarRoverImage(solState, pageState)
                                    }
                                )
                            }}

                            items(marsRoverImageList) { item ->
                                MarsRoverImagesCard(
                                    photoModel = item
                                ){
                                    navController.currentBackStackEntry?.savedStateHandle?.set("roverImage", item)
                                    navController.navigate(Routes.MarsRoverDetailsScreen.routes)
                                }

                            }

                        }
                    }
                }
            }
        }
    }
}