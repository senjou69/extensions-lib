package eu.kanade.tachiyomi.source

interface ConfigurableAnimeSource {

    fun setupPreferenceScreen(screen: android.support.v7.preference.PreferenceScreen)

    fun setupPreferenceScreen(screen: androidx.preference.PreferenceScreen)

}
