package uk.ac.tees.mad.D3905133

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import uk.ac.tees.mad.D3905133.ui.theme.NewsDroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NewsDroidTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                   NewsNavigation(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}