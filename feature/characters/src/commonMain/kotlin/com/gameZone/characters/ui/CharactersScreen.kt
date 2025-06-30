package com.gameZone.characters.ui


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.gameZone.models.Character
import com.gameZone.models.CharacterGender
import com.gameZone.models.CharacterStatus
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun CharactersRoute(
    viewModel: CharactersViewModel = koinViewModel(),
    onCharacterClicked: (Int) -> Unit
) {
    val uiState = viewModel.charactersUiState.collectAsStateWithLifecycle()
    CharactersScreen(uiState.value, onCharacterClicked)
}

@Composable
fun CharactersScreen(uiState: CharactersResultUiState, onCharacterClicked: (Int) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(6.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (uiState) {
            is CharactersResultUiState.Error -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(uiState.message)
                }
            }

            CharactersResultUiState.Idle -> Unit
            CharactersResultUiState.Loading -> CircularProgressIndicator()
            is CharactersResultUiState.Success -> CharactersList(
                uiState.characters,
                onCharacterClicked
            )
        }
    }
}

@Composable
fun CharactersList(
    characters: List<Character>,
    onCharacterClicked: (Int) -> Unit
) {
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize()
            .padding(6.dp),
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(12.dp)
    ) {
        items(characters) {
            CharacterCard(it, onCharacterClicked)
        }
    }
}

@Composable
fun CharacterCard(
    character: Character,
    onItemClicked: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .width(100.dp)
            .height(250.dp)
            .clickable {
                onItemClicked(character.id)
            }
            .padding(12.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = character.name,
                style = MaterialTheme.typography.headlineSmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(Modifier.height(4.dp))
            AsyncImage(
                model = character.imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}

@Preview
@Composable
private fun ScreenPreview() {
    CharactersScreen(
        CharactersResultUiState.Success(
            characters = listOf(
                Character(
                    id = 1,
                    name = "Rick Sanchez",
                    imageUrl = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
                    status = CharacterStatus.Alive,
                    species = "Human",
                    gender = CharacterGender.Male,
                    origin = Character.Origin(
                        name = "Earth",
                        url = "https://rickandmortyapi.com/api/location/1"
                    ),
                    location = Character.Location(
                        name = "Earth",
                        url = "https://rickandmortyapi.com/api/location/20"
                    ),
                    created = "2017-11-04T18:48:46.250Z",
                    episodesUrl = listOf(""),
                    type = ""
                )
            )
        )
    ) {}
}