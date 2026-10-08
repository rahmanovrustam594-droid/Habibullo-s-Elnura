<div align="center">
<img width="1200" height="475" alt="Zulmat Shartnomasi" src="https://ai.google.dev/static/site-assets/images/share-ais-513315318.png" />
</div>

# Zulmat Shartnomasi - Bio-Gothic Survival Horror Game

**Bio-Gothic Survival Horror | Necro-Finance | Blood Con Defense | AI-Powered Occult Codex**

Qon, Eter va Oliy Zom qarz shartnomalari girdobidagi omon qolish kurashi.

## 🎮 Loyihaning Tavsifi

**Zulmat Shartnomasi** — qo'rqinchli o'yinning maksimal versiyasi:

- **Kunduzgi Faza**: Resurs yigʻish, Blood Con tayyorlash, Oliy Zomdan qarz olish
- **Tungi Bosqin**: Birinchi shaxs ko'rinishida qonli miltiqdan otish, turretlarni boshqarish
- **Necro-Moliya**: Oliy Zom bankiridan qarz olib, tanangizning a'zolarini garov qoʻyish
- **Bio-Growth Pod**: Garovga ketgan a'zolarni Biomassa va Qon evaziga tiklash
- **AI Kodeks**: Gemini 3.5 Flash + Google Search Grounding orqali qadimiy okult bilimlarni soʻrash

## 🚀 Boshlash

### Talablar
- Android Studio (eng so'nggi versiya)
- Android SDK 34+
- Gemini API kaliti (https://ai.google.dev)

### Lokal O'rnatish

1. Reponi kloun qiling:
   ```bash
   git clone https://github.com/rahmanovrustam594-droid/Habibullo-s-Elnura.git
   cd Habibullo-s-Elnura
   ```

2. Android Studioda oching

3. `.env` fayli yarating va API kalitingizni qoʻshing:
   ```
   GEMINI_API_KEY=your_api_key_here
   ```

4. `app/build.gradle.kts` faylidan quyidagi qatorni oʻchiring:
   ```kotlin
   signingConfig = signingConfigs.getByName("debugConfig")
   ```

5. Emulyator yoki qurilmada ishga tushiring:
   ```bash
   ./gradlew installDebug
   ```

## 📱 APK Qurish

### Debug APK
```bash
./gradlew assembleDebug
```
Fayl joylashuvі: `app/build/outputs/apk/debug/app-debug.apk`

### Release APK (Imzosiz)
```bash
./gradlew assembleRelease
```
Fayl joylashuvі: `app/build/outputs/apk/release/app-release-unsigned.apk`

### Release APK (Imzoli) - Google Play uchun

**1. Keystore yarating (birinchi bor):**
```bash
keytool -genkey -v -keystore zulmat.jks -keyalg RSA -keysize 2048 -validity 10000 -alias zulmat_key
```

**2. `app/build.gradle.kts` ga signing konfiguratsiya qoʻshing:**
```kotlin
android {
    signingConfigs {
        create("release") {
            storeFile = file("../zulmat.jks")
            storePassword = "your_store_password"
            keyAlias = "zulmat_key"
            keyPassword = "your_key_password"
        }
    }
    
    buildTypes {
        release {
            signingConfig = signingConfigs.getByName("release")
        }
    }
}
```

**3. Imzoli Release APK qurish:**
```bash
./gradlew assembleRelease
```

## 🔑 Environment Setup

### Gemini API Kaliti

1. [Google AI Studio](https://ai.google.dev) ga oching
2. "Get API Key" bosing
3. Yangi API kalit yarating
4. `.env` fayliga qoʻshing:
   ```
   GEMINI_API_KEY=sk-...
   ```

### BuildConfig Generatsiyasi

API kalit avtomatik ravishda `BuildConfig.GEMINI_API_KEY` sifatida qo'llaniladi:
```kotlin
val apiKey = BuildConfig.GEMINI_API_KEY
```

## 🏗️ Loyihaning Tuzilishi

```
app/
├── src/main/
│   ├── kotlin/com/example/
│   │   ├── MainActivity.kt              # Asosiy faoliyat
│   │   ├── ui/
│   │   │   ├── GameViewModel.kt         # O'yin mantiqasi
│   │   │   ├── GameStrings.kt           # Lokalizatsiya (UZ, EN, RU, TR)
│   │   │   └── screens/
│   │   │       ├── MainMenuScreen.kt
│   │   │       ├── DayCampScreen.kt
│   │   │       ├── NightDefenseScreen.kt
│   │   │       └── CodexOracleScreen.kt
│   │   ├── data/
│   │   │   ├── GameRepository.kt        # Ma'lumot qatlami
│   │   │   ├── GameEntities.kt          # Room entitylar
│   │   │   ├── GameDao.kt               # Room DAO
│   │   │   ├── AppDatabase.kt           # Room database
│   │   │   └── OccultCodexService.kt    # Gemini API integratsiyasi
│   │   └── theme/
│   │       ├── Color.kt                 # Bio-Gothic ranglar
│   │       └── Theme.kt
│   └── res/
│       ├── drawable/          # Tasvirlar (background, icons)
│       └── values/
│           ├── strings.xml    # Lokalizatsiya
│           └── colors.xml
└── build.gradle.kts            # Gradle konfiguratsiya
```

## 🎨 Sahnalar

1. **Main Menu** - Oʻyin boshidagi menyu
2. **Day Camp** - Kunduzgi resurs boshqaruvi va tayyorlash
   - World Map Explorer
   - Scavenge Expeditions
   - Oliy Zom Bank
   - Blood Con Engineering
   - Bio-Pod Regeneration
   - Occult Radio
3. **Night Defense** - Birinchi shaxs tungi mudofaa
4. **Codex Oracle** - AI bilan savol-javob (Gemini + Google Search)

## 🔧 Texnik Stack

- **Kotlin** - Asosiy til
- **Jetpack Compose** - UI framework
- **Room** - Lokal database
- **Retrofit** - HTTP client
- **OkHttp** - Network logging
- **Gemini API** - AI queries + Google Search Grounding
- **Coroutines** - Async operatsiyalar
- **JUnit 4 & Robolectric** - Testing

## 📊 Test O'tkazish

```bash
# Barchasi
./gradlew test

# Faqat unit testlar
./gradlew testDebugUnitTest

# Faqat instrumentatsion testlar
./gradlew connectedAndroidTest
```

## 🐛 Xatoliklarni Tuzatish

### "GEMINI_API_KEY is missing"
- `.env` fayli yo'q yoki noto'g'ri joyda
- `BuildConfig.GEMINI_API_KEY` placeholder `MY_GEMINI_API_KEY` qolgan

### APK qurish xatosi
- `signingConfig = signingConfigs.getByName("debugConfig")` qatorini o'chiring
- Gradle cache tozalang: `./gradlew clean`

### Gemini API xatosi
- API kalit amal qilmayotganligini tekshiring
- Internet ulanishini tekshiring
- OkHttp logging orqali network so'rovlarini ko'ring

## 📝 Litsenziya

MIT License - Batafsil uchun LICENSE faylini ko'ring

## 👥 Muallif

**Habibullo & Elnura** - Bio-Gothic O'yin Loyihasi

---

**Zulmat Shartnomasi bilan oʻyinni boshlang va Oliy Zom garovi ichidan omon qoling! 🩸👻⚡**