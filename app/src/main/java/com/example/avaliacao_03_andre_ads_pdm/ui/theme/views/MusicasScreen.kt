package com.example.avaliacao_03_andre_ads_pdm.ui.theme.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.avaliacao_03_andre_ads_pdm.R
import com.example.avaliacao_03_andre_ads_pdm.data.Musica
import com.example.avaliacao_03_andre_ads_pdm.network.BASE_URL

@Composable
fun MusicasScreen(
    musicasViewModel: MusicasViewModel = viewModel()
) {
    val uiState by musicasViewModel.uiState.collectAsState()

    when(uiState) {
        is MusicasUiState.Loading -> LoadingScreen()
        is MusicasUiState.Success -> MusicasList((uiState as MusicasUiState.Success).musicas)
        is MusicasUiState.Error -> ErrorScreen()
    }
}

@Composable
fun ErrorScreen() {

}

@Composable
fun MusicasList(
    musicas: List<Musica>
) {
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Green),
        columns = GridCells.Fixed(
            1
        )
    ) {
        items(musicas) {musicas ->
            MusicasEntry(musica = musicas)
        }
    }
}

@Composable
fun LoadingScreen() {
}

@Composable
fun MusicasEntry(
    musica: Musica
) {
    Card(
        modifier = Modifier
            .padding(6.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Box(

        ) {

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(BASE_URL + musica.id.toString())
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.ic_launcher_foreground),
                contentDescription = musica.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.clip(RectangleShape)
            )
            Text(
                modifier = Modifier.align(
                    Alignment.BottomCenter
                ),
                text = musica.title,
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = Color.White, fontWeight = FontWeight.Bold
                )
            )
            Text(
                modifier = Modifier.align(
                    Alignment.BottomCenter
                ),
                text = musica.seconds.toString(),
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = Color.White, fontWeight = FontWeight.Bold
                )
            )
        }
    }
}
