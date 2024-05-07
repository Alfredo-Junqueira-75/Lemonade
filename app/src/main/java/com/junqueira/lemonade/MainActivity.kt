package com.junqueira.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.junqueira.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                lemonadeApp()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun lemonadeApp(){
    lemonadeImageButtonAndText()
}

@Composable
fun lemonadeImageButtonAndText(modifier: Modifier = Modifier
    .fillMaxSize()
    .wrapContentSize(Alignment.Center)
) {
    Column(modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally) {
        var counter = (2..4).random()
        var aux = 1
        var index by remember { mutableStateOf(1) }
        var textResource: Int
        var contentDescriptionResource: Int
        var imageResource: Int

        when(index) {
            1-> {
                textResource = R.string.Tap_the_lemon_tree_to_select_a_lemon
                contentDescriptionResource = R.string.Lemon_tree_content_description
                imageResource = R.drawable.lemon_tree

            }
            2 -> {
                textResource = R.string.Keep_tapping_the_lemon_to_squeeze_it
                contentDescriptionResource = R.string.Lemon_content_description
                imageResource = R.drawable.lemon_squeeze
            }
            3 -> {
                textResource = R.string.Tap_the_lemonade_to_drink_it
                contentDescriptionResource = R.string.Glass_of_lemonade_content_description
                imageResource = R.drawable.lemon_drink
            }
            else -> {
                textResource = R.string.Tap_the_empty_glass_to_start_again
                contentDescriptionResource = R.string.Glass_of_lemonade_content_description
                imageResource = R.drawable.lemon_restart
            }

        }
        Button(onClick = {
            if (index != 2){
                index += 1
                if(index == 5) index = 1
            }else if( index == 2)
            {
                if(aux == counter){
                    index += 1
                    counter = (2..4).random()
                }
                aux++
            }
        },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Cyan),
            shape = RoundedCornerShape(30.dp)
        )
        {
            Image(painter = painterResource(imageResource),
                contentDescription = stringResource(contentDescriptionResource))
        }
        Spacer(modifier = Modifier.height(36.dp))
        Text(text = stringResource(textResource),
            fontSize = 18.sp)
    }
}