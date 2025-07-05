package com.gameZone.episodeDetails.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.material3.*
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.gameZone.episodeDetails.domain.model.EpisodeDetails
import com.gameZone.models.Character
import com.gameZone.models.CharacterGender
import com.gameZone.models.CharacterStatus
import com.gameZone.models.Episode
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun EpisodeDetailsScreen(viewModel: EpisodeDetailsViewModel = koinViewModel(), episodeId: Int) {
    val state = viewModel.episodeDetailsState.collectAsStateWithLifecycle().value
    LaunchedEffect(episodeId) {
        viewModel.fetchEpisodeDetails(episodeId.toString())
    }
    EpisodeDetailsScreen(state = state)
}

@Composable
private fun EpisodeDetailsScreen(state: EpisodeDetailsState) {
    when (val episodeDetails = state) {
        is EpisodeDetailsState.Loading -> {
            CircularProgressIndicator(modifier = Modifier.fillMaxSize())
        }

        is EpisodeDetailsState.Success -> {
            val episodeDetails = episodeDetails.details
            val episode = episodeDetails.episode
            val characters = episodeDetails.characters
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text(
                    text = episode.name,
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = episode.episode,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(characters) { character ->
                        AsyncImage(
                            model = character.imageUrl,
                            contentDescription = character.name,
                            modifier = Modifier
                                .size(64.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }
        }

        is EpisodeDetailsState.Error -> {
            Text(
                text = "Error: ${episodeDetails.message}",
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.fillMaxSize(),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview
@Composable
private fun EpisodeDetailsScreenPreview() {
    EpisodeDetailsScreen(
        EpisodeDetailsState.Success(
            EpisodeDetails(
                episode = Episode(
                    id = 1,
                    name = "Episode 1",
                    episode = "S01E01",
                    characters = listOf()
                ),
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
        )
    )
}

