package characterDetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import coil3.compose.AsyncImage
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import characterDetails.components.CharacterStatusComponent
import com.gameZone.models.Character
import com.gameZone.models.CharacterGender
import com.gameZone.models.CharacterStatus
import com.gameZone.models.Episode
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CharacterDetailsRoute(
    viewModel: CharacterDetailsViewModel = koinViewModel(),
    characterId: Int,
    onEpisodeClicked: (Int) -> Unit
) {
    val uiState by viewModel.characterUiState.collectAsStateWithLifecycle()

    LaunchedEffect(characterId) {
        viewModel.getCharacter(characterId)
    }
    CharacterDetailsScreen(uiState, onEpisodeClicked)

}

@Composable
fun CharacterDetailsScreen(
    state: CharacterResultUiState,
    onEpisodeClicked: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when (state) {
            is CharacterResultUiState.Error -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(state.message)
                }
            }

            CharacterResultUiState.Idle -> Unit
            CharacterResultUiState.Loading -> CircularProgressIndicator()
            is CharacterResultUiState.Success -> {
                val character = state.characters
                val characterDataPoints: List<DataPoint> by remember {
                    derivedStateOf {
                        buildList {
                            character?.let {
                                add(DataPoint("Last Known Location", it.location.name))
                                add(DataPoint("Species", it.species))
                                add(DataPoint("Gender", it.gender.name))
                                it.type.takeIf { type -> type.isNotEmpty() }?.let { type ->
                                    add(DataPoint("Type", type))
                                }
                                add(DataPoint("Origin", it.location.name))
                                add(DataPoint("Episode count", it.episodesUrl.size.toString()))
                            }
                        }
                    }
                }
                CharacterDetailsCard(
                    character!!,
                    characterDataPoints,
                    state.episodes,
                    onEpisodeClicked = onEpisodeClicked
                )
            }
        }
    }
}

@Composable
fun CharacterDetailsCard(
    character: Character,
    characterDataPoints: List<DataPoint>,
    characterEpisodes: List<Episode>,
    onEpisodeClicked: (Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        item { CharacterStatusComponent(characterStatus = character.status) }
        item { Spacer(Modifier.height(12.dp)) }
        item {
            Text(
                text = character.name,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.SemiBold,
                color = Color.Cyan
            )
        }
        item { Spacer(Modifier.height(12.dp)) }
        item { CharacterImage(character.imageUrl) }
        item { Spacer(Modifier.height(12.dp)) }
        item { CharacterEpisodes(characterEpisodes, onEpisodeClicked) }
        item { Spacer(Modifier.height(12.dp)) }
        items(characterDataPoints) {
            SmallMagentaText(it.title)
            Spacer(Modifier.height(4.dp))
            MediumWhiteText(it.desc)
            Spacer(Modifier.height(22.dp))
        }
    }
}

@Composable
fun CharacterEpisodes(episodes: List<Episode>, onEpisodeClicked: (Int) -> Unit) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        items(episodes) {
            Spacer(Modifier.width(8.dp))
            Card(
                shape = RoundedCornerShape(12.dp), modifier = Modifier.size(150.dp),
                onClick = { onEpisodeClicked(it.id) }) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = it.episode, fontSize = 20.sp, color = Color.Cyan)
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = it.name,
                        fontSize = 16.sp,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Composable
fun SmallMagentaText(text: String) {
    Text(text = text, style = MaterialTheme.typography.headlineSmall, color = Color.White)
}

@Composable
fun MediumWhiteText(text: String) {
    Text(text = text, style = MaterialTheme.typography.bodyLarge, color = Color.LightGray)
}

@Composable
fun CharacterImage(imageUrl: String) {
    AsyncImage(
        model = imageUrl,
        contentScale = ContentScale.Crop,
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .clip(RoundedCornerShape(12.dp))
    )
}


@Preview
@Composable
private fun CharacterScreenPreview() {
    val character = Character(
        id = 1,
        name = "Rick Sanchez",
        status = CharacterStatus.Alive,
        created = "2017-11-04T18:48:46.250Z",
        gender = CharacterGender.Male,
        imageUrl = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
        location = Character.Location(
            name = "Citadel of Ricks",
            url = ""
        ),
        origin = Character.Origin(
            name = "Earth (C-137)",
            url = ""
        ),
        species = "Human",
        episodesUrl = listOf(
            "https://rickandmortyapi.com/api/episode/1",
            "https://rickandmortyapi.com/api/episode/2",
            "https://rickandmortyapi.com/api/episode/3"
        ),
        type = ""
    )
    val dataPoints = listOf(
        DataPoint("Last Known Location", "Earth (C-137)"),
        DataPoint("Species", "Human"),
        DataPoint("Gender", "Male"),
        DataPoint("Type", ""),
        DataPoint("Origin", "Earth (C-137)"),
        DataPoint("Episode count", "3")
    )
    CharacterDetailsScreen(
        state = CharacterResultUiState.Success(
            characters = character
        )
    ){}
}
