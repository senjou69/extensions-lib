package eu.kanade.tachiyomi.source

import eu.kanade.tachiyomi.source.model.Page
import eu.kanade.tachiyomi.source.model.SEpisode
import eu.kanade.tachiyomi.source.model.SAnime
import rx.Observable

/**
 * A basic interface for creating a source. It could be an online source, a local source, etc...
 */
interface AnimeSource {

    /**
     * Id for the source. Must be unique.
     */
    val id: Long

    /**
     * Name of the source.
     */
    val name: String

    /**
     * Returns an observable with the updated details for a anime.
     *
     * @param anime the anime to update.
     */
    fun fetchAnimeDetails(anime: SAnime): Observable<SAnime>

    /**
     * Returns an observable with all the available episodes for a anime.
     *
     * @param anime the anime to update.
     */
    fun fetchEpisodeList(anime: SAnime): Observable<List<SEpisode>>

    /**
     * Returns an observable with all the available episodes for a anime.
     *
     * @param anime the anime to update.
     */
    fun fetchEpisodeLink(episode: SEpisode): Observable<String>

    /**
     * Returns an observable with the list of pages a episode has.
     *
     * @param episode the episode.
     */
    fun fetchPageList(episode: SEpisode): Observable<List<Page>>

}