package com.example.data

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

data class GroundedLoreResult(
    val title: String,
    val content: String,
    val searchSources: List<String>,
    val searchQueries: List<String>
)

class OccultCodexService {
    private val client = OkHttpClient.Builder()
        .connectTimeout(15, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        .build()

    suspend fun queryOccultLore(
        userQuery: String,
        category: String = "JINN"
    ): Result<GroundedLoreResult> = withContext(Dispatchers.IO) {
        try {
            // API kalitisiz - offline grimoire bilimidan foydalanish
            return@withContext Result.success(getFallbackGrimoireKnowledge(userQuery, category))
        } catch (e: Exception) {
            Log.e("OccultCodexService", "Occult query failed", e)
            Result.success(getFallbackGrimoireKnowledge(userQuery, category))
        }
    }

    private fun getFallbackGrimoireKnowledge(query: String, category: String): GroundedLoreResult {
        return when (category) {
            "JINN" -> GroundedLoreResult(
                title = "Jinlar Ierarxiyasi & Zaifliklar",
                content = """
                    [QADIMIY KODEKS YOZUVI]
                    Sharq va O'zbek mifologiyasida Jinlar olov va tutunsiz alangadan yaratilgan. 
                    • Marid: Suv va sovuq eter jini, aql-idrokni (Sanity) illyuziyalar orqali kemiradi. Zaifligi: Yuqori voltli Qora Eter qurollari.
                    • Ifrit: Olovli qasos ruhi. Qonli quvurlar orqali harakatlanadi. Zaifligi: Qonli ritual tilsimlari va safran chiziqlari.
                    • Soya Jinlari: Tunda chiroqsiz qolgan joylarda to'planadi. Oliy Zom ularni qarzdorlarni jazolash uchun yollaydi.
                """.trimIndent(),
                searchSources = listOf("Qadimiy Sharq Okultizmi", "Alvasti va Jinlar Ensiklopediyasi"),
                searchQueries = listOf("Jinlar mifologiyasi", "Ifrit zaifliklari")
            )
            "NECRO_FINANCE" -> GroundedLoreResult(
                title = "Oliy Zom: Soya Banki Nizomi",
                content = """
                    [SOYA BANKI SHARTNOMASI]
                    Oliy Zom — o'lmas aristokrat nekro-bankir. 
                    Agar o'yinchi resurs yetishmovchiligida qarz olsa, har kecha 25% qon foizi hisoblanadi.
                    Kollektorlar reydi oldidan qarzingizni yoping. Aks holda tanangiz a'zolari (ko'z, qo'l) musodara qilinadi.
                    Musodara qilingan a'zolarni Bio-Growth Pod (Tirik Organ Kapsulasi) orqali Biomassa va Suyuq Qon sarflab qayta tiklash mumkin.
                """.trimIndent(),
                searchSources = listOf("Zulmat Shartnomasi Nizomlari"),
                searchQueries = listOf("Oliy Zom qarz mexanikasi")
            )
            "BLOOD_CON" -> GroundedLoreResult(
                title = "Blood Con: Bio-Gothic Muhandislik",
                content = """
                    [BIOMEXANIKA KO'RSATMASI]
                    Resurslar omborlarda saqlanmaydi, balki doimiy Gibrid Quvurlar orqali aylanadi.
                    • Suyuq Qon (Liquid Blood): Turretlar va bio-reaktorlarni oziqlantiradi.
                    • Qora Eter (Dark Ether): Sehrli to'siqlarni quvvatlaydi va Sanity tushishini to'xtatadi.
                    • Xom Biomassa (Biomass): Organ o'stirish va qurollar tayyorlash uchun xomashyo.
                """.trimIndent(),
                searchSources = listOf("Gibrid Quvurlar Texnik Qollanmasi"),
                searchQueries = listOf("Blood Con quvurlar sinergiyasi")
            )
            else -> GroundedLoreResult(
                title = "Anomaliya: $query",
                content = """
                    [RADAR CHASTOTASI $query]
                    Atmospheric anomaliya aniqlandi. Qora yomg'in va efir to'lqinlari paytida Sanity yo'qotilishi 2 barobar ortadi. 
                    Efir mash'alasini yoqing va ritual chizig'idan tashqariga chiqmang!
                """.trimIndent(),
                searchSources = listOf("Okult Radio Signallar Arxivi"),
                searchQueries = listOf("Anomaliya $query")
            )
        }
    }
}
